package it.polimi.ingsw.am40.client.view.GUI.FXexamples;

import it.polimi.ingsw.am40.server.model.Coordinates;

/**
 * This class purpose is to serve as URL builder and interpreter for the GUI, in order to link the Application and the
 * resources
 */
public class GraphicResourceFetcher {

    /**
     * This method builds the String representing the URL of a card graphic resource
     * @param cardID is the ID of the Card
     * @param cardFace is the CardFace orientation of the Card
     * @return the card url as String
     */
    public String findCardResource(int cardID, String cardFace ) {
        String fileName = "/images/";
        if (cardFace.equals("FRONT")) {
            fileName += "frontCards/";
        } else {
            fileName += "backCards/";
        }
        if (cardID < 100 && cardID>=10) {
            fileName = fileName + "0" + cardID;
        } else if (cardID<10) {
            fileName = fileName + "00" + cardID;
        } else {
            fileName += cardID;
        }
        fileName += ".png";
        return fileName;
    }

    /**
     * This method builds the String representing the URL of a token graphic resource
     * @param color is the color of the token
     * @return the token url as String
     */
    public String findTokenResource(String color) {
        String fileName = "/images/board/";
        fileName = fileName + color + "Token";
        fileName += ".png";
        return fileName;
    }


    /**
     * This method extracts the color of a token by splitting the token url
     * @param url is the link to the graphic resource
     * @return the color of the token as String
     */
    public String tokenFromURL (String url) {
        String color;
        color = url.substring(14, url.length()-9);
        return color.toUpperCase();
    }

    /**
     * This method extracts the ID of a card by splitting the card url
     * @param url is the link to the graphic resource
     * @return the ID of the card as int
     */
    public int cardIDFromURL(String url) {
        String IDString;
        IDString = url.substring(url.length()-7, url.length()-4);
        int cardID = Integer.parseInt(IDString);
        return cardID;
    }

    /**
     * This method extracts the Face of a card by splitting the token url
     * @param url is the link to the graphic resource
     * @return the face of the card as a String
     */
    public String faceFromURL (String url) {
        char recognizer = url.charAt(8);
        if(recognizer == 'f') {
            return "FRONT";
        } else {
            return "BACK";
        }
    }

    public Coordinates coordinatesFromString (String text) {
        char charX = text.charAt(1);
        char charY = text.charAt(3);
        int x = (int) charX;
        int y = (int) charY;

        return new Coordinates(x, y);
    }
}
