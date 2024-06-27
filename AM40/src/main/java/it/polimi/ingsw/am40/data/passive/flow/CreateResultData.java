package it.polimi.ingsw.am40.data.passive.flow;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.client.ClientMessages.passiveMessages.flow.CreateResponseMessage;
import it.polimi.ingsw.am40.client.network.RMI.RemoteInterfaceClient;
import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.server.actions.Action;
import it.polimi.ingsw.am40.server.network.RMI.RemoteInterfaceServer;

import java.rmi.RemoteException;

@JsonTypeName("CREATE_RESULT")
public class CreateResultData extends Data {
    //ATTRIBUTES
    private int numOfPlayers;
    private int playersLogged;

    //Json constructor
    @JsonCreator
    public CreateResultData(@JsonProperty("nickname") String nickname,
                            @JsonProperty("numOfPlayers") int numOfPlayers,
                            @JsonProperty("playersLogged") int playersLogged) {
        super("CREATE_RESULT", nickname);
        this.numOfPlayers = numOfPlayers;
        this.playersLogged = playersLogged;
    }

    public int getNumOfPlayers() {
        return numOfPlayers;
    }

    public void setNumOfPlayers(int numOfPlayers) {
        this.numOfPlayers = numOfPlayers;
    }

    public int getPlayersLogged() {
        return playersLogged;
    }

    public void setPlayersLogged(int playersLogged) {
        this.playersLogged = playersLogged;
    }

    //PUBLIC METHODS


    public Action onServer(){
        return null;
    }

    public Message onClient() {
        return new CreateResponseMessage(this.getNickname(),this.numOfPlayers);
    }

    /**
     * Method which calls the right RMI interface method for each data (with override)
     * @param skeleton the client remote interface. Null if data active
     * @param stub the server remote interface. Null if data passive
     */
    @Override
    public void doRMI(RemoteInterfaceClient skeleton, RemoteInterfaceServer stub){
        try {
            skeleton.createResultPassiveFlow(this);
        } catch (RemoteException e) {
            System.out.println("RMI call went wrong! message: " + e);
        }
    }
}
