package it.polimi.ingsw.am40.server.model;

/**
 * The Token class models the game piece of a player in the game.
 * Tokens will be displayed on the corresponding score on the common board
 */
public class Token {

    //TOKEN ATTRIBUTES

    /**
     * Color of the game piece (token) of a specific player in a specific game.
     * Each player must have a distinct color
     */
    private Color color;





    //CONSTRUCTORS METHOD

    /**
     * Constructor for Token class, which create a new token (game piece, for a specific player in a game).
     * This method initializes color to default value
     */
    public Token(){
        this.color = Color.NONE;
    }

    /**
     * Constructor for Token class, which create a new token (game piece, for a specific player in a game).
     * This method initializes color to the value passed in input
     * @param initialColor to set the initial color of a new token
     */
    public Token(String initialColor){
        this.color = Color.valueOf(initialColor);
    }


    //GETTER METHOD

    /**
     * Getter for color
     * @return the color of the token as Color (ENUM)
     */
    public Color getColor() {
        return color;
    }


    //SETTER METHOD

    /**
     * Setter for color
     * @param color to set the token color
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Setter for color
     * @param color to set the token color
     */
    public void setColor(String color) {
        this.color = Color.valueOf(color);
    }
}
