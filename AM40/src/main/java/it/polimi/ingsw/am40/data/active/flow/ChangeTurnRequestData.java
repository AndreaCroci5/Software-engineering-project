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

@JsonTypeName("CHANGE_TURN")
public class ChangeTurnRequestData extends Data {
    //CONSTRUCTOR


    //Json constructor
    @JsonCreator
    public ChangeTurnRequestData(@JsonProperty("nickname") String nickname) {
        super("CHANGE_TURN", nickname);
    }

    //PUBLIC METHODS

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
