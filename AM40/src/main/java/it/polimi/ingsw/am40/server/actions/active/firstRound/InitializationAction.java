package it.polimi.ingsw.am40.server.actions.active.firstRound;

import it.polimi.ingsw.am40.server.ActionAgent;
import it.polimi.ingsw.am40.server.actions.Action;
import it.polimi.ingsw.am40.server.controller.GameManager;
import it.polimi.ingsw.am40.server.model.*;
import it.polimi.ingsw.am40.server.network.virtual_view.VVServer;

import java.util.ArrayList;
import java.util.Set;

public class InitializationAction extends Action {
    //ATTRIBUTES
    private ArrayList<String> players;

    private VVServer gameListener;

    //CONSTRUCTOR
    /**
     * Constructor for InitializationAction
     */
    public InitializationAction(String nickname, int gameID, int playerID, ArrayList<String> players, VVServer gameListener){
        super("GAME_INIT", nickname, gameID, playerID);
        this.players = players;
        this.gameListener = gameListener;
    }

    /**
     * Override of doAction for the Initialization of the Game Phase
     * @param agent is the Game in which we perform the Action
     */
    @Override
    public void doAction(ActionAgent agent){
        //TODO add the filter in onEvent() in GameManager for this Action, in order to put this(GameManager) as ActionAgent
        GameManager controller = (GameManager) agent;
        //New Game Creation
        //TODO card load is missing somewhere, now added in startGame method but game never get set the VVServer as listener
        // so we need to pass it as an attribute of this Action
        Game newGame = new Game();
        newGame.startGame(this.players);
        newGame.addListener(this.gameListener, newGame.getListeners());
        //Game registration to activeGames
        //controller.getActiveGames.put(this.getGameID(), newGame);
    }
}
