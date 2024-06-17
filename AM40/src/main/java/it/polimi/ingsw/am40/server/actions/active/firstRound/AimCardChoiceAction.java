package it.polimi.ingsw.am40.server.actions.active.firstRound;

import it.polimi.ingsw.am40.server.ActionAgent;
import it.polimi.ingsw.am40.server.actions.Action;
import it.polimi.ingsw.am40.server.actions.passive.firstRound.AimCardResultAction;
import it.polimi.ingsw.am40.server.model.Game;


/**
 * This class represent the Action made by the Server in response of an input by the network from the Client that chooses
 * his personal AimCard
 */
public class AimCardChoiceAction extends Action {
    //ATTRIBUTES
    /** This attribute represents one the AimCard chosen by the Client: (0) the first, (1) the second*/
    private final int choice;

    //CONSTRUCTOR
    /**
     * Constructor for AimCardChoiceAction
     */
    public AimCardChoiceAction(String nickname, int gameID, int playerID, int choice){
        super("AIM_CARD_SELECTION", nickname, gameID, playerID);
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
        gameContext.notifyListeners(new AimCardResultAction(this.getNickname(), this.getGameID(), this.getPlayerID(), aimCardChosenID), gameContext.getListeners());
    }
}
