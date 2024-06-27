package it.polimi.ingsw.am40.client.ClientMessages.passiveMessages.flow;

import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.client.network.Client;
import it.polimi.ingsw.am40.client.network.States.activeStates.ChooseGameState;
import java.util.ArrayList;
import java.util.Map;

/**
 * This message is sent by the server in order to let the client know all the possible games he can join
 */
public class JoinResponseMessage extends Message {

    /**
     * It's the name of the active client
     */
    private final String clientNickname;

    /**
     * It's a list with all the gameIds of the possible games that the client can join
     */
    private Map<Integer, ArrayList<Integer>> currentParties;


    /**
     * Constructor for the JoinResponseMessage
     * @param clientNickname is the name of the active client
     * @param currentParties are the IDs of the possible games the client can join
     */
    public JoinResponseMessage(String clientNickname,Map<Integer, ArrayList<Integer>> currentParties) {
        super("POSITIVE_JOIN",clientNickname);
        this.clientNickname = clientNickname;
        this.currentParties = currentParties;
    }

    /**
     * It displays all possible games the player can join
     * It sets the next state of the client state machine
     * @param context is the context of the client with his view and his network communication protocol
     */
    public void process(Client context) {

        // display all possible games
        // set the new state
        if (this.clientNickname.equalsIgnoreCase(context.getNickname())) {
            context.getViewManager().displayAllGameIds(this.currentParties);
            context.setState(new ChooseGameState(this.currentParties));
        }
    }
}
