package it.polimi.ingsw.am40.server.actions.passive.firstRound;

import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.data.passive.firstRound.PositiveTokenColorData;
import it.polimi.ingsw.am40.server.ActionAgent;
import it.polimi.ingsw.am40.server.actions.Action;

/**
 * This class serves as a mean to notify to the VirtualView which then will notify the client by using the Network interface
 * that the user has correctly selected his token color
 */
public class PositiveTokenColorAction extends Action {
    //COLOR CHOSEN
    private final String color;
    //CONSTRUCTOR
    /**
     * Constructor for PositiveTokenAction response
     */
    public PositiveTokenColorAction(int gameID, int playerID, String color){
        super("POSITIVE_TOKEN_COLOR", gameID, playerID);
        this.color = color;
    }

    /**
     * Override of doAction for the positive Token selection
     * @param agent is the Game in which we perform the Action
     */
    @Override
    public void doAction(ActionAgent agent){

    }

    public Data dataCreator() {
        return null;
    }
}
