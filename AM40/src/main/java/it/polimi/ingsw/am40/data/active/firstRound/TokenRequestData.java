package it.polimi.ingsw.am40.data.active.firstRound;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import it.polimi.ingsw.am40.client.network.RMI.RemoteInterfaceClient;
import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.server.actions.Action;
import it.polimi.ingsw.am40.server.actions.active.firstRound.TokenRequestAction;
import it.polimi.ingsw.am40.server.network.RMI.RemoteInterfaceServer;

import java.rmi.RemoteException;

/**
 * This class contains the information that will be carried by being sent on the network.
 * This class is also the bridge from TokenRequestMessage on the Client to the TokenRequestAction on Server
 */
@JsonTypeName("TOKEN_REQUEST")
public class TokenRequestData extends Data {

    //CONSTRUCTOR

    @JsonCreator
    public TokenRequestData(@JsonProperty("nickname") String nickname) {
        super("TOKEN_REQUEST", nickname);
    }

    //PUBLIC METHODS

    /**
     * Override of the method onServer that returns the related TokenRequestAction on the Server
     * @return a TokenRequestAction
     */
    @Override
    public Action onServer() {
        return new TokenRequestAction(this.getNickname(), this.getGameID(),this.getPlayerID());
    }

    /**
     * Method which calls the right RMI interface method for each data (with override)
     * @param skeleton the client remote interface. Null if data active
     * @param stub the server remote interface. Null if data passive
     */
    @Override
    public synchronized void doRMI(RemoteInterfaceClient skeleton, RemoteInterfaceServer stub){
        try {
            stub.requestTokenActiveFirstRound(this);
        } catch (RemoteException e) {
            System.out.println("RMI call went wrong! message: " + e);
        }
    }
}
