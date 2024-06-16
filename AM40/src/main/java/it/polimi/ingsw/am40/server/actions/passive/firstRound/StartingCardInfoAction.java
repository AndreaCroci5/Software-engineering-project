package it.polimi.ingsw.am40.server.actions.passive.firstRound;

import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.data.passive.firstRound.StartingCardInfoData;
import it.polimi.ingsw.am40.server.ActionAgent;
import it.polimi.ingsw.am40.server.actions.Action;

public class StartingCardInfoAction extends Action {
    //ATTRIBUTES
    private int startingCardID;

    //CONSTRUCTOR
    /**
     * Constructor for StartingCardInfoAction
     */
    public StartingCardInfoAction(int gameID, int playerID, int startingCardID){
        super("STARTING_CARD_INFO", gameID, playerID);
        this.startingCardID = startingCardID;
    }

    /**
     * Override of doAction for AimCard info transport
     * @param agent is the Game in which we perform the Action
     */
    @Override
    public void doAction(ActionAgent agent){

    }

    public Data dataCreator() {
        return new StartingCardInfoData(nickname, this.startingCardID);
    }
}
