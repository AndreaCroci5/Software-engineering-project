package it.polimi.ingsw.am40.client.view.GUI.FXGUI;

import it.polimi.ingsw.am40.client.smallModel.SmallCard;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is used as an interface that keeps all the methods that can be called on the GUI by the GUIManager.
 * By assigning a different subclass of this one to the attribute in GUIManager, it is possible to call dynamically the
 * methods from the scene shown by correctly setting the current scene controller that implements through override the
 * method called (typically by a net Message event)
 */
public class GeneralController {
    //LOGIN METHODS

    /**
     * This method is always called by the GUIManger through override made by the subclass, when a Player joins the party that the Client is in, or
     * the Client creates a new a party
     * @param playersRequired is the amount of the Players required in order to start the Game
     */
    public void waitingForPlayersEvent(int playersRequired) {
    }

    /**
     * This method is always called by the GUIManger through override made by the subclass.
     * This method is called after a Client sends a join request and receives a Server response.
     * @param gameIDsNotification  is the list of the GameIDs available
     */
    public void displayAllGameIDs(String gameIDsNotification) {

    }

    /**
     * This method is always called by the GUIManger through override made by the subclass.
     * This method is called when the Client selects a GameID that isn't available
     * to be joined
     */
    public void failedGameID() {

    }

    /**
     * This method is called by the GUIManger through override made by the subclass, when the Client wants to join a game and requests
     * the list of the gameIDs but there are no parties available.
     */
    public void noActiveParties() {

    }

    /**
     * This method is called by the GUIManger through override made by the subclass, when the party reaches the required size and through the net arrives at the Client a GameInitData
     * that triggers a StartingGameMessage.
     * @param nicknames are the nicknames chosen by the Players
     * @param resource  is the situation of the ResourceCards in CommonBoard
     * @param golden is the situation of the GoldenResourceCards in CommonBoard
     * @param aim is the situation of the AimCards in CommonBoard
     * @throws IOException in case the FXML file is not found
     */
    public void startingGame(ArrayList<String> nicknames, List<Integer> resource, List<Integer> golden, List<Integer> aim) throws IOException {
    }



    //FIRST ROUND METHODS

    /**
     * This method is called by override made by the subclass from the GUI manager after the net notifies the client with the information regarding
     * his StartingCard.
     * @param cardID is the ID of the StartingCard
     */
    public void startingCardInfo(int cardID) {
        System.out.println("Scheduler is messing up");
    }

    /**
     * This method is called by the GUI manager through override made by the subclass when another player is choosing his StartingCard
     * @param nickname is the nickname of the player that is choosing his StartingCard
     */
    public void showPassiveStartingCard (String nickname){

    }

    /**
     * This method is called by the GUI manager through override made by the subclass when another player is choosing his Token
     * @param nickname is the nickname of the player that is choosing his Token
     */
    public void showPassiveToken (String nickname) {

    }

    /**
     * This method is called by the GUI manager through override made by the subclass when the Server confirms the color selection of the token
     * made by a Player, including the Client
     * @param clientNickname is the name of the Player that has just chosen the Token
     * @param token is the color of the Token
     */
    public void acceptedToken (String clientNickname, String token) {

    }

    /**
     * This method is called through override made by the subclass from the GUI manager after the net notifies the client with the information regarding
     * the colors available for the Token.
     * @param tokens are the remaining colors
     */
    public void tokenInfo(List<String> tokens) {
    }

    /**
     * This method updates the CommonBoard on the GUI when invoked through override made by the subclass
     * @param resource is the reference to a List containing the resource deck and plate cards IDs
     * @param golden is the reference to a List containing the golden deck and plate cards IDs
     * @param aim is the reference to a List containing the aim deck and plate cards IDs
     */
    public void updateCommonBoard( List<Integer> resource, List<Integer> golden, List<Integer> aim) {

    }

    /**
     * This method is called by the GUI manager through override made by the subclass when the Server responds to the dealCards request from
     * the client with the information of the cards that the client got
     * @param handDeckIDs is a reference to an ArrayList containing the ID of the cards in the Client handDeck
     */
    public void dealCards (ArrayList<Integer> handDeckIDs) {

    }

    /**
     * This method is called by override made by the subclass from the GUI manager after the net notifies the client with the information regarding
     * his AimCards.
     * @param aimIDs is a reference to a List containing the IDs of the AimCards
     */
    public void aimCardsInfo(List<Integer> aimIDs) {

    }

    /**
     * This method is called by the GUI manager through override made by the subclass when another player is choosing his AimCard
     * @param nickname is the nickname of the player that is choosing his AimCard
     */
    public void showPassiveAimCard (String nickname) {

    }

    /**
     * This method is called by override made by the subclass from the GUI manager after the net notifies the clients with the information regarding
     * the new playersOrder ongoing for the rest of the Game
     * @param namesInOrder is a reference to a List containing the names of the player sorted by their turn's right to play
     */
    public void playersOrder(List<String> namesInOrder) {

    }

    /**
     * This method is called by the GUI manager through override made by the subclass when the Client has the right to place a card on the grid.
     * @param myHand is a reference to a List containing the card IDs of the Client handDeck
     * @param myGrid is a reference to a List containing the cards in the Client cardGrid
     */
    public void placing (List<Integer> myHand, List<SmallCard> myGrid) {

    }

    /**
     * This method is called by the GUI manager through override made by the subclass when the Client placing has got a positive feedback by
     * the server.
     */
    public void positivePlacing () {

    }

    /**
     * This method is called by the GUI manager through override made by the subclass when a draw has given a positive feedback from the server.
     * @param resource is the reference to a List containing the resource deck and plate cards IDs
     * @param golden is the reference to a List containing the golden deck and plate cards IDs
     * @param aim is the reference to a List containing the aim deck and plate cards IDs
     */
    public void positiveDraw (List<Integer> resource, List<Integer> golden, List<Integer> aim) {

    }

    /**
     * This method is called by the GUI manager through override made by the subclass when another player has made a draw.
     * @param resource is the reference to a List containing the resource deck and plate cards IDs
     * @param golden is the reference to a List containing the golden deck and plate cards IDs
     * @param aim is the reference to a List containing the aim deck and plate cards IDs
     */
    public void passiveDraw (List<Integer> resource, List<Integer> golden, List<Integer> aim) {

    }

    /**
     * This method is called by the GUI manager through override made by the subclass when the Client placing has got
     * a negative feedback by the server.
     */
    public void negativePlacing() {

    }

    /**
     * This method is called by the GUI manager through override made by the subclass when a draw has given a negative feedback from the server.
     */
    public void negativeDraw() {

    }

    /**
     * This method is called by the GUI manager through override made by the subclass when another player is placing a card
     * @param nickname is the nickname of the player that is active in the current turn
     */
    public void passivePlacingState(String nickname) {

    }

    /**
     * This method is called by the GUI manager through override made by the subclass when another player has placed a card.
     * @param nickname is the nickname of the player that is active in the current turn
     */
    public void passivePlacingResult (String nickname) {

    }

    /**
     * This method is called by the GUI manager through override made by the subclass when the lastRounds condition is triggered.
     * @param nickname is the name of the client that triggered the lastRounds condition
     */
    public void lastRounds (String nickname) {

    }

    /**
     * This method is called by the GUI manager through override made by the subclass when the endgame is reached, so the lastRounds ended.
     * @param winners is a reference to the List containing the names of the winners
     * @throws IOException in case the endgame.fxml is not correctly loaded
     */
    public void endGame(List<String> winners) throws IOException {

    }
}
