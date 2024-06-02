package it.polimi.ingsw.am40.server.actions.active.firstRound;

import it.polimi.ingsw.am40.server.ActionAgent;
import it.polimi.ingsw.am40.server.actions.Action;
import it.polimi.ingsw.am40.server.actions.passive.firstRound.StartingCardResultAction;
import it.polimi.ingsw.am40.server.model.CardFace;
import it.polimi.ingsw.am40.server.model.Game;

/**
 * This class represent the Action made by the Server in response of an input coming through the network made
 * by the Client that chooses the phase of his StartingCard
 */
public class StartingCardChoiceAction extends Action{
    //ATTRIBUTES
    /** CardFace chosen by the Client for the Starting Card*/
    private final CardFace cardFace;

    //CONSTRUCTOR
    /**
     * Constructor for StartingCardChoiceAction
     */
    public StartingCardChoiceAction(int gameID, int playerID, CardFace cardFace){
        super("STARTING_CARD_CHOICE", gameID, playerID);
        this.cardFace = cardFace;
    }

    /**
     * Override of doAction for the StartingCard face selection
     * @param agent is the Game in which we perform the Action
     */
    @Override
    public void doAction(ActionAgent agent){
        Game gameContext = (Game) agent;
        //Face Change
        gameContext.chooseStartingCardFace(this.cardFace);
        //Changes Notification
        gameContext.notifyListeners(new StartingCardResultAction(this.getGameID(), this.getPlayerID()), gameContext.getListeners());
    }
}
