package it.polimi.ingsw.am40.server.controller;


import it.polimi.ingsw.am40.server.ActionListener;
import it.polimi.ingsw.am40.server.actions.Action;
import it.polimi.ingsw.am40.server.model.Game;
import it.polimi.ingsw.am40.server.network.virtual_view.VVServer;

import java.util.HashMap;
import java.util.Map;


/**
 * The GameManager class is the "interface" of the controller in an O.O. MVC optic.
 * Through this class you can access to the FSM behind the game logic.
 * Furthermore, this class manages the entire game, not only for one active game,
 * but also with multiple active games which are running.
 */
public class GameManager implements ActionListener {

    //ATTRIBUTES

    /**
     * References to the Model in an MVC optic.
     * Through these references you can call methods on the right model to modify it
     * and to obtain information about its current status.
     * In this map there is an association between the PartID and the reference
     */
    public Map<Integer, Game> activeGames;





    //CONSTRUCTOR

    /**
     * Constructor for GameManager
     */
    public GameManager(){
        this.activeGames = new HashMap<>();
    }



    //LISTENER METHOD

    /**
     * OnEvent is the method called by the notifyListeners() which execute the Action instructions
     * @param event the action which "explains" what to do
     */
    @Override
    public void onEvent(Action event) {
        //Filter in case a party triggers the Game Creation by reaching the necessary amount of Players
        if (event.getDescription().equalsIgnoreCase("GAME_INIT")) {
            Game newGame = new Game();
            this.activeGames.put(event.getGameID(), newGame);
        }
        //Normal behaviour
        Game game = this.activeGames.get(event.getGameID());
        event.doAction(game);
    }


    //PUBLIC METHODS

    /**
     * This method create a new server, initializes the listeners system and calls initCommunication on the
     * network managers
     * @param portTCP the port of the TCP server
     * @param portRMI the port of the RMI server
     * @param hostName the name of the server
     */
    public void initApplication(int portTCP, int portRMI, String hostName){

        VVServer server = new VVServer();
        server.addListener(this, server.getListeners());
        server.initServer(portTCP, portRMI, hostName);

    }
}
