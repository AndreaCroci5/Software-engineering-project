package it.polimi.ingsw.am40.model.aimStrategy;

import it.polimi.ingsw.am40.model.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Class that implements the method for calculating how many times the L pattern occurs in a player grid
 */
public class AimCheckerLPattern implements AimChecker{

    /**
     *
     * @param privateBoard is the board of the player with grid of cards and map of resources that the player has
     * @param checkResources is a list of the resources that the aimCard require
     * @param rotation is a string that indicate how the path is oriented
     * @return the number of times that the pattern is verified
     */
    public int check(PrivateBoard privateBoard, List<CardElements> checkResources, String rotation) {
        // copying the grid of the player
        ArrayList<ResourceCard> tempGrid = new ArrayList<>(privateBoard.getCardGrid());
        ArrayList<ResourceCard> elemMatching = new ArrayList<>();
        int multiplier = 0; // return value

        // array list that contains all possible starting card for L pattern
        for (ResourceCard card : tempGrid) {
            if(card.getCardElement().equals(checkResources.getFirst())) {
                elemMatching.add(card);
            }
        }

        while(!elemMatching.isEmpty()) {
            ResourceCard toCheck = elemMatching.getFirst(); // card that we are checking
            ArrayList<Coordinates> nextCord = new ArrayList<>(neighborsCord(toCheck,rotation)); // coordinates to check
            ArrayList<ResourceCard> neighbors = new ArrayList<>(findNeighbors(tempGrid,nextCord,checkResources));
            if(neighbors.size() == 2) { // we found two cards that has the parameters the aim want in the right position
                ++multiplier;
                tempGrid.remove(toCheck);
                tempGrid.removeAll(neighbors); // remove all the card used
            }
            elemMatching.remove(toCheck); // remove the card just checked
        }
        return multiplier;
    }

    /**
     * This method set the coordinates that we have to check based on the rotation of the pattern we are checking
     * @param toCheck is the card that we are checking
     * @param rotation is a string that indicate how the path is oriented
     * @return an array list of the coordinates where are the cards we want to check
     */
    public static ArrayList<Coordinates> neighborsCord(ResourceCard toCheck, String rotation) {
        ArrayList<Coordinates> temp = new ArrayList<>();
        Coordinates toVerify = new Coordinates(toCheck.getCoordinates().getX(), toCheck.getCoordinates().getY());
        Coordinates cord1 = new Coordinates(0,0);
        Coordinates cord2 = new Coordinates(0,0);
        switch (rotation) {
            case "-x" -> {
                cord1.setX(toVerify.getX() - 1);
                cord1.setY(toVerify.getY() + 1);
                temp.add(cord1);
                cord2.setX(toVerify.getX() - 1);
                cord2.setY(toVerify.getY() + 2);
                temp.add(cord2);
            }
            case "-y" -> {
                cord1.setX(toVerify.getX() - 1);
                cord1.setY(toVerify.getY() - 1);
                temp.add(cord1);
                cord2.setX(toVerify.getX() - 1);
                cord2.setY(toVerify.getY() - 2);
                temp.add(cord2);
            }
            case "x" -> {
                cord1.setX(toVerify.getX() + 1);
                cord1.setY(toVerify.getY() - 1);
                temp.add(cord1);
                cord2.setX(toVerify.getX() + 1);
                cord2.setY(toVerify.getY() - 2);
                temp.add(cord2);
            }
            case "y" -> {
                cord1.setX(toVerify.getX() + 1);
                cord1.setY(toVerify.getY() + 1);
                temp.add(cord1);
                cord2.setX(toVerify.getX() + 1);
                cord2.setY(toVerify.getY() + 2);
                temp.add(cord2);
            }
        }
        return temp;
    }

    /**
     * This method check if the card at the coordinates we calculate before respect the requirements that
     * the aim card asks for
     * @param tempGrid is a copy of the player private board
     * @param nextCord are coordinate where we have to check
     * @param checkResources are the resources required by the aim card
     * @return an array list that contains cards with all the requirements the aim card asks for
     */
    public static ArrayList<ResourceCard> findNeighbors(ArrayList<ResourceCard> tempGrid, ArrayList<Coordinates> nextCord, List<CardElements> checkResources) {
        ArrayList<ResourceCard> temp = new ArrayList<>();
        for(ResourceCard next : tempGrid) {
            if (next.getCardElement().equals(checkResources.getLast())) {
                if (next.getCoordinates().equals(nextCord.getFirst()) || next.getCoordinates().equals(nextCord.getLast())) {
                    temp.add(next);
                }
            }
        }
        return temp;
    }

}
