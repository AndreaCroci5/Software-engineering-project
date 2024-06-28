package it.polimi.ingsw.am40.data.passive.firstRound;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.client.ClientMessages.passiveMessages.flow.DecidePlayerOrderResponse;
import it.polimi.ingsw.am40.client.network.RMI.RemoteInterfaceClient;
import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.server.actions.Action;
import it.polimi.ingsw.am40.server.network.RMI.RemoteInterfaceServer;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * This data is sent to the client to tell the order of the players in the round
 */
@JsonTypeName("PLAYERS_ORDER_INFO")
public class PlayersOrderInfoData extends Data {
    //ATTRIBUTES
    /**
     * Is the order of the players
     */
    private ArrayList<String> players;

    //CONSTRUCTOR

    /**
     * Constructor for the PlayerOrderInfoData
     * @param nickname is the name of the active client
     * @param players is the order of players
     */
    @JsonCreator
    public PlayersOrderInfoData(@JsonProperty("nickname") String nickname,
                                @JsonProperty("players") ArrayList<String> players) {
        super("PLAYERS_ORDER_INFO", nickname);
        this.players = players;
    }

    public ArrayList<String> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<String> players) {
        this.players = players;
    }

    //PUBLIC METHODS

    /**
     * This method is called once the Data reaches the Server and creates the Action related to the Data sent by polymorphism
     * @return the corresponding Action on the Server
     */
    public Action onServer(){
        return null;
    }

    /**
     * This method is called once the Data reaches the Client and creates the Message related to the Data sent by polymorphism
     * @return the corresponding Message on the Client
     */
    public Message onClient() {
        return new DecidePlayerOrderResponse(this.getNickname(), this.players);
    }

    /**
     * Method which calls the right RMI interface method for each data (with override)
     * @param skeleton the client remote interface. Null if data active
     * @param stub the server remote interface. Null if data passive
     */
    @Override
    public void doRMI(RemoteInterfaceClient skeleton, RemoteInterfaceServer stub){
        try {
            skeleton.playersOrderInfoPassiveFirstRound(this);
        } catch (RemoteException e) {
            System.out.println("RMI call went wrong! message: " + e);
        }
    }
}
