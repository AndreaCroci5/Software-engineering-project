package it.polimi.ingsw.am40.data.passive.flow;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.client.ClientMessages.passiveMessages.flow.StartingGameMessage;
import it.polimi.ingsw.am40.client.network.RMI.RemoteInterfaceClient;
import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.server.network.RMI.RemoteInterfaceServer;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Map;

/**
 * This data is sent to start the game
 */
@JsonTypeName("GAME_INIT_RESULT")
public class GameInitResultData extends Data {
    //ATTRIBUTES
    /**
     * This represents the order of the players
     */
    private ArrayList<String> nicknames;

    /**
     * Reference to a Map containing as Keys the name of the Type of Cards that the Values Represent: "RESOURCE", "GOLDEN", "AIM".
     * The values represent an ArrayList of Integers, whose element represent the card ID and the index the position on the CommonBoard:
     * indexes: (0) plate first card, (1) plate second card, (2) deck card
     */
    private Map<String, ArrayList<Integer>> commonBoard;


    public ArrayList<String> getNicknames() {
        return nicknames;
    }

    public void setNicknames(ArrayList<String> nicknames) {
        this.nicknames = nicknames;
    }

    public Map<String, ArrayList<Integer>> getCommonBoard() {
        return commonBoard;
    }

    public void setCommonBoard(Map<String, ArrayList<Integer>> commonBoard) {
        this.commonBoard = commonBoard;
    }

    /**
     * Constructor for GameInitResultData
     * @param nickname is the name of the active client
     * @param nicknames represents the order of the player
     * @param commonBoard is the commonBoard of the game
     */
    @JsonCreator
    public GameInitResultData(@JsonProperty("nickname") String nickname,
                              @JsonProperty("nicknames") ArrayList<String> nicknames,
                              @JsonProperty("commonBoard") Map<String,ArrayList<Integer>> commonBoard) {
        super("GAME_INIT_RESULT", nickname);
        this.nicknames = nicknames;
        this.commonBoard = commonBoard;
    }

    /**
     * This method is called once the Data reaches the Client and creates the Message related to the Data sent by polymorphism
     * @return the corresponding Message on the Client
     */
    public Message onClient() {
        return new StartingGameMessage(this.getNickname(),this.nicknames,this.commonBoard);
    }

    /**
     * Method which calls the right RMI interface method for each data (with override)
     * @param skeleton the client remote interface. Null if data active
     * @param stub the server remote interface. Null if data passive
     */
    @Override
    public void doRMI(RemoteInterfaceClient skeleton, RemoteInterfaceServer stub){
        try {
            skeleton.initGameResultPassiveFlow(this);
        } catch (RemoteException e) {
            System.out.println("RMI call went wrong! message: " + e);
        }
    }
}
