package it.polimi.ingsw.am40.server.model;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.polimi.ingsw.am40.server.model.aimStrategy.AimChecker;
import it.polimi.ingsw.am40.server.model.aimStrategy.AimCheckerDiagonalPattern;
import it.polimi.ingsw.am40.server.model.aimStrategy.AimCheckerLPattern;
import it.polimi.ingsw.am40.server.model.aimStrategy.AimCheckerResource;
import it.polimi.ingsw.am40.server.model.scoreStrategy.CoverageScoreType;
import it.polimi.ingsw.am40.server.model.scoreStrategy.NormalScoreType;
import it.polimi.ingsw.am40.server.model.scoreStrategy.ObjectScoreType;
import it.polimi.ingsw.am40.server.model.scoreStrategy.ScoreType;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * The JSONCardLoader class import the decks from the JSON file "Cards"
 */
public class JSONCardLoader {
    /**
     * Unique attribute of this class (static attribute).
     * A string which indicates the path of the file in the project root
     */
    private static final String sourceFile = "Cards.json";


    /**
     * This method does the parsing and the loading of all the cards in the JSON file.
     * @return the list of four decks ((1st) Resource Deck, (2nd) Gold Resource Deck, (3rd) Starting Deck, (4th) Aim Deck)
     */
    public List<Deck<? extends Card>> loadCards() {

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        InputStream is = null;
        try {
            is = getClass().getClassLoader().getResourceAsStream(sourceFile);
            if (is == null) {
                throw new IllegalArgumentException("Resource not found: " + sourceFile);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        JSONParser parser = new JSONParser();


            try {
                Object obj = parser.parse(new InputStreamReader(is, StandardCharsets.UTF_8));
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

                    Long cardID = (Long) cardObj.get("cardID");

                    //Basing on the "collection" the cards will take different parameters in input, and they'll be appended in a different deck
                    //Auxiliary private methods are used to make easier and more clear the code reading
                    //All the parameters are 'ad hoc' for the file, so the abstraction level of this class and the re-usability of the code are reduced


                    //Resource Cards loading
                    switch (cardCollection) {
                        case "FUNGI_RESOURCE", "PLANT_RESOURCE", "ANIMAL_RESOURCE", "INSECT_RESOURCE" -> {

                            ResourceCard c = loadResourceCard(cardObj, cardID, (long) 0);
                            resourceCardDeck.appendToBottom(c);


                            //Resource Cards loading with score points
                        }
                        case "FUNGI_GOLD_1", "PLANT_GOLD_1", "ANIMAL_GOLD_1", "INSECT_GOLD_1" -> {

                            ResourceCard c = loadResourceCard(cardObj, cardID, (Long) cardObj.get("scorePoints"));
                            resourceCardDeck.appendToBottom(c);


                            //Gold Resource Cards loading
                        }
                        case "FUNGI_GOLD_2", "PLANT_GOLD_2", "ANIMAL_GOLD_2", "INSECT_GOLD_2" -> {

                            GoldResourceCard c = loadGoldResourceCard(cardObj, cardID);
                            goldenResourceCardDeck.appendToBottom(c);


                            //Starting Cards loading
                        }
                        case "STARTING" -> {

                            StartingCard c = loadStartingCard(cardObj, Math.toIntExact(cardID));
                            startingCardDeck.appendToBottom(c);


                            //Aim Cards loading
                        }
                        case "AIM" -> {

                            AimCard c = loadAimCard(cardObj, cardID);
                            aimCardDeck.appendToBottom(c);


                        }
                        case null, default ->
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


            } catch (IOException | ParseException ex) {
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
    private ResourceCard loadResourceCard(JSONObject cardObj, Long cardID, Long score) {

        CardElements cardElement = CardElements.valueOf((String) cardObj.get("cardElement"));

        JSONArray frontEdgeResourcesArray = (JSONArray) cardObj.get("frontEdgeResources");

        Iterator<JSONArray> frontEdgeResourcesIterator = frontEdgeResourcesArray.iterator();
        List<CardElements> frontEdgeResources = new ArrayList<>();
        int i = 0;

        while (frontEdgeResourcesIterator.hasNext()) {
            frontEdgeResources.add(CardElements.valueOf((String) frontEdgeResourcesArray.get(i)));
            i++;
            frontEdgeResourcesIterator.next();
        }

        if (score == 0) {
            return new ResourceCard(Math.toIntExact(cardID), cardElement, frontEdgeResources);
        } else {
            return new ResourceCard(Math.toIntExact(cardID), cardElement, frontEdgeResources, Math.toIntExact(score));
        }
    }


    /**
     * This method loads the parameters in a gold resource card
     * @param cardObj A JSON object representing a card according to the arbitrary file syntax
     * @param cardID The identification card number
     * @return a new card from JSON
     */
    private GoldResourceCard loadGoldResourceCard(JSONObject cardObj, Long cardID) {

        CardElements cardElement = CardElements.valueOf((String) cardObj.get("cardElement"));

        Long scorePoints = (Long) cardObj.get("scorePoints");

        JSONArray frontEdgeResourcesArray = (JSONArray) cardObj.get("frontEdgeResources");

        Iterator<JSONArray> frontEdgeResourcesIterator = frontEdgeResourcesArray.iterator();
        List<CardElements> frontEdgeResources = new ArrayList<>();
        int i = 0;

        while (frontEdgeResourcesIterator.hasNext()) {
            frontEdgeResources.add(CardElements.valueOf((String) frontEdgeResourcesArray.get(i)));
            i++;
            frontEdgeResourcesIterator.next();
        }

        JSONArray requiresArray = (JSONArray) cardObj.get("requires");

        Iterator<JSONArray> requiresIterator = requiresArray.iterator();
        List<CardElements> requires = new ArrayList<>();
        int j = 0;

        while (requiresIterator.hasNext()) {
            requires.add(CardElements.valueOf((String) requiresArray.get(j)));
            j++;
            requiresIterator.next();
        }

        ScoreType scoreType;
        switch ((String) cardObj.get("scoreType")){
            case "NormalScoreType":
                scoreType = new NormalScoreType();
                break;
            case "ObjectScoreType":
                scoreType = new ObjectScoreType();
                CardElements objectScoreTypeElement = CardElements.valueOf((String) cardObj.get("objectScoreType"));
                return new GoldResourceCard(Math.toIntExact(cardID), cardElement, frontEdgeResources, Math.toIntExact(scorePoints), requires, scoreType, objectScoreTypeElement);
            case "CoverageScoreType":
                scoreType = new CoverageScoreType();
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + cardObj.get("scoreType"));
        }
        return new GoldResourceCard(Math.toIntExact(cardID), cardElement, frontEdgeResources, Math.toIntExact(scorePoints), requires, scoreType);

    }


    /**
     * This method loads the parameters in a starting card
     * @param cardObj A JSON object representing a card according to the arbitrary file syntax
     * @param cardID The identification card number
     * @return a new card from JSON
     */
    private StartingCard loadStartingCard(JSONObject cardObj, int cardID) {

        JSONArray frontEdgeResourcesArray = (JSONArray) cardObj.get("frontEdgeResources");

        Iterator<JSONArray> frontEdgeResourcesIterator = frontEdgeResourcesArray.iterator();
        List<CardElements> frontEdgeResources = new ArrayList<>();
        int i = 0;

        while (frontEdgeResourcesIterator.hasNext()) {
            frontEdgeResources.add(CardElements.valueOf((String) frontEdgeResourcesArray.get(i)));
            i++;
            frontEdgeResourcesIterator.next();
        }


        JSONArray backEdgeResourcesArray = (JSONArray) cardObj.get("backEdgeResources");

        Iterator<JSONArray> backEdgeResourcesIterator = backEdgeResourcesArray.iterator();
        List<CardElements> backEdgeResources = new ArrayList<>();
        int j = 0;

        while (backEdgeResourcesIterator.hasNext()) {
            backEdgeResources.add(CardElements.valueOf((String) backEdgeResourcesArray.get(j)));
            j++;
            backEdgeResourcesIterator.next();
        }


        JSONArray startingResourceArray = (JSONArray) cardObj.get("startingResource");

        Iterator<JSONArray> startingResourceIterator = startingResourceArray.iterator();
        List<CardElements> startingResources = new ArrayList<>();
        int k = 0;

        while (startingResourceIterator.hasNext()) {
            startingResources.add(CardElements.valueOf((String) startingResourceArray.get(k)));
            k++;
            startingResourceIterator.next();
        }


        return new StartingCard(Math.toIntExact(cardID), startingResources, frontEdgeResources, backEdgeResources);
    }


    /**
     * This method loads the parameters in an aim resource card
     * @param cardObj A JSON object representing a card according to the arbitrary file syntax
     * @param cardID The identification card number
     * @return a new card from JSON
     */
    private AimCard loadAimCard(JSONObject cardObj, Long cardID) {
        Long points = (Long) cardObj.get("points");

        JSONArray checkResourcesArray = (JSONArray) cardObj.get("checkResources");

        Iterator<JSONArray> checkResourcesIterator = checkResourcesArray.iterator();
        List<CardElements> checkResources = new ArrayList<>();
        int i = 0;

        while (checkResourcesIterator.hasNext()) {
            checkResources.add(CardElements.valueOf((String) checkResourcesArray.get(i)));
            i++;
            checkResourcesIterator.next();
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

        return new AimCard(Math.toIntExact(cardID), Math.toIntExact(points), checkResources, checker, rotation);
    }

}



