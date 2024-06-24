package it.polimi.ingsw.am40.client.view.GUI;

import it.polimi.ingsw.am40.client.network.Client;
import it.polimi.ingsw.am40.client.smallModel.SmallCard;
import it.polimi.ingsw.am40.client.view.GUI.FXexamples.HelloApplication;
import it.polimi.ingsw.am40.client.view.ViewManager;
import it.polimi.ingsw.am40.server.model.CardElements;
import javafx.application.Platform;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
//TODO add a lock for startingcard and startingGame if needed

/**
 * The GUIManager is the class which make possible to display the information
 * and to manage the inputs with JavaFX
 */
public class GUIManager implements ViewManager {

    ///ATTRIBUTES
    /**
     * Reference to the Application that starts the software in GUI
     */
    HelloApplication GUIApplication;

    /**
     * Reference to the Client information
     */
    private Client client;

    //CONSTRUCTOR

    public GUIManager(Client client) {
        this.client = client;
    }


    /**
     * Method to initialize the entire display protocol and to set initial parameters
     */
    @Override
    public void initView() {
        try {
            this.GUIApplication = new HelloApplication();
            HelloApplication.client = this.client;
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

        Platform.runLater( () -> HelloApplication.controller.displayAllGameIDs(finalGameIDs));
    }

    /**
     * This method is called after a Client sends a StartingCard request and receives a Server response.
     * @param startingCardID is the ID of the StartingCard assigned
     */
    @Override
    public void displayStartingCardInfo(int startingCardID) {
        final int cardID = startingCardID;
        Platform.runLater( () -> HelloApplication.controller.startingCardInfo(cardID));
    }

    @Override
    public void askStartingFace() {

    }

    @Override
    public void showPassiveStartingCard(String nickname, int startingCardID, String cardFace) {
        final String currentPlayer = nickname;
        Platform.runLater( () -> HelloApplication.controller.showPassiveStartingCard(currentPlayer));
    }

    /**
     * This method is called after a Client sends a Token request and receives a Server response.
     * @param tokens is the list of the tokens available
     */
    @Override
    public void displayPossibleTokens(List<String> tokens) {
        final List<String> colors = tokens;
        Platform.runLater( () -> HelloApplication.controller.tokenInfo(colors));

    }

    @Override
    public void showPositiveTokenColor(String clientNickname, String token) {

    }

    //FIXME amiCards typo in aimCards in parameters signature
    @Override
    public void displayAimCardsToChoose(List<Integer> amiCardsID) {
        final List<Integer> aimIDs = amiCardsID;
        Platform.runLater( () -> HelloApplication.controller.aimCardsInfo(aimIDs));

    }

    @Override
    public void showPassiveAimCardResult(String nickname) {

    }

    @Override
    public void displayPlayerOrder(List<String> playerOrder) {

    }

    @Override
    public void displayPlacingCardChoice(List<SmallCard> myHand, List<SmallCard> myGrid) {

    }

    @Override
    public void displayLastRoundMessage(String clientNickname) {

    }

    @Override
    public void displayEndGame(List<String> winners) {

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
        //FIXME Check index problems in case
        int slider;
        for (int i = 0; i<3; i++) {
            resource.add(commonBoard.get(i).getCardID());
        }

        for (int i = 3; i<6; i++) {
            golden.add(commonBoard.get(i).getCardID());
        }

        for (int i = 6; i<9; i++) {
            aim.add(commonBoard.get(i).getCardID());
        }


        Platform.runLater( () -> {
            try {
                HelloApplication.controller.updateCommonBoard(resource, golden, aim);
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
    public void displayOtherPlayersGrid() {

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
        Platform.runLater( () -> HelloApplication.controller.waitingForPlayersEvent(playersRequired));
    }

    @Override
    public void displayError() {

    }

    @Override
    public void showNoActiveParties() {

    }

    @Override
    public void showFailedGameID() {

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
        //FIXME Check index problems in case
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
                HelloApplication.controller.startingGame(finalNicknames, resource, golden, aim);
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
    public void showPassiveAimState(String clientNickname) {
        Platform.runLater( () -> HelloApplication.controller.showPassiveAimCard(clientNickname));

    }

    @Override
    public void showPassiveStartingCardState(String clientNickname) {
        Platform.runLater( () -> HelloApplication.controller.showPassiveStartingCard(clientNickname));

    }

    @Override
    public void showPassiveTokenState(String clientNickname) {
        //TODO add in the signature the token color
        Platform.runLater( () -> HelloApplication.controller.showPassiveToken(clientNickname));
    }

    @Override
    public void displayStartingCardResult(int cardID, String cardFace) {

    }

    @Override
    public void displayDealCardState(List<SmallCard> myHand) {
        final ArrayList<Integer> handDeck = new ArrayList<>();
        for (SmallCard card : myHand) {
            handDeck.add(card.getCardID());
        }


        Platform.runLater( () -> {
            try {
                HelloApplication.controller.dealCards(handDeck);
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

    @Override
    public void displayPassivePlacingState(String clientNickname) {

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

    @Override
    public void displayPositivePlacing() {

    }

    @Override
    public void displayPassivePlacingResult(String clientNickname) {

    }

    @Override
    public void displayDrawChoice(List<SmallCard> commonBoard) {

    }

    @Override
    public void displayPositiveDraw() {

    }

    @Override
    public void diplayElementsCounter(Map<CardElements, Integer> elementsCounter) {

    }
}
