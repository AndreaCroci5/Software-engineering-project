package it.polimi.ingsw.am40.data.active.firstRound;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import it.polimi.ingsw.am40.client.network.RMI.RemoteInterfaceClient;
import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.server.actions.Action;
import it.polimi.ingsw.am40.server.actions.active.firstRound.AimCardRequestAction;
import it.polimi.ingsw.am40.server.network.RMI.RemoteInterfaceServer;

import java.rmi.RemoteException;

@JsonTypeName("AIM_CARD_REQUEST")
public class AimCardRequestData extends Data {

    //CONSTRUCTOR

    @JsonCreator public AimCardRequestData (@JsonProperty("nickname") String nickname) {
        super("AIM_CARD_REQUEST", nickname);
    }

    //PUBLIC METHODS

    public Action onServer() {
        return new AimCardRequestAction(this.getNickname(), this.getGameID(), this.getPlayerID());
    }

    /**
     * Method which calls the right RMI interface method for each data (with override)
     * @param skeleton the client remote interface. Null if data active
     * @param stub the server remote interface. Null if data passive
     */
    @Override
    public synchronized void doRMI(RemoteInterfaceClient skeleton, RemoteInterfaceServer stub){
        try {
            stub.requestAimCardActiveFirstRound(this);
        } catch (RemoteException e) {
            System.out.println("RMI call went wrong! message: " + e);
        }
    }
}
