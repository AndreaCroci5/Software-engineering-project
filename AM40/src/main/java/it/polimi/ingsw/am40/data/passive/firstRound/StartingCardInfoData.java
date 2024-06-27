package it.polimi.ingsw.am40.data.passive.firstRound;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.client.ClientMessages.passiveMessages.firstRound.StartingCardInfoMessage;
import it.polimi.ingsw.am40.client.network.RMI.RemoteInterfaceClient;
import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.server.actions.Action;
import it.polimi.ingsw.am40.server.network.RMI.RemoteInterfaceServer;

import java.rmi.RemoteException;

@JsonTypeName("STARTING_INFO")
public class StartingCardInfoData extends Data {
    //ATTRIBUTES
    private int startingCardID;

    //CONSTRUCTOR

    @JsonCreator
    public StartingCardInfoData(@JsonProperty("nickname") String nickname,
                                @JsonProperty("startingCardID") int startingCardID) {
        super("STARTING_INFO", nickname);
        this.startingCardID = startingCardID;
    }

    public int getStartingCardID() {
        return startingCardID;
    }

    public void setStartingCardID(int startingCardID) {
        this.startingCardID = startingCardID;
    }

    //PUBLIC METHODS


    public Action onServer(){
        return null;
    }

    public Message onClient() {
        return new StartingCardInfoMessage(this.getNickname(), this.startingCardID);
    }

    /**
     * Method which calls the right RMI interface method for each data (with override)
     * @param skeleton the client remote interface. Null if data active
     * @param stub the server remote interface. Null if data passive
     */
    @Override
    public void doRMI(RemoteInterfaceClient skeleton, RemoteInterfaceServer stub){
        try {
            skeleton.startingCardInfoPassiveFirstRound(this);
        } catch (RemoteException e) {
            System.out.println("RMI call went wrong! message: " + e);
        }
    }
}
