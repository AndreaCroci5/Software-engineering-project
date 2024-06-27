package it.polimi.ingsw.am40.server.network.RMI;

import it.polimi.ingsw.am40.client.network.RMI.RemoteInterfaceClient;
import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.data.HostNameData;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interface RemoteInterfaceServer is used by the client to call methods on the server (RMI).
 * These methods are "callable" in the network.
 */
public interface RemoteInterfaceServer extends Remote{

    /**
     * Example method for RMI debugging
     * @return a "hello" string
     * @throws RemoteException (standard RMI calls exception)
     */
    @Deprecated
    String example() throws RemoteException;

    /**
     * Method to register a new orphan client on the server with RMI
     * @param client the remote interface of this client
     * @return the clientID of the new network client
     * @throws RemoteException (standard RMI calls exception)
     */
    Integer registerClient(RemoteInterfaceClient client,String hostname) throws RemoteException;


    /**
     * Method to unregister a client from the server with RMI
     * @param clientID the clientID of the client to unregister
     * @throws RemoteException (standard RMI calls exception)
     */
    void unregisterClient(Integer clientID) throws RemoteException;





    //*******ACTIVE ACTIONS*******

    //DO RMI FOR ACTIVE FIRST ROUND

    /**
     * Method to choice the aim card in the first round through actions with RMI calls
     * @throws RemoteException (standard RMI calls exception)
     */
    void choiceAimCardActiveFirstRound(Data d) throws RemoteException;

    /**
     * Method to request an aim card in the first round through actions with RMI calls
     * @throws RemoteException (standard RMI calls exception)
     */
    void requestAimCardActiveFirstRound(Data d) throws RemoteException;

    /**
     * Method to request deal cards in the first round through actions with RMI calls
     * @throws RemoteException (standard RMI calls exception)
     */
    void requestDealCardsActiveFirstRound(Data d) throws RemoteException;

    /**
     * Method to request the player order in the first round through actions with RMI calls
     * @throws RemoteException (standard RMI calls exception)
     */
    void requestPlayerOrderActiveFirstRound(Data d) throws RemoteException;

    /**
     * Method to choice the starting card in the first round through actions with RMI calls
     * @throws RemoteException (standard RMI calls exception)
     */
    void choiceStartingCardActiveFirstRound(Data d) throws RemoteException;

    /**
     * Method to request the starting card in the first round through actions with RMI calls
     * @throws RemoteException (standard RMI calls exception)
     */
    void requestStartingCardActiveFirstRound(Data d) throws RemoteException;

    /**
     * Method to choice the token in the first round through actions with RMI calls
     * @throws RemoteException (standard RMI calls exception)
     */
    void choiceTokenActiveFirstRound(Data d) throws RemoteException;

    /**
     * Method to request the token in the first round through actions with RMI calls
     * @throws RemoteException (standard RMI calls exception)
     */
    void requestTokenActiveFirstRound(Data d) throws RemoteException;



    //DO RMI FOR ACTIVE FLOW

    /**
     * Method to request the turn change in the active flow through actions with RMI calls
     * @throws RemoteException (standard RMI calls exception)
     */
    void requestChangeTurnActiveFlow(Data d) throws RemoteException;

    /**
     * Method to request the creation of a game through actions with RMI calls
     * @throws RemoteException (standard RMI calls exception)
     */
    void requestCreateActiveFlow(Data d) throws RemoteException;

    /**
     * Method to choice a gameID through actions with RMI calls
     * @throws RemoteException (standard RMI calls exception)
     */
    void choiceGameIDActiveFlow(Data d) throws RemoteException;

    /**
     * Method to request a join in a game through actions with RMI calls
     * @throws RemoteException (standard RMI calls exception)
     */
    void requestJoinActiveFlow(Data d) throws RemoteException;

    /**
     * Method to communicate that a player is ready to play through actions with RMI calls
     * @throws RemoteException (standard RMI calls exception)
     */
    void readyToPlayActiveFlow(Data d) throws RemoteException;



    //DO RMI FOR ACTIVE ROUND

    /**
     * Method to draw a card during a round through actions with RMI calls
     * @throws RemoteException (standard RMI calls exception)
     */
    void drawActiveRound(Data d) throws RemoteException;

    /**
     * Method to place a card during a round through actions with RMI calls
     * @throws RemoteException (standard RMI calls exception)
     */
    void placingActiveRound(Data d) throws RemoteException;

    void HostNameActiveFlow(Data d) throws RemoteException;
}
