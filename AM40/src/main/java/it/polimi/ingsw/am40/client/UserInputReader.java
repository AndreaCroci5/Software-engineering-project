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
            client.getCurrentState().execute(client);
            return true;
        }

        if (input.equalsIgnoreCase("myAimCard")) {
            client.getViewManager().displayPersonalAimCard(client.getSmallModel().getMyAimCard());
            client.getCurrentState().execute(client);
            return true;
        }

        if (input.equalsIgnoreCase("myToken")) {
            client.getViewManager().displayPersonalToken(client.getSmallModel().getToken());
            client.getCurrentState().execute(client);
            return true;
        }

        if (input.equalsIgnoreCase("myBoard")) {
            client.getViewManager().displayPersonalGrid(client.getSmallModel().getMyGrid());
            client.getCurrentState().execute(client);
            return true;
        }

        if (input.equalsIgnoreCase("scoreBoard")) {
            client.getViewManager().displayScoreBoard(client.getSmallModel().getScoreBoard());
            client.getCurrentState().execute(client);
            return true;
        }

        if (input.equalsIgnoreCase("commonBoard")) {
            client.getViewManager().displayCommonBoard(client.getSmallModel().getCommonBoard());
            client.getCurrentState().execute(client);
            return true;
        }

        if (input.equalsIgnoreCase("otherBoard")) {
            client.getViewManager().displayOtherPlayersGrid();
            client.getCurrentState().execute(client);
            return true;
        }

        if (input.equalsIgnoreCase("chat")) {
            client.getViewManager().displayChat();
            client.getCurrentState().execute(client);
            return true;
        }

        if (input.equalsIgnoreCase("legend")) {
            client.getViewManager().displaySymbolLegend();
            client.getCurrentState().execute(client);
            return true;
        }

        if (input.equalsIgnoreCase("counter")) {
            client.getViewManager().diplayElementsCounter(client.getSmallModel().getElementsCounter());
            client.getCurrentState().execute(client);
            return true;
        }
        return false;
    }
}
