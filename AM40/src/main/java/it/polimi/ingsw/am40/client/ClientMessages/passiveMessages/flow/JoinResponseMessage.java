package it.polimi.ingsw.am40.client.ClientMessages.passiveMessages.flow;

import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.client.network.ClientContext;
import it.polimi.ingsw.am40.client.network.States.activeStates.ChooseGameState;
import java.util.ArrayList;

public class JoinResponseMessage extends Message {

    /**
     * It's the name of the active client
     */
    private final String clientNickname;

    /**
     * It's a list with all the gameIds of the possible games that the client can join
     */
    private final ArrayList<Integer> gamesIDs;


    /**
     * This message contains all the IDs of the client that he can join
     * @param clientNickname is the name of the active client
     * @param gameIDs is the list of the possible game that the client can join
     */
    public JoinResponseMessage(String clientNickname,ArrayList<Integer> gameIDs) {
        super("POSITIVE_JOIN");
        this.clientNickname = clientNickname;
        this.gamesIDs = gameIDs;
    }

    /**
     * It sets the next state of the client state machine
     * @param context is the context of the client with his view and his network communication protocol
     */
    public void process(ClientContext context) {
        if (clientNickname.equalsIgnoreCase(context.getNickname())) {
            context.setState(new ChooseGameState());
        }
    }
}
