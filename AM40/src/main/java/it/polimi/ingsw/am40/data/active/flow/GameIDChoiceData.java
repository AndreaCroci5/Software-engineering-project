package it.polimi.ingsw.am40.data.active.flow;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import it.polimi.ingsw.am40.client.network.RMI.RemoteInterfaceClient;
import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.server.actions.Action;
import it.polimi.ingsw.am40.server.actions.active.setup.GameIDChoiceAction;
import it.polimi.ingsw.am40.server.network.RMI.RemoteInterfaceServer;

import java.rmi.RemoteException;

/**
 * This class contains the information that will be carried by being sent on the network.
 * This class is also the bridge from GameIDChoiceMessage on the Client to the GameIDChoiceAction on Server
 */
@JsonTypeName("GAME_ID_CHOICE")
public class GameIDChoiceData extends Data {

    /**
     * ID of the party/game chosen to be joined by the Client
     */
    private int gameIDChoice;


    public int getGameIDChoice() {
        return gameIDChoice;
    }

    public void setGameIDChoice(int gameIDChoice) {
        this.gameIDChoice = gameIDChoice;
    }

    @JsonCreator
    public GameIDChoiceData(@JsonProperty("nickname") String nickname,
                            @JsonProperty("gameIDChoice") int gameIDChoice) {
        super("GAME_ID_CHOICE", nickname);
        this.gameIDChoice = gameIDChoice;
    }

    /**
     * Override of the method onServer that returns the related GameIDChoiceAction on the Server
     * @return a GameIDChoiceAction
     */
    @Override
    public Action onServer(){
        return new GameIDChoiceAction(this.getNickname(), this.getGameID(), this.getPlayerID(), this.gameIDChoice);
    }

    /**
     * Method which calls the right RMI interface method for each data (with override)
     * @param skeleton the client remote interface. Null if data active
     * @param stub the server remote interface. Null if data passive
     */
    @Override
    public synchronized void doRMI(RemoteInterfaceClient skeleton, RemoteInterfaceServer stub){
        try {
            stub.choiceGameIDActiveFlow(this);
        } catch (RemoteException e) {
            System.out.println("RMI call went wrong! message: " + e);
        }
    }
}
