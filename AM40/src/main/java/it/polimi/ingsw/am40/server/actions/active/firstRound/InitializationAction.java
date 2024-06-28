package it.polimi.ingsw.am40.server.actions.active.firstRound;

import it.polimi.ingsw.am40.server.ActionAgent;
import it.polimi.ingsw.am40.server.actions.Action;
import it.polimi.ingsw.am40.server.actions.passive.setup.GameInitResultAction;
import it.polimi.ingsw.am40.server.model.*;
import it.polimi.ingsw.am40.server.network.virtual_view.VVServer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * This class represents the Action made by the Server when a Party reaches the desired amount of players selected by
 * the Client that created the Party
 */
public class InitializationAction extends Action {
    //ATTRIBUTES
    /**
     * Reference to an ArrayList containing the names of the Players according to the first round order
     */
    private ArrayList<String> players;

    /**
     * Reference to the VVServer main server class responsible for listening to the model (Game).
     * This attribute has to be set when the Game is initialized
     */
    private VVServer gameListener;

    //CONSTRUCTOR
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
