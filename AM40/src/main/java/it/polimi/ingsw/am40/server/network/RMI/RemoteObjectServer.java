package it.polimi.ingsw.am40.server.network.RMI;

import it.polimi.ingsw.am40.client.network.RMI.RemoteInterfaceClient;
import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.data.HostNameData;
import it.polimi.ingsw.am40.data.active.flow.CreateRequestData;
import it.polimi.ingsw.am40.data.active.flow.JoinRequestData;
import it.polimi.ingsw.am40.server.actions.Action;
import it.polimi.ingsw.am40.server.network.virtual_view.NetworkClient;
import it.polimi.ingsw.am40.server.network.virtual_view.NetworkParty;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * RemoteObjectServer class is the concrete implementation of the RemoteInterfaceServer for RMI. The interface is logged
 * in the RMI registry and the client can call methods on it. This class contains te implementations for those methods.
 */
public class RemoteObjectServer extends UnicastRemoteObject implements RemoteInterfaceServer {

    private ServerNetworkRMIManager manager;


    //CONSTRUCTOR METHODS

    /**
     * Creates and exports a new UnicastRemoteObject object using the
     * particular supplied port and socket factories.
     *
     * <p>Either socket factory may be {@code null}, in which case
     * the corresponding client or server socket creation method of
     * ... is used instead.
     *
     * @throws RemoteException if failed to export object
     * @since 1.2
     */
    protected RemoteObjectServer() throws RemoteException {
        super();
    }



    //REMOTE INTERFACE METHODS

    /**
     * Example method for RMI mechanics
     * @return a string of example
     * @throws RemoteException if the RMI communication fails
     */
    @Override
    public String example() throws RemoteException{
        System.out.println("PROVA");
        return "hello world;";
    }

    //FIXME
    @Override
    public Integer registerClient(RemoteInterfaceClient client, String hostname) {
        return this.manager.newConnectedClientNotification(null, client);
    }

    /**
     * Method to unregister a client from the server with RMI
     *
     * @param clientID the clientID of the client to unregister
     * @throws RemoteException (standard RMI calls exception)
     */
    @Override
    public void unregisterClient(Integer clientID) throws RemoteException {

    }


    //DATA HANDLING METHODS

    /**
     * Method to choice the aim card in the first round through actions with RMI calls
     *
     * @param d
     * @throws RemoteException (standard RMI calls exception)
     */
    @Override
    public void choiceAimCardActiveFirstRound(Data d) throws RemoteException {
        this.handleAndFilter(d);
    }

    /**
     * Method to request an aim card in the first round through actions with RMI calls
     *
     * @param d
     * @throws RemoteException (standard RMI calls exception)
     */
    @Override
    public void requestAimCardActiveFirstRound(Data d) throws RemoteException {
        this.handleAndFilter(d);
    }

    /**
     * Method to request deal cards in the first round through actions with RMI calls
     *
     * @param d
     * @throws RemoteException (standard RMI calls exception)
     */
    @Override
    public void requestDealCardsActiveFirstRound(Data d) throws RemoteException {
        this.handleAndFilter(d);
    }

    /**
     * Method to request the player order in the first round through actions with RMI calls
     *
     * @param d
     * @throws RemoteException (standard RMI calls exception)
     */
    @Override
    public void requestPlayerOrderActiveFirstRound(Data d) throws RemoteException {
        this.handleAndFilter(d);
    }

    /**
     * Method to choice the starting card in the first round through actions with RMI calls
     *
     * @param d
     * @throws RemoteException (standard RMI calls exception)
     */
    @Override
    public void choiceStartingCardActiveFirstRound(Data d) throws RemoteException {
        this.handleAndFilter(d);
    }

    /**
     * Method to request the starting card in the first round through actions with RMI calls
     *
     * @param d
     * @throws RemoteException (standard RMI calls exception)
     */
    @Override
    public void requestStartingCardActiveFirstRound(Data d) throws RemoteException {
        this.handleAndFilter(d);
    }

    /**
     * Method to choice the token in the first round through actions with RMI calls
     *
     * @param d
     * @throws RemoteException (standard RMI calls exception)
     */
    @Override
    public void choiceTokenActiveFirstRound(Data d) throws RemoteException {
        this.handleAndFilter(d);
    }

    /**
     * Method to request the token in the first round through actions with RMI calls
     *
     * @param d
     * @throws RemoteException (standard RMI calls exception)
     */
    @Override
    public void requestTokenActiveFirstRound(Data d) throws RemoteException {
        this.handleAndFilter(d);
    }


    //***************************************



    /**
     * Method to request the turn change in the active flow through actions with RMI calls
     *
     * @param d
     * @throws RemoteException (standard RMI calls exception)
     */
    @Override
    public void requestChangeTurnActiveFlow(Data d) throws RemoteException {
        this.handleAndFilter(d);
    }

    /**
     * Method to request the creation of a game through actions with RMI calls
     *
     * @param d
     * @throws RemoteException (standard RMI calls exception)
     */
    @Override
    public void requestCreateActiveFlow(Data d) throws RemoteException {
        CreateRequestData data = (CreateRequestData) d;

        // Fetching client network information based on client's username
        for (NetworkClient c : this.manager.getMainServerClass().getOrphanClients()) {
            if (c.getUsername().equals(d.getNickname())) {
                d.setPlayerID(c.getClientID());
            }
        }

        this.manager.getMainServerClass().onEvent(d.onServer());
    }

    /**
     * Method to choice a gameID through actions with RMI calls
     *
     * @param d
     * @throws RemoteException (standard RMI calls exception)
     */
    @Override
    public void choiceGameIDActiveFlow(Data d) throws RemoteException {

        // Fetching client network information based on client's username
        for (NetworkClient c : this.manager.getMainServerClass().getOrphanClients()) {
            if (c.getUsername().equals(d.getNickname())) {
                d.setPlayerID(c.getClientID());
            }
        }

        this.manager.getMainServerClass().onEvent(d.onServer());

    }

    /**
     * Method to request a join in a game through actions with RMI calls
     *
     * @param d
     * @throws RemoteException (standard RMI calls exception)
     */
    @Override
    public void requestJoinActiveFlow(Data d) throws RemoteException {
        JoinRequestData data = (JoinRequestData) d;

        // Fetching client network information based on client's username
        for (NetworkClient c : this.manager.getMainServerClass().getOrphanClients()) {
            if (c.getUsername().equals(d.getNickname())) {
                d.setPlayerID(c.getClientID());
            }
        }

        this.manager.getMainServerClass().onEvent(d.onServer());
    }

    /**
     * Method to communicate that a player is ready to play through actions with RMI calls
     *
     * @param d
     * @throws RemoteException (standard RMI calls exception)
     */
    @Override
    public void readyToPlayActiveFlow(Data d) throws RemoteException {
        // Fetching client network information based on client's username
        for (NetworkClient c : this.manager.getMainServerClass().getOrphanClients()) {
            if (c.getUsername().equals(d.getNickname())) {
                d.setPlayerID(c.getClientID());
            }
        }

        this.manager.getMainServerClass().onEvent(d.onServer());
    }

    /**
     * Method to draw a card during a round through actions with RMI calls
     *
     * @param d
     * @throws RemoteException (standard RMI calls exception)
     */
    @Override
    public void drawActiveRound(Data d) throws RemoteException {
        this.handleAndFilter(d);
    }

    /**
     * Method to place a card during a round through actions with RMI calls
     *
     * @param d
     * @throws RemoteException (standard RMI calls exception)
     */
    @Override
    public void placingActiveRound(Data d) throws RemoteException {
        this.handleAndFilter(d);
    }

    @Override
    public void HostNameActiveFlow(Data d) throws RemoteException {
        HostNameData data = (HostNameData) d;
        for (NetworkClient c : this.manager.getMainServerClass().getOrphanClients()) {
            System.out.println(">Data skeleton: " + data.getSkeleton());
            if (c.getRemoteInterface().equals(data.getSkeleton())) {
                System.out.println(">C skeleton: " + c.getRemoteInterface());
                c.setUsername(data.getNickname());
                data.setPlayerID(c.getClientID());
            }
        }
        this.manager.getMainServerClass().onEvent(data.onServer());
    }

    //fixme differenza tra newconnection e reconnection





    //INTERNAL METHODS
    protected ServerNetworkRMIManager getManager() {
        return manager;
    }

    protected void setManager(ServerNetworkRMIManager manager) {
        this.manager = manager;
    }



    protected void handleAndFilter(Data d){
        // Fetching client network information based on client's username
        for (NetworkClient c : this.manager.getMainServerClass().getOrphanClients()) {
            if (c.getUsername().equals(d.getNickname())) {
                d.setPlayerID(c.getClientID());
            }
        }

        for (NetworkParty p : this.manager.getMainServerClass().getActiveParties()) {
            for (NetworkClient c : p.getClients()) {
                if (c.getClientID() == d.getPlayerID()) {
                    // GameID/PartyID fetch
                    d.setGameID(p.getPartyID());
                }
            }
        }
        Action a = d.onServer();
        this.manager.getMainServerClass().notifyListeners(a, this.manager.getMainServerClass().getListeners());
    }
}
