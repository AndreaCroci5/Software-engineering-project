package it.polimi.ingsw.am40.client.view.GUI;

import it.polimi.ingsw.am40.client.network.Client;
import it.polimi.ingsw.am40.client.smallModel.SmallCard;
import it.polimi.ingsw.am40.client.view.GUI.FXexamples.HelloApplication;
import it.polimi.ingsw.am40.client.view.ViewManager;

import java.util.List;
import java.util.Map;


/**
 * The GUIManager is the class which make possible to display the information
 * and to manage the inputs with JavaFX
 */
public class GUIManager implements ViewManager {

    ///ATTRIBUTES
    HelloApplication GUIApplication;

    private Client client;

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

    @Override
    public void displayInitialisation() {

    }

    @Override
    public void displaySetUp() {

    }

    @Override
    public void displayCreate() {

    }

    @Override
    public void displayAllGameIds(List<Integer> gamesIDs) {

    }

    @Override
    public void displayStartingCardInfo(int startingCardID) {

    }

    @Override
    public void askStartingFace() {

    }

    @Override
    public void showPassiveStartingCard(String nickname, int startingCardID) {

    }

    @Override
    public void displayPossibleTokens(List<String> tokens) {

    }

    @Override
    public void showPositiveTokenColor(String clientNickname, String token) {

    }

    @Override
    public void displayAimCardsToChoose(List<Integer> amiCardsID) {

    }

    @Override
    public void showPassiveAimCardResult(String nickname) {

    }

    @Override
    public void displayPlayerOrder(List<String> playerOrder) {

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

    @Override
    public void displayCommonBoard(List<SmallCard> commonBoard) {

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

    @Override
    public void displayWaitingForPlayers(int numOfActualPlayers, int NumOfFinalPlayers) {

    }

    @Override
    public void displayError() {

    }
}
