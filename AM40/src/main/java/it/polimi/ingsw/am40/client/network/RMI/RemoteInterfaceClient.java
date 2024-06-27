package it.polimi.ingsw.am40.client.network.RMI;

import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.data.FailedHostNameData;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interface RemoteInterfaceClients is used by the server to call methods on the client (RMI).
 * These methods are "callable" in the network.
 */
public interface RemoteInterfaceClient extends Remote {

    /**
     * Method to ping a client with RMI
     * @throws RemoteException (Standard RMI problem exception)
     */
    void pingClient() throws RemoteException;





    //*******PASSIVE ACTIONS*******

    //DO RMI FOR PASSIVE FIRST ROUND

    /**
     * Method to pass info about the aim card in the first round through actions with RMI calls
     * @throws RemoteException (standard RMI calls exception)
     */
    void aimCardInfoPassiveFirstRound(Data d) throws RemoteException;

    /**
     * Method to pass the result for an aim card in the first round through actions with RMI calls
     * @throws RemoteException (standard RMI calls exception)
     */
    void aimCardResultPassiveFirstRound(Data d) throws RemoteException;

    /**
     * Method to pass the result for a deal card in the first round through actions with RMI calls
     * @throws RemoteException (standard RMI calls exception)
     */
    void dealCardResultPassiveFirstRound(Data d) throws RemoteException;

    /**
     * Method to pass the player order in the first round through actions with RMI calls
     * @throws RemoteException (standard RMI calls exception)
     */
    void playersOrderInfoPassiveFirstRound(Data d) throws RemoteException;

    /**
     * Method for token handling in the first round through actions with RMI calls
     * @throws RemoteException (standard RMI calls exception)
     */
    void positiveTokenColorPassiveFirstRound(Data d) throws RemoteException;

    /**
     * Method to pass info about a starting card in the first round through actions with RMI calls
     * @throws RemoteException (standard RMI calls exception)
     */
    void startingCardInfoPassiveFirstRound(Data d) throws RemoteException;

    /**
     * Method to pass the result of an action on starting card in the first round through actions with RMI calls
     * @throws RemoteException (standard RMI calls exception)
     */
    void startingCardResultPassiveFirstRound(Data d) throws RemoteException;

    /**
     * Method to pass info about the token in the first round through actions with RMI calls
     * @throws RemoteException (standard RMI calls exception)
     */
    void tokenInfoPassiveFirstRound(Data d) throws RemoteException;



    //DO RMI FOR PASSIVE FLOW

    /**
     * Method to pass the info about the change turn through actions with RMI calls
     * @throws RemoteException (standard RMI calls exception)
     */
    void changeTurnInfoPassiveFlow(Data d) throws RemoteException;

    /**
     * Method to create the result through actions with RMI calls
     * @throws RemoteException (standard RMI calls exception)
     */
    void createResultPassiveFlow(Data d) throws RemoteException;

    /**
     * Method to communicate a creation failure through actions with RMI calls
     * @throws RemoteException (standard RMI calls exception)
     */
    void failedCreationPassiveFlow(Data d) throws RemoteException;

    /**
     * Method to communicate a GameID parameter error through actions with RMI calls
     * @throws RemoteException (standard RMI calls exception)
     */
    void failedGameIDPassiveFlow(Data d) throws RemoteException;

    /**
     * Method to communicate the result for gameID through actions with RMI calls
     * @throws RemoteException (standard RMI calls exception)
     */
    void gameIDResultPassiveFlow(Data d) throws RemoteException;

    /**
     * Method to pass the result of the game initialization through actions with RMI calls
     * @throws RemoteException (standard RMI calls exception)
     */
    void initGameResultPassiveFlow(Data d) throws RemoteException;

    /**
     * Method to communicate the response of a join through actions with RMI calls
     * @throws RemoteException (standard RMI calls exception)
     */
    void joinResponsePassiveFlow(Data d) throws RemoteException;

    /**
     * Method to get info about the last rounds through actions with RMI calls
     * @throws RemoteException (standard RMI calls exception)
     */
    void lastRoundsInfoPassiveFlow(Data d) throws RemoteException;

    /**
     * Method to communicate that there aren't active parties through actions with RMI calls
     * @throws RemoteException (standard RMI calls exception)
     */
    void noActivePartiesPassiveFlow(Data d) throws RemoteException;

    /**
     * Method to communicate that there aren't enough players through actions with RMI calls
     * @throws RemoteException (standard RMI calls exception)
     */
    void notEnoughPlayersPassiveFLow(Data d) throws RemoteException;



    //DO RMI FOR PASSIVE ROUND


    /**
     * Method to handle the end game phase during a round through actions with RMI calls
     * @throws RemoteException (standard RMI calls exception)
     */
    void endGamePassiveRound(Data d) throws RemoteException;

    /**
     * Method for the draw update during a round through actions with RMI calls
     * @throws RemoteException (standard RMI calls exception)
     */
    void positiveDrawPassiveRound(Data d) throws RemoteException;

    /**
     * Method for the placing update during a round through actions with RMI calls
     * @throws RemoteException (standard RMI calls exception)
     */
    void positivePlacingPassiveRound(Data d) throws RemoteException;

    /**
     * Method for repeating the draw during a round through actions with RMI calls
     * @throws RemoteException (standard RMI calls exception)
     */
    void repeatDrawPassiveRound(Data d) throws RemoteException;

    /**
     * Method for repeating the placing during a round through actions with RMI calls
     * @throws RemoteException (standard RMI calls exception)
     */
    void repeatPlacingPassiveRound(Data d) throws RemoteException;

    void interruptedGame(Data d) throws RemoteException;

    void FailedHostNamePassiveFlow(Data d) throws RemoteException;
}
