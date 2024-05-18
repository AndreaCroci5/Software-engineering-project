package it.polimi.ingsw.am40.server.actions.passive.flow;

import it.polimi.ingsw.am40.server.actions.Action;

public class EndTokenPhaseAction extends Action {
    //ATTRIBUTES
    int nextActivePlayerModelIndex;

    //CONSTRUCTOR
    /**
     * Constructor for Token selection phase ending Information response
     */
    public EndTokenPhaseAction(int gameID, int playerID, int nextActivePlayerModelIndex){
        super("END_TOKEN_PHASE", gameID, playerID);
        this.nextActivePlayerModelIndex = nextActivePlayerModelIndex;
    }
}
