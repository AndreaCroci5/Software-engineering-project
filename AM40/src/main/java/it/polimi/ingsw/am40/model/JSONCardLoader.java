package it.polimi.ingsw.am40.model;

import it.polimi.ingsw.am40.model.scoreStrategy.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Objects;

import static it.polimi.ingsw.am40.model.CardElements.NONE;


/**
 * The JSONCardLoader class import the decks from the JSON file "Cards"
 */
public class JSONCardLoader {
    /**
     * Unique attribute of this class (static attribute).
     * A string which indicates the path of the file in the project root
     */
    private static final String sourceFile = "src/main/resources/it.polimi.ingsw.am40/Cards.json";


    /**
     * This method does the parsing and the loading of all the cards in the JSON file.
     * @return the list of four decks ((1st) Resource Deck, (2nd) Gold Resource Deck, (3rd) Starting Deck, (4th) Aim Deck)
     */
    public List<Deck<? extends Card>> loadCards() {
        JSONParser parser = new JSONParser();

        try (FileReader reader = new FileReader(JSONCardLoader.sourceFile)) {

            Object obj = parser.parse(reader);
            JSONArray cardArray = (JSONArray) obj;

            //Creation of the decks
            Deck<ResourceCard> resourceCardDeck = new Deck<>();
            Deck<GoldResourceCard> goldenResourceCardDeck = new Deck<>();
            Deck<StartingCard> startingCardDeck = new Deck<>();
            Deck<AimCard> aimCardDeck = new Deck<>();

            //For each card in the JSON array (all cards)
            for (Object card : cardArray) {
                JSONObject cardObj = (JSONObject) card;
                String cardCollection = (String) cardObj.get("collection");

                Integer cardID = (Integer) cardObj.get("cardID");

                //Basing on the "collection" the cards will take different parameters in input, and they'll be appended in a different deck
                //Auxiliary private methods are used to make easier and more clear the code reading
                //All the parameters are 'ad hoc' for the file, so the abstraction level of this class and the re-usability of the code are reduced


                    //Resource Cards loading
                if ((Objects.equals(cardCollection, "FUNGI_RESOURCE")) ||
                        (Objects.equals(cardCollection, "PLANT_RESOURCE")) ||
                        (Objects.equals(cardCollection, "ANIMAL_RESOURCE")) ||
                        (Objects.equals(cardCollection, "INSECT_RESOURCE"))) {

                    ResourceCard c = loadResourceCard(cardObj, cardID, 0);
                    resourceCardDeck.appendToBottom(c);


                    //Resource Cards loading with score points
                } else if ((Objects.equals(cardCollection, "FUNGI_GOLD_1")) ||
                        (Objects.equals(cardCollection, "PLANT_GOLD_1")) ||
                        (Objects.equals(cardCollection, "ANIMAL_GOLD_1")) ||
                        (Objects.equals(cardCollection, "INSECT_GOLD_1"))) {

                    ResourceCard c = loadResourceCard(cardObj, cardID, (Integer) cardObj.get("scorePoints"));
                    resourceCardDeck.appendToBottom(c);


                    //Gold Resource Cards loading
                } else if ((Objects.equals(cardCollection, "FUNGI_GOLD_2")) ||
                        (Objects.equals(cardCollection, "PLANT_GOLD_2")) ||
                        (Objects.equals(cardCollection, "ANIMAL_GOLD_2")) ||
                        (Objects.equals(cardCollection, "INSECT_GOLD_2"))){

                    GoldResourceCard c = loadGoldResourceCard(cardObj, cardID);
                    goldenResourceCardDeck.appendToBottom(c);


                    //Starting Cards loading
                } else if (Objects.equals(cardCollection, "STARTING")){

                    StartingCard c = loadStartingCard(cardObj, cardID);
                    startingCardDeck.appendToBottom(c);


                    //Aim Cards loading
                } else if (Objects.equals(cardCollection, "AIM")){

                    AimCard c = loadAimCard(cardObj, cardID);
                    aimCardDeck.appendToBottom(c);


                }else{
                    throw new IllegalStateException("Unexpected value: " + cardCollection + ", cardID: " + cardID);
                }
            }


            //Actual creation of the deck list
            ArrayList<Deck<? extends Card>> decks = new ArrayList<>();

            decks.add(resourceCardDeck);
            decks.add(goldenResourceCardDeck);
            decks.add(startingCardDeck);
            decks.add(aimCardDeck);

            return decks;

        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        } catch (ParseException ex) {
            throw new RuntimeException(ex);
        }

    }



    //PRIVATE METHODS

    /**
     * This method loads the parameters in a resource card
     * @param cardObj A JSON object representing a card according to the arbitrary file syntax
     * @param cardID The identification card number
     * @param score The scorePoints of the card
     * @return a new card from JSON
     */
    private ResourceCard loadResourceCard(JSONObject cardObj, Integer cardID, Integer score) {

        CardElements cardElement = (CardElements) cardObj.get("cardElement");

        JSONArray frontEdgeResourcesArray = (JSONArray) cardObj.get("frontEdgeResources");

        Iterator<JSONArray> frontEdgeResourcesIterator = frontEdgeResourcesArray.iterator();
        List<CardElements> frontEdgeResources = new ArrayList<>();
        int i = 0;

        while (frontEdgeResourcesIterator.hasNext()) {
            frontEdgeResources.add((CardElements) frontEdgeResourcesArray.get(i));
            i++;
        }

        if (score == 0) {
            return new ResourceCard(cardID, cardElement, frontEdgeResources);
        } else {
            return new ResourceCard(cardID, cardElement, frontEdgeResources, score);
        }
    }


    /**
     * This method loads the parameters in a gold resource card
     * @param cardObj A JSON object representing a card according to the arbitrary file syntax
     * @param cardID The identification card number
     * @return a new card from JSON
     */
    private GoldResourceCard loadGoldResourceCard(JSONObject cardObj, Integer cardID) {

        CardElements cardElement = (CardElements) cardObj.get("cardElement");

        Integer scorePoints = (Integer) cardObj.get("scorePoints");

        JSONArray frontEdgeResourcesArray = (JSONArray) cardObj.get("frontEdgeResources");

        Iterator<JSONArray> frontEdgeResourcesIterator = frontEdgeResourcesArray.iterator();
        List<CardElements> frontEdgeResources = new ArrayList<>();
        int i = 0;

        while (frontEdgeResourcesIterator.hasNext()) {
            frontEdgeResources.add((CardElements) frontEdgeResourcesArray.get(i));
            i++;
        }

        JSONArray requiresArray = (JSONArray) cardObj.get("requires");

        Iterator<JSONArray> requiresIterator = requiresArray.iterator();
        List<CardElements> requires = new ArrayList<>();
        int j = 0;

        while (requiresIterator.hasNext()) {
            requires.add((CardElements) requiresArray.get(j));
            j++;
        }

        ScoreType scoreType;
        CardElements objectScoreTypeElement = NONE;
        switch ((String) cardObj.get("scoreType")){
            case "NormalScoreType":
                scoreType = new NormalScoreType();
                break;
            case "ObjectScoreType":
                scoreType = new ObjectScoreType();
                objectScoreTypeElement = (CardElements) cardObj.get("ObjectScoreType");
                break;
            case "CoverageScoreType":
                scoreType = new CoverageScoreType();
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + (String) cardObj.get("scoreType"));
        }
        return new GoldResourceCard(cardID, cardElement, frontEdgeResources, scorePoints, requires, scoreType, objectScoreTypeElement);

    }


    /**
     * This method loads the parameters in a starting card
     * @param cardObj A JSON object representing a card according to the arbitrary file syntax
     * @param cardID The identification card number
     * @return a new card from JSON
     */
    private StartingCard loadStartingCard(JSONObject cardObj, Integer cardID) {

        JSONArray frontEdgeResourcesArray = (JSONArray) cardObj.get("frontEdgeResources");

        Iterator<JSONArray> frontEdgeResourcesIterator = frontEdgeResourcesArray.iterator();
        List<CardElements> frontEdgeResources = new ArrayList<>();
        int i = 0;

        while (frontEdgeResourcesIterator.hasNext()) {
            frontEdgeResources.add((CardElements) frontEdgeResourcesArray.get(i));
            i++;
        }


        JSONArray backEdgeResourcesArray = (JSONArray) cardObj.get("backEdgeResources");

        Iterator<JSONArray> backEdgeResourcesIterator = backEdgeResourcesArray.iterator();
        List<CardElements> backEdgeResources = new ArrayList<>();
        int j = 0;

        while (backEdgeResourcesIterator.hasNext()) {
            backEdgeResources.add((CardElements) backEdgeResourcesArray.get(j));
            j++;
        }


        JSONArray startingResourceArray = (JSONArray) cardObj.get("startingResource");

        Iterator<JSONArray> startingResourceIterator = startingResourceArray.iterator();
        List<CardElements> startingResources = new ArrayList<>();
        int k = 0;

        while (startingResourceIterator.hasNext()) {
            startingResources.add((CardElements) startingResourceArray.get(k));
            k++;
        }


        return new StartingCard(cardID, startingResources, frontEdgeResources, backEdgeResources);
    }


    /**
     * This method loads the parameters in an aim resource card
     * @param cardObj A JSON object representing a card according to the arbitrary file syntax
     * @param cardID The identification card number
     * @return a new card from JSON
     */
    private AimCard loadAimCard(JSONObject cardObj, Integer cardID) {
        Integer points = (Integer) cardObj.get("points");

        JSONArray checkResourcesArray = (JSONArray) cardObj.get("checkResources");

        Iterator<JSONArray> checkResourcesIterator = checkResourcesArray.iterator();
        List<CardElements> checkResources = new ArrayList<>();
        int i = 0;

        while (checkResourcesIterator.hasNext()) {
            checkResources.add((CardElements) checkResourcesArray.get(i));
            i++;
        }

        AimChecker checker;
        String rotation;
        switch ((String) cardObj.get("checker")){
            case "AimCheckerDiagonalPattern":
                checker = new AimCheckerDiagonalPattern();
                rotation = (String) cardObj.get("rotation");
                break;
            case "AimCheckerLPattern":
                checker = new AimCheckerLPattern();
                rotation = (String) cardObj.get("rotation");
                break;
            case "AimCheckerResource":
                checker = new AimCheckerResource();
                rotation = null;
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + (String) cardObj.get("checker"));
        }

        return new AimCard(cardID, points, checkResources, checker, rotation);
    }
}

