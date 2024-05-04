package it.polimi.ingsw.am40.server.model;

/**The Color Enum class defines the colors for Token class,
 * that are chosen by the players at the beginning of the game.
 * Black type, which distinguishes the initial player, is not made explicit in this enum,
 * but rather it will be handled by other classes
 */
public enum Color {

    /**
     * When a new Token is built, before having a color, it will be set to this default value.
     * The color will need to be changed
     */
    NONE,
    /**
     * Red token
     */
    RED,
    /**
     * Yellow token
     */
    YELLOW,
    /**
     * Green token
     */
    GREEN,
    /**
     * Blue token
     */
    BLUE
}
