package it.polimi.ingsw.am40.client.view;

import it.polimi.ingsw.am40.client.ConcreteContext;
import it.polimi.ingsw.am40.client.network.Client;
import it.polimi.ingsw.am40.client.smallModel.SmallCard;
import it.polimi.ingsw.am40.server.model.CardElements;

import java.util.ArrayList;
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

    /**
     * Setter for the client
     * @param client sets the client
     */
    void setClient(Client client);

    /**
     * Getter for the client
     * @return a reference to the client
     */
    Client getClient();

    /**
     * Asks for the username
     */
    void displayInitialisation();

    /**
     * Asks if the client want to join or create a game
     */
    void displaySetUp();

    /**
     * Asks information about the creation
     */
    void displayCreate();

    /**
     * Shows all gameIDs of the available games
     * @param gamesIDs are all the available games
     */
    void displayAllGameIds(Map<Integer, ArrayList<Integer>> gamesIDs);


    /**
     * Shows the information of the starting card
     * @param startingCardID is the ID of the starting card
     */
    void displayStartingCardInfo(int startingCardID);

    /**
     * Asks on which face the client wants to place the starting card
     */
    void askStartingFace();

    /**
     * Show the result of the starting card placement at all the passive clients
     * @param nickname is the name of the active player
     * @param startingCardID is the ID of the starting card
     * @param cardFace is the face on which the client place his starting card
     */
    void showPassiveStartingCard(String nickname, int startingCardID, String cardFace);

    /**
     * Displays the possible tokens that can be chosen by the client
     * @param tokens the list of possible tokens
     */
    void displayPossibleTokens(List<String> tokens);

    /**
     * Displays the positive token color for a specific client
     * @param clientNickname the nickname of the client
     * @param token the color of the token chosen by the client
     */
    void showPositiveTokenColor(String clientNickname, String token);

    /**
     * Displays the aim cards for the user to choose from
     * @param aimCardsID the IDs of the aim cards to be displayed
     */
    void displayAimCardsToChoose(List<Integer> aimCardsID);

    /**
     * Shows the passive aim card result to the passive client
     * @param nickname the nickname of the active client
     */
    void showPassiveAimCardResult(String nickname);

    /**
     * Displays the order of the players
     * @param playerOrder the list of player names in the order of their turn
     */
    void displayPlayerOrder(List<String> playerOrder);


    /**
     * Display the placing card choice to the user tha has to choose the card to place
     * @param myHand  the list of small cards in the user's hand
     * @param myGrid  the list of small cards in the user's grid
     */
    void displayPlacingCardChoice(List<SmallCard> myHand, List<SmallCard> myGrid);

    /**
     * Displays the last round message to the client
     * @param clientNickname the nickname of the active player
     */
    void displayLastRoundMessage(String clientNickname);

    /**
     * Displays the end game banner and the names of the winners
     * @param winners the list of names of the winners
     */
    void displayEndGame(List<String> winners);


    /**
     * Displays the possible inputs available to the user
     */
    void showPossibleInputs();

    /**
     * Displays the common board, which consists of a list of small cards
     * @param commonBoard the list of small cards that make up the common board
     */
    void displayCommonBoard(List<SmallCard> commonBoard);

    /**
     * Displays the score board to the user
     * @param scoreBoard the map containing player names as keys and scores as values
     */
    void displayScoreBoard(Map<String, Integer> scoreBoard);

    /**
     * Displays the card in my hand
     * @param myHand the list of small cards in the player's hand
     */
    void displayMyHand(List<SmallCard> myHand);

    /**
     * Displays the personal aim card to the user
     * @param card the small card representing the personal aim card
     */
    void displayPersonalAimCard(SmallCard card);

    /**
     * Displays the personal token of the client
     * @param token the personal token of the client to be displayed
     */
    void displayPersonalToken(String token);

    /**
     * Displays the list of SmallCards representing the personal grid of the player
     * @param myGrid the list of SmallCards representing the personal grid of the player
     */
    void displayPersonalGrid(List<SmallCard> myGrid);

    /**
     * Displays the grid of other players to the user
     * @param othersPlayersGrid a map containing the grid of each other player,
     *                          where the key is the player's name and the value is a list of SmallCards representing their grid.
     */
    void displayOtherPlayersGrid(Map<String,ArrayList<SmallCard>> othersPlayersGrid);

    /**
     * Displays the chat in the user interface
     */
    void displayChat();

    /**
     * This method displays the legend of the symbols used in the game UI
     */
    void displaySymbolLegend();

    /**
     * Displays a message indicating that it is not the turn of the current player
     */
    void showNotYouTurn();

    /**
     * Displays the waiting message for players to join the game
     * @param numOfActualPlayers The number of players currently joined
     * @param NumOfFinalPlayers  The total number of players needed to start the game
     */
    void displayWaitingForPlayers(int numOfActualPlayers, int NumOfFinalPlayers);
    void displayError();

    /**
     * Displays a message to the client indicating that there are no active parties to join, and they need to create a new game
     */
    void showNoActiveParties();

    /**
     * Displays a message to indicate that joining a game with the given game ID has failed
     * This method is called when an error occurs during the joining process
     */
    void showFailedGameID();

    /**
     * Displays the starting game screen with player nicknames and the common board
     * @param nicknames    the list of player nicknames
     * @param commonBoard  the list of small cards representing the common board
     */
    void displayStartingGame(ArrayList<String> nicknames,List<SmallCard> commonBoard);

    /**
     * Displays that another player is choosing his aim card
     * @param clientNickname the nickname of the active client
     */
    void showPassiveAimState(String clientNickname);

    /**
     * Displays that another player is placing the starting card
     * @param clientNickname the nickname of the client active client
     */
    void showPassiveStartingCardState(String clientNickname);

    /**
     * Shows that another client is choosing a token
     * @param clientNickname the nickname of the active client
     */
    void showPassiveTokenState(String clientNickname);

    /**
     * Displays the result of placing the starting card on the UI
     * @param cardID The ID of the starting card.
     * @param cardFace The face on which the client placed the starting card.
     */
    void displayStartingCardResult(int cardID, String cardFace);

    /**
     * Displays the 3 cards in the hand of the client
     * @param myHand the list of small cards in the player's hand
     */
    void displayDealCardState(List<SmallCard> myHand);

    /**
     * Displays the positive aim card choice to the user
     */
    void displayPositiveAimCardChoice();

    /**
     * Display that another player is placing
     * @param clientNickname is the name of the active client
     */
    void displayPassivePlacingState(String clientNickname);

    /**
     * Ask the card that the client want to cover on his grid
     * @param myGrid is the client's grid
     */
    void displayPlacingCardToCoverChoice(List<SmallCard> myGrid);

    /**
     * Asks the user which face he wants to place the card on
     */
    void displayPlacingFaceChoice();

    /**
     * Asks the user which corner of the card in his board want to cover
     */
    void displayPlacingCornerCover();

    /**
     * Show that the placing went good
     */
    void displayPositivePlacing();

    /**
     * Show that another player place a card
     * @param clientNickname is the name of the active client
     */
    void displayPassivePlacingResult(String clientNickname);

    /**
     * Asks where the user want to draw from
     * @param commonBoard is the commonBoard of the game
     */
    void displayDrawChoice(List<SmallCard> commonBoard);

    /**
     * It shows that all went good with the draw
     */
    void displayPositiveDraw();

    /**
     * It shows the elements counter in the user private board
     * @param elementsCounter is the counter of the elements in the client private board
     */
    void diplayElementsCounter(Map<CardElements, Integer> elementsCounter);

    /**
     * Show that something went wrong with the draw
     */
    void displayNegativeDraw();

    /**
     * Show that something went wrong with the placing
     */
    void displayNegativePlacing();

    /**
     * Show that a player draw a card
     * @param clientNickname is the name of the active client
     */
    void displayPassiveDrawResult(String clientNickname);

    /**
     * Show that the game has been interrupted
     */
    void displayInterruptedGame();
}
