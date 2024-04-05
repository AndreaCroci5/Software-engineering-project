package it.polimi.ingsw.am40.model;

/**
 * The EdgeState Enum class defines the state of the edges of a card;
 * each player chooses, during his personal round, how to cover the
 * edges of a card
 */

public enum EdgeState {

    /**
     * Free edge
     */
    FREE,
    /**
     * Taken edge
     *
     */
    TAKEN,
    /**
     *hidden edge (not available)
     */
    HIDDEN
}
