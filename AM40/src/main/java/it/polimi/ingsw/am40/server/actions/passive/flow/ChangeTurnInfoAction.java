package it.polimi.ingsw.am40.server.actions.passive.flow;

import it.polimi.ingsw.am40.server.actions.Action;

public class ChangeTurnInfoAction extends Action {
    //Attributes
    int nextActivePlayerModelIndex;

    //CONSTRUCTOR
    /**
     * Constructor for Change Turn Information response
     */
    public ChangeTurnInfoAction(int gameID, int playerID, int nextActivePlayerModelIndex){
        super("CHANGE_TURN", gameID, playerID);
        this.nextActivePlayerModelIndex = nextActivePlayerModelIndex;
    }
}
