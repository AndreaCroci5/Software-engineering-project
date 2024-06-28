package it.polimi.ingsw.am40.data.passive.firstRound;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.client.ClientMessages.passiveMessages.firstRound.PositiveTokenColorMessage;
import it.polimi.ingsw.am40.client.network.RMI.RemoteInterfaceClient;
import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.server.actions.Action;
import it.polimi.ingsw.am40.server.network.RMI.RemoteInterfaceServer;

import java.rmi.RemoteException;

/**
 * This data confirm the choice of the token made by the client
 */
@JsonTypeName("POSITIVE_TOKEN_COLOR")
public class PositiveTokenColorData extends Data {
    //ATTRIBUTES
    /** Color of the Token chosen by the Player*/
    private String color;



    //CONSTRUCTOR

    /**
     * Constructor for the PositiveTokenColorData
     * @param nickname is the name of the active client
     * @param color is the color chosen by the client
     */
    @JsonCreator
    public PositiveTokenColorData(@JsonProperty("nickname") String nickname,
                                  @JsonProperty("color") String color) {
        super("POSITIVE_TOKEN_COLOR", nickname);
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    //PUBLIC METHODS


    /**
     * This method is called once the Data reaches the Server and creates the Action related to the Data sent by polymorphism
     * @return the corresponding Action on the Server
     */
    public Action onServer(){
        return null;
    }

    /**
     * This method is called once the Data reaches the Client and creates the Message related to the Data sent by polymorphism
     * @return the corresponding Message on the Client
     */
    public Message onClient() {
        return new PositiveTokenColorMessage(this.getNickname(), this.color);
    }

    /**
     * Method which calls the right RMI interface method for each data (with override)
     * @param skeleton the client remote interface. Null if data active
     * @param stub the server remote interface. Null if data passive
     */
    @Override
    public void doRMI(RemoteInterfaceClient skeleton, RemoteInterfaceServer stub){
        try {
            skeleton.positiveTokenColorPassiveFirstRound(this);
        } catch (RemoteException e) {
            System.out.println("RMI call went wrong! message: " + e);
        }
    }
}
