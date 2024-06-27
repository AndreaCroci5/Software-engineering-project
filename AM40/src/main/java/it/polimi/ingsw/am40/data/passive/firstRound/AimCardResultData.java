package it.polimi.ingsw.am40.data.passive.firstRound;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.client.ClientMessages.passiveMessages.firstRound.AimCardResultMessage;
import it.polimi.ingsw.am40.client.network.RMI.RemoteInterfaceClient;
import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.server.actions.Action;
import it.polimi.ingsw.am40.server.network.RMI.RemoteInterfaceServer;

import java.rmi.RemoteException;

@JsonTypeName("AIM_CARD_SELECTED")
public class AimCardResultData extends Data {
    //ATTRIBUTES
    /** ID of the AimCard chosen by the Player*/
    private int aimCardChosenID;

    //CONSTRUCTOR

    @JsonCreator
    public AimCardResultData(@JsonProperty("nickname") String nickname,
                             @JsonProperty("aimCardChosenID") int aimCardChosenID) {
        super("AIM_CARD_SELECTED", nickname);
        this.aimCardChosenID = aimCardChosenID;
    }

    public int getAimCardChosenID() {
        return aimCardChosenID;
    }

    public void setAimCardChosenID(int aimCardChosenID) {
        this.aimCardChosenID = aimCardChosenID;
    }

    //PUBLIC METHODS


    public Action onServer(){
        return null;
    }

    public Message onClient() {
        return new AimCardResultMessage(this.getNickname(), this.aimCardChosenID);
    }

    /**
     * Method which calls the right RMI interface method for each data (with override)
     * @param skeleton the client remote interface. Null if data active
     * @param stub the server remote interface. Null if data passive
     */
    @Override
    public void doRMI(RemoteInterfaceClient skeleton, RemoteInterfaceServer stub){
        try {
            skeleton.aimCardResultPassiveFirstRound(this);
        } catch (RemoteException e) {
            System.out.println("RMI call went wrong! message: " + e);
        }
    }
}
