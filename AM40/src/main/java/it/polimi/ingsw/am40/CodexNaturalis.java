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
                String[] argsServer = new String[3];
                argsServer[0] = args[0];
                argsServer[1] = args[1];
                argsServer[2] = args[2];
                CodexNaturalisServer.main(argsServer);
            }
            case 1 -> {
                String[] argsClient = new String[4];
                argsClient[0] = args[0];
                argsClient[1] = args[1];
                argsClient[2] = args[2];
                argsClient[3] = args[3];
                CodexNaturalisClient.main(argsClient);
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
