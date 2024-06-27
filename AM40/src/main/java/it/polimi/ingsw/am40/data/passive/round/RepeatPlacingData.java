package it.polimi.ingsw.am40.data.passive.round;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.client.network.RMI.RemoteInterfaceClient;
import it.polimi.ingsw.am40.client.ClientMessages.passiveMessages.round.RepeatPlacingMessage;
import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.server.actions.Action;
import it.polimi.ingsw.am40.server.network.RMI.RemoteInterfaceServer;

import java.rmi.RemoteException;

@JsonTypeName("REPEAT_PLACING")
public class RepeatPlacingData extends Data {
    //ATTRIBUTES

    //CONSTRUCTOR

    @JsonCreator
    public RepeatPlacingData(@JsonProperty("nickname") String nickname){
        super("REPEAT_PLACING", nickname);
    }


    //PUBLIC METHODS


    public Action onServer(){
        return null;
    }

    public Message onClient() {
        return new RepeatPlacingMessage(this.getNickname());
    }

    /**
     * Method which calls the right RMI interface method for each data (with override)
     * @param skeleton the client remote interface. Null if data active
     * @param stub the server remote interface. Null if data passive
     */
    @Override
    public void doRMI(RemoteInterfaceClient skeleton, RemoteInterfaceServer stub){
        try {
            skeleton.repeatPlacingPassiveRound(this);
        } catch (RemoteException e) {
            System.out.println("RMI call went wrong! message: " + e);
        }
    }
}
