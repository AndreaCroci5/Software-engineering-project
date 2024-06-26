package it.polimi.ingsw.am40.client;

import it.polimi.ingsw.am40.client.network.Client;

import java.util.Scanner;

public class UserInputReader implements Runnable {

    private Client client;

    public UserInputReader(Client client) {
        this.client = client;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("exit")) {
                break;
            }
            boolean command = commandInput(input);
            if (!command) {
                client.getCurrentState().checkInput(client,input);
            }
        }
        scanner.close();
        System.out.println("Thread is exiting... ");
    }

    public boolean commandInput(String input) {

        if (input.equalsIgnoreCase("myHand")) {
            client.getViewManager().displayMyHand(client.getSmallModel().getMyHand());
            return true;
        }

        if (input.equalsIgnoreCase("myAimCard")) {
            client.getViewManager().displayPersonalAimCard(client.getSmallModel().getMyAimCard());
            return true;
        }

        if (input.equalsIgnoreCase("myToken")) {
            client.getViewManager().displayPersonalToken(client.getSmallModel().getToken());
            return true;
        }

        if (input.equalsIgnoreCase("myBoard")) {
            client.getViewManager().displayPersonalGrid(client.getSmallModel().getMyGrid());
            return true;
        }

        if (input.equalsIgnoreCase("scoreBoard")) {
            client.getViewManager().displayScoreBoard(client.getSmallModel().getScoreBoard());
            return true;
        }

        if (input.equalsIgnoreCase("commonBoard")) {
            client.getViewManager().displayCommonBoard(client.getSmallModel().getCommonBoard());
            return true;
        }

        if (input.equalsIgnoreCase("otherBoard")) {
            client.getViewManager().displayOtherPlayersGrid(client.getSmallModel().getOtherPlayersGrid());
            return true;
        }

        if (input.equalsIgnoreCase("chat")) {
            client.getViewManager().displayChat();
            return true;
        }

        if (input.equalsIgnoreCase("legend")) {
            client.getViewManager().displaySymbolLegend();
            return true;
        }

        if (input.equalsIgnoreCase("counter")) {
            client.getViewManager().diplayElementsCounter(client.getSmallModel().getElementsCounter());
            return true;
        }

        if (input.equalsIgnoreCase("inputs")) {
            client.getViewManager().showPossibleInputs();
            return true;
        }
        return false;
    }
}
