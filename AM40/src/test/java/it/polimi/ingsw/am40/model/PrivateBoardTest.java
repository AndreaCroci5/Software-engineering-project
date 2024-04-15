package it.polimi.ingsw.am40.model;

import it.polimi.ingsw.am40.model.scoreStrategy.CoverageScoreType;
import it.polimi.ingsw.am40.model.scoreStrategy.NormalScoreType;
import it.polimi.ingsw.am40.model.scoreStrategy.ObjectScoreType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

//INDEX: 17 private methods, 162 card flow, 216 check placing,
//       306 placing, 401 refresh score

class PrivateBoardTest {

    //Factory
    /**
     * This method creates a full FUNGI ResourceCard
     * @return the FUNGI ResourceCard
     */
    private ResourceCard resourceCardCreator(){
        ArrayList<CardElements> exampleCardElements = new ArrayList<>();
        exampleCardElements.add(CardElements.FUNGI);
        exampleCardElements.add(CardElements.FUNGI);
        exampleCardElements.add(CardElements.FUNGI);
        exampleCardElements.add(CardElements.FUNGI);
        ResourceCard card = new ResourceCard(0, CardElements.FUNGI, exampleCardElements);
        return card;
    }

    /**
     * This method creates a ResourceCard with Points on it.
     * NOTE: it creates the Card with ID = 8
     * @return
     */
    private ResourceCard resourceCardWithPointsCreator(){
        ArrayList<CardElements> exampleCardElements = new ArrayList<>();
        exampleCardElements.add(CardElements.EMPTY);
        exampleCardElements.add(CardElements.FUNGI);
        exampleCardElements.add(CardElements.EMPTY);
        exampleCardElements.add(CardElements.NONE);
        ResourceCard card = new ResourceCard(8, CardElements.FUNGI, exampleCardElements, 1);
        return card;
    }

    /**
     * This method creates a full FUNGI ResourceCard with the coordinates and the cardFace ready to be set
     * @param x coordinate
     * @param y coordinate
     * @param cardFace coordinate
     * @return the resource card to add to the CardGrid
     */
    private ResourceCard placedResourceCardCreator(int x, int y, CardFace cardFace){
        ArrayList<CardElements> exampleCardElements = new ArrayList<>();
        exampleCardElements.add(CardElements.FUNGI);
        exampleCardElements.add(CardElements.FUNGI);
        exampleCardElements.add(CardElements.FUNGI);
        exampleCardElements.add(CardElements.FUNGI);
        ResourceCard card = new ResourceCard(1, CardElements.FUNGI, exampleCardElements);
        card.setCoordinates(new Coordinates(x, y));
        card.setFace(cardFace);
        return card;
    }

    /**
     * This method creates the Golden Resource Card with ID 41 for generic purpose
     * @return ID 41 GoldResource Card
     */
    private GoldResourceCard goldResourceCardCreator(){
        ArrayList<CardElements> frontEdgeRes = new ArrayList<>();
        frontEdgeRes.add(CardElements.NONE);
        frontEdgeRes.add(CardElements.EMPTY);
        frontEdgeRes.add(CardElements.EMPTY);
        frontEdgeRes.add(CardElements.QUILL);
        ArrayList<CardElements> requires = new ArrayList<>();
        requires.add(CardElements.FUNGI);
        requires.add(CardElements.FUNGI);
        requires.add(CardElements.ANIMAL);
        GoldResourceCard card = new GoldResourceCard(41, CardElements.FUNGI, frontEdgeRes, 2, requires, new ObjectScoreType());
        card.setObjectScoreTypeElement(CardElements.QUILL);
        return card;
    }

    /**
     * This method creates the GoldResourceCard with ID 41 for calculating the score of refreshScore method (ObjectScoreType)
     * @return ID 41 GoldResource Card
     */
    private GoldResourceCard goldResourceCardObjectScoreTypeCreator(){
        ArrayList<CardElements> frontEdgeRes = new ArrayList<>();
        frontEdgeRes.add(CardElements.NONE);
        frontEdgeRes.add(CardElements.EMPTY);
        frontEdgeRes.add(CardElements.EMPTY);
        frontEdgeRes.add(CardElements.QUILL);
        ArrayList<CardElements> requires = new ArrayList<>();
        requires.add(CardElements.FUNGI);
        requires.add(CardElements.FUNGI);
        requires.add(CardElements.ANIMAL);
        GoldResourceCard card = new GoldResourceCard(41, CardElements.FUNGI, frontEdgeRes, 2, requires, new ObjectScoreType());
        card.setObjectScoreTypeElement(CardElements.QUILL);
        return card;
    }

    /**
     * This method creates the GoldResourceCard with ID 48 for calculating the score of refreshScore method (NormalScoreType)
     * @return ID 48 GoldResourceCard
     */
    private GoldResourceCard goldResourceCardNormalScoreTypeCreator(){
        ArrayList<CardElements> frontEdgeRes = new ArrayList<>();
        frontEdgeRes.add(CardElements.QUILL);
        frontEdgeRes.add(CardElements.EMPTY);
        frontEdgeRes.add(CardElements.NONE);
        frontEdgeRes.add(CardElements.NONE);
        ArrayList<CardElements> requires = new ArrayList<>();
        requires.add(CardElements.FUNGI);
        requires.add(CardElements.FUNGI);
        requires.add(CardElements.FUNGI);
        GoldResourceCard card = new GoldResourceCard(48, CardElements.FUNGI, frontEdgeRes, 3, requires, new NormalScoreType());
        return card;
    }

    /**
     * This method creates the GoldResourceCard with ID 44 for calculating the score of refreshScore method (CoverageScoreType)
     * @return ID 44 GoldResourceCard
     */
    private GoldResourceCard goldResourceCardCoverageScoreTypeCreator(){
        ArrayList<CardElements> frontEdgeRes = new ArrayList<>();
        frontEdgeRes.add(CardElements.EMPTY);
        frontEdgeRes.add(CardElements.EMPTY);
        frontEdgeRes.add(CardElements.NONE);
        frontEdgeRes.add(CardElements.EMPTY);
        ArrayList<CardElements> requires = new ArrayList<>();
        requires.add(CardElements.FUNGI);
        requires.add(CardElements.FUNGI);
        requires.add(CardElements.FUNGI);
        requires.add(CardElements.ANIMAL);
        GoldResourceCard card = new GoldResourceCard(44, CardElements.FUNGI, frontEdgeRes, 2, requires, new CoverageScoreType());
        return card;
    }



    /**
     * This method creates a PrivateBoard with a hanDeck full of resourceCards
     * @return initialized PrivateBoard
     */
    private PrivateBoard privateBoardCreatorWithHandDeck(){
        PrivateBoard privateBoard = new PrivateBoard();
        ArrayList<CardElements> exampleCardElements = new ArrayList<>();
        exampleCardElements.add(CardElements.FUNGI);
        exampleCardElements.add(CardElements.FUNGI);
        exampleCardElements.add(CardElements.FUNGI);
        exampleCardElements.add(CardElements.FUNGI);
        for (int i=1; i<4; i++){
            privateBoard.addCardToHand(new ResourceCard(i, CardElements.FUNGI, exampleCardElements));
        }
        return privateBoard;
    }



    //HandDeck Cards flow tests

    /**
     * This test proceeds to test the correct adding operation to the HandDeck
     */
    @Test
    void addCardToHand() {
        PrivateBoard privateBoard = new PrivateBoard();
        ResourceCard exampleCard = resourceCardCreator();
        privateBoard.addCardToHand(exampleCard);
        int handDeckSize = privateBoard.getHandDeck().size();
        assertEquals(1, handDeckSize);

    }

    /**
     * This test tests the two takeCardFromHand possible cases
     */
    @Test
    void takeCardFromHand() {
        takeCardFromHandLegalChoice();
        takeCardFromHandIllegalChoice();
    }

    /**
     * This test takes a Card from the Player's hand by receiving a choice that is in the handDeck size bound
     */
    @Test
    void takeCardFromHandLegalChoice() {
        PrivateBoard privateBoard = privateBoardCreatorWithHandDeck();
        int targetCardID = privateBoard.getHandDeck().get(1).getCardID();
        ResourceCard c = privateBoard.takeCardFromHand(1);
        assertEquals(targetCardID, c.getCardID());
        assertEquals(2, c.getCardID());
        assertEquals(2, privateBoard.getHandDeck().size());

    }

    /**
     * This test takes a Card from the Player's hand by receiving a choice that isn't in the handDeck size bound
     */
    @Test
    void takeCardFromHandIllegalChoice() {
        PrivateBoard privateBoard = privateBoardCreatorWithHandDeck();
        int targetCardID = privateBoard.getHandDeck().get(0).getCardID();
        ResourceCard c = privateBoard.takeCardFromHand(7);
        assertEquals(targetCardID, c.getCardID());
        assertEquals(1, c.getCardID());
        assertEquals(2, privateBoard.getHandDeck().size());

    }



    //CheckPlacing tests

    /**
     * This test ensures that a ResourceCard can be placed on the FRONT by checking on the coordinate case
     */
    @Test
    void checkLegalPlacing() {
        PrivateBoard privateBoard = new PrivateBoard();
        privateBoard.getPlacingCoordinates().add(new Coordinates(0,1));
        assertTrue(privateBoard.checkPlacing(resourceCardCreator(), new Coordinates(0,1), CardFace.FRONT));
    }

    /**
     * This test ensures that a ResourceCard can't be placed on the FRONT checking on the coordinate case
     */
    @Test
    void checkIllegalPlacing() {
        PrivateBoard privateBoard = new PrivateBoard();
        privateBoard.getPlacingCoordinates().add(new Coordinates(0,4));
        assertFalse(privateBoard.checkPlacing(resourceCardCreator(), new Coordinates(0,1), CardFace.FRONT));
    }

    /**
     * This test ensures that a Golden Card whose requirements are satisfied can be placed on the FRONT
     */
    @Test
    void checkGoldenCardLegalFrontPlacing(){
        PrivateBoard privateBoard = new PrivateBoard();
        privateBoard.getPlacingCoordinates().add(new Coordinates(0,1));
        privateBoard.getElementsCounter().replace(CardElements.FUNGI, 2);
        privateBoard.getElementsCounter().replace(CardElements.ANIMAL, 1);
        assertTrue(privateBoard.checkPlacing(goldResourceCardCreator(), new Coordinates(0,1), CardFace.FRONT));
    }

    /**
     * This test ensures that a Golden Card whose requirements aren't satisfied can't be placed on the FRONT
     */
    @Test
    void checkGoldenCardIllegalFrontPlacingWorst(){
        PrivateBoard privateBoard = new PrivateBoard();
        privateBoard.getPlacingCoordinates().add(new Coordinates(0,1));
        assertFalse(privateBoard.checkPlacing(goldResourceCardCreator(), new Coordinates(0,1), CardFace.FRONT));
    }

    /**
     * This test ensures that a Golden Card whose requirements are partially satisfied can't be placed on the FRONT
     */
    @Test
    void checkGoldenCardIllegalFrontPlacingClose(){
        PrivateBoard privateBoard = new PrivateBoard();
        privateBoard.getPlacingCoordinates().add(new Coordinates(0,1));
        privateBoard.getElementsCounter().replace(CardElements.FUNGI, 2);
        assertFalse(privateBoard.checkPlacing(goldResourceCardCreator(), new Coordinates(0,1), CardFace.FRONT));
    }

    /**
     * This test ensures that a GoldenCard can always be placed on the BACK in the case also the requirements are satisfied
     */
    @Test
    void checkGoldenCardLegalBackPlacing(){
        PrivateBoard privateBoard = new PrivateBoard();
        privateBoard.getPlacingCoordinates().add(new Coordinates(0,1));
        privateBoard.getElementsCounter().replace(CardElements.FUNGI, 2);
        privateBoard.getElementsCounter().replace(CardElements.ANIMAL, 1);
        assertTrue(privateBoard.checkPlacing(goldResourceCardCreator(), new Coordinates(0,1), CardFace.BACK));
    }

    /**
     * This test ensures that a GoldenCard can always be placed on the BACK even if the requirements aren't satisfied
     */
    @Test
    void checkGoldenCardIllegalBackPlacingWorst(){
        PrivateBoard privateBoard = new PrivateBoard();
        privateBoard.getPlacingCoordinates().add(new Coordinates(0,1));
        assertTrue(privateBoard.checkPlacing(goldResourceCardCreator(), new Coordinates(0,1), CardFace.BACK));
    }

    /**
     * This test ensures that a GoldenCard can always be placed on the BACK even if the requirements are partially satisfied
     */
    @Test
    void checkGoldenCardIllegalBackPlacingClose(){
        PrivateBoard privateBoard = new PrivateBoard();
        privateBoard.getPlacingCoordinates().add(new Coordinates(0,1));
        privateBoard.getElementsCounter().replace(CardElements.FUNGI, 2);
        assertTrue(privateBoard.checkPlacing(goldResourceCardCreator(), new Coordinates(0,1), CardFace.BACK));
    }


    //Place a Card Testing

    /**
     * This test ensures that a ResourceCard, if been chosen to be placed and doesn't pass the placing check,
     * it can't be placed and ensures that the status of the cardGrid didn't change.
     * NOTE: Only one edge could be covered
     */
    @Test
    void illegalSimplePlacingOnBack() {
        PrivateBoard privateBoard = new PrivateBoard();
        //Add a Card on the Grid in order to do the placing
        privateBoard.getCardGrid().add(placedResourceCardCreator(0,1,CardFace.BACK));
        privateBoard.refreshPlacingCoordinates();
        if(privateBoard.checkPlacing(resourceCardCreator(), new Coordinates(0,400), CardFace.BACK)) {
            privateBoard.placing(resourceCardCreator(), new Coordinates(0,400), CardFace.BACK);
        }
        //The Card hasn't been added to the Grid
        assertEquals(1, privateBoard.getCardGrid().size());
        //The only Card is the one that's been already placed by the tester and not the one chosen by the Player
        assertTrue(privateBoard.getCardGrid().get(0).getCoordinates().equals(new Coordinates(0,1)));
    }

    /**
     * This test ensures that a Card, that pass the placing check by Coordinates, can be placed and, once placed,
     * ensures the presence of that Card in the cardGrid by checking the Coordinates attribute.
     * Lastly it ensures that the coverage of each card is correctly set.
     * NOTE: Only one edge is covered
     */
    @Test
    void legalSimplePlacingOnBack() {
        PrivateBoard privateBoard = new PrivateBoard();
        //Add a Card on the Grid in order to do the placing
        privateBoard.getCardGrid().add(placedResourceCardCreator(0,1,CardFace.BACK));
        privateBoard.getPlacingCoordinates().add(new Coordinates(1,1));
        privateBoard.getPlacingCoordinates().add(new Coordinates(0,0));
        privateBoard.getPlacingCoordinates().add(new Coordinates(0,2));
        if(privateBoard.checkPlacing(resourceCardCreator(), new Coordinates(0,2), CardFace.BACK)) {
            privateBoard.placing(resourceCardCreator(), new Coordinates(0,2), CardFace.BACK);
        }
        //The Card has been added to the Grid
        //CardGrid size check
        assertEquals(2, privateBoard.getCardGrid().size());
        //Checks that the last Card of the grid is the Card been placed by checking the coordinates
        assertTrue(privateBoard.getCardGrid().getLast().getCoordinates().equals(new Coordinates(0, 2)));
        //Check that the card just placed has the related edge correctly set to TAKEN
        assertEquals(EdgeState.TAKEN, privateBoard.getCardGrid().getLast().getEdgeCoverage().get(2));
        //Check that the card with the edge covered has the related edge correctly set to TAKEN
        assertEquals(EdgeState.TAKEN, privateBoard.getCardGrid().getFirst().getEdgeCoverage().get(1));
        //Accessory test to see if the rest of the edges of the Card with the edge covered are set to free
        assertEquals(EdgeState.FREE, privateBoard.getCardGrid().getFirst().getEdgeCoverage().get(0));
        assertEquals(EdgeState.FREE, privateBoard.getCardGrid().getFirst().getEdgeCoverage().get(0));
        assertEquals(EdgeState.FREE, privateBoard.getCardGrid().getFirst().getEdgeCoverage().get(3));

    }

    /**
     * This test ensures that in case of a placing that covers multiple edges, all the edges are correctly set to TAKEN
     * NOTE: It is displayed only the legal test case because it's impossible to see Coordinates in placingCoordinates
     * that represent illegal Coordinates due to a possible multiple placing with a NONE EdgeState that is going to be
     * covered. The refreshPlacingCoordinates ensures that these type of Coordinates are not added in placingCoordinates
     */
    @Test
    void legalMultiplePlacing() {
        PrivateBoard privateBoard = new PrivateBoard();
        //Add adjacent Cards on the grid, in the center Coordinate (2,2) the Card will be placed
        privateBoard.getCardGrid().add(placedResourceCardCreator(1,2,CardFace.BACK));
        privateBoard.getCardGrid().add(placedResourceCardCreator(2,3,CardFace.BACK));
        privateBoard.getCardGrid().add(placedResourceCardCreator(2,1,CardFace.BACK));
        privateBoard.getCardGrid().add(placedResourceCardCreator(3,2,CardFace.BACK));
        //Assume the refreshingCoordinates method was executed, let's add the coordinate designed
        privateBoard.getPlacingCoordinates().add(new Coordinates(2,2));
        //Let's place the Card
        if(privateBoard.checkPlacing(resourceCardCreator(), new Coordinates(2,2), CardFace.BACK)) {
            privateBoard.placing(resourceCardCreator(), new Coordinates(2,2), CardFace.BACK);
        }
        //The Card has been added to the Grid
        //CardGrid size check
        assertEquals(5, privateBoard.getCardGrid().size());
        //Checks that the last Card of the grid is the Card been placed by checking the coordinates
        assertTrue(privateBoard.getCardGrid().getLast().getCoordinates().equals(new Coordinates(2, 2)));
        //Check that the card just placed has all the related edge correctly set to TAKEN
        assertEquals(EdgeState.TAKEN, privateBoard.getCardGrid().getLast().getEdgeCoverage().get(0));
        assertEquals(EdgeState.TAKEN, privateBoard.getCardGrid().getLast().getEdgeCoverage().get(1));
        assertEquals(EdgeState.TAKEN, privateBoard.getCardGrid().getLast().getEdgeCoverage().get(2));
        assertEquals(EdgeState.TAKEN, privateBoard.getCardGrid().getLast().getEdgeCoverage().get(3));
        //Check that the adjacent cards with the edge covered have the related edge correctly set to TAKEN
        assertEquals(EdgeState.TAKEN, privateBoard.getCardGrid().get(0).getEdgeCoverage().get(3));
        assertEquals(EdgeState.TAKEN, privateBoard.getCardGrid().get(1).getEdgeCoverage().get(2));
        assertEquals(EdgeState.TAKEN, privateBoard.getCardGrid().get(2).getEdgeCoverage().get(1));
        assertEquals(EdgeState.TAKEN, privateBoard.getCardGrid().get(3).getEdgeCoverage().get(0));
    }




    //Refresh Score Testing

    /**
     * This test ensures that no points will be added if a ResourceCard is placed with BACK CardFace
     */
    @Test
    void resourceCardScoreOnBack() {
        PrivateBoard privateBoard = new PrivateBoard();
        //Card To Place Creation
        ResourceCard cardToPlace = resourceCardCreator();
        //CardGrid setup in order to get a position to place in Coordinates (2,3)
        privateBoard.getCardGrid().add(placedResourceCardCreator(2, 2, CardFace.BACK));
        privateBoard.getPlacingCoordinates().add(new Coordinates(2,3));
        privateBoard.placing(cardToPlace, new Coordinates(2,3), CardFace.BACK);
        //Test that the score increase is 0
        assertEquals(0,privateBoard.refreshPoints());
    }

    /**
     * This test ensures that no points will be added if a ResourceCard is placed with FRONT CardFace and got 0 points on
     */
    @Test
    void resourceCardScoreOnFrontNormal() {
        PrivateBoard privateBoard = new PrivateBoard();
        //Card To Place Creation
        ResourceCard cardToPlace = resourceCardCreator();
        //CardGrid setup in order to get a position to place in Coordinates (2,3)
        privateBoard.getCardGrid().add(placedResourceCardCreator(2, 2, CardFace.BACK));
        privateBoard.getPlacingCoordinates().add(new Coordinates(2,3));
        privateBoard.placing(cardToPlace, new Coordinates(2,3), CardFace.FRONT);
        //Test that the score increase is 0
        assertEquals(0,privateBoard.refreshPoints());
    }

    /**
     * This test ensures that points will be added if a ResourceCard is placed with FRONT CardFace and got some points on
     */
    @Test
    void resourceCardScoreOnFrontWithPoints() {
        PrivateBoard privateBoard = new PrivateBoard();
        //Card To Place Creation, the Card has 1 as point shown
        ResourceCard cardToPlace = resourceCardWithPointsCreator();
        //CardGrid setup in order to get a position to place in Coordinates (2,3)
        privateBoard.getCardGrid().add(placedResourceCardCreator(2, 2, CardFace.BACK));
        privateBoard.getPlacingCoordinates().add(new Coordinates(2,3));
        privateBoard.placing(cardToPlace, new Coordinates(2,3), CardFace.FRONT);
        //Test that the score increase is 1
        assertEquals(1,privateBoard.refreshPoints());
    }

    /**
     *
     */
    @Test
    void goldCardOnBack() {
        PrivateBoard privateBoard = new PrivateBoard();
        //Card To Place Creation, it creates a GoldResourceCard
        GoldResourceCard cardToPlace = goldResourceCardCreator();
        //CardGrid setup in order to get a position to place in Coordinates (2,3)
        privateBoard.getCardGrid().add(placedResourceCardCreator(2, 2, CardFace.BACK));
        privateBoard.getPlacingCoordinates().add(new Coordinates(2,3));
        privateBoard.placing(cardToPlace, new Coordinates(2,3), CardFace.BACK);
        //Test that the score increase is 0
        assertEquals(0,privateBoard.refreshPoints());
    }

    /**
     * This test ensures that a GoldResourceCard with just 1 QUILL present on the Grid (in this case on the placed Card),
     * returns 2 which are the points shown on the GoldResourceCard
     */
    @Test
    void goldCardObjectScoreTypeSingle(){
        PrivateBoard privateBoard = new PrivateBoard();
        //Card To Place Creation, the Card has 2 as point shown, an ObjectScoreType and a QUILL as ObjectScoreType element
        GoldResourceCard cardToPlace = goldResourceCardObjectScoreTypeCreator();
        //CardGrid setup in order to get a position to place in Coordinates (2,3)
        privateBoard.getCardGrid().add(placedResourceCardCreator(2, 2, CardFace.BACK));
        privateBoard.getPlacingCoordinates().add(new Coordinates(2,3));
        //Assume that the Card has passed the requirements checking for placing
        privateBoard.placing(cardToPlace, new Coordinates(2,3), CardFace.FRONT);
        //Assume that the refreshElementsCounter method was called
        privateBoard.getElementsCounter().replace(CardElements.QUILL, 1);
        //Test that the score increase is 2
        assertEquals(2,privateBoard.refreshPoints());
    }

    /**
     * This test ensures that a GoldResourceCard with 3 QUILL present on the Grid (1 on the Card),
     * returns 6 which are the points shown on the GoldResourceCard multiplied by the amount of QUILLs on the cardGrid
     */
    @Test
    void goldCardObjectScoreTypeMultiplierApplied(){
        PrivateBoard privateBoard = new PrivateBoard();
        //Card To Place Creation, the Card has 2 as point shown, an ObjectScoreType and a QUILL as ObjectScoreType element
        GoldResourceCard cardToPlace = goldResourceCardObjectScoreTypeCreator();
        //CardGrid setup in order to get a position to place in Coordinates (2,3)
        privateBoard.getCardGrid().add(placedResourceCardCreator(2, 2, CardFace.BACK));
        privateBoard.getPlacingCoordinates().add(new Coordinates(2,3));
        //Assume that the Card has passed the requirements checking for placing
        privateBoard.placing(cardToPlace, new Coordinates(2,3), CardFace.FRONT);
        //Assume that the refreshElementsCounter method was called
        privateBoard.getElementsCounter().replace(CardElements.QUILL, 3);
        //Test that the score increase is 6 = 3 QUILLs * 2 points
        assertEquals(6,privateBoard.refreshPoints());
    }

    /**
     * This test ensures that a GoldResourceCard with a NormalScoreType increases the score by using only the points
     * shown on the Card.
     * NOTE: Similar behaviour to the ResourceCards with points on them
     */
    @Test
    void goldCardNormalScoreType(){
        PrivateBoard privateBoard = new PrivateBoard();
        //Card To Place Creation, the Card has 3 as point shown and a NormalScoreType
        GoldResourceCard cardToPlace = goldResourceCardNormalScoreTypeCreator();
        //CardGrid setup in order to get a position to place in Coordinates (2,3)
        privateBoard.getCardGrid().add(placedResourceCardCreator(2, 2, CardFace.BACK));
        privateBoard.getPlacingCoordinates().add(new Coordinates(2,3));
        //Assume that the Card has passed the requirements checking for placing
        privateBoard.placing(cardToPlace, new Coordinates(2,3), CardFace.FRONT);
        //Test that the score increase is 3
        assertEquals(3,privateBoard.refreshPoints());
    }

    /**
     * This test ensures that a GoldResourceCard with a CoverageScoreType increases the score by multiplying the only
     * edge covered with the number of points shown on a Card
     */
    @Test
    void goldCardCoverageScoreTypeOneEdge(){
        PrivateBoard privateBoard = new PrivateBoard();
        //Card To Place Creation, the Card has 2 as point shown and a CoverageScoreType
        GoldResourceCard cardToPlace = goldResourceCardCoverageScoreTypeCreator();
        //CardGrid setup in order to get a position to place in Coordinates (2,3)
        privateBoard.getCardGrid().add(placedResourceCardCreator(2, 2, CardFace.BACK));
        privateBoard.getPlacingCoordinates().add(new Coordinates(2,3));
        //Assume that the Card has passed the requirements checking for placing
        privateBoard.placing(cardToPlace, new Coordinates(2,3), CardFace.FRONT);
        //Test that the score increase is 2
        assertEquals(2,privateBoard.refreshPoints());
    }

    /**
     * This test ensures that a GoldResourceCard with a CoverageScoreType increases the score by multiplying the 4
     * edges covered with the number of points shown on a Card
     */
    @Test
    void goldCardCoverageScoreTypeFourEdges(){
        PrivateBoard privateBoard = new PrivateBoard();
        //Card To Place Creation, the Card has 2 as point shown and a CoverageScoreType
        GoldResourceCard cardToPlace = goldResourceCardCoverageScoreTypeCreator();
        //Add adjacent Cards on the grid, in the center Coordinate (2,2) the Card will be placed
        privateBoard.getCardGrid().add(placedResourceCardCreator(1,2,CardFace.BACK));
        privateBoard.getCardGrid().add(placedResourceCardCreator(2,3,CardFace.BACK));
        privateBoard.getCardGrid().add(placedResourceCardCreator(2,1,CardFace.BACK));
        privateBoard.getCardGrid().add(placedResourceCardCreator(3,2,CardFace.BACK));
        //Assume the refreshingCoordinates method was executed, let's add the coordinate designed
        privateBoard.getPlacingCoordinates().add(new Coordinates(2,2));
        //Assume that the Card has passed the requirements checking for placing
        privateBoard.placing(cardToPlace, new Coordinates(2,2), CardFace.FRONT);
        //Test that the score increase is 8 = 2 points * 4 Edges Covered
        assertEquals(8,privateBoard.refreshPoints());
    }

    //Refresh Coordinates Testing
    @Test
    void checkCorrectRefreshing(){

    }

    //Tests that will be updated in the next days
        /*
    @Test
    void refreshPoints() {
    }

    @Test
    void refreshElementsCounter() {
    }

    @Test
    void refreshPlacingCoordinates() {
    }

     */
}