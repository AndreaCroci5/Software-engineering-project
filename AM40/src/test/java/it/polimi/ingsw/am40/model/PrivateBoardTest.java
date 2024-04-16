package it.polimi.ingsw.am40.model;

import it.polimi.ingsw.am40.model.scoreStrategy.CoverageScoreType;
import it.polimi.ingsw.am40.model.scoreStrategy.NormalScoreType;
import it.polimi.ingsw.am40.model.scoreStrategy.ObjectScoreType;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

//TODO Javadoc the last tests
//INDEX: 17 private methods for factory, 227 card flow, 281 check placing,
//       370 placing, 465 refresh score, 633 refresh placingCoordinates
//       1163 refresh ElementsCounter

class PrivateBoardTest {

    //Factory
    //GENERAL CARD
    /**
     * This method creates a full FUNGI ResourceCard with all FREE EdgeState
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


    //SPECIFIC CARDS

    /**
     * This method creates a full FUNGI ResourceCard with all FREE EdgeState, with the only exception that
     * the top-right corner is NONE
     * @return the ResourceCard with a top-right corner NONE
     */
    private ResourceCard resourceCardCreatorWithEdgeNoneTR(){
        ArrayList<CardElements> exampleCardElements = new ArrayList<>();
        exampleCardElements.add(CardElements.FUNGI);
        exampleCardElements.add(CardElements.NONE);
        exampleCardElements.add(CardElements.FUNGI);
        exampleCardElements.add(CardElements.FUNGI);
        ResourceCard card = new ResourceCard(0, CardElements.FUNGI, exampleCardElements);
        return card;
    }

    /**
     * This method creates a full FUNGI ResourceCard with all FREE EdgeState, with the only exception that
     * the top-right and the bottom-right corner are NONE
     * @return the ResourceCard with NONE on top-right and bottom-right corners
     */
    private ResourceCard resourceCardCreatorWithEdgesNoneTRnBR(){
        ArrayList<CardElements> exampleCardElements = new ArrayList<>();
        exampleCardElements.add(CardElements.FUNGI);
        exampleCardElements.add(CardElements.NONE);
        exampleCardElements.add(CardElements.FUNGI);
        exampleCardElements.add(CardElements.NONE);
        ResourceCard card = new ResourceCard(0, CardElements.FUNGI, exampleCardElements);
        return card;
    }

    /**
     * This method creates a full FUNGI ResourceCard with all FREE EdgeState, with the only exception that
     * the bottom-left corner is NONE
     * @return the ResourceCard with a bottom-left corner NONE
     */
    private ResourceCard resourceCardCreatorWithEdgeNoneBL(){
        ArrayList<CardElements> exampleCardElements = new ArrayList<>();
        exampleCardElements.add(CardElements.FUNGI);
        exampleCardElements.add(CardElements.FUNGI);
        exampleCardElements.add(CardElements.NONE);
        exampleCardElements.add(CardElements.FUNGI);
        ResourceCard card = new ResourceCard(0, CardElements.FUNGI, exampleCardElements);
        return card;
    }

    /**
     * This method creates a full FUNGI ResourceCard with all FREE EdgeState, with the only exception that
     * the top-left corner is NONE
     * @return the ResourceCard with a top-left corner NONE
     */
    private ResourceCard resourceCardCreatorWithEdgeNoneTL(){
        ArrayList<CardElements> exampleCardElements = new ArrayList<>();
        exampleCardElements.add(CardElements.NONE);
        exampleCardElements.add(CardElements.FUNGI);
        exampleCardElements.add(CardElements.FUNGI);
        exampleCardElements.add(CardElements.FUNGI);
        ResourceCard card = new ResourceCard(0, CardElements.FUNGI, exampleCardElements);
        return card;
    }

    //CARDS WITH ID
    /**
     * This method creates a ResourceCard with Points on it.
     * NOTE: it creates the Card with ID = 8
     * @return ID 8 ResourceCard
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
     * This method creates the Golden Resource Card with ID 41 for generic purpose
     * @return ID 41 GoldResourceCard
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
     * @return ID 41 GoldResourceCard
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
     * that represent illegal Coordinates due to a possible multiple placing with a NONE Edge that is going to be
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




    //Refresh placingCoordinates Testing

    //SIMPLE CASES: One Card covers an edge of another one Card

    /**
     * This test ensures that, given a card already placed and with the related new Coordinates for future placing already refreshed,
     * the Card, that will be placed at legal Coordinates chosen by a Player, removes those Coordinates from the placingCoordinates ArrayList
     * and correctly adds the new adjacentCoordinates in case its edges are FREE
     * NOTE: placingCoordinates size : | before placing 4 | refresh: after placing 3, after new adjacentCoordinates 6
     */
    @Test
    void coordinatesRefreshingSingleResourceCardFreeEdges(){
        PrivateBoard privateBoard = new PrivateBoard();
        ResourceCard cardToPlace = resourceCardCreator();
        //Setup of the cardGrid
        //Assume that the placing was made correctly
        privateBoard.getCardGrid().add(placedResourceCardCreator(2,2,CardFace.BACK));
        //Assume that refresh Coordinates was made correctly till this moment
        privateBoard.getPlacingCoordinates().add(new Coordinates(1,2));
        privateBoard.getPlacingCoordinates().add(new Coordinates(2,1));
        privateBoard.getPlacingCoordinates().add(new Coordinates(2,3));
        privateBoard.getPlacingCoordinates().add(new Coordinates(3,2));
        //Card placing phase, NOTE: The checking of the placement is skipped for obvious reasons
        privateBoard.placing(cardToPlace, new Coordinates(2,3), CardFace.FRONT);
        //Refresh placing Coordinates by using the method we want to test
        privateBoard.refreshPlacingCoordinates();
        //Test that the coordinate of the Card just placed are removed and the method didn't add the Coordinates
        //of the Card already placed
        for(Coordinates c: privateBoard.getPlacingCoordinates()){
            assertTrue(!c.equals(new Coordinates(2,3)));
            assertTrue(!c.equals(new Coordinates(2,2)));
        }
        // Test that the coordinates of possible future placing are 6 = (4-1) old + 3 new
        assertEquals(6, privateBoard.getPlacingCoordinates().size());
    }

    /**
     * This test ensures that, given a card already placed and with the related new Coordinates for future placing already refreshed,
     * the Card, that will be placed at legal Coordinates chosen by a Player, removes those Coordinates from the placingCoordinates ArrayList
     * and correctly adds the new adjacentCoordinates in case its edges are FREE and do not add in NONE case
     * Top-right NONE Edge case
     * NOTE: placingCoordinates size : | before placing 4 | refresh: after placing 3, after new adjacentCoordinates 5
     */
    @Test
    void coordinatesRefreshingSingleResourceCardWithTREdgeNone(){
        PrivateBoard privateBoard = new PrivateBoard();
        ResourceCard cardToPlace = resourceCardCreatorWithEdgeNoneTR();
        //Setup of the cardGrid
        //Assume that the placing was made correctly
        privateBoard.getCardGrid().add(placedResourceCardCreator(2,2,CardFace.BACK));
        //Assume that refresh Coordinates was made correctly till this moment
        privateBoard.getPlacingCoordinates().add(new Coordinates(1,2));
        privateBoard.getPlacingCoordinates().add(new Coordinates(2,1));
        privateBoard.getPlacingCoordinates().add(new Coordinates(2,3));
        privateBoard.getPlacingCoordinates().add(new Coordinates(3,2));
        //Card placing phase
        privateBoard.placing(cardToPlace, new Coordinates(2,3), CardFace.FRONT);
        //Refresh placing Coordinates by using the method we want to test
        privateBoard.refreshPlacingCoordinates();
        //Test that: the coordinate of the Card just placed are removed ,the method didn't add the Coordinates
        //of the Card already placed and the coordinates (2,4) aren't present due to the Card EdgeCoverage
        for(Coordinates c: privateBoard.getPlacingCoordinates()){
            assertTrue(!c.equals(new Coordinates(2,3)));
            assertTrue(!c.equals(new Coordinates(2,2)));
            assertTrue(!c.equals(new Coordinates(2,4)));
        }
        // Test that the coordinates of possible future placing are 5 = (4-1) old + 2 new
        assertEquals(5, privateBoard.getPlacingCoordinates().size());
    }

    /**
     * This test ensures that, given a card already placed and with the related new Coordinates for future placing already refreshed,
     * the Card, that will be placed at legal Coordinates chosen by a Player, removes those Coordinates from the placingCoordinates ArrayList
     * and correctly adds the new adjacentCoordinates in case its edges are FREE and do not add in NONE case
     * Top-right and bottom-right NONE Edge case
     * NOTE: placingCoordinates size : | before placing 4 | refresh: after placing 3, after new adjacentCoordinates 5
     */
    @Test
    void coordinatesRefreshingSingleResourceCardWithTRnBREdgesNone(){
        PrivateBoard privateBoard = new PrivateBoard();
        ResourceCard cardToPlace = resourceCardCreatorWithEdgesNoneTRnBR();
        //Setup of the cardGrid
        //Assume that the placing was made correctly
        privateBoard.getCardGrid().add(placedResourceCardCreator(2,2,CardFace.BACK));
        //Assume that refresh Coordinates was made correctly till this moment
        privateBoard.getPlacingCoordinates().add(new Coordinates(1,2));
        privateBoard.getPlacingCoordinates().add(new Coordinates(2,1));
        privateBoard.getPlacingCoordinates().add(new Coordinates(2,3));
        privateBoard.getPlacingCoordinates().add(new Coordinates(3,2));
        //Card placing phase
        privateBoard.placing(cardToPlace, new Coordinates(2,3), CardFace.FRONT);
        //Refresh placing Coordinates by using the method we want to test
        privateBoard.refreshPlacingCoordinates();
        //Test that the coordinate of the Card just placed are removed and the method didn't add the Coordinates
        //of the Card already placed and the coordinates (2,4) aren't present due to the Card EdgeCoverage
        for(Coordinates c: privateBoard.getPlacingCoordinates()){
            assertTrue(!c.equals(new Coordinates(2,3)));
            assertTrue(!c.equals(new Coordinates(2,2)));
            assertTrue(!c.equals(new Coordinates(2,4)));
            assertTrue(!c.equals(new Coordinates(3,3)));
        }
        // Test that the coordinates of possible future placing are 4 = (4-1) old + 1 new
        assertEquals(4, privateBoard.getPlacingCoordinates().size());
    }

    /**
     * This test ensures that, given a card already placed and with the related new Coordinates for future placing already refreshed,
     * the Card, that will be placed at legal Coordinates chosen by a Player, removes those Coordinates from the placingCoordinates ArrayList
     * and correctly adds the new adjacentCoordinates in case its edges are FREE, even if the Edge, that covers the edge
     * of the card already set on the grid, is set to NONE. (Legal move by the rules)
     * This test ensures that in this case, the refreshPlacingCoordinates behaves like in the case of a Card with all edges
     * set to FREE before placing
     * NOTE: placingCoordinates size : | before placing 4 | refresh: after placing 3, after new adjacentCoordinates 6
     */
    @Test
    void coordinatesRefreshingSingleResourceCardWithBLEdgeNone(){
        PrivateBoard privateBoard = new PrivateBoard();
        ResourceCard cardToPlace = resourceCardCreatorWithEdgeNoneBL();
        //Setup of the cardGrid
        //Assume that the placing was made correctly
        privateBoard.getCardGrid().add(placedResourceCardCreator(2,2,CardFace.BACK));
        //Assume that refresh Coordinates was made correctly till this moment
        privateBoard.getPlacingCoordinates().add(new Coordinates(1,2));
        privateBoard.getPlacingCoordinates().add(new Coordinates(2,1));
        privateBoard.getPlacingCoordinates().add(new Coordinates(2,3));
        privateBoard.getPlacingCoordinates().add(new Coordinates(3,2));
        //Card placing phase
        privateBoard.placing(cardToPlace, new Coordinates(2,3), CardFace.FRONT);
        //Refresh placing Coordinates by using the method we want to test
        privateBoard.refreshPlacingCoordinates();
        //Test that: the coordinate of the Card just placed are removed ,the method didn't add the Coordinates
        //of the Card already placed
        for(Coordinates c: privateBoard.getPlacingCoordinates()){
            assertTrue(!c.equals(new Coordinates(2,3)));
            assertTrue(!c.equals(new Coordinates(2,2)));
        }
        // Test that the coordinates of possible future placing are 6 = (4-1) old + 3 new
        assertEquals(6, privateBoard.getPlacingCoordinates().size());
    }


    //COMPLICATED PREVISION FUTURE MULTIPLE COVER CASES:
    // One of the possible coordinates that a player can choose for the future placing might cover
    // multiple edges of the Cards placed in the cardGrid

    /**
     * This test ensures that Coordinates refresh for a possible multiple corner cover placing is possible.
     * In this case we have a Card in (2,2) and a Card in (2,3) already placed and with placingCoordinates attribute
     * already up to date.
     * We start the test by placing a Card in (3,2) with BACK CardFace creating a conflict in Coordinates (3,3). This test ensures that
     * the placingCoordinates remains the same with the old coordinates, adds the new Coordinates which are not in conflict
     * and, at last, removes the conflict Coordinates to check if the Edges conditions makes possible a future placing
     * and, then, adds the coordinates once the check is passed
     */
    @Test
    void coordinatesRefreshingMultipleCoverFreeEdgesCardPlacedOnBACK() {
        PrivateBoard privateBoard = new PrivateBoard();
        //Card with all FREE EdgeState
        ResourceCard cardToPlace = resourceCardCreator();
        //Setup of the cardGrid
        //Assume that all the placing was made correctly
        privateBoard.getCardGrid().add(placedResourceCardCreator(2,2,CardFace.BACK));
        privateBoard.getCardGrid().get(0).getEdgeCoverage().set(3, EdgeState.TAKEN);
        privateBoard.getCardGrid().add(placedResourceCardCreator(2,3,CardFace.BACK));
        privateBoard.getCardGrid().get(1).getEdgeCoverage().set(2, EdgeState.TAKEN);
        //Assume that all refresh Coordinates was made correctly till this moment
        privateBoard.getPlacingCoordinates().add(new Coordinates(1, 2));
        privateBoard.getPlacingCoordinates().add(new Coordinates(2, 1));
        privateBoard.getPlacingCoordinates().add(new Coordinates(3, 2));
        privateBoard.getPlacingCoordinates().add(new Coordinates(1, 3));
        privateBoard.getPlacingCoordinates().add(new Coordinates(2, 4));
        privateBoard.getPlacingCoordinates().add(new Coordinates(3, 3));
        //Check to see if the Coordinates setup is correct
        assertEquals(6, privateBoard.getPlacingCoordinates().size());
        //Place the Card in (3,2) in order to create a conflict in (3,3) coordinate
        privateBoard.placing(cardToPlace, new Coordinates(3,2), CardFace.BACK);
        assertEquals(3, privateBoard.getCardGrid().size());
        privateBoard.refreshPlacingCoordinates();
        //Test by checking that all is coherent:
        for(Coordinates c: privateBoard.getPlacingCoordinates()){
            //Test that there aren't Coordinates in placingCoordinates equal to the Coordinates of the Card placed in cardGrid
            assertTrue(!c.equals(new Coordinates(2,3)));
            assertTrue(!c.equals(new Coordinates(2,2)));
            assertTrue(!c.equals(new Coordinates(3,2)));
        }
        //Test that the Coordinates responsible for the conflict of multiple placing is present in placingCoordinates
        Coordinates coordsToTest = new Coordinates(10,10);
        int counter = 0;
        for(Coordinates c: privateBoard.getPlacingCoordinates()){
            if(c.equals(new Coordinates(3,3))){
                coordsToTest = new Coordinates(3,3);
                counter++;
            }

        }
        assertTrue(coordsToTest.equals(new Coordinates(3,3)));
        //Test that there is no repetition in the placingCoordinates of the Coordinates 3,3
        assertEquals(1, counter);
        //Test that the coordinates of possible future placing are  = (6-1) old --> for (5-1) presence of 3,3 --> 4+3 refresh
        assertEquals(7, privateBoard.getPlacingCoordinates().size());

    }

    /**
     * This test ensures that Coordinates refresh for a possible multiple corner cover placing is possible.
     * In this case we have a Card in (2,2) and a Card in (2,3) already placed and with placingCoordinates attribute
     * already up to date.
     * We start the test by placing a Card in (3,2) on FRONT CardFace creating a conflict in Coordinates (3,3). This test ensures that
     * the placingCoordinates remains the same with the old coordinates, adds the new Coordinates which are not in conflict
     * and, at last, removes the conflict Coordinates to check if the Edges conditions makes possible a future placing
     * and, then, adds the coordinates once the check is passed
     */
    @Test
    void coordinatesRefreshingMultipleCoverFreeEdgesCardPlacedOnFRONT() {
        PrivateBoard privateBoard = new PrivateBoard();
        //Card with all FREE EdgeState
        ResourceCard cardToPlace = resourceCardCreator();
        //Setup of the cardGrid
        //Assume that all the placing was made correctly
        privateBoard.getCardGrid().add(placedResourceCardCreator(2,2,CardFace.BACK));
        privateBoard.getCardGrid().get(0).getEdgeCoverage().set(3, EdgeState.TAKEN);
        privateBoard.getCardGrid().add(placedResourceCardCreator(2,3,CardFace.BACK));
        privateBoard.getCardGrid().get(1).getEdgeCoverage().set(2, EdgeState.TAKEN);
        //Assume that all refresh Coordinates was made correctly till this moment
        privateBoard.getPlacingCoordinates().add(new Coordinates(1, 2));
        privateBoard.getPlacingCoordinates().add(new Coordinates(2, 1));
        privateBoard.getPlacingCoordinates().add(new Coordinates(3, 2));
        privateBoard.getPlacingCoordinates().add(new Coordinates(1, 3));
        privateBoard.getPlacingCoordinates().add(new Coordinates(2, 4));
        privateBoard.getPlacingCoordinates().add(new Coordinates(3, 3));
        //Check to see if the Coordinates setup is correct
        assertEquals(6, privateBoard.getPlacingCoordinates().size());
        //Place the Card in (3,2) in order to create a conflict in (3,3) coordinate
        privateBoard.placing(cardToPlace, new Coordinates(3,2), CardFace.FRONT);
        assertEquals(3, privateBoard.getCardGrid().size());
        privateBoard.refreshPlacingCoordinates();
        //Test by checking that all is coherent:
        for(Coordinates c: privateBoard.getPlacingCoordinates()){
            //Test that there aren't Coordinates in placingCoordinates equal to the Coordinates of the Card placed in cardGrid
            assertTrue(!c.equals(new Coordinates(2,3)));
            assertTrue(!c.equals(new Coordinates(2,2)));
            assertTrue(!c.equals(new Coordinates(3,2)));
        }
        //Test that the Coordinates responsible for the conflict of multiple placing is present in placingCoordinates
        Coordinates coordsToTest = new Coordinates(10,10);
        int counter = 0;
        for(Coordinates c: privateBoard.getPlacingCoordinates()){
            if(c.equals(new Coordinates(3,3))){
                coordsToTest = new Coordinates(3,3);
                counter++;
            }
        }
        assertTrue(coordsToTest.equals(new Coordinates(3,3)));
        //Test that there is no repetition in the placingCoordinates of the Coordinates 3,3
        assertEquals(1, counter);
        //Test that the coordinates of possible future placing are  = (6-1) old --> for (5-1) presence of 3,3 --> 4+3 refresh
        assertEquals(7, privateBoard.getPlacingCoordinates().size());

    }

    /**
     * This test ensures that Coordinates refresh for a possible multiple corner cover placing is not possible if
     * an Edge is set as NONE.
     * In this case we have a Card in (2,2) and a Card in (2,3) already placed and with placingCoordinates attribute already up to date.
     * We start the test by placing a Card in (3,2) on FRONT CardFace with a NONE Edge on top-right corner,
     * creating a conflict in Coordinates (3,3). This test ensures that the placingCoordinates
     * remains the same with the old coordinates, adds the new Coordinates which are not in conflict and, at last,
     * removes the conflict Coordinates to check if the Edges conditions makes possible a future placing. In this case,
     * it's the check is not passed, so the test ensures that the Coordinates are not added
     *
     */
    @Test
    void coordinatesRefreshingMultipleCoverOneEdgeNone() {
        PrivateBoard privateBoard = new PrivateBoard();
        //Card with EdgeState NONE on top-left edge
        ResourceCard cardToPlace = resourceCardCreatorWithEdgeNoneTR();
        //Setup of the cardGrid
        //Assume that all the placing was made correctly
        privateBoard.getCardGrid().add(placedResourceCardCreator(2,2,CardFace.BACK));
        privateBoard.getCardGrid().get(0).getEdgeCoverage().set(3, EdgeState.TAKEN);
        privateBoard.getCardGrid().add(placedResourceCardCreator(2,3,CardFace.BACK));
        privateBoard.getCardGrid().get(1).getEdgeCoverage().set(2, EdgeState.TAKEN);
        //Assume that all refresh Coordinates was made correctly till this moment
        privateBoard.getPlacingCoordinates().add(new Coordinates(1, 2));
        privateBoard.getPlacingCoordinates().add(new Coordinates(2, 1));
        privateBoard.getPlacingCoordinates().add(new Coordinates(3, 2));
        privateBoard.getPlacingCoordinates().add(new Coordinates(1, 3));
        privateBoard.getPlacingCoordinates().add(new Coordinates(2, 4));
        privateBoard.getPlacingCoordinates().add(new Coordinates(3, 3));
        //Check to see if the Coordinates setup is correct
        assertEquals(6, privateBoard.getPlacingCoordinates().size());
        //Place the Card in (3,2) in order to create a conflict in (3,3) coordinate
        privateBoard.placing(cardToPlace, new Coordinates(3,2), CardFace.FRONT);
        assertEquals(3, privateBoard.getCardGrid().size());
        privateBoard.refreshPlacingCoordinates();
        //Test by checking that all is coherent:
        for(Coordinates c: privateBoard.getPlacingCoordinates()){
            //Test that there aren't Coordinates in placingCoordinates equal to the Coordinates of the Card placed in cardGrid
            assertTrue(!c.equals(new Coordinates(2,3)));
            assertTrue(!c.equals(new Coordinates(2,2)));
            assertTrue(!c.equals(new Coordinates(3,2)));
            //Test that (3,3) coordinates aren't in placingCoordinates
            assertTrue(!c.equals(new Coordinates(3,3)));
        }
        //Test that the coordinates of possible future placing are  = (6-1) old --> for (5-1) presence of 3,3 --> 4+2 refresh
        assertEquals(6, privateBoard.getPlacingCoordinates().size());
    }


    //THREE CARDS MULTIPLE PLACING COORDINATES CONFLICT


    /**
     * This test ensures that Coordinates refresh for a possible multiple corner cover placing is possible
     * (in this case we have 3 corners in conflict).
     * In this case we have a Card in (2,2), a Card in (2,3) and a Card in (4,3) already placed
     * with placingCoordinates attribute already up to date.
     * We start the test by placing a Card in (3,2) on FRONT CardFace with all FREE EdgeStates, creating again a conflict in Coordinates (3,3), which
     * we assume was already handled successfully when the Card (4,3) was placed.
     * This test ensures that the placingCoordinates remains the same with the old coordinates, adds the new Coordinates which are not in conflict and, at last,
     * removes the conflict Coordinates to check if the Edges conditions makes possible a future placing.
     * In this case, (3,3) must be added to placingCoordinates.
     * PLUS: SIDE EFFECT, there is a conflict also in (4,2), but the case was already tested before.
     *       Anyway, here is also tested that (4,2) is correctly added to placingCoordinates
     */
    @Test
    void coordinatesRefreshingThreeCornersPotentiallyCoverPossible() {
        PrivateBoard privateBoard = new PrivateBoard();
        //Card with EdgeState NONE on top-left edge
        ResourceCard cardToPlace1 = resourceCardCreator();
        //Setup of the cardGrid
        //Assume that all the placing was made correctly
        privateBoard.getCardGrid().add(placedResourceCardCreator(2,2,CardFace.BACK));
        privateBoard.getCardGrid().get(0).getEdgeCoverage().set(3, EdgeState.TAKEN);
        privateBoard.getCardGrid().add(placedResourceCardCreator(2,3,CardFace.BACK));
        privateBoard.getCardGrid().get(1).getEdgeCoverage().set(2, EdgeState.TAKEN);
        privateBoard.getCardGrid().add(placedResourceCardCreator(4,3,CardFace.FRONT));
        //Assume that all refresh Coordinates was made correctly till this moment
        privateBoard.getPlacingCoordinates().add(new Coordinates(1, 2));
        privateBoard.getPlacingCoordinates().add(new Coordinates(2, 1));
        privateBoard.getPlacingCoordinates().add(new Coordinates(3, 2));

        privateBoard.getPlacingCoordinates().add(new Coordinates(1, 3));
        privateBoard.getPlacingCoordinates().add(new Coordinates(2, 4));

        privateBoard.getPlacingCoordinates().add(new Coordinates(3, 3));

        privateBoard.getPlacingCoordinates().add(new Coordinates(4, 4));
        privateBoard.getPlacingCoordinates().add(new Coordinates(5, 3));
        privateBoard.getPlacingCoordinates().add(new Coordinates(4, 2));
        //Check to see if the Coordinates setup is correct
        assertEquals(9, privateBoard.getPlacingCoordinates().size());
        //Place the Card in (3,2) in order to create a conflict in (3,3) coordinate
        privateBoard.placing(cardToPlace1, new Coordinates(3,2), CardFace.FRONT);
        //Test the correct Card addition
        assertEquals(4, privateBoard.getCardGrid().size());
        //Call of the method to test
        privateBoard.refreshPlacingCoordinates();
        for(Coordinates c: privateBoard.getPlacingCoordinates()){
            //Test that there aren't Coordinates in placingCoordinates equal to the Coordinates of the Card placed in cardGrid
            assertTrue(!c.equals(new Coordinates(2,3)));
            assertTrue(!c.equals(new Coordinates(2,2)));
            assertTrue(!c.equals(new Coordinates(3,2)));
            assertTrue(!c.equals(new Coordinates(4,3)));
        }

        //Test that the Coordinates responsible for the main conflict of multiple placing are present in placingCoordinates
        Coordinates coordsToTest = new Coordinates(10,10);
        int counter = 0;
        for(Coordinates c: privateBoard.getPlacingCoordinates()){
            if(c.equals(new Coordinates(3,3))){
                coordsToTest = new Coordinates(3,3);
                counter++;
            }
        }

        //Test that the Coordinates responsible for the side effect conflict of multiple placing are present in placingCoordinates
        coordsToTest = new Coordinates(10,10);
        counter = 0;
        for(Coordinates c: privateBoard.getPlacingCoordinates()){
            if(c.equals(new Coordinates(4,2))){
                coordsToTest = new Coordinates(4,2);
                counter++;
            }
        }

        //Test that the coordinates of possible future placing are in the correct amount
        assertEquals(9, privateBoard.getPlacingCoordinates().size());
    }

    /**
     * This test ensures that Coordinates refresh for a possible multiple corner cover placing is not possible in a particular situation
     * (in this case we have 3 corners in conflict).
     * In this case we have a Card in (2,2), a Card in (2,3) and a Card in (4,3) already placed
     * with placingCoordinates attribute already up to date.
     * We start the test by placing a Card in (3,2) on FRONT CardFace with NONE top-right Edge, creating again a conflict in Coordinates (3,3), which
     * we assume was already handled successfully when the Card (4,3) was placed.
     * This test ensures that the placingCoordinates remains the same with the old coordinates, adds the new Coordinates which are not in conflict and, at last,
     * removes the conflict Coordinates to check if the Edges conditions makes possible a future placing.
     * In this case, (3,3) must not be added to placingCoordinates.
     * PLUS: SIDE EFFECT, there is a conflict also in (4,2), but the case was already tested before.
     *       Anyway, here is also tested that (4,2) is correctly added to placingCoordinates
     */
    @Test
    void coordinatesRefreshingThreeCornersPotentiallyCoveredTwoPlacingNotPossible1() {
        PrivateBoard privateBoard = new PrivateBoard();
        //Card with EdgeState NONE on top-left edge
        ResourceCard cardToPlace1 = resourceCardCreatorWithEdgeNoneTR();
        //Setup of the cardGrid
        //Assume that all the placing was made correctly
        privateBoard.getCardGrid().add(placedResourceCardCreator(2,2,CardFace.BACK));
        privateBoard.getCardGrid().get(0).getEdgeCoverage().set(3, EdgeState.TAKEN);
        privateBoard.getCardGrid().add(placedResourceCardCreator(2,3,CardFace.BACK));
        privateBoard.getCardGrid().get(1).getEdgeCoverage().set(2, EdgeState.TAKEN);
        privateBoard.getCardGrid().add(placedResourceCardCreator(4,3,CardFace.FRONT));
        //Assume that all refresh Coordinates was made correctly till this moment
        privateBoard.getPlacingCoordinates().add(new Coordinates(1, 2));
        privateBoard.getPlacingCoordinates().add(new Coordinates(2, 1));
        privateBoard.getPlacingCoordinates().add(new Coordinates(3, 2));

        privateBoard.getPlacingCoordinates().add(new Coordinates(1, 3));
        privateBoard.getPlacingCoordinates().add(new Coordinates(2, 4));

        privateBoard.getPlacingCoordinates().add(new Coordinates(3, 3));

        privateBoard.getPlacingCoordinates().add(new Coordinates(4, 4));
        privateBoard.getPlacingCoordinates().add(new Coordinates(5, 3));
        privateBoard.getPlacingCoordinates().add(new Coordinates(4, 2));
        //Check to see if the Coordinates setup is correct
        assertEquals(9, privateBoard.getPlacingCoordinates().size());
        //Place the Card in (3,2) in order to create a conflict in (3,3) coordinate
        privateBoard.placing(cardToPlace1, new Coordinates(3,2), CardFace.FRONT);
        //Test the correct Card addition
        assertEquals(4, privateBoard.getCardGrid().size());
        //Call of the method to test
        privateBoard.refreshPlacingCoordinates();
        for(Coordinates c: privateBoard.getPlacingCoordinates()){
            //Test that there aren't Coordinates in placingCoordinates equal to the Coordinates of the Card placed in cardGrid
            assertTrue(!c.equals(new Coordinates(2,3)));
            assertTrue(!c.equals(new Coordinates(2,2)));
            assertTrue(!c.equals(new Coordinates(3,2)));
            assertTrue(!c.equals(new Coordinates(4,3)));
            //Test that (3,3) coordinates aren't in placingCoordinates
            assertTrue(!c.equals(new Coordinates(3,3)));
        }

        //Test that the Coordinates responsible for the side effect conflict of multiple placing are present in placingCoordinates
        Coordinates coordsToTest = new Coordinates(10,10);
        int counter = 0;
        for(Coordinates c: privateBoard.getPlacingCoordinates()){
            if(c.equals(new Coordinates(4,2))){
                coordsToTest = new Coordinates(4,2);
                counter++;
            }
        }
        //Test that the coordinates of possible future placing are in the correct amount
        assertEquals(8, privateBoard.getPlacingCoordinates().size());
    }

    /**
     * This test ensures that Coordinates refresh for a possible multiple corner cover placing is possible (in this case 3).
     * In this case we have a Card in (2,2), a Card in (2,3) and a Card in (4,3) with a NONE top-left EdgeState already placed
     * with placingCoordinates attribute already up to date.
     * We start the test by placing a Card in (3,2) on FRONT CardFace with all FREE EdgeStates, creating again a conflict in Coordinates (3,3),
     * in this case, (3,3) was excluded when the Card in (4,3) was placed due to its NONE top-left Edge, so we do have to
     * test that (3,3) is not considered to be added in placingCoordinates when the last Card is placed in (3,2)
     * This test ensures that the placingCoordinates remains the same with the old coordinates, adds the new Coordinates which are not in conflict and, at last,
     * removes the conflict Coordinates to check if the Edges conditions makes possible a future placing.
     * In this case, (3,3) must not be added to placingCoordinates due to its history.
     * PLUS: SIDE EFFECT, there is a conflict also in (4,2), but the case was already tested before.
     *       Anyway, here is also tested that (4,2) is correctly added to placingCoordinates
     */
    @Test
    void coordinatesRefreshingThreeCornersPotentiallyCoveredTwoPlacingNotPossible2() {
        PrivateBoard privateBoard = new PrivateBoard();
        //Card with FREE EdgeStates to place
        ResourceCard cardToPlace1 = resourceCardCreator();
        //Card to add in setup
        ResourceCard setupCard = resourceCardCreatorWithEdgeNoneTL();
        setupCard.setCoordinates(new Coordinates(4,3));
        setupCard.setFace(CardFace.FRONT);
        //Setup of the cardGrid
        //Assume that all the placing was made correctly
        privateBoard.getCardGrid().add(placedResourceCardCreator(2,2,CardFace.BACK));
        privateBoard.getCardGrid().get(0).getEdgeCoverage().set(3, EdgeState.TAKEN);
        privateBoard.getCardGrid().add(placedResourceCardCreator(2,3,CardFace.BACK));
        privateBoard.getCardGrid().get(1).getEdgeCoverage().set(2, EdgeState.TAKEN);
        privateBoard.getCardGrid().add(setupCard);
        //Assume that all refresh Coordinates was made correctly till this moment
        privateBoard.getPlacingCoordinates().add(new Coordinates(1, 2));
        privateBoard.getPlacingCoordinates().add(new Coordinates(2, 1));
        privateBoard.getPlacingCoordinates().add(new Coordinates(3, 2));

        privateBoard.getPlacingCoordinates().add(new Coordinates(1, 3));
        privateBoard.getPlacingCoordinates().add(new Coordinates(2, 4));

        privateBoard.getPlacingCoordinates().add(new Coordinates(4, 4));
        privateBoard.getPlacingCoordinates().add(new Coordinates(5, 3));
        privateBoard.getPlacingCoordinates().add(new Coordinates(4, 2));
        //Check to see if the Coordinates setup is correct
        assertEquals(8, privateBoard.getPlacingCoordinates().size());
        //Place the Card in (3,2) in order to create a conflict in (3,3) coordinate
        privateBoard.placing(cardToPlace1, new Coordinates(3,2), CardFace.FRONT);
        //Test the correct Card addition
        assertEquals(4, privateBoard.getCardGrid().size());
        //Call of the method to test
        privateBoard.refreshPlacingCoordinates();
        for(Coordinates c: privateBoard.getPlacingCoordinates()){
            //Test that there aren't Coordinates in placingCoordinates equal to the Coordinates of the Card placed in cardGrid
            assertTrue(!c.equals(new Coordinates(2,3)));
            assertTrue(!c.equals(new Coordinates(2,2)));
            assertTrue(!c.equals(new Coordinates(3,2)));
            assertTrue(!c.equals(new Coordinates(4,3)));
            //Test that (3,3) coordinates aren't in placingCoordinates
            assertTrue(!c.equals(new Coordinates(3,3)));
        }
        //Test that the Coordinates responsible for the side effect conflict of multiple placing are present in placingCoordinates
        Coordinates coordsToTest = new Coordinates(10,10);
        int counter = 0;
        for(Coordinates c: privateBoard.getPlacingCoordinates()){
            if(c.equals(new Coordinates(4,2))){
                coordsToTest = new Coordinates(4,2);
                counter++;
            }
        }
        //Test that the coordinates of possible future placing are in the correct amount
        assertEquals(8, privateBoard.getPlacingCoordinates().size());
    }

    //Test RefreshElementsCounter

    /**
     * This test ensures that elementsCounter refresh is executed correctly by checking the number of times how many times
     * an element is present on the cardGrid.
     * Here we have a placed FUNGI card with CardFace FRONT and a FUNGI in every corner.
     * Then we place a copy of the Card onto the top-right corner of the card already placed.
     * In this case we should have 7 FUNGI = 4 - 1 (covered) + 4 (new Card)
     */
    @Test
    void simpleRefreshElementsCounterFRONT() {
        PrivateBoard privateBoard = new PrivateBoard();
        ResourceCard cardToPlace = resourceCardCreator();
        //PrivateBoard setup
        privateBoard.getCardGrid().add(placedResourceCardCreator(1,1,CardFace.FRONT));
        privateBoard.getElementsCounter().replace(CardElements.FUNGI, 4);
        //Place Phase
        privateBoard.placing(cardToPlace, new Coordinates(1, 2), CardFace.FRONT);
        privateBoard.refreshElementsCounter();
        //Test
        assertEquals(7, privateBoard.getElementsCounter().get(CardElements.FUNGI));
    }

    /**
     * This test ensures that elementsCounter refresh is executed correctly by checking the number of times how many times
     * an element is present on the cardGrid.
     * Here we have a placed FUNGI card with CardFace FRONT and a FUNGI in every corner.
     * Then we place a copy of the Card with the only difference of having a NONE onto the bottom left corner.
     * So, we place this copy onto the top-right corner of the card already placed.
     * In this case we should have 6 FUNGI = 4 - 1 (covered) + 3 (new Card)
     */
    @Test
    void simpleRefreshElementsCounterFrontWCardPlacedBLEdgeNONEFRONT() {
        PrivateBoard privateBoard = new PrivateBoard();
        ResourceCard cardToPlace = resourceCardCreatorWithEdgeNoneBL();
        //PrivateBoard setup
        privateBoard.getCardGrid().add(placedResourceCardCreator(1,1,CardFace.FRONT));
        privateBoard.getElementsCounter().replace(CardElements.FUNGI, 4);
        //Place Phase
        privateBoard.placing(cardToPlace, new Coordinates(1, 2), CardFace.FRONT);
        privateBoard.refreshElementsCounter();
        //Test
        assertEquals(6, privateBoard.getElementsCounter().get(CardElements.FUNGI));
    }

    /**
     * This test ensures that elementsCounter refresh is executed correctly by checking the number of times how many times
     * an element is present on the cardGrid.
     * Here we have a placed FUNGI card with CardFace BACK and a FUNGI in every corner on the FRONT.
     * Then we place on the BACK a copy of the Card onto the top-right corner of the card already placed.
     * In this case we should have 2 FUNGI = 1 + 1 (new Card)
     */
    @Test
    void simpleRefreshElementsCounterDoubleBACK() {
        PrivateBoard privateBoard = new PrivateBoard();
        ResourceCard cardToPlace = resourceCardCreator();
        //PrivateBoard setup
        privateBoard.getCardGrid().add(placedResourceCardCreator(1,1,CardFace.BACK));
        privateBoard.getElementsCounter().replace(CardElements.FUNGI, 1);
        //Place Phase
        privateBoard.placing(cardToPlace, new Coordinates(1, 2), CardFace.BACK);
        privateBoard.refreshElementsCounter();
        //Test
        assertEquals(2, privateBoard.getElementsCounter().get(CardElements.FUNGI));
    }

    /**
     * This test ensures that elementsCounter refresh is executed correctly by checking the number of times how many times
     * an element is present on the cardGrid.
     * Here we have a placed FUNGI card with CardFace FRONT and a FUNGI in every corner on the FRONT.
     * Then we place on the BACK a copy of the Card onto the top-right corner of the card already placed.
     * In this case we should have  FUNGI = 4 - 1 (covered) + 1 (new Card)
     */
    @Test
    void simpleRefreshElementsCounterCardPlacedOnBACK() {
        PrivateBoard privateBoard = new PrivateBoard();
        ResourceCard cardToPlace = resourceCardCreator();
        //PrivateBoard setup
        privateBoard.getCardGrid().add(placedResourceCardCreator(1,1,CardFace.FRONT));
        privateBoard.getElementsCounter().replace(CardElements.FUNGI, 4);
        //Place Phase
        privateBoard.placing(cardToPlace, new Coordinates(1, 2), CardFace.BACK);
        privateBoard.refreshElementsCounter();
        //Test
        assertEquals(4, privateBoard.getElementsCounter().get(CardElements.FUNGI));
    }

    /**
     * This test ensures that elementsCounter refresh is executed correctly by checking the number of times how many times
     * an element is present on the cardGrid.
     * Here we have a placed FUNGI card with CardFace FRONT and a FUNGI in every corner on the FRONT.
     * Then we place on the BACK a copy of the Card onto the top-right corner of the card already placed.
     * In this case we should have  FUNGI = 1 + 4 (new Card)
     */
    @Test
    void simpleRefreshElementsCounterAdjacentBACKCardPlacedOnFRONT() {
        PrivateBoard privateBoard = new PrivateBoard();
        ResourceCard cardToPlace = resourceCardCreator();
        //PrivateBoard setup
        privateBoard.getCardGrid().add(placedResourceCardCreator(1,1,CardFace.BACK));
        privateBoard.getElementsCounter().replace(CardElements.FUNGI, 1);
        //Place Phase
        privateBoard.placing(cardToPlace, new Coordinates(1, 2), CardFace.FRONT);
        privateBoard.refreshElementsCounter();
        //Test
        assertEquals(5, privateBoard.getElementsCounter().get(CardElements.FUNGI));
    }


    //TO JAVADOC
    //LAST SINGLE TESTS BY USING NONE
    

    
    //MULTIPLE PLACEMENT REFRESH ELEMENTS COUNTER

    @Test
    void multipleRefreshElementsCounterAllFRONT() {
        PrivateBoard privateBoard = new PrivateBoard();
        ResourceCard cardToPlace = resourceCardCreator();
        //PrivateBoard setup
        privateBoard.getCardGrid().add(placedResourceCardCreator(1,1,CardFace.FRONT));
        privateBoard.getCardGrid().add(placedResourceCardCreator(1,3,CardFace.FRONT));
        privateBoard.getElementsCounter().replace(CardElements.FUNGI, 8);
        //Place Phase
        privateBoard.placing(cardToPlace, new Coordinates(1, 2), CardFace.FRONT);
        privateBoard.refreshElementsCounter();
        //Test
        assertEquals(10, privateBoard.getElementsCounter().get(CardElements.FUNGI));
    }

    @Test
    void multipleRefreshElementsCounterCardPlacedOnBACKOthersFRONT() {
        PrivateBoard privateBoard = new PrivateBoard();
        ResourceCard cardToPlace = resourceCardCreator();
        //PrivateBoard setup
        privateBoard.getCardGrid().add(placedResourceCardCreator(1,1,CardFace.FRONT));
        privateBoard.getCardGrid().add(placedResourceCardCreator(1,3,CardFace.FRONT));
        privateBoard.getElementsCounter().replace(CardElements.FUNGI, 8);
        //Place Phase
        privateBoard.placing(cardToPlace, new Coordinates(1, 2), CardFace.BACK);
        privateBoard.refreshElementsCounter();
        //Test
        assertEquals(7, privateBoard.getElementsCounter().get(CardElements.FUNGI));
    }

    @Test
    void multipleRefreshElementsCounterCardPlacedOnFRONTOthersBACK() {
        PrivateBoard privateBoard = new PrivateBoard();
        ResourceCard cardToPlace = resourceCardCreator();
        //PrivateBoard setup
        privateBoard.getCardGrid().add(placedResourceCardCreator(1,1,CardFace.BACK));
        privateBoard.getCardGrid().add(placedResourceCardCreator(1,3,CardFace.BACK));
        privateBoard.getElementsCounter().replace(CardElements.FUNGI, 2);
        //Place Phase
        privateBoard.placing(cardToPlace, new Coordinates(1, 2), CardFace.FRONT);
        privateBoard.refreshElementsCounter();
        //Test
        assertEquals(6, privateBoard.getElementsCounter().get(CardElements.FUNGI));
    }

    @Test
    void multipleRefreshElementsCounterAllBACK() {
        PrivateBoard privateBoard = new PrivateBoard();
        ResourceCard cardToPlace = resourceCardCreator();
        //PrivateBoard setup
        privateBoard.getCardGrid().add(placedResourceCardCreator(1,1,CardFace.BACK));
        privateBoard.getCardGrid().add(placedResourceCardCreator(1,3,CardFace.BACK));
        privateBoard.getElementsCounter().replace(CardElements.FUNGI, 2);
        //Place Phase
        privateBoard.placing(cardToPlace, new Coordinates(1, 2), CardFace.BACK);
        privateBoard.refreshElementsCounter();
        //Test
        assertEquals(3, privateBoard.getElementsCounter().get(CardElements.FUNGI));
    }

    @Test
    void multipleRefreshElementsCounterCardPlacedOnFRONTOneBACKOneFRONT() {
        PrivateBoard privateBoard = new PrivateBoard();
        ResourceCard cardToPlace = resourceCardCreator();
        //PrivateBoard setup
        privateBoard.getCardGrid().add(placedResourceCardCreator(1,1,CardFace.BACK));
        privateBoard.getCardGrid().add(placedResourceCardCreator(1,3,CardFace.FRONT));
        privateBoard.getElementsCounter().replace(CardElements.FUNGI, 5);
        //Place Phase
        privateBoard.placing(cardToPlace, new Coordinates(1, 2), CardFace.FRONT);
        privateBoard.refreshElementsCounter();
        //Test
        assertEquals(8, privateBoard.getElementsCounter().get(CardElements.FUNGI));
    }

    @Test
    void multipleRefreshElementsCounterCardPlacedOnBACKOneBACKOneFRONT() {
        PrivateBoard privateBoard = new PrivateBoard();
        ResourceCard cardToPlace = resourceCardCreator();
        //PrivateBoard setup
        privateBoard.getCardGrid().add(placedResourceCardCreator(1,1,CardFace.BACK));
        privateBoard.getCardGrid().add(placedResourceCardCreator(1,3,CardFace.FRONT));
        privateBoard.getElementsCounter().replace(CardElements.FUNGI, 5);
        //Place Phase
        privateBoard.placing(cardToPlace, new Coordinates(1, 2), CardFace.BACK);
        privateBoard.refreshElementsCounter();
        //Test
        assertEquals(5, privateBoard.getElementsCounter().get(CardElements.FUNGI));
    }

    //Try the tests above including NONE Edges


    @Test
    void multipleRefreshElementsCounterCardPlacedOnFRONTWEdgeNONE() {
        PrivateBoard privateBoard = new PrivateBoard();
        ResourceCard cardToPlace = resourceCardCreatorWithEdgeNoneBL();
        //PrivateBoard setup
        privateBoard.getCardGrid().add(placedResourceCardCreator(1,1,CardFace.FRONT));
        privateBoard.getCardGrid().add(placedResourceCardCreator(1,3,CardFace.FRONT));
        privateBoard.getElementsCounter().replace(CardElements.FUNGI, 8);
        //Place Phase
        privateBoard.placing(cardToPlace, new Coordinates(1, 2), CardFace.FRONT);
        privateBoard.refreshElementsCounter();
        //Test
        assertEquals(9, privateBoard.getElementsCounter().get(CardElements.FUNGI));
    }

    @Test
    void multipleRefreshElementsCounterCardPlacedOnBACKEdgeNONE() {
        PrivateBoard privateBoard = new PrivateBoard();
        ResourceCard cardToPlace = resourceCardCreatorWithEdgeNoneBL();
        //PrivateBoard setup
        privateBoard.getCardGrid().add(placedResourceCardCreator(1,1,CardFace.FRONT));
        privateBoard.getCardGrid().add(placedResourceCardCreator(1,3,CardFace.FRONT));
        privateBoard.getElementsCounter().replace(CardElements.FUNGI, 8);
        //Place Phase
        privateBoard.placing(cardToPlace, new Coordinates(1, 2), CardFace.BACK);
        privateBoard.refreshElementsCounter();
        //Test
        assertEquals(7, privateBoard.getElementsCounter().get(CardElements.FUNGI));
    }

    @Test
    void multipleRefreshElementsCounterCardPlacedOnFRONTEdgeNONEWPlacedCardOnBACK() {
        PrivateBoard privateBoard = new PrivateBoard();
        ResourceCard cardToPlace = resourceCardCreatorWithEdgeNoneBL();
        //PrivateBoard setup
        privateBoard.getCardGrid().add(placedResourceCardCreator(1,1,CardFace.FRONT));
        privateBoard.getCardGrid().add(placedResourceCardCreator(1,3,CardFace.BACK));
        privateBoard.getElementsCounter().replace(CardElements.FUNGI, 5);
        //Place Phase
        privateBoard.placing(cardToPlace, new Coordinates(1, 2), CardFace.FRONT);
        privateBoard.refreshElementsCounter();
        //Test
        assertEquals(7, privateBoard.getElementsCounter().get(CardElements.FUNGI));
    }
}