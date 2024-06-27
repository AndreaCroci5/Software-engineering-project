package it.polimi.ingsw.am40.data.passive.flow;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.client.ClientMessages.passiveMessages.flow.FailedCreationMessage;
import it.polimi.ingsw.am40.client.network.RMI.RemoteInterfaceClient;
import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.server.actions.Action;
import it.polimi.ingsw.am40.server.network.RMI.RemoteInterfaceServer;

import java.rmi.RemoteException;

@JsonTypeName("FAILED_CREATION")
public class FailedCreationData extends Data {
    //Json constructor
    @JsonCreator
    public FailedCreationData(@JsonProperty("nickname") String nickname) {
        super("FAILED_CREATION", nickname);
    }


    //PUBLIC METHODS


    public Action onServer(){
        return null;
    }

    public Message onClient() {
       return new FailedCreationMessage(this.getNickname());
    }

    /**
     * Method which calls the right RMI interface method for each data (with override)
     * @param skeleton the client remote interface. Null if data active
     * @param stub the server remote interface. Null if data passive
     */
    @Override
    public void doRMI(RemoteInterfaceClient skeleton, RemoteInterfaceServer stub){
        try {
            skeleton.failedCreationPassiveFlow(this);
        } catch (RemoteException e) {
            System.out.println("RMI call went wrong! message: " + e);
        }
    }
}
