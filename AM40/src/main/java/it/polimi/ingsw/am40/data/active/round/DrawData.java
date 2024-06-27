package it.polimi.ingsw.am40.data.active.round;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.client.network.RMI.RemoteInterfaceClient;
import it.polimi.ingsw.am40.server.actions.Action;
import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.server.actions.active.round.DrawAction;
import it.polimi.ingsw.am40.server.network.RMI.RemoteInterfaceServer;

import java.rmi.RemoteException;

@JsonTypeName("DRAW")
public class DrawData extends Data {
    //ATTRIBUTES
    /** Choice of which type of Card to draw from the CommonBoard: resource(0) or golden(1)*/
    private String choice;
    /** Possible selection on the CommonBoard: the two cards on plate(0), plate(1); or deck(2)*/
    private int selection;

    //CONSTRUCTOR

    @JsonCreator
    public DrawData(@JsonProperty("nickname") String nickname,
                    @JsonProperty("choice") String choice,
                    @JsonProperty("selection") int selection) {
        super("DRAW", nickname);
        this.choice = choice;
        this.selection = selection;
    }

    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }

    public int getSelection() {
        return selection;
    }

    public void setSelection(int selection) {
        this.selection = selection;
    }

    //PUBLIC METHODS

    public Action onServer(){
        //String parse to an Integer
        int source;
        if (this.choice.equalsIgnoreCase("res")){
            source = 0;
        } else {
            source = 1;
        }
        return new DrawAction(this.getNickname(), this.getGameID(), this.getPlayerID(), source, selection);
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
            stub.drawActiveRound(this);
        } catch (RemoteException e) {
            System.out.println("RMI call went wrong! message: " + e);
        }
    }
}
