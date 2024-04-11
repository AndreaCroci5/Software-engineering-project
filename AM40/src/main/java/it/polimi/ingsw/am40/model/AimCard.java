package it.polimi.ingsw.am40.model;

import java.util.List;

/**
 * The AimCard class defines the aim cards, which indicate the private and public
 * aims for the players in the game.
 * The aims can be of different types; for each type there is a method check called
 * dynamically through the AimChecker strategy pattern.
 * When an aim is checked, all the players who have done it get a specific number of points
 */
public class AimCard extends Card{

    //ATTRIBUTES

    /**
     * Number of points given to who reaches the aim (they can be multiplied)
     */
    private final int points;

    /**
     * Reference to the strategy patter to calculate the reaching of the aim
     */
    private final AimChecker checker;

    /**
     * List of resource order for the pattern check.
     * For L pattern, the first element of the list is always the one of the "short" side of the L
     */
    private List<CardElements> checkResources;

    /**
     * Rotation is a String which indicates where the pattern are oriented.
     * "x": in the x axes direction (about -45 degrees in polar coordinates)
     * "-x": in the x axes direction (negative verse) (about -135 degrees in polar coordinates)
     * "y": in the y axes direction (about 45 degrees in polar coordinates)
     * "-y": in the y axes direction (negative verse) (about 135 degrees in polar coordinates)
     * null: if not used
     */
    private final String rotation;

    //CONSTRUCTOR METHOD

    /**
     * Constructor for Aim Card, which create and initializes the attributes for a new aim card.
     *
     * @param cardID         Identification number of the card
     * @param points         Number of points printed on the card
     * @param checkResources List of elements of the cards for pattern checking
     * @param checker        as reference of the strategy pattern AimChecker (statically AimChecker,
     *                       dynamically of the specific class which implements Checker)
     * @param rotation       string which indicate the rotation of the pattern
     */
    public AimCard(int cardID, int points, List<CardElements> checkResources, AimChecker checker, String rotation) {
        super(cardID);
        this.points = points;
        this.checker = checker;
        this.checkResources = checkResources;
        this.rotation = rotation;
    }


    //GETTER METHODS

    /**
     * Getter for points
     * @return the value of points attribute
     */
    public int getPoints(){
        return this.points;
    }

    /**
     * Getter for the reference of the aim checker (strategy pattern)
     * @return the reference to the checker implementer
     */
    public AimChecker getChecker() {
        return this.checker;
    }

    /**
     * Getter for check resources
     * @return the list of resources of the cards to check for the pattern
     */
    public List<CardElements> getCheckResources() {
        return this.checkResources;
    }

    /**
     * Getter for rotation
     * @return the rotation as a String
     */
    public String getRotation(){
        return this.rotation;
    }
}
