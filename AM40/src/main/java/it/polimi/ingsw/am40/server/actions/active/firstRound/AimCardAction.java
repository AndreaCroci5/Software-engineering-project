package it.polimi.ingsw.am40.server.actions.active.firstRound;

import it.polimi.ingsw.am40.server.ActionAgent;
import it.polimi.ingsw.am40.server.actions.Action;
import it.polimi.ingsw.am40.server.actions.passive.firstRound.AimCardResultAction;
import it.polimi.ingsw.am40.server.model.Game;

//TODO put in the end the player order decision and add passive actions and Synchronize with the Map in the Server
//FIXME AIM CARD managing
public class AimCardAction extends Action {
    //ATTRIBUTES
    /** This attribute represents one the AimCard chosen by the Client: (0) the first, (1) the second*/
    int choice;

    //CONSTRUCTOR
    /**
     * Constructor for AimCardAction
     */
    public AimCardAction(int gameID, int playerID, int choice){
        super("AIMCARD_SELECTION", gameID, playerID);
        this.choice = choice;
    }

    /**
     * Override of doAction for AimCard Selection
     * @param agent is the Game in which we perform the Action
     */
    @Override
    public void doAction(ActionAgent agent){
        Game gameContext = (Game) agent;

        //AimCard selection
        gameContext.aimCardSelection(this.choice);
        //Data source
        int playerIndex = gameContext.getIndexOfPlayingPlayer();
        int aimCardChosenID = gameContext.getPlayers().get(playerIndex).getPrivateAim().getCardID();
        //Notification
        gameContext.notifyListeners(new AimCardResultAction(this.getGameID(), this.getPlayerID(), aimCardChosenID), gameContext.getListeners());
    }
}
