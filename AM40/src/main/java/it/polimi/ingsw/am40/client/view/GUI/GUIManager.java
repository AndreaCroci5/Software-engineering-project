package it.polimi.ingsw.am40.client.view.GUI;

import it.polimi.ingsw.am40.client.network.Client;
import it.polimi.ingsw.am40.client.smallModel.SmallCard;
import it.polimi.ingsw.am40.client.view.GUI.FXGUI.GUIApplication;
import it.polimi.ingsw.am40.client.view.ViewManager;
import it.polimi.ingsw.am40.server.model.CardElements;
import javafx.application.Platform;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The GUIManager is the class which make possible to display the information
 * and to manage the inputs with JavaFX
 */
public class GUIManager implements ViewManager {

    ///ATTRIBUTES
    /**
     * Reference to the Application that starts the software in GUI
     */
    it.polimi.ingsw.am40.client.view.GUI.FXGUI.GUIApplication GUIApplication;

    /**
     * Reference to the Client information
     */
    private Client client;

    //CONSTRUCTOR

    /**
     * Constructor of the GUIManager
     * @param client is the reference to the client
     */
    public GUIManager(Client client) {
        this.client = client;
    }


    /**
     * Method to initialize the entire display protocol and to set initial parameters
     */
    @Override
    public void initView() {
        try {
            this.GUIApplication = new GUIApplication();
            it.polimi.ingsw.am40.client.view.GUI.FXGUI.GUIApplication.client = this.client;
            this.GUIApplication.startGUI();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println(e.getCause());
            System.out.println(e.getLocalizedMessage());
            System.out.println(e.getStackTrace());
        }

    }

    @Override
    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public Client getClient() {
        return this.client;
    }


    //GUI METHODS

    @Override
    public void displayInitialisation() {

    }

    @Override
    public void displaySetUp() {

    }

    @Override
    public void displayCreate() {

    }

    /**
     * This method is called after a Client sends a join request and receives a Server response.
     * Then proceeds to show the Parties available
     * @param gamesIDs is the list of the GameIDs available
     */
    @Override
    public void displayAllGameIds(Map<Integer, ArrayList<Integer>> gamesIDs) {
        String gameIDsNotification = "Here are all the possible games you can choose:\n";
        for (Integer gameID : gamesIDs.keySet()) {
            gameIDsNotification += "Game ID: " + gameID + "\n" ;
        }
        final String finalGameIDs = gameIDsNotification;

        Platform.runLater( () -> it.polimi.ingsw.am40.client.view.GUI.FXGUI.GUIApplication.controller.displayAllGameIDs(finalGameIDs));
    }

    /**
     * This method is called after a Client sends a StartingCard request and receives a Server response.
     * @param startingCardID is the ID of the StartingCard assigned
     */
    @Override
    public void displayStartingCardInfo(int startingCardID) {
        final int cardID = startingCardID;
        Platform.runLater( () -> it.polimi.ingsw.am40.client.view.GUI.FXGUI.GUIApplication.controller.startingCardInfo(cardID));
    }

    @Override
    public void askStartingFace() {

    }

    @Override
    public void showPassiveStartingCard(String nickname, int startingCardID, String cardFace) {
        final String currentPlayer = nickname;
        Platform.runLater( () -> it.polimi.ingsw.am40.client.view.GUI.FXGUI.GUIApplication.controller.showPassiveStartingCard(currentPlayer));
    }

    /**
     * This method is called after a Client sends a Token request and receives a Server response.
     * @param tokens is the list of the tokens available
     */
    @Override
    public void displayPossibleTokens(List<String> tokens) {
        final List<String> colors = tokens;
        Platform.runLater( () -> it.polimi.ingsw.am40.client.view.GUI.FXGUI.GUIApplication.controller.tokenInfo(colors));

    }

    /**
     * This method is called after a Client receives a PositiveTokenColor response
     * @param clientNickname the nickname of the client
     * @param token the color of the token chosen by the client
     */
    @Override
    public void showPositiveTokenColor(String clientNickname, String token) {
        final String nickname = clientNickname;
        final String color = token;

        Platform.runLater( () -> it.polimi.ingsw.am40.client.view.GUI.FXGUI.GUIApplication.controller.acceptedToken(nickname, color));
    }

    /**
     * This method is called after a Client sends an AimCardRequest and receives a Server response with the information on the
     * two AimCards
     * @param aimCardsID the IDs of the aim cards to be displayed
     */
    @Override
    public void displayAimCardsToChoose(List<Integer> aimCardsID) {
        final List<Integer> aimIDs = aimCardsID;
        Platform.runLater( () -> it.polimi.ingsw.am40.client.view.GUI.FXGUI.GUIApplication.controller.aimCardsInfo(aimIDs));

    }

    @Override
    public void showPassiveAimCardResult(String nickname) {

    }

    /**
     * This method is called after a Client sends an AimCardRequest and receives the Server response
     * @param playerOrder the list of player names in the order of their turn
     */
    @Override
    public void displayPlayerOrder(List<String> playerOrder) {
        final List<String> namesInOrder = playerOrder;
        Platform.runLater( () -> it.polimi.ingsw.am40.client.view.GUI.FXGUI.GUIApplication.controller.playersOrder(namesInOrder));
    }

    /**
     * This method is called when a Player has the right in his turn to place a card
     * @param myHand  the list of small cards in the user's hand
     * @param myGrid  the list of small cards in the user's grid
     */
    @Override
    public void displayPlacingCardChoice(List<SmallCard> myHand, List<SmallCard> myGrid) {
        final ArrayList<Integer> handDeck = new ArrayList<>();
        for (SmallCard card : myHand) {
            handDeck.add(card.getCardID());
        }
        final List<SmallCard> cardGrid = myGrid;

        Platform.runLater( () -> it.polimi.ingsw.am40.client.view.GUI.FXGUI.GUIApplication.controller.placing(handDeck, cardGrid));
    }

    /**
     * This method is called when a Player triggers the last rounds condition
     * @param clientNickname the nickname of the active player
     */
    @Override
    public void displayLastRoundMessage(String clientNickname) {
        Platform.runLater( () -> it.polimi.ingsw.am40.client.view.GUI.FXGUI.GUIApplication.controller.lastRounds(clientNickname));
    }

    /**
     * This method is called when the Server reaches the end of last rounds condition and calculates the winner
     * @param winners the list of names of the winners
     */
    @Override
    public void displayEndGame(List<String> winners) {
        Platform.runLater( () -> {
            try {
                it.polimi.ingsw.am40.client.view.GUI.FXGUI.GUIApplication.controller.endGame(winners);
            } catch (IOException e) {
                System.out.println("The file loading went wrong");
            }
        });
    }

    @Override
    public void showPossibleInputs() {

    }

    /**
     * This method is used when the CommonBoard gets a change
     * @param commonBoard is the CommonBoard situation
     */
    @Override
    public void displayCommonBoard(List<SmallCard> commonBoard) {
        final List<Integer> resource = new ArrayList<>();
        final List<Integer> golden = new ArrayList<>();
        final List<Integer> aim = new ArrayList<>();

        int slider;
        for (int i = 0; i<3; i++) {
            resource.add(commonBoard.get(i).getCardID());
        }
        slider = resource.removeLast();
        resource.addFirst(slider);
        for (int i = 3; i<6; i++) {
            golden.add(commonBoard.get(i).getCardID());
        }
        slider = golden.removeLast();
        golden.addFirst(slider);
        for (int i = 6; i<9; i++) {
            aim.add(commonBoard.get(i).getCardID());
        }
        slider = aim.removeLast();
        aim.addFirst(slider);

        Platform.runLater( () -> {
            try {
                it.polimi.ingsw.am40.client.view.GUI.FXGUI.GUIApplication.controller.updateCommonBoard(resource, golden, aim);
            } catch (Exception e) {
                System.out.println("Error in loading the fxml file");
                System.out.println(e.getMessage());
                System.out.println(e.getLocalizedMessage());
                System.out.println(e.getCause());
                System.out.println(e.getStackTrace());
            }
        });

    }

    @Override
    public void displayScoreBoard(Map<String, Integer> scoreBoard) {

    }

    @Override
    public void displayMyHand(List<SmallCard> myHand) {

    }

    @Override
    public void displayPersonalAimCard(SmallCard card) {

    }

    @Override
    public void displayPersonalToken(String token) {

    }

    @Override
    public void displayPersonalGrid(List<SmallCard> myGrid) {

    }

    @Override
    public void displayOtherPlayersGrid(Map<String, ArrayList<SmallCard>> othersPlayersGrid) {

    }

    @Override
    public void displayChat() {

    }

    @Override
    public void displaySymbolLegend() {

    }

    @Override
    public void showNotYouTurn() {

    }

    /**
     * This method is called when a Player joins the party that the Client is in, or the Client creates a new a party
     * @param numOfActualPlayers is the current number of players
     * @param NumOfFinalPlayers is the required number of players to start the game
     */
    @Override
    public void displayWaitingForPlayers(int numOfActualPlayers, int NumOfFinalPlayers) {
        final int playersRequired = NumOfFinalPlayers - numOfActualPlayers;
        Platform.runLater( () -> it.polimi.ingsw.am40.client.view.GUI.FXGUI.GUIApplication.controller.waitingForPlayersEvent(playersRequired));
    }

    @Override
    public void displayError() {

    }

    /**
     * This method is called when a Server notifies a Client that there are no parties available after a join request
     */
    @Override
    public void showNoActiveParties() {
        Platform.runLater( () -> it.polimi.ingsw.am40.client.view.GUI.FXGUI.GUIApplication.controller.noActiveParties());
    }

    /**
     * This method is called when a Server notifies a Client that the GameID chosen through GameIDChoice isn't available
     */
    @Override
    public void showFailedGameID() {
        Platform.runLater( () -> it.polimi.ingsw.am40.client.view.GUI.FXGUI.GUIApplication.controller.failedGameID());
    }

    /**
     * This method is called when the party reaches the required size and through the net arrives at the Client a GameInitData
     * that triggers a StartingGameMessage
     * @param nicknames are the nicknames chosen by the Players
     * @param commonBoard is the situation of the CommonBoard
     */
    @Override
    public void displayStartingGame(ArrayList<String> nicknames,List<SmallCard> commonBoard) {
        final ArrayList<String> finalNicknames = nicknames;
        final List<Integer> resource = new ArrayList<>();
        final List<Integer> golden = new ArrayList<>();
        final List<Integer> aim = new ArrayList<>();


        int slider;
        for (int i = 0; i<3; i++) {
            resource.add(commonBoard.get(i).getCardID());
        }
        slider = resource.removeLast();
        resource.addFirst(slider);
        for (int i = 3; i<6; i++) {
            golden.add(commonBoard.get(i).getCardID());
        }
        slider = golden.removeLast();
        golden.addFirst(slider);
        for (int i = 6; i<9; i++) {
            aim.add(commonBoard.get(i).getCardID());
        }
        slider = aim.removeLast();
        aim.addFirst(slider);


        Platform.runLater( () -> {
            try {
                it.polimi.ingsw.am40.client.view.GUI.FXGUI.GUIApplication.controller.startingGame(finalNicknames, resource, golden, aim);
            } catch (Exception e) {
                System.out.println("Error in loading the fxml file");
                System.out.println(e.getMessage());
                System.out.println(e.getLocalizedMessage());
                System.out.println(e.getCause());
                System.out.println(e.getStackTrace());
            }
        });
    }

    /**
     * This method is called when another player is choosing his AimCard
     * @param clientNickname the nickname of the active client
     */
    @Override
    public void showPassiveAimState(String clientNickname) {
        Platform.runLater( () -> it.polimi.ingsw.am40.client.view.GUI.FXGUI.GUIApplication.controller.showPassiveAimCard(clientNickname));

    }

    /**
     * This method is called when another player is choosing his StartingCard
     * @param clientNickname the nickname of the active client
     */
    @Override
    public void showPassiveStartingCardState(String clientNickname) {
        Platform.runLater( () -> it.polimi.ingsw.am40.client.view.GUI.FXGUI.GUIApplication.controller.showPassiveStartingCard(clientNickname));

    }

    /**
     * This method is called when another player is choosing his Token
     * @param clientNickname the nickname of the active client
     */
    @Override
    public void showPassiveTokenState(String clientNickname) {
        Platform.runLater( () -> it.polimi.ingsw.am40.client.view.GUI.FXGUI.GUIApplication.controller.showPassiveToken(clientNickname));
    }

    @Override
    public void displayStartingCardResult(int cardID, String cardFace) {

    }

    /**
     * This method is called after the server responds to the Client DealCards request
     * @param myHand the list of small cards in the player's hand
     */
    @Override
    public void displayDealCardState(List<SmallCard> myHand) {
        final ArrayList<Integer> handDeck = new ArrayList<>();
        for (SmallCard card : myHand) {
            handDeck.add(card.getCardID());
        }


        Platform.runLater( () -> {
            try {
                it.polimi.ingsw.am40.client.view.GUI.FXGUI.GUIApplication.controller.dealCards(handDeck);
            } catch (Exception e) {
                System.out.println("Error in loading the fxml file");
                System.out.println(e.getMessage());
                System.out.println(e.getLocalizedMessage());
                System.out.println(e.getCause());
                System.out.println(e.getStackTrace());
            }
        });


    }

    @Override
    public void displayPositiveAimCardChoice() {

    }

    /**
     * This method is called when another player is Placing his card in his turn
     * @param clientNickname the nickname of the active client
     */
    @Override
    public void displayPassivePlacingState(String clientNickname) {
        Platform.runLater( () -> it.polimi.ingsw.am40.client.view.GUI.FXGUI.GUIApplication.controller.passivePlacingState(clientNickname));
    }

    @Override
    public void displayPlacingCardToCoverChoice(List<SmallCard> myGrid) {

    }

    @Override
    public void displayPlacingFaceChoice() {

    }

    @Override
    public void displayPlacingCornerCover() {

    }

    /**
     * This method is called when a placing has received a PositivePlacing feedback from the Server
     */
    @Override
    public void displayPositivePlacing() {
        Platform.runLater( () -> it.polimi.ingsw.am40.client.view.GUI.FXGUI.GUIApplication.controller.positivePlacing());

    }

    /**
     * This method is called when another player has placed a Card
     * @param clientNickname the nickname of the active client
     */
    @Override
    public void displayPassivePlacingResult(String clientNickname) {
        Platform.runLater( () -> it.polimi.ingsw.am40.client.view.GUI.FXGUI.GUIApplication.controller.passivePlacingResult(clientNickname));
    }

    @Override
    public void displayDrawChoice(List<SmallCard> commonBoard) {

    }

    /**
     * This method is called when a draw has received a PositiveDraw feedback from the Server
     */
    @Override
    public void displayPositiveDraw() {
        List<SmallCard> commonBoard = this.client.getSmallModel().getCommonBoard();
        final List<Integer> resource = new ArrayList<>();
        final List<Integer> golden = new ArrayList<>();
        final List<Integer> aim = new ArrayList<>();

        int slider;
        for (int i = 0; i<3; i++) {
            resource.add(commonBoard.get(i).getCardID());
        }
        slider = resource.removeLast();
        resource.addFirst(slider);
        for (int i = 3; i<6; i++) {
            golden.add(commonBoard.get(i).getCardID());
        }
        slider = golden.removeLast();
        golden.addFirst(slider);
        for (int i = 6; i<9; i++) {
            aim.add(commonBoard.get(i).getCardID());
        }
        slider = aim.removeLast();
        aim.addFirst(slider);


        Platform.runLater( () -> it.polimi.ingsw.am40.client.view.GUI.FXGUI.GUIApplication.controller.positiveDraw(resource, golden, aim));
    }

    @Override
    public void diplayElementsCounter(Map<CardElements, Integer> elementsCounter) {

    }

    /**
     * This method is called when a draw has received a NegativeDraw feedback from the Server
     */
    @Override
    public void displayNegativeDraw() {
        Platform.runLater( () -> it.polimi.ingsw.am40.client.view.GUI.FXGUI.GUIApplication.controller.negativeDraw());
    }

    /**
     * This method is called when a draw has received a NegativePlacing feedback from the Server
     */
    @Override
    public void displayNegativePlacing() {
        Platform.runLater( () -> it.polimi.ingsw.am40.client.view.GUI.FXGUI.GUIApplication.controller.negativePlacing());
    }

    /**
     * This method is called when another player has drawn a Card
     * @param clientNickname the nickname of the active client
     */
    @Override
    public void displayPassiveDrawResult(String clientNickname) {
        List<SmallCard> commonBoard = this.client.getSmallModel().getCommonBoard();
        final List<Integer> resource = new ArrayList<>();
        final List<Integer> golden = new ArrayList<>();
        final List<Integer> aim = new ArrayList<>();


        int slider;
        for (int i = 0; i<3; i++) {
            resource.add(commonBoard.get(i).getCardID());
        }
        slider = resource.removeLast();
        resource.addFirst(slider);
        for (int i = 3; i<6; i++) {
            golden.add(commonBoard.get(i).getCardID());
        }
        slider = golden.removeLast();
        golden.addFirst(slider);
        for (int i = 6; i<9; i++) {
            aim.add(commonBoard.get(i).getCardID());
        }
        slider = aim.removeLast();
        aim.addFirst(slider);


        Platform.runLater( () -> it.polimi.ingsw.am40.client.view.GUI.FXGUI.GUIApplication.controller.passiveDraw(resource, golden, aim));
    }

    /**
     * This method is called when the Server notifies the Client that the Game has been interrupted
     */
    @Override
    public void displayInterruptedGame() {
        Platform.runLater( () -> {
            try {
                it.polimi.ingsw.am40.client.view.GUI.FXGUI.GUIApplication.controller.endGame(new ArrayList<String>());
            } catch (IOException e) {
                System.out.println("Error in loading the file");
            }
        });
    }
}
