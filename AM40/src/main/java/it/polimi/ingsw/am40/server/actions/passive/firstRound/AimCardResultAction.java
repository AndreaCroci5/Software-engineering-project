package it.polimi.ingsw.am40.server.actions.passive.firstRound;

import it.polimi.ingsw.am40.server.actions.Action;

//TODO this class should carry also the information of the AimCardIDs of the next Player
public class AimCardResultAction extends Action {
    //ATTRIBUTES
    /** ID of the AimCard chosen by the Player*/
    int aimCardChosenID;


    //CONSTRUCTOR
    /**
     * Constructor for AimCard choice selection response
     */
    public AimCardResultAction(int gameID, int playerID, int aimCardChosenID){
        super("AIMCARD_SELECTED", gameID, playerID);
        this.aimCardChosenID = aimCardChosenID;
    }

}
