package it.polimi.ingsw.am40.data.active.firstRound;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.client.network.RMI.RemoteInterfaceClient;
import it.polimi.ingsw.am40.server.actions.Action;
import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.server.actions.active.firstRound.DealCardsRequestAction;
import it.polimi.ingsw.am40.server.network.RMI.RemoteInterfaceServer;

import java.rmi.RemoteException;

//FIXME Rename by adding request between cards and data in class name A+N
@JsonTypeName("CARDS_DEAL")
public class DealCardsData extends Data {

    //CONSTRUCTOR

    @JsonCreator
    public DealCardsData(@JsonProperty("nickname") String nickname) {
        super("CARDS_DEAL", nickname);
    }


    //PUBLIC METHODS


    public Action onServer(){
        return new DealCardsRequestAction(this.getNickname(), this.getGameID(), this.getPlayerID());
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
            stub.requestDealCardsActiveFirstRound(this);
        } catch (RemoteException e) {
            System.out.println("RMI call went wrong! message: " + e);
        }
    }
}
