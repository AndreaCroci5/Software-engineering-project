package it.polimi.ingsw.am40.client.network.RMI;

import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.client.network.Client;
import it.polimi.ingsw.am40.data.Data;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * RemoteObjectClient class is the concrete implementation of the RemoteInterfaceClient for RMI. The interface is logged
 * in the server RMI registry and the server can call methods on it. This class contains te implementations for those methods.
 */
public class RemoteObjectClient extends UnicastRemoteObject implements RemoteInterfaceClient{

    private Client client;


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
    protected RemoteObjectClient() throws RemoteException {
        super();
    }



    //GETTER METHOD

    /**
     * Getter for the client class
     * @return the client as reference
     */
    public Client getClient() {
        return client;
    }


    //SETTER METHOD

    /**
     * Setter for the client
     * @param client the client class as reference
     */
    public void setClient(Client client) {
        this.client = client;
    }



    //IMPLEMENTATIONS FOR DATAs

   @Override
    public void pingClient() throws RemoteException {
    }




    /**
     * Method to pass info about the aim card in the first round through actions with RMI calls
     *
     * @param d
     * @throws RemoteException (standard RMI calls exception)
     */
    @Override
    public void aimCardInfoPassiveFirstRound(Data d) throws RemoteException {
        Message m = d.onClient();
        this.client.handleMessage(m);
    }

    /**
     * Method to pass the result for an aim card in the first round through actions with RMI calls
     *
     * @param d
     * @throws RemoteException (standard RMI calls exception)
     */
    @Override
    public void aimCardResultPassiveFirstRound(Data d) throws RemoteException {
        Message m = d.onClient();
        this.client.handleMessage(m);
    }

    /**
     * Method to pass the result for a deal card in the first round through actions with RMI calls
     *
     * @param d
     * @throws RemoteException (standard RMI calls exception)
     */
    @Override
    public void dealCardResultPassiveFirstRound(Data d) throws RemoteException {
        Message m = d.onClient();
        this.client.handleMessage(m);
    }

    /**
     * Method to pass the player order in the first round through actions with RMI calls
     *
     * @param d
     * @throws RemoteException (standard RMI calls exception)
     */
    @Override
    public void playersOrderInfoPassiveFirstRound(Data d) throws RemoteException {
        Message m = d.onClient();
        this.client.handleMessage(m);
    }

    /**
     * Method for token handling in the first round through actions with RMI calls
     *
     * @param d
     * @throws RemoteException (standard RMI calls exception)
     */
    @Override
    public void positiveTokenColorPassiveFirstRound(Data d) throws RemoteException {
        Message m = d.onClient();
        this.client.handleMessage(m);
    }

    /**
     * Method to pass info about a starting card in the first round through actions with RMI calls
     *
     * @param d
     * @throws RemoteException (standard RMI calls exception)
     */
    @Override
    public void startingCardInfoPassiveFirstRound(Data d) throws RemoteException {
        Message m = d.onClient();
        this.client.handleMessage(m);
    }

    /**
     * Method to pass the result of an action on starting card in the first round through actions with RMI calls
     *
     * @param d
     * @throws RemoteException (standard RMI calls exception)
     */
    @Override
    public void startingCardResultPassiveFirstRound(Data d) throws RemoteException {
        Message m = d.onClient();
        this.client.handleMessage(m);
    }

    /**
     * Method to pass info about the token in the first round through actions with RMI calls
     *
     * @param d
     * @throws RemoteException (standard RMI calls exception)
     */
    @Override
    public void tokenInfoPassiveFirstRound(Data d) throws RemoteException {
        Message m = d.onClient();
        this.client.handleMessage(m);
    }

    /**
     * Method to pass the info about the change turn through actions with RMI calls
     *
     * @param d
     * @throws RemoteException (standard RMI calls exception)
     */
    @Override
    public void changeTurnInfoPassiveFlow(Data d) throws RemoteException {
        Message m = d.onClient();
        this.client.handleMessage(m);
    }

    /**
     * Method to create the result through actions with RMI calls
     *
     * @param d
     * @throws RemoteException (standard RMI calls exception)
     */
    @Override
    public void createResultPassiveFlow(Data d) throws RemoteException {
        Message m = d.onClient();
        this.client.handleMessage(m);
    }

    /**
     * Method to communicate a creation failure through actions with RMI calls
     *
     * @param d
     * @throws RemoteException (standard RMI calls exception)
     */
    @Override
    public void failedCreationPassiveFlow(Data d) throws RemoteException {
        Message m = d.onClient();
        this.client.handleMessage(m);
    }

    /**
     * Method to communicate a GameID parameter error through actions with RMI calls
     *
     * @param d
     * @throws RemoteException (standard RMI calls exception)
     */
    @Override
    public void failedGameIDPassiveFlow(Data d) throws RemoteException {
        Message m = d.onClient();
        this.client.handleMessage(m);
    }

    /**
     * Method to communicate the result for gameID through actions with RMI calls
     *
     * @param d
     * @throws RemoteException (standard RMI calls exception)
     */
    @Override
    public void gameIDResultPassiveFlow(Data d) throws RemoteException {
        Message m = d.onClient();
        this.client.handleMessage(m);
    }

    /**
     * Method to pass the result of the game initialization through actions with RMI calls
     *
     * @param d
     * @throws RemoteException (standard RMI calls exception)
     */
    @Override
    public void initGameResultPassiveFlow(Data d) throws RemoteException {
        Message m = d.onClient();
        this.client.handleMessage(m);
    }

    /**
     * Method to communicate the response of a join through actions with RMI calls
     *
     * @param d
     * @throws RemoteException (standard RMI calls exception)
     */
    @Override
    public void joinResponsePassiveFlow(Data d) throws RemoteException {
        Message m = d.onClient();
        this.client.handleMessage(m);
    }

    /**
     * Method to get info about the last rounds through actions with RMI calls
     *
     * @param d
     * @throws RemoteException (standard RMI calls exception)
     */
    @Override
    public void lastRoundsInfoPassiveFlow(Data d) throws RemoteException {
        Message m = d.onClient();
        this.client.handleMessage(m);
    }

    /**
     * Method to communicate that there aren't active parties through actions with RMI calls
     *
     * @param d
     * @throws RemoteException (standard RMI calls exception)
     */
    @Override
    public void noActivePartiesPassiveFlow(Data d) throws RemoteException {
        Message m = d.onClient();
        this.client.handleMessage(m);
    }

    /**
     * Method to communicate that there aren't enough players through actions with RMI calls
     *
     * @param d
     * @throws RemoteException (standard RMI calls exception)
     */
    @Override
    public void notEnoughPlayersPassiveFLow(Data d) throws RemoteException {
        Message m = d.onClient();
        this.client.handleMessage(m);
    }

    /**
     * Method to handle the end game phase during a round through actions with RMI calls
     *
     * @param d
     * @throws RemoteException (standard RMI calls exception)
     */
    @Override
    public void endGamePassiveRound(Data d) throws RemoteException {
        Message m = d.onClient();
        this.client.handleMessage(m);
    }

    /**
     * Method for the draw update during a round through actions with RMI calls
     *
     * @param d
     * @throws RemoteException (standard RMI calls exception)
     */
    @Override
    public void positiveDrawPassiveRound(Data d) throws RemoteException {
        Message m = d.onClient();
        this.client.handleMessage(m);
    }

    /**
     * Method for the placing update during a round through actions with RMI calls
     *
     * @param d
     * @throws RemoteException (standard RMI calls exception)
     */
    @Override
    public void positivePlacingPassiveRound(Data d) throws RemoteException {
        Message m = d.onClient();
        this.client.handleMessage(m);
    }

    /**
     * Method for repeating the draw during a round through actions with RMI calls
     *
     * @param d
     * @throws RemoteException (standard RMI calls exception)
     */
    @Override
    public void repeatDrawPassiveRound(Data d) throws RemoteException {
        Message m = d.onClient();
        this.client.handleMessage(m);
    }

    /**
     * Method for repeating the placing during a round through actions with RMI calls
     *
     * @param d
     * @throws RemoteException (standard RMI calls exception)
     */
    @Override
    public void repeatPlacingPassiveRound(Data d) throws RemoteException {
        Message m = d.onClient();
        this.client.handleMessage(m);
    }

    @Override
    public void interruptedGame(Data d) throws RemoteException {
        Message m = d.onClient();
        this.client.handleMessage(m);
    }

}
