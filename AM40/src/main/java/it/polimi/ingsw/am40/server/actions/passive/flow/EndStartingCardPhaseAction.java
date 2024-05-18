package it.polimi.ingsw.am40.server.actions.passive.flow;

import it.polimi.ingsw.am40.server.actions.Action;

public class EndStartingCardPhaseAction extends Action {
    //ATTRIBUTES
    int nextActivePlayerModelIndex;

    //CONSTRUCTOR
    /**
     * Constructor for Starting Card face selection phase ending Information response
     */
    public EndStartingCardPhaseAction(int gameID, int playerID, int nextActivePlayerModelIndex){
        super("END_STARTING_CARD_PHASE", gameID, playerID);
        this.nextActivePlayerModelIndex = nextActivePlayerModelIndex;
    }
}
