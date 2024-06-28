package it.polimi.ingsw.am40.server.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * The Coordinates class defines the position of the cards in the grid in the private board, which also allow the player
 * to know where he can place the next card.
 * How to work with Coordinates: in all the methods related to the game logic, the coordinates represent a point with their x and y
 * based on a Cartesian Plane whose axes are rotated by 45°.
 * The center of a Card represents the point represented by the Coordinates, and the StartingCard is located in (0,0)
 * The x-axis grows positive towards the bottom-right corner of a card and the y-axis towards the top-right corner
 *
 */
public class Coordinates implements Serializable {

    //ATTRIBUTE

    /**
     * X coordinate
     */
    private int x;

    /**
     * Y coordinate
     */
    private int y;


    //CONSTRUCTOR METHOD


    /**
     * Constructor for the Coordinates
     * @param x coordinate
     * @param y coordinate
     */
    @JsonCreator
    public Coordinates(@JsonProperty("x") int x,
                       @JsonProperty("y") int y) {
        this.x = x;
        this.y = y;
    }


    //GETTER METHODS

    /**
     * Getter for x coordinate
     * @return the x coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * Getter for y coordinate
     * @return the y coordinate
     */
    public int getY() {
        return y;
    }


    //SETTER METHODS

    /**
     * Setter for x coordinate
     * @param x sets the x coordinate
     */
    public void setX(int x){
        this.x = x;
    }

    /**
     * Setter for the y coordinate
     * @param y sets the y coordinate
     */
    public void setY(int y){
        this.y = y;
    }

    //EQUALS AND HASHCODE OVERRIDE

    /**
     * Override of equals method in order to ease Card place checking operations
     * @param o is the object to compare with the one who calls this method
     * @return true if two coordinates has the same value of x and y, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coordinates otherCoordinates = (Coordinates) o;

        return x == otherCoordinates.x && y == otherCoordinates.y;

    }

    /**
     * Calculates the hash code value for this Coordinates object
     * @return the hash code value for this object
     */
    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}
