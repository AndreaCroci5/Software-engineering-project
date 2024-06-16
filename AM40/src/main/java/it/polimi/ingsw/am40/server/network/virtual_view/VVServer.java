package it.polimi.ingsw.am40.server.network.virtual_view;


import it.polimi.ingsw.am40.client.network.RMI.RemoteInterfaceClient;
import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.server.ActionListener;
import it.polimi.ingsw.am40.server.ActionPoster;
import it.polimi.ingsw.am40.server.actions.Action;
import it.polimi.ingsw.am40.server.actions.active.firstRound.InitializationAction;
import it.polimi.ingsw.am40.server.network.NetworkManagerServer;
import it.polimi.ingsw.am40.server.network.RMI.ServerNetworkRMIManager;
import it.polimi.ingsw.am40.server.network.TCP.ServerNetworkTCPManager;

import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

/**
 * VirtualView/Server class is the main junction between the network and tha MVC pattern.
 * It receives the incoming request of connection and Actions and handles them by managing them calling the right classes.
 * This class works like a "mediator".
 */
public class VVServer implements ActionPoster, ActionListener  {

    //ATTRIBUTES

    /**
     * List of listeners for MVC action listeners-posters system
     */
    private final List<ActionListener> listeners;

    /**
     * References to all the concrete implementation of network protocol managers (TCP, RMI)
     */
    private final List<NetworkManagerServer> concreteNetworkManagers;

    /**
     * References to all the active Parties on the server
     */
    private final List<NetworkParty> activeParties;

    /**
     * Clients not logged in a Party yet
     */
    private final List<NetworkClient> orphanClients;

    public boolean serverOn;

    /**
     * Attribute introduced to avoid that a great number of clients are connected in the same time
     */
    public static int activeConnections = 0;






    //CONSTRUCTOR METHOD

    /**
     * Constructor for VVServer
     * 1)Creates the protocol managers
     * 2)Creates a new list to contain the future parties
     * 3)Creates a new list for orphan clients (the ones that aren't logged in a Party yet)
     * 4)Creates a new list of listeners
     */
    public VVServer() {
        this.concreteNetworkManagers = new ArrayList<>();
        //FIXME A+S concrete net managers to add to the client in the creation for polymorphism
        this.concreteNetworkManagers.add(new ServerNetworkRMIManager(this));
        this.concreteNetworkManagers.add(new ServerNetworkTCPManager(this));

        this.activeParties = new ArrayList<>();
        this.orphanClients = new ArrayList<>();
        this.listeners = new ArrayList<>();
    }



    //GETTER METHODS

    /**
     * Getter for the concreteNetworkManagers list
     * @return the references to the objects which implement the NetworkManager interface
     */
    public List<NetworkManagerServer> getConcreteNetworkManagers() {
        return concreteNetworkManagers;
    }

    /**
     * Getter for existent parties
     * @return the list of reference to the parties
     */
    public List<NetworkParty> getActiveParties() {
        return activeParties;
    }

    /**
     * Getter for orphan clients (the ones which are not in a Party yet)
     * @return the list of the orphan clients references
     */
    public List<NetworkClient> getOrphanClients() {
        return orphanClients;
    }


    /**
     * Getter for the list of listeners
     *
     * @return the list of references of the listeners
     */
    @Override
    public List<ActionListener> getListeners() {
        return this.listeners;
    }

    /**
     * Getter for party reference by its ID
     * (with linear search (O(n), n = num of parties)
     * @param partyID the ID number of the party to search
     * @return the party as reference (null if not found)
     * @throws NonExistentPartyException if the Party doesn't exist
     */
    public NetworkParty getPartyByID(int partyID) throws NonExistentPartyException {
        for (NetworkParty p : this.activeParties) {
            if(p.getPartyID() == partyID){
                return p;
            }
        }
        throw new NonExistentPartyException();
    }

    /**
     * Getter for reference to a client by its ID
     * (with linear search (O(n*m), n = num of parties, m = num of clients in the party)
     * @param playerID the ID of the client to search
     * @return the client as reference (null if not found)
     * @throws NonExistentClientException if the client doesn't exist in any Party
     */
    public NetworkClient getClientInAPartyByID(int playerID)  throws NonExistentClientException{
        for (NetworkParty p : this.activeParties) {
            for(NetworkClient c : p.getClients()){
                if(c.getClientID() == playerID){
                    return c;
                }
            }
        }
        throw new NonExistentClientException();
    }

    /**
     * Getter for reference to a client by its ID (in the orphan list)
     * (with linear search)
     * @param clientID the ID of the client to search
     * @return the client as reference (null if not found)
     * @throws NonExistentClientException if the client doesn't exist as orphan
     */
    public synchronized NetworkClient getOrphanClientByID(int clientID) throws NonExistentClientException{
        for (NetworkClient c : this.orphanClients) {
            if(c.getClientID() == clientID){
                return c;
            }
        }
        throw new NonExistentClientException();
    }

    /**
     * Getter for reference to a client by its ID (in the orphan list or in the active parties)
     * (with linear search)
     * @param clientID the ID of the client to search
     * @return the client as reference (null if not found)
     * @throws NonExistentClientException if the client doesn't exist on either orphans list or in the Parties
     */
    public NetworkClient getClientByID(int clientID) throws NonExistentClientException {
        for (NetworkClient c1 : this.orphanClients) {
            if(c1.getClientID() == clientID){
                return c1;
            }
        }
        for (NetworkParty p : this.activeParties) {
            for (NetworkClient c2 : p.getClients()) {
                if(c2.getClientID() == clientID){
                    return c2;
                }
            }
        }
        throw new NonExistentClientException();
    }

    /**
     * Getter for all the clients connected with TCP
     * @return the list of the NetworkClient connected
     */
    public List<NetworkClient> getAllTCPClients(){
        List<NetworkClient> result = new ArrayList<>();
        for (NetworkClient client : this.orphanClients) {
            if(client.getProtocol() == Protocol.TCP){
                result.add(client);
            }
        }


        for(NetworkParty party : this.activeParties){
            for (NetworkClient client : party.getClients()) {
                if (client.getProtocol() == Protocol.TCP) {
                    result.add(client);
                }
            }
        }

        return result;
    }

    /**
     * Getter for all the clients connected with RMI
     * @return the list of the NetworkClient connected
     */
    public List<NetworkClient> getAllRMIClients(){//fixme attenzione alla sincronizzazione su stesso lock
        List<NetworkClient> result = null;
        synchronized (orphanClients){
            for (NetworkClient client : this.orphanClients) {
                if(client.getProtocol() == Protocol.RMI){
                    result.add(client);
                }
            }
        }


        for(NetworkParty party : this.activeParties){
            for (NetworkClient client : party.getClients()) {
                if (client.getProtocol() == Protocol.RMI) {
                    result.add(client);
                }
            }
        }

        return result;
    }

    /**
     * Getter for all the clients connected with the server
     * @return the list of the NetworkClient connected
     */
    public List<NetworkClient> getAllClients(){
        List<NetworkClient> result = null;
        result.addAll(this.getAllTCPClients());
        result.addAll(this.getAllRMIClients());
        return result;
    }

    public boolean isAClientLoggedInAParty(NetworkClient client){

        NetworkClient result;
        try {
            result = this.getClientInAPartyByID(client.getClientID());
        } catch (NonExistentClientException e) {
            return false;
        }

        if(result == null){
            return false;
        }

        return true;
    }


    //SETTER METHODS

    public void setClientOnline(NetworkClient client) throws NonExistentClientException {
        if(this.isAClientLoggedInAParty(client)){
            this.getClientByID(client.getClientID()).setOnline(true);
        }
    }

    public void setClientOffline(NetworkClient client) throws NonExistentClientException {
        if(this.isAClientLoggedInAParty(client)){
            this.getClientByID(client.getClientID()).setOnline(true);
        }
    }



    //POSTER METHODS

    /**
     * It adds a new listener to the container of listeners in a "poster" class
     *
     * @param listener           Reference to the new object which implements "action listener"
     * @param listenersContainer Collection of the listeners
     */
    @Override
    public void addListener(ActionListener listener, Collection<ActionListener> listenersContainer) {
        listenersContainer.add(listener);
    }

    /**
     * It removes a new listener to the container of listeners in a "poster" class
     *
     * @param listener           Reference to the object you want to remove
     * @param listenersContainer Collection of the listeners
     */
    @Override
    public void removeListener(ActionListener listener, Collection<ActionListener> listenersContainer) {
        listenersContainer.remove(listener);
    }

    /**
     * It notifies all the listeners of an event
     * It will probably call the onEvent method of ActionListener interface
     *
     * @param event              Message to post
     * @param listenersContainer Collection of the listeners
     */
    @Override
    public void notifyListeners(Action event, Collection<ActionListener> listenersContainer) {
        for (ActionListener l : listenersContainer)
        {
            l.onEvent(event);
        }
    }



    //LISTENER METHODS

    /**
     * This method receives an action, generated by an event.
     * Once notified, the implementer must answer to the notification
     * (a call of "onEvent" when a condition is verified).
     * Implementer will call the Action "do" (when "ExampleMessage" is received, do...)
     *
     * @param event The event action. Basing on the information in the message, onEvent do something
     */
    @Override
    public void onEvent(Action event) {
        event.doAction(this);
    }



    //"CREATION" METHODS

    /**
     * Method to create a new party
     * @param totalNumOfClients to set the total number of clients expected for this lobby.
     *                         Once reached, a new game will start
     * @return the PartyID
     */
    private int createNewParty(int totalNumOfClients){
        NetworkParty p;
        this.activeParties.add(p = new NetworkParty(totalNumOfClients));
        return p.getPartyID();
    }

    private int createNewParty(int totalNumOfClients, NetworkClient creator) throws ClientAlreadyLoggedException, NonExistentPartyException, NonExistentClientException {
        NetworkParty p;
        this.activeParties.add(p = new NetworkParty(totalNumOfClients));
        this.logClientInAParty(p.getPartyID(), creator.getClientID());
        return p.getPartyID();
    }

    /**
     * Method to create a new client and to add it in the orphan list
     * @return the ClientID
     */
    public int createNewOrphanClient(Protocol protocol, Socket socket, RemoteInterfaceClient remoteInterface){
        NetworkClient c = new NetworkClient(protocol, socket, remoteInterface, this.concreteNetworkManagers);
        this.orphanClients.add(c);
        return c.getClientID();
    }


    //LOG IN AND LOG OUT METHODS


    /**
     * Method to add a new client to a party.
     * As requirement, the partID must exist.
     * @param partyID the ID number of the party where add the client
     * @param clientID the ID of the client
     * @throws NonExistentClientException if the client doesn't exist
     * @throws NonExistentPartyException if the party doesn't exist
     * @throws ClientAlreadyLoggedException if the client is already logged in another Party
     */
    public void logClientInAParty(int partyID, int clientID) throws NonExistentClientException, NonExistentPartyException, ClientAlreadyLoggedException {
        NetworkParty p = null;
        try {
            p = getPartyByID(partyID);
        } catch (NonExistentPartyException e) {
            throw new NonExistentPartyException();
        }

        NetworkClient c = null;
        try {
            c = getOrphanClientByID(clientID);
        } catch (NonExistentClientException e){
            try {
                getClientInAPartyByID(partyID);
            } catch (NonExistentClientException e1){
                throw new ClientAlreadyLoggedException();
            }
            throw new NonExistentClientException();
        }

        p.logClient(c);

        if(p.getCurrentNumOfClients() == p.getTotalNumOfClients()){
            this.notifyListeners(new InitializationAction(partyID, -1),this.listeners);
        }

    }


    //INIT METHOD

    /**
     * This method call the initCommunication() method on the Network Managers
     * //fixme doc
     * @param portTCP     the port of the server
     * @param portRMI
     * @param hostName the name of the server
     */
    public void initServer(int portTCP, int portRMI, String hostName) {

        for (NetworkManagerServer nm : this.concreteNetworkManagers) {
            nm.setHostName(hostName);//fixme * ridondanza
            switch(nm.getUsedProtocol()){
                case RMI -> nm.setPort(portRMI);
                case TCP -> nm.setPort(portTCP);
                case null, default -> {
                    break;
                }
            }
            nm.initCommunication();
            nm.initPing();
        }


        this.serverOn = true;
        String input;
        Scanner scanner = new Scanner(System.in);

        try {
            Thread.sleep(500L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println();
        System.out.println("Type 'quit' to close the server communications");

        while (serverOn) {

            input = scanner.nextLine();

            switch (input) {
                case "quit" :
                    System.out.println("Closing the server application");
                    serverOn = false;
                    break;
                default:
                    System.out.println("Invalid input!");
                    break;
            }

        }

        scanner.close();

        System.exit(0);

    }
    //fixme A+S sistemo roba switch networkManager

    public void sendOnNetworkUnicast(int playerID, Data data){

        NetworkClient c;
        try {
            c = this.getClientByID(playerID);
        } catch (NonExistentClientException e) {
            System.out.println("Client doesn't exist!");
            throw new RuntimeException(e);
        }

        c.getManager().sendSerializedMessage(data, c);

    }

    public void sendOnNetworkBroadcastInAParty(int partyID, Data data){

        NetworkParty p ;
        try {
            p = this.getPartyByID(partyID);
        } catch (NonExistentPartyException e) {
            System.out.println("Party doesn't exist!");
            throw new RuntimeException(e);
        }
        for (NetworkClient c : p.getClients()) {
            c.getManager().sendSerializedMessage(data, c);
        }
    }

    public void sendOnNetworkMulticast(List<Integer> recipients, Data data){
        for (Integer i : recipients) {
            try {
                NetworkClient c = this.getClientByID(i) ;
                c.getManager().sendSerializedMessage(data, c);
            } catch (NonExistentClientException e) {
                System.out.println("Client doesn't exist!");
                throw new RuntimeException(e);
            }
        }

    }

    public void sendOnNetworkOneActiveOtherPassive(int partyID, int activePlayerID, Data activeData, Data passiveData){

        NetworkParty p;
        try {
             p = this.getPartyByID(partyID);
        } catch (NonExistentPartyException e) {
            System.out.println("Party doesn't exist!");
            throw new RuntimeException(e);
        }

        for (NetworkClient c : p.getClients()) {
            if(c.getClientID() != activePlayerID){
                this.sendOnNetworkUnicast(c.getClientID(), passiveData);
            }
        }
        this.sendOnNetworkUnicast(activePlayerID, activeData);

    }
}
