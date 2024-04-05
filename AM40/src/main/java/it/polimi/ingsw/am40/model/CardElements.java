package it.polimi.ingsw.am40.model;

/**The CardElements Enum class defines the elements of the game.
 * These elements are divided in stuff (QUILL, INKWELL, MANUSCRIPT),base
 * resources (FUNGI, PLANT, ANIMAL, INSECT) and "others" (EMPTY and NONE).
 * Cards have a specific type (base resource), shown in the background graphic,
 * Furthermore, in the four edges of a card, we could find an element.
 */
public enum CardElements {
    /**
     * Fungi type (base resource)
     */
    FUNGI,
    /**
     * Plant type (base resource)
     */
    PLANT,
    /**
     * Animal type (base resource)
     */
    ANIMAL,
    /**
     * Insect type (base resource)
     */
    INSECT,
    /**
     * Quill type (stuff resource)
     */
    QUILL,
    /**
     * Inkwell type (stuff resource)
     */
    INKWELL,
    /**
     * Manuscript type (stuff resource)
     */
    MANUSCRIPT,
    /**
     * "Empty" means that an edge is shown on a card, but it's empty
     * (without a specific type).
     * Starter cards have empty type
     */
    EMPTY,
    /**
     * For hidden edges (not visible)
     */
    NONE
}
