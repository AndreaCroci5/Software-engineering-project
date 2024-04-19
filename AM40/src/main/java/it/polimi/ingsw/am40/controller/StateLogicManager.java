package it.polimi.ingsw.am40.controller;

import it.polimi.ingsw.am40.controller.states.*;

import java.util.HashMap;
import java.util.Map;

public class StateLogicManager implements StateContext{

    //ATTRIBUTES

    /**
     * A map which contains the states owned by the state context (Name, State reference)
     */
    Map<String, State> states;

    /**
     * The state in which the FSM is
     */
    State currentState;


    //CONSTRUCTORS METHOD

    /**
     * First constructor which creates the states and initializes the map of the states
     */
    public StateLogicManager(){
        //Firstly it creates the map of the states
        this.states = new HashMap<>();
        //Then it fills up the map with the states owned by the context
        this.initStates();
        //Finally it sets the initial state as default state
        this.currentState = states.get("InitialGameState");
    }

    /**
     * Second constructor which creates the states and initializes the map of the states.
     * The initial state is set manually
     * @param initialState Initial state chosen as first instead of the default starting state
     */
    public StateLogicManager(String initialState){
        //Firstly it creates the map of the states
        this.states = new HashMap<>();
        //Then it fills up the map with the states owned by the context
        this.initStates();
        //Finally it sets the initial state as default state
        this.currentState = states.get(initialState);
    }


    //GETTER METHODS

    /**
     * Getter for the map of the states
     * @return the map of the states of the context
     */
    public Map<String, State> getStates() {
        return this.states;
    }

    /**
     * Getter for the current state
     * @return the current state
     */
    public State getCurrentState() {
        return currentState;
    }


    //IMPLEMENTED METHOD

    /**
     * Interface method to set the context to another state
     * @param stateToSet New state to set
     * @throws InexistentStateException
     */
    @Override
    public void setState(State stateToSet) throws InexistentStateException{
        if(this.states.containsValue(stateToSet) == false){
            throw new InexistentStateException();
        }

        this.currentState = stateToSet;
    }


    //PRIVATE METHODS

    /**
     * This method creates the states of the context and puts them into the state map
     */
    private void initStates(){
        this.states.put("InfoState", new InfoState());
        this.states.put("InitialGameState", new InitialGameState());
        this.states.put("LoginState", new LoginState());
        this.states.put("PlayRoundState", new PlayRoundState());
        this.states.put("EndTurnState", new EndTurnState());
        this.states.put("EndGameState", new EndGameState());
    }


    //PUBLIC METHODS

    /**
     * It executes the current state
     */
    public void requestForExecutionCurrentState(){
        this.currentState.execute(this);
    }
}
