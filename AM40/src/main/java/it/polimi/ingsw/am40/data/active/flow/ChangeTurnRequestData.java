package it.polimi.ingsw.am40.data.active.flow;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.client.network.RMI.RemoteInterfaceClient;
import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.server.actions.Action;
import it.polimi.ingsw.am40.server.actions.active.flow.ChangeTurnRequestAction;
import it.polimi.ingsw.am40.server.network.RMI.RemoteInterfaceServer;

import java.rmi.RemoteException;

/**
 * This class contains the information that will be carried by being sent on the network.
 * This class is also the bridge from ChangeTurnRequestMessage on the Client to the ChangeTurnRequestAction on Server
 */
@JsonTypeName("CHANGE_TURN")
public class ChangeTurnRequestData extends Data {
    //CONSTRUCTOR


    //Json constructor
    @JsonCreator
    public ChangeTurnRequestData(@JsonProperty("nickname") String nickname) {
        super("CHANGE_TURN", nickname);
    }

    //PUBLIC METHODS

    /**
     * Override of the method onServer that returns the related ChangeTurnRequestAction on the Server
     * @return a ChangeTurnRequestAction
     */
    @Override
    public Action onServer(){
        return new ChangeTurnRequestAction(this.getNickname(), this.getGameID(), this.getPlayerID());
    }

    public Message onClient() {
        return null;
    }

    /**
     * Method which calls the right RMI interface method for each data (with override)
     * @param skeleton the client remote interface. Null if data active
     * @param stub the server remote interface. Null if data passive
     */
    @Override
    public synchronized void doRMI(RemoteInterfaceClient skeleton, RemoteInterfaceServer stub){
        try {
            stub.requestChangeTurnActiveFlow(this);
        } catch (RemoteException e) {
            System.out.println("RMI call went wrong! message: " + e);
        }
    }
}
