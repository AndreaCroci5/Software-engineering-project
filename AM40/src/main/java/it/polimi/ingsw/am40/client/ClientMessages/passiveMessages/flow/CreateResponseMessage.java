package it.polimi.ingsw.am40.client.ClientMessages.passiveMessages.flow;

import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.client.ClientMessages.activeMessages.flow.ReadyToPlayMessage;
import it.polimi.ingsw.am40.client.network.Client;
import it.polimi.ingsw.am40.client.network.States.activeStates.ReadyToPlayState;

/**
 * This message is sent by the server, and it's an ack that he had created a new game
 */
public class CreateResponseMessage extends Message {

    /**
     * It's the name of the active client
     */
    private final String clientNickname;

    /**
     * It's the number of players that the client decided to have in your game
     */
    private final int NumOfFinalPlayer;

    /**
     * Getter for clientNickname
     * @return the nickname of the client
     */
    public String getClientNickname() {
        return clientNickname;
    }

    /**
     * Getter for numOfFinalPlayer
     * @return the number of the final players
     */
    public int getNumOfFinalPlayer() {
        return NumOfFinalPlayer;
    }

    /**
     * Constructor for the CreateResponseMessage
     * @param clientNickname is the name of the active client
     * @param numOfFinalPlayer is the number of the player that are going to be in the game
     */
    public CreateResponseMessage(String clientNickname,int numOfFinalPlayer) {
        super("POSITIVE_CREATE",clientNickname);
        this.clientNickname = clientNickname;
        this.NumOfFinalPlayer = numOfFinalPlayer;
    }

    /**
     * It sets the next state of the client state machine
     * It displays waiting for players
     * @param context is the context of the client with his view and his network communication protocol
     */
    public void process(Client context) {
        if (context.getNickname().equalsIgnoreCase(this.clientNickname)) {
            context.getViewManager().displayWaitingForPlayers(1,this.NumOfFinalPlayer);
            context.setState(new ReadyToPlayState());
            // send the message to the server in order to start the game
            context.getNetworkManager().send(new ReadyToPlayMessage(context.getNickname()));
        }
    }
}
