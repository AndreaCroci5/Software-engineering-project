package it.polimi.ingsw.am40.server.model;

/**
 * The Coordinates class defines the position of the cards in the grid in the private board, which also allow the player
 * to know where he can place the next card.
 * How to work with Coordinates: in all the methods related to the game logic, the coordinates represent a point with their x and y
 * based on a Cartesian Plane whose axes are rotated by 45°.
 * The center of a Card represents the point represented by the Coordinates, and the StartingCard is located in (0,0)
 * The x-axis grows positive towards the bottom-right corner of a card and the y-axis towards the top-right corner
 *
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

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}
