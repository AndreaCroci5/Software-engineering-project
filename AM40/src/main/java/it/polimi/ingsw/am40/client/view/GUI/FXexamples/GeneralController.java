package it.polimi.ingsw.am40.client.view.GUI.FXexamples;

import it.polimi.ingsw.am40.client.smallModel.SmallCard;
import javafx.application.Platform;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//TODO javadoc

public class GeneralController {
    //LOGIN METHODS
    public void waitingForPlayersEvent(int playersRequired) {
    }

    public void displayAllGameIDs(String gameIDsNotification) {

    }

    public void failedGameID() {

    }

    public void noActiveParties() {

    }

    public void startingGame(ArrayList<String> nicknames, List<Integer> resource, List<Integer> golden, List<Integer> aim) throws IOException {
    }

    //FIRST ROUND METHODS
    public void startingCardInfo(int cardID) {
        System.out.println("Scheduler is messing up");
    }

    public void showPassiveStartingCard (String nickname){

    }

    public void showPassiveToken (String nickname) {

    }

    public void acceptedToken (String clientNickname, String token) {

    }

    public void tokenInfo(List<String> tokens) {
    }

    public void updateCommonBoard( List<Integer> resource, List<Integer> golden, List<Integer> aim) {

    }

    public void dealCards (ArrayList<Integer> handDeckIDs) {

    }

    public void aimCardsInfo(List<Integer> aimIDs) {

    }

    public void showPassiveAimCard (String nickname) {

    }

    public void playersOrder(List<String> namesInOrder) {

    }

    public void placing (List<Integer> myHand, List<SmallCard> myGrid) {

    }

    public void positivePlacing () {

    }

    public void positiveDraw (List<Integer> resource, List<Integer> golden, List<Integer> aim) {

    }

    public void passiveDraw (List<Integer> resource, List<Integer> golden, List<Integer> aim) {

    }

    public void negativePlacing() {

    }

    public void passivePlacingState(String nickname) {

    }

    public void passivePlacingResult (String nickname) {

    }

    public void lastRounds (String nickname) {

    }

    public void endGame(List<String> winners) throws IOException {

    }
}
