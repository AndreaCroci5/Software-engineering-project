package it.polimi.ingsw.am40.client.ClientMessages.passiveMessages.flow;

import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.client.ClientMessages.activeMessages.flow.ReadyToPlayMessage;
import it.polimi.ingsw.am40.client.network.Client;
import it.polimi.ingsw.am40.client.network.States.activeStates.ReadyToPlayState;

/**
 * This message is sent by the server, and it's an ack that client joined the game he chose
 */
public class GameIdResponseMessage extends Message {

    /**
     * It's the name of the active client
     */
    private final String clientNickname;

    /**
     * It's the ID of the party of the player
     */
    private int partyId;

    /**
     * It's the numbers of the players already logged
     */
    private final int numOfActualPlayers;

    /**
     * It's the numbers of the players that are going to play the game
     */
    private final int numOfFinalPlayers;

    /**
     * Constructor for the gameIdResponseMessage
     * @param clientNickname is the name of the active client
     * @param partyId is the ID of the party of the client
     * @param numOfActualPlayers is the name of the actual players in the party
     * @param numOfFinalPlayers is the final number of players
     */
    public GameIdResponseMessage(String clientNickname,int partyId, int numOfActualPlayers, int numOfFinalPlayers) {
        super("POSITIVE_GAME_CHOICE",clientNickname);
        this.clientNickname = clientNickname;
        this.numOfActualPlayers = numOfActualPlayers;
        this.numOfFinalPlayers = numOfFinalPlayers;
    }

    /**
     * It sets the next state of the client state machine
     * @param context is the context of the client with his view and his network communication protocol
     */
    public void process(Client context) {
        if (context.getNickname().equalsIgnoreCase(this.clientNickname)) {
            context.getViewManager().displayWaitingForPlayers(this.numOfActualPlayers,this.numOfFinalPlayers);
            if (!context.getCurrentState().getClass().equals(ReadyToPlayState.class)) {
                // send the message to the server in order to start the game
                context.getNetworkManager().send(new ReadyToPlayMessage(context.getNickname()));
            }
            context.setState(new ReadyToPlayState());
        }
        else {
            context.getViewManager().displayWaitingForPlayers(this.numOfActualPlayers,this.numOfFinalPlayers);
        }
    }

}
