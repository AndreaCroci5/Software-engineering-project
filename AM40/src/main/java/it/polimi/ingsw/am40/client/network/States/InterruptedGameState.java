package it.polimi.ingsw.am40.client.network.States;

import it.polimi.ingsw.am40.client.network.Client;
import it.polimi.ingsw.am40.client.network.State;

public class InterruptedGameState implements State {

    public InterruptedGameState() {
    }

    /**
     * This state show up the interrupted game condition (a client has disconnected)
     * @param context is the context of the client with his view and his network communication protocol
     */
    @Override
    public void execute(Client context) {
        //nothing
    }

    /**
     * In this state the input must be a number and must be in the possible games IDs
     * @param context is the context of the client with his view and his network communication protocol
     * @param input is the input of the client
     */
    @Override
    public void checkInput(Client context, String input) {

        if(!(input.equalsIgnoreCase("quit")) && !(input.equalsIgnoreCase("lobby"))){
            System.out.println(">You have to type 'quit' or 'lobby'");
        }else {
            if (input.equalsIgnoreCase("quit")){
                System.out.println("GOODBYE!");
                System.exit(0);
            }else{
                System.out.println("GOODBYE!");
                System.exit(0);
            }
        }
    }
}
