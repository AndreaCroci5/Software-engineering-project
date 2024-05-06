package it.polimi.ingsw.am40.server.network.virtual_view;


import it.polimi.ingsw.am40.server.ActionListener;
import it.polimi.ingsw.am40.server.ActionPoster;
import it.polimi.ingsw.am40.server.actions.Action;
import it.polimi.ingsw.am40.server.network.NetworkManagerServer;
import it.polimi.ingsw.am40.server.network.ServerNetworkRMIManager;
import it.polimi.ingsw.am40.server.network.ServerNetworkTCPManager;

import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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






    //CONSTRUCTOR METHOD

    /**
     * Constructor for VVServer
     * 1)Creates the protocol managers
     * 2)Initializes the connections by calling the init() method on the managers
     * 3)Creates a new list to contain the future parties
     * 4)Creates a new list for orphan clients (the ones that aren't logged in a Party yet)
     * 5)Creates a new list of listeners
     * @param port the listening port of the server
     * @param hostName the name of the server in the network
     */
    public VVServer(int port, String hostName) {
        this.concreteNetworkManagers = new ArrayList<>();
        this.concreteNetworkManagers.add(new ServerNetworkRMIManager());
        this.concreteNetworkManagers.add(new ServerNetworkTCPManager(port, hostName));
       // this.initCommunicationProtocols();
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
    private NetworkClient getClientInAPartyByID(int playerID)  throws NonExistentClientException{
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
    private NetworkClient getOrphanClientByID(int clientID) throws NonExistentClientException{
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
    private NetworkClient getClientByID(int clientID) throws NonExistentClientException {
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



    //PRIVATE INIT METHODS

    /**
     * Method that invokes the init() on networkManagers to run the server connections
     */
    private void initCommunicationProtocols(int port, String hostName){
        for (NetworkManagerServer nm : this.concreteNetworkManagers) {
            nm.initCommunication(port, hostName);
        }
    }



    //PRIVATE METHODS

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

    /**
     * Method to create a new client and to add it in the orphan list
     * @return the ClientID
     */
    private int createNewOrphanClient(String username, Protocol protocol, Socket socket){
        NetworkClient c = new NetworkClient(username, protocol, socket);
        this.orphanClients.add(c);
        return c.getClientID();
    }

    /**
     * Method to add a new client to a party.
     * As requirement, the partID must exist.
     * @param partyID the ID number of the party where add the client
     * @param clientID the ID of the client
     * @throws NonExistentClientException if the client doesn't exist
     * @throws NonExistentPartyException if the party doesn't exist
     * @throws ClientAlreadyLoggedException if the client is already logged in another Party
     */
    private void logClientInAParty(int partyID, int clientID) throws NonExistentClientException, NonExistentPartyException, ClientAlreadyLoggedException {
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

    }

}
