package it.polimi.ingsw.am40.client.view;

import it.polimi.ingsw.am40.client.ConcreteContext;
import it.polimi.ingsw.am40.client.network.Client;
import it.polimi.ingsw.am40.client.smallModel.SmallCard;

import java.util.List;
import java.util.Map;

/**
 * The ViewManager interface forces the implementers to have methods to show something in the
 * user interface and to be able to manage incoming user inputs
 */
public interface ViewManager extends ConcreteContext {

    /**
     * Method to initialize the entire display protocol and to set initial parameters
     */
    void initView();

    void setClient(Client client);

    Client getClient();

    // initialisation
    void displayInitialisation();
    void displayCreate();
    void displayAllGameIds(List<Integer> gamesIDs);

    // First round
    void displayStartingCardInfo(int startingCardID);
    void askStartingFace();
    void showPassiveStartingCard(String nickname, int startingCardID);

    void displayPossibleTokens(List<String> tokens);
    void showPositiveTokenColor(String clientNickname, String token);

    void displayAimCardsToChoose(List<Integer> amiCardsID);
    // The active resul is simply his card in his hand so the method is below in the possible inputs
    void showPassiveAimCardResult(String nickname);

    void displayPlayerOrder(List<String> playerOrder);


    // Round
    void displayLastRoundMessage(String clientNickname);
    void displayEndGame(List<String> winners);


    // Possible inputs
    void showPossibleInputs();
    void displayCommonBoard(List<SmallCard> commonBoard);
    void displayScoreBoard(Map<String, Integer> scoreBoard);
    void displayMyHand(List<SmallCard> myHand);
    void displayPersonalAimCard(SmallCard card);
    void displayPersonalToken(String token);
    void displayPersonalGrid(List<SmallCard> myGrid);
    void displayOtherPlayersGrid();
    void displayChat();
    void displaySymbolLegend();

    // OTHER

    void showNotYouTurn();
    void displayWaitingForPlayers(int numOfActualPlayers, int NumOfFinalPlayers);
    void displayError();
}
