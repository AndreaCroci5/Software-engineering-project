package it.polimi.ingsw.am40.controller;

public class InexistentStateException extends Exception{

    public InexistentStateException(){
        super("You're trying to set a non-existent state in your context !");
    }
}
