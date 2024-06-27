package it.polimi.ingsw.am40;

import it.polimi.ingsw.am40.client.application.CodexNaturalisClient;
import it.polimi.ingsw.am40.server.application.CodexNaturalisServer;

import java.util.Scanner;

public class CodexNaturalis {
    public static void main(String[] args) {
        //TODO add clearscreen
        System.out.println("Welcome to Codex Naturalis! What type of instance do you want to launch?");
        System.out.println("0 - Server");
        System.out.println("1 - Client");
        System.out.print(">");

        Scanner scanner = new Scanner(System.in);
        int selection = 0;
        try {
            selection = scanner.nextInt();
        } catch (Exception e) {
            System.err.println("Only numbers are accepted. Exiting...");
            System.exit(1);
        }


        switch (selection) {
            case 0 -> {;
                CodexNaturalisServer.main(args);
            }
            case 1 -> {

                CodexNaturalisClient.main(args);
            }
            default -> {
                System.err.println("Wrong selection. Exiting...");
                try {
                    Thread.sleep(10000);
                    //TODO add clearscreen
                } catch (Exception e) {
                    System.exit(1);
                }
                main(null);
            }
        }
    }
}
