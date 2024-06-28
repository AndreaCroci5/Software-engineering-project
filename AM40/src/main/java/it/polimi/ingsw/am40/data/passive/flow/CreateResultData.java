package it.polimi.ingsw.am40.data.passive.flow;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.client.ClientMessages.passiveMessages.flow.CreateResponseMessage;
import it.polimi.ingsw.am40.client.network.RMI.RemoteInterfaceClient;
import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.server.actions.Action;
import it.polimi.ingsw.am40.server.network.RMI.RemoteInterfaceServer;

import java.rmi.RemoteException;

/**
 * This data is sent to the client to confirm the creation of a new game
 */
@JsonTypeName("CREATE_RESULT")
public class CreateResultData extends Data {
    //ATTRIBUTES
    /**
     * It's the number of players that the wants in the game
     */
    private int numOfPlayers;
    /**
     * It's the number of players logged
     */
    private int playersLogged;

    //Json constructor

    /**
     * Constructor for the CreateResultData
     * @param nickname is the name of the active client
     * @param numOfPlayers is the number of players that the wants in the game
     * @param playersLogged is the number of players logged
     */
    @JsonCreator
    public CreateResultData(@JsonProperty("nickname") String nickname,
                            @JsonProperty("numOfPlayers") int numOfPlayers,
                            @JsonProperty("playersLogged") int playersLogged) {
        super("CREATE_RESULT", nickname);
        this.numOfPlayers = numOfPlayers;
        this.playersLogged = playersLogged;
    }

    public int getNumOfPlayers() {
        return numOfPlayers;
    }

    public void setNumOfPlayers(int numOfPlayers) {
        this.numOfPlayers = numOfPlayers;
    }

    public int getPlayersLogged() {
        return playersLogged;
    }

    public void setPlayersLogged(int playersLogged) {
        this.playersLogged = playersLogged;
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
        return new CreateResponseMessage(this.getNickname(),this.numOfPlayers);
    }

    /**
     * Method which calls the right RMI interface method for each data (with override)
     * @param skeleton the client remote interface. Null if data active
     * @param stub the server remote interface. Null if data passive
     */
    @Override
    public void doRMI(RemoteInterfaceClient skeleton, RemoteInterfaceServer stub){
        try {
            skeleton.createResultPassiveFlow(this);
        } catch (RemoteException e) {
            System.out.println("RMI call went wrong! message: " + e);
        }
    }
}
