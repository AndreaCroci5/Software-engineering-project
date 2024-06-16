package it.polimi.ingsw.am40.server.actions.passive.firstRound;

import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.data.passive.firstRound.TokenInfoData;
import it.polimi.ingsw.am40.server.ActionAgent;
import it.polimi.ingsw.am40.server.actions.Action;

import java.util.List;

public class TokenInfoAction extends Action {
    //ATTRIBUTES
    private final List<String> remainingColors;

    //CONSTRUCTOR
    /**
     * Constructor for TokenInfoAction
     */
    public TokenInfoAction(int gameID, int playerID, List<String> remainingColors){
        super("TOKEN_INFO", gameID, playerID);
        this.remainingColors = remainingColors;
    }

    /**
     * Override of doAction for AimCard info transport
     * @param agent is the Game in which we perform the Action
     */
    @Override
    public void doAction(ActionAgent agent){

    }

    public Data dataCreator() {
        return new TokenInfoData(nickname, this.remainingColors);
    }
}
