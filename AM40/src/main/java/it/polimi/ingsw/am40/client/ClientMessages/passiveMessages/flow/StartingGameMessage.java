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

    private ArrayList<String> nicknames;

    private Map<String,ArrayList<Integer>> commonBoard;

    public StartingGameMessage(String clientNickname, ArrayList<String> nicknames, Map<String,ArrayList<Integer>> commonBoard) {
        super("GAME_INIT_RESULT",clientNickname);
        this.nicknames = nicknames;
        this.commonBoard = commonBoard;
    }


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

        // FIXME
        if (context.getNickname().equals(this.nicknames.getFirst())) {
            context.getNetworkManager().send(new StartingCardRequestMessage(context.getNickname()));
        }
        else {
            context.setState(new PassiveStartingCardChoiceState());
        }
    }
}
