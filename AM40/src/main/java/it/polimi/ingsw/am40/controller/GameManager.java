package it.polimi.ingsw.am40.controller;


import it.polimi.ingsw.am40.ActionListener;
import it.polimi.ingsw.am40.messages.Message;
import it.polimi.ingsw.am40.model.Game;


/**
 * The GameManager class is the "interface" of the controller in an O.O. MVC optic.
 * Through this class you can access to the FSM behind the game logic.
 * Furthermore, this class manages the entire game, not only for one active game,
 * but also with multiple active games which are running.
 */
public class GameManager implements ActionListener {

    //ATTRIBUTES

    /**
     * Reference to the Model in an MVC optic.
     * Through this reference you can call methods on the model to modify it
     * and to obtain information about its current status
     */
    Game gameModel;

    /**
     * This is a reference to the manager which coordinates the state logic
     */
    StateLogicManager stateManager;


    //IMPLEMENTATION

    /**
     *
     * @param event
     */
    @Override
    public void onEvent(Message event) {

    }


    //
}
