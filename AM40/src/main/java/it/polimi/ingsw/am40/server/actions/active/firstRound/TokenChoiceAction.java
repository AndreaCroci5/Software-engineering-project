package it.polimi.ingsw.am40.server.actions.active.firstRound;

import it.polimi.ingsw.am40.server.ActionAgent;
import it.polimi.ingsw.am40.server.actions.Action;
import it.polimi.ingsw.am40.server.actions.passive.firstRound.PositiveTokenColorAction;
import it.polimi.ingsw.am40.server.model.Color;
import it.polimi.ingsw.am40.server.model.Game;

/**
 * This class represent the Action made by the Server in response of an input coming through the network made by
 * the Client that chooses the Color of his Token
 */
public class TokenChoiceAction extends Action {
    //ATTRIBUTES
    /**Color of the Token Chosen*/
    private final Color tokenColor;

    //CONSTRUCTOR
    /**
     * Constructor for TokenChoiceAction
     */
    public TokenChoiceAction(int gameID, int playerID, Color tokenColor){
        super("TOKEN_SELECTION", gameID, playerID);
        this.tokenColor = tokenColor;
    }

    /**
     * Override of doAction for Token Selection action
     * @param agent is the Game in which we perform the Action
     */
    @Override
    public void doAction(ActionAgent agent){
        Game gameContext = (Game) agent;

        //TokenSelection
            //Token color selection
            gameContext.chooseTokenColor(tokenColor);
            //Token data getter
            String color = tokenColor.toString();
            //Changes Notification
            gameContext.notifyListeners(new PositiveTokenColorAction(this.getGameID(), this.getPlayerID(), color),gameContext.getListeners());




    }
}
