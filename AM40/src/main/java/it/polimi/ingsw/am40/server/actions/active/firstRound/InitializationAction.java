package it.polimi.ingsw.am40.server.actions.active.firstRound;

import it.polimi.ingsw.am40.server.ActionAgent;
import it.polimi.ingsw.am40.server.actions.Action;
import it.polimi.ingsw.am40.server.actions.passive.setup.GameInitResultAction;
import it.polimi.ingsw.am40.server.model.*;
import it.polimi.ingsw.am40.server.network.virtual_view.VVServer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
        Game gameContext = (Game) agent;
        //New Game Creation
        //TODO card load is missing somewhere, now added in startGame method but game never get set the VVServer as listener
        // so we need to pass it as an attribute of this Action (RESOLVED, only discuss)
        gameContext.startGame(this.players);
        gameContext.addListener(this.gameListener, gameContext.getListeners());

        //Information fetch
        //Nicknames with implicit order
        ArrayList<String> nicknames = new ArrayList<>();
        for (Player p : gameContext.getPlayers()) {
            nicknames.add(p.getNickname());
        }

        //Boards Card
        Map<String, ArrayList<Integer>> commonboard = new HashMap<>();
        //ResourceCards
        ArrayList<Integer> resourceIDs = new ArrayList<>();
        //FIXME Possible CommonBoard static problem
        resourceIDs.add(CommonBoard.plateResourceCard[0].getCardID());
        resourceIDs.add(CommonBoard.plateResourceCard[1].getCardID());
        resourceIDs.add(gameContext.getCommonBoard().getResourceDeck().peekFirstCard());
        commonboard.put("RESOURCE", resourceIDs);
        //GoldenResourceCards
        ArrayList<Integer> goldenIDs = new ArrayList<>();
        goldenIDs.add(CommonBoard.plateGoldenResourceCard[0].getCardID());
        goldenIDs.add(CommonBoard.plateGoldenResourceCard[1].getCardID());
        goldenIDs.add(gameContext.getCommonBoard().getGoldenResourceDeck().peekFirstCard());
        commonboard.put("GOLDEN", goldenIDs);
        //AimCards
        ArrayList<Integer> aimIDs = new ArrayList<>();
        aimIDs.add(CommonBoard.plateAimCard[0].getCardID());
        aimIDs.add(CommonBoard.plateAimCard[1].getCardID());
        aimIDs.add(gameContext.getCommonBoard().getAimDeck().peekFirstCard());
        commonboard.put("AIM", aimIDs);

        //Client notification
        gameContext.notifyListeners(new GameInitResultAction(this.getNickname(), this.getGameID(), this.getPlayerID(), nicknames, commonboard), gameContext.getListeners());
    }
}
