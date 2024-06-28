package it.polimi.ingsw.am40.client.smallModel;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.List;

/**
 * Class used to load the cards on the client
 */
public class SmallCardLoader {

    /**
     * List of the cards of the game
     */
    public static final List<SmallCard> TuiCards = loadCards("Cards.json");

    /**
     * Getter for the Cards
     * @return the cards of the game
     */
    public List<SmallCard> getCards() {
        return TuiCards;
    }

    /**
     * Method to load the card from JSON
     * @param fileName name of the JSON file
     * @return the cards of the game
     */
    public static List<SmallCard> loadCards(String fileName) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try {
            InputStream is = SmallCardLoader.class.getClassLoader().getResourceAsStream(fileName);
            if (is == null) {
                throw new IllegalArgumentException("Resource not found: " + fileName);
            }
            return mapper.readValue(is, new TypeReference<List<SmallCard>>() {});
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Method to find a card by his ID
     * @param cardID is the ID of the card
     * @return the small card that has this ID
     */
    public static SmallCard findCardById(int cardID) {
        assert TuiCards != null;
        for (SmallCard card : TuiCards) {
            if (card.getCardID() == cardID) {
                return card;
            }
        }
        return null;
    }
}
