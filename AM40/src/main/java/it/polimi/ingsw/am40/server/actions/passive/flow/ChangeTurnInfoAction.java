package it.polimi.ingsw.am40.server.actions.passive.flow;

import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.data.passive.flow.ChangeTurnInfoData;
import it.polimi.ingsw.am40.server.actions.Action;

/**
 * This class serves as a mean to notify to the VirtualView which then will notify the client by using the Network interface
 * that the turn has changed on the model
 */
public class ChangeTurnInfoAction extends Action {
    //Attributes
    /**
     * Index of the next Player that has the right to play
     */
    private final int nextActivePlayerModelIndex;

    //CONSTRUCTOR

    /**
     * Constructor for Change Turn Information response
     */
    public ChangeTurnInfoAction(int gameID, int playerID, int nextActivePlayerModelIndex) {
        super("CHANGE_TURN_INFO", gameID, playerID);
        this.nextActivePlayerModelIndex = nextActivePlayerModelIndex;
    }
}
    /*
    public Data dataCreator() {
        return new ChangeTurnInfoData(nicknameNext);
    }
}
*/