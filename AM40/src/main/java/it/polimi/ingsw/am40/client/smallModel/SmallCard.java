package it.polimi.ingsw.am40.client.smallModel;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import it.polimi.ingsw.am40.server.model.Coordinates;

/**
 * Representation of the card for the client
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SmallCard {

    /**
     * Collection of the card
     */
    private String collection;
    /**
     * ID of the card
     */
    private int cardID;
    /**
     * Element of the card
     */
    private String cardElement;
    /**
     * Resource on the front edges
     */
    private List<String> frontEdgeResources;
    /**
     * Points of the card
     */
    private int scorePoints;
    /**
     * Requirements to place a card
     */
    private List<String> requires;
    /**
     * Type of scoring method of a card
     */
    private String scoreType;
    /**
     * Object that gives the score on an ObjectScoreType card
     */
    private String objectScoreType;
    /**
     * Points of the card
     */
    private int points;
    /**
     * Rotation of the aim card
     */
    private String rotation;
    /**
     * Resources of the aim card
     */
    private List<String> checkResources;
    /**
     * Type of pattern of the aim card
     */
    private String checker;
    /**
     * Resources of a starting card
     */
    private List<String> startingResource;
    /**
     * Resources on the back edges of a card
     */
    private List<String> backEdgeResources;
    /**
     * Face on which the card is placed
     */
    private String face;

    /**
     * Coordinates where the card is placed
     */
    private Coordinates coordinates;

    // Getters and setters for all fields

    /**
     * Getter for collection
     * @return the collection of a card
     */
    public String getCollection() {
        return collection;
    }

    /**
     * Getter for cardID
     * @return the ID of a card
     */
    public int getCardID() {
        return cardID;
    }

    /**
     * Setter for cardID
     * @param cardID is the ID of the card
     */
    public void setCardID(int cardID) {
        this.cardID = cardID;
    }

    /**
     * Getter for cardElement
     * @return the element of the card
     */
    public String getCardElement() {
        return cardElement;
    }

    /**
     * Getter for frontEdgeResources
     * @return the resources on the front edges of the card
     */
    public List<String> getFrontEdgeResources() {
        return frontEdgeResources;
    }

    /**
     * Getter for scorePoints
     * @return the points of a card
     */
    public int getScorePoints() {
        return scorePoints;
    }

    /**
     * Getter for requires
     * @return the requirements to place a card
     */
    public List<String> getRequires() {
        return requires;
    }

    /**
     * Getter for scoreType
     * @return the ScoreType of the card
     */
    public String getScoreType() {
        return scoreType;
    }

    /**
     * Getter for the ObjectScoreType
     * @return the ObjectScoreType of the card
     */
    public String getObjectScoreType() {
        return objectScoreType;
    }

    /**
     * Getter for the points of the card
     * @return the points of the card
     */
    public int getPoints() {
        return points;
    }

    /**
     * Getter for the rotation of the card
     * @return the rotation of the card
     */
    public String getRotation() {
        return rotation;
    }

    /**
     * Getter for the CheckResources of the card
     * @return the CheckResources of the card
     */
    public List<String> getCheckResources() {
        return checkResources;
    }

    /**
     * Getter for the Checker of the card
     * @return the Checker of the card
     */
    public String getChecker() {
        return checker;
    }

    /**
     * Getter for the startingResources of the card
     * @return the starting resources of the card
     */
    public List<String> getStartingResource() {
        return startingResource;
    }

    /**
     * Setter for points
     * @param points that are going to set on the card
     */
    public void setPoints(int points) {
        this.points = points;
    }

    /**
     * Getter for backEdgeResources
     * @return the back edge resources of the card
     */
    public List<String> getBackEdgeResources() {
        return backEdgeResources;
    }

    /**
     * Setter for the collection of the card
     * @param collection sets the collection of the card
     */
    public void setCollection(String collection) {
        this.collection = collection;
    }

    /**
     * Setter for CardElement
     * @param cardElement sets the element of the card
     */
    public void setCardElement(String cardElement) {
        this.cardElement = cardElement;
    }

    /**
     * Setter for frontEdgeResources
     * @param frontEdgeResources sets the front edge resources of the card
     */
    public void setFrontEdgeResources(List<String> frontEdgeResources) {
        this.frontEdgeResources = frontEdgeResources;
    }

    /**
     * Setter for the scorePoints
     * @param scorePoints sets the score points of the card
     */
    public void setScorePoints(int scorePoints) {
        this.scorePoints = scorePoints;
    }

    /**
     * Setter for the requirements
     * @param requires sets the requirements of the card
     */
    public void setRequires(List<String> requires) {
        this.requires = requires;
    }

    /**
     * Setter for the ScoreType
     * @param scoreType sets the score type of the card
     */
    public void setScoreType(String scoreType) {
        this.scoreType = scoreType;
    }

    /**
     * Setter for the ObjectScoreType
     * @param objectScoreType sets the object score type of the card
     */
    public void setObjectScoreType(String objectScoreType) {
        this.objectScoreType = objectScoreType;
    }

    /**
     * Setter for the rotation of the card
     * @param rotation sets the rotation of the card
     */
    public void setRotation(String rotation) {
        this.rotation = rotation;
    }

    /**
     * Setter for checkResources of the card
     * @param checkResources sets the checkResources of the card
     */
    public void setCheckResources(List<String> checkResources) {
        this.checkResources = checkResources;
    }

    /**
     * Setter for checker
     * @param checker sets the Checker of the card
     */
    public void setChecker(String checker) {
        this.checker = checker;
    }

    /**
     * Setter for the startingResources
     * @param startingResource sets the starting resources of the card
     */
    public void setStartingResource(List<String> startingResource) {
        this.startingResource = startingResource;
    }

    /**
     * Setter for the backEdgeResources
     * @param backEdgeResources sets the backEdgeResources
     */
    public void setBackEdgeResources(List<String> backEdgeResources) {
        this.backEdgeResources = backEdgeResources;
    }

    /**
     * Getter for the coordinates of the card
     * @return the coordinates of the card
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * Setter for the coordinates of the card
     * @param coordinates are the coordinates that are going to be set on the card
     */
    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    /**
     * Getter for the face of the card
     * @return the face of the card
     */
    public String getFace() {
        return face;
    }

    /**
     * Setter for the face of the card
     * @param face is the face that is going to be set on the card
     */
    public void setFace(String face) {
        this.face = face;
    }

    /**
     * Returns a string representation of the SmallCard object. The string representation
     * includes the values of all the fields in the SmallCard object
     * @return a string representation of the SmallCard object
     */
    @Override
    public String toString() {
        return "Card{" +
                "collection='" + collection + '\'' +
                ", cardID=" + cardID +
                ", cardElement='" + cardElement + '\'' +
                ", frontEdgeResources=" + frontEdgeResources +
                ", scorePoints=" + scorePoints +
                ", requires=" + requires +
                ", scoreType='" + scoreType + '\'' +
                ", objectScoreType='" + objectScoreType + '\'' +
                ", points='" + points + '\'' +
                ", rotation='" + rotation + '\'' +
                ", checkResources=" + checkResources +
                ", checker='" + checker + '\'' +
                ", startingResource=" + startingResource +
                ", backEdgeResources=" + backEdgeResources +
                '}';
    }

}
