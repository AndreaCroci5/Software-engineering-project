package it.polimi.ingsw.am40.model.aimStrategy;

import it.polimi.ingsw.am40.model.CardElements;
import it.polimi.ingsw.am40.model.PrivateBoard;
import java.util.HashMap;
import java.util.List;

/**
 * Class that implements the method for calculating how many times the resource pattern occurs in a player grid
 */
public class AimCheckerResource implements AimChecker{

    /**
     * This method checks how many times the pattern is verified
     * Requirements : arguments passed cannot be null
     * @param privateBoard is the board of the player with grid of cards and map of resources that the player has
     * @param checkResources is a list of the resources that the aimCard require
     * @param rotation is a string that indicate how the path is oriented
     * @return the number of times that the pattern is verified
     */
    public int check(PrivateBoard privateBoard, List<CardElements> checkResources, String rotation) {
        // getting the numbers of the elements required for the aim
        HashMap<CardElements, Integer> aimCardElementsCounter = new HashMap<>();
        for (CardElements element : checkResources) {
            aimCardElementsCounter.put(element, aimCardElementsCounter.getOrDefault(element, 0) + 1);
        }

        // Make a copy of the resource that the player has in is PrivateBoard
        HashMap<CardElements, Integer> temp = new HashMap<>(privateBoard.getElementsCounter());
        int multiplier = 0; // how many times the pattern is verified
        int exit=0; // when the number of an element that the player has is smaller than the requirement it will be set to 1
        while (exit==0) {
            for (CardElements element : aimCardElementsCounter.keySet()) {
                if (temp.get(element) < aimCardElementsCounter.get(element)) {
                    exit = 1;
                } else {
                    temp.put(element, temp.get(element) - aimCardElementsCounter.get(element));
                }
            }
            // After checking that the player has the resources, the multiplier increment
            // then we do the same check on the updated map that has player's resources(temp)
            ++ multiplier;
        }

        // Multiplier was incremented also before the exit, so we have to decrement by 1
        -- multiplier;

        return multiplier;
    }
}
