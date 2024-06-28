package it.polimi.ingsw.am40.data.passive.flow;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.client.ClientMessages.passiveMessages.flow.ChangeTurnResponseMessage;
import it.polimi.ingsw.am40.client.network.RMI.RemoteInterfaceClient;
import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.server.actions.Action;
import it.polimi.ingsw.am40.server.network.RMI.RemoteInterfaceServer;

import java.rmi.RemoteException;

/**
 * This data is sent to let the clients knows who is the next player
 */
@JsonTypeName("CHANGE_TURN_INFO")
public class ChangeTurnInfoData extends Data {
    //ATTRIBUTES
    /** Nickname of the next Player that has the right to play*/
    private String nextActivePlayer;

    //CONSTRUCTOR

    /**
     * Constructor for the ChangeTurnInfoData
     * @param nickname is the name of the active client
     * @param nextActivePlayer is the name of the next active client
     */
    @JsonCreator
    public ChangeTurnInfoData(@JsonProperty("nickname") String nickname,
                              @JsonProperty("nextActivePlayer") String nextActivePlayer) {
        super("CHANGE_TURN_INFO", nickname);
        this.nextActivePlayer = nextActivePlayer;
    }

    public String getNextActivePlayer() {
        return nextActivePlayer;
    }

    public void setNextActivePlayer(String nextActivePlayer) {
        this.nextActivePlayer = nextActivePlayer;
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
        return new ChangeTurnResponseMessage(this.nextActivePlayer);
    }

    /**
     * Method which calls the right RMI interface method for each data (with override)
     * @param skeleton the client remote interface. Null if data active
     * @param stub the server remote interface. Null if data passive
     */
    @Override
    public void doRMI(RemoteInterfaceClient skeleton, RemoteInterfaceServer stub){
        try {
            skeleton.changeTurnInfoPassiveFlow(this);
        } catch (RemoteException e) {
            System.out.println("RMI call went wrong! message: " + e);
        }
    }
}
