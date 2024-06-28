package it.polimi.ingsw.am40.data.active.flow;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import it.polimi.ingsw.am40.client.network.RMI.RemoteInterfaceClient;
import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.server.actions.Action;
import it.polimi.ingsw.am40.server.actions.active.setup.ReadyToPlayAction;
import it.polimi.ingsw.am40.server.network.RMI.RemoteInterfaceServer;

import java.rmi.RemoteException;

/**
 * This class contains the information that will be carried by being sent on the network.
 * This class is also the bridge from ReadyToPlayMessage on the Client to the ReadyToPlayAction on Server
 */
@JsonTypeName("READY_TO_PLAY")
public class ReadyToPlayData extends Data {



    @JsonCreator
    public ReadyToPlayData(@JsonProperty("nickname") String nickname) {
        super("READY_TO_PLAY", nickname);
    }

    /**
     * Override of the method onServer that returns the related ReadyToPlayAction on the Server
     * @return a ReadyToPlayAction
     */
    @Override
    public Action onServer() {
        return new ReadyToPlayAction(getDescription(), getNickname(), getGameID(), getPlayerID());
    }

    /**
     * Method which calls the right RMI interface method for each data (with override)
     * @param skeleton the client remote interface. Null if data active
     * @param stub the server remote interface. Null if data passive
     */
    @Override
    public synchronized void doRMI(RemoteInterfaceClient skeleton, RemoteInterfaceServer stub){
        try {
            stub.readyToPlayActiveFlow(this);
        } catch (RemoteException e) {
            System.out.println("RMI call went wrong! message: " + e);
        }
    }
}
