package it.polimi.ingsw.am40.client.ClientMessages.passiveMessages.flow;

import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.client.ClientMessages.activeMessages.firstRound.StartingCardRequestMessage;
import it.polimi.ingsw.am40.client.network.Client;
import it.polimi.ingsw.am40.client.network.States.passiveStates.PassiveStartingCardChoiceState;
import it.polimi.ingsw.am40.client.smallModel.SmallCard;
import it.polimi.ingsw.am40.client.smallModel.SmallCardLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StartingGameMessage extends Message {

    /**
     * Names of all the players in the game
     */
    private ArrayList<String> nicknames;

    /**
     * CommonBoard of the game
     */
    private Map<String,ArrayList<Integer>> commonBoard;

    /**
     * This message is sent from the server and it is used to start the game
     * @param clientNickname is the name of the active client
     * @param nicknames are the names of all the players in the game
     * @param commonBoard is common board of the game
     */
    public StartingGameMessage(String clientNickname, ArrayList<String> nicknames, Map<String,ArrayList<Integer>> commonBoard) {
        super("GAME_INIT_RESULT",clientNickname);
        this.nicknames = nicknames;
        this.commonBoard = commonBoard;
    }


    /**
     * It sets the common board and scoreboard in the small model
     * It sets the first player to start the first phase of the game
     * @param context is the context of the client with his view and his network communication protocol
     */
    public void process(Client context) {

        List<SmallCard> board = new ArrayList<>();
        board.add(SmallCardLoader.findCardById(commonBoard.get("RESOURCE").get(0)));
        board.add(SmallCardLoader.findCardById(commonBoard.get("RESOURCE").get(1)));
        board.add(SmallCardLoader.findCardById(commonBoard.get("RESOURCE").get(2)));
        board.add(SmallCardLoader.findCardById(commonBoard.get("GOLDEN").get(0)));
        board.add(SmallCardLoader.findCardById(commonBoard.get("GOLDEN").get(1)));
        board.add(SmallCardLoader.findCardById(commonBoard.get("GOLDEN").get(2)));
        board.add(SmallCardLoader.findCardById(commonBoard.get("AIM").get(0)));
        board.add(SmallCardLoader.findCardById(commonBoard.get("AIM").get(1)));
        board.add(SmallCardLoader.findCardById(commonBoard.get("AIM").get(2)));


        context.getSmallModel().setCommonBoard(board);
        context.getSmallModel().setScoreBoard(new HashMap<>());
        for (String s : nicknames) {
            context.getSmallModel().getScoreBoard().put(s, 0);
        }
        context.getViewManager().displayStartingGame(this.nicknames,context.getSmallModel().getCommonBoard());

        if (context.getNickname().equals(this.nicknames.getFirst())) {
            context.getNetworkManager().send(new StartingCardRequestMessage(context.getNickname()));
        }
        else {
            context.setState(new PassiveStartingCardChoiceState());
        }
    }
}
