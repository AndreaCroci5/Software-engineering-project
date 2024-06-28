package it.polimi.ingsw.am40.data.passive.flow;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.client.ClientMessages.passiveMessages.flow.FailedGameIDMessage;
import it.polimi.ingsw.am40.client.network.RMI.RemoteInterfaceClient;
import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.server.actions.Action;
import it.polimi.ingsw.am40.server.network.RMI.RemoteInterfaceServer;

import java.rmi.RemoteException;

/**
 * This data is sent when a client tries to join a full match
 */
@JsonTypeName("FAILED_GAME_ID")
public class FailedGameIDData extends Data {

    //Json constructor

    /**
     * Constructor for FailedGameIDData
     * @param nickname is the name of the active client
     */
    @JsonCreator
    public FailedGameIDData(@JsonProperty("nickname") String nickname) {
        super("FAILED_GAME_ID", nickname);
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
        return new FailedGameIDMessage(this.getNickname());
    }

    /**
     * Method which calls the right RMI interface method for each data (with override)
     * @param skeleton the client remote interface. Null if data active
     * @param stub the server remote interface. Null if data passive
     */
    @Override
    public void doRMI(RemoteInterfaceClient skeleton, RemoteInterfaceServer stub){
        try {
            skeleton.failedGameIDPassiveFlow(this);
        } catch (RemoteException e) {
            System.out.println("RMI call went wrong! message: " + e);
        }
    }
}
