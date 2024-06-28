package it.polimi.ingsw.am40.data.active.firstRound;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.client.network.RMI.RemoteInterfaceClient;
import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.server.actions.Action;
import it.polimi.ingsw.am40.server.actions.active.firstRound.PlayersOrderRequestAction;
import it.polimi.ingsw.am40.server.network.RMI.RemoteInterfaceServer;

import java.rmi.RemoteException;

/**
 * This class contains the information that will be carried by being sent on the network.
 * This class is also the bridge form DecidePlayerOrderRequestMessage on the Client to the PlayersOrderRequestAction on Server
 */
@JsonTypeName("PLAYERS_ORDER_REQUEST")
public class PlayersOrderRequestData extends Data {
    //CONSTRUCTOR

    @JsonCreator
    public PlayersOrderRequestData(@JsonProperty("nickname") String nickname) {
        super("PLAYERS_ORDER_REQUEST", nickname);
    }


    //PUBLIC METHODS
    /**
     * Override of the method onServer that returns the related PlayersOrderRequestAction on the Server
     * @return a PlayersOrderRequestAction
     */
    @Override
    public Action onServer(){
        return new PlayersOrderRequestAction(this.getNickname(), this.getGameID(), this.getPlayerID());
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
            stub.requestPlayerOrderActiveFirstRound(this);
        } catch (RemoteException e) {
            System.out.println("RMI call went wrong! message: " + e);
        }
    }
}
