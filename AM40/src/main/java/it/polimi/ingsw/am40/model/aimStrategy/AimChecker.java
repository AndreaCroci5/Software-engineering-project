package it.polimi.ingsw.am40.model.aimStrategy;

import it.polimi.ingsw.am40.model.CardElements;
import it.polimi.ingsw.am40.model.PrivateBoard;
import java.util.List;

/**
 * Interface for the strategy pattern
 * It requests all the other classes on the strategy pattern to implement the method check
 * The method check will be chosen based on the runtime type of the aimCard
 */

public interface AimChecker {
    /**
     * This is the method that calculate how many times a pattern of an aimCard occurs on the player grid
     * @param privateBoard is the board of the player with grid of cards and map of resources that the player has
     * @param checkResources is a list of the resources that the aimCard require
     * @param rotation is a string that indicate how the path is oriented
     * @return the number of times that the pattern is verified
     */
    public int check(PrivateBoard privateBoard, List<CardElements> checkResources, String rotation);
}
