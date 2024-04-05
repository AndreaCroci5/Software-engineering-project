package it.polimi.ingsw.am40.model;

/**
 * The Coordinates class defines the position of the cards in the grid in the private board, which also allow the player
 * to know where he can place the next card
 */


public class Coordinates {

    //ATTRIBUTE

    private int x;
    private int y;


    //CONSTRUCTOR METHOD

    public Coordinates(int x, int y){
        this.x = x;
        this.y = y;
    }


    //GETTER METHODS

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    //SETTER METHODS

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }
}
