package it.polimi.ingsw.am40.client.smallModel;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import it.polimi.ingsw.am40.server.model.Coordinates;

@JsonIgnoreProperties(ignoreUnknown = true)

public class SmallCard {

    private String collection;
    private int cardID;
    private String cardElement;
    private List<String> frontEdgeResources;
    private int scorePoints;
    private List<String> requires;
    private String scoreType;
    private String objectScoreType;
    private int points;
    private String rotation;
    private List<String> checkResources;
    private String checker;
    private List<String> startingResource;
    private List<String> backEdgeResources;
    private String face;

    private Coordinates coordinates;

    // Getters and setters for all fields

    public String getCollection() {
        return collection;
    }

    public int getCardID() {
        return cardID;
    }

    public void setCardID(int cardID) {
        this.cardID = cardID;
    }

    public String getCardElement() {
        return cardElement;
    }

    public List<String> getFrontEdgeResources() {
        return frontEdgeResources;
    }

    public int getScorePoints() {
        return scorePoints;
    }

    public List<String> getRequires() {
        return requires;
    }

    public String getScoreType() {
        return scoreType;
    }

    public String getObjectScoreType() {
        return objectScoreType;
    }

    public int getPoints() {
        return points;
    }

    public String getRotation() {
        return rotation;
    }

    public List<String> getCheckResources() {
        return checkResources;
    }

    public String getChecker() {
        return checker;
    }

    public List<String> getStartingResource() {
        return startingResource;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public List<String> getBackEdgeResources() {
        return backEdgeResources;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }

    public void setCardElement(String cardElement) {
        this.cardElement = cardElement;
    }

    public void setFrontEdgeResources(List<String> frontEdgeResources) {
        this.frontEdgeResources = frontEdgeResources;
    }

    public void setScorePoints(int scorePoints) {
        this.scorePoints = scorePoints;
    }

    public void setRequires(List<String> requires) {
        this.requires = requires;
    }

    public void setScoreType(String scoreType) {
        this.scoreType = scoreType;
    }

    public void setObjectScoreType(String objectScoreType) {
        this.objectScoreType = objectScoreType;
    }

    public void setRotation(String rotation) {
        this.rotation = rotation;
    }

    public void setCheckResources(List<String> checkResources) {
        this.checkResources = checkResources;
    }

    public void setChecker(String checker) {
        this.checker = checker;
    }

    public void setStartingResource(List<String> startingResource) {
        this.startingResource = startingResource;
    }

    public void setBackEdgeResources(List<String> backEdgeResources) {
        this.backEdgeResources = backEdgeResources;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public String getFace() {
        return face;
    }

    public void setFace(String face) {
        this.face = face;
    }

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
