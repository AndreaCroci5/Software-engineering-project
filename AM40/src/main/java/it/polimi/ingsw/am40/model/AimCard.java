package it.polimi.ingsw.am40.model;

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



    //CONSTRUCTOR METHOD

    /**
     * Constructor for Aim Card, which create and initializes the attributes for a new aim card.
     *
     * @param cardID  Identification number of the card
     * @param points Number of points printed on the card
     * @param checker as reference of the strategy pattern AimChecker (statically AimChecker,
     *                dynamically of the specific class which implements Checker)
     */
    public AimCard(int cardID, int points, AimChecker checker) {
        super(cardID);
        this.points = points;
        this.checker = checker;
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
}
