package it.polimi.ingsw.am40.data.passive.firstRound;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.client.ClientMessages.passiveMessages.firstRound.AimCardInfoMessage;
import it.polimi.ingsw.am40.client.network.RMI.RemoteInterfaceClient;
import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.server.actions.Action;
import it.polimi.ingsw.am40.server.network.RMI.RemoteInterfaceServer;

import java.rmi.RemoteException;
import java.util.Arrays;

/**
 * This data is used to send from server to client the information of the aim card
 * that the client has to choose
 */
@JsonTypeName("AIM_CARD_INFO")
public class AimCardInfoData extends Data {
    //ATTRIBUTES
    /**
     * ID of the first aim card
     */
    private int aimID1;

    /**
     * ID of the second aim card
     */
    private int aimID2;

    //CONSTRUCTOR

    /**
     * Constructor for the aimCardInfoData
     * @param nickname is the name of the active client
     * @param aimID1 is the ID of the first aim card
     * @param aimID2 is the ID of the second aim card
     */
    @JsonCreator
    public AimCardInfoData(@JsonProperty("nickname") String nickname,
                           @JsonProperty("aimID1") int aimID1,
                           @JsonProperty("aimID2") int aimID2) {
        super("AIM_CARD_INFO", nickname);
        this.aimID1 = aimID1;
        this.aimID2 = aimID2;
    }

    public int getAimID1() {
        return aimID1;
    }

    public void setAimID1(int aimID1) {
        this.aimID1 = aimID1;
    }

    public int getAimID2() {
        return aimID2;
    }

    public void setAimID2(int aimID2) {
        this.aimID2 = aimID2;
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
        return new AimCardInfoMessage(getNickname(), Arrays.asList(aimID1, aimID2));
    }

    /**
     * Method which calls the right RMI interface method for each data (with override)
     * @param skeleton the client remote interface. Null if data active
     * @param stub the server remote interface. Null if data passive
     */
    @Override
    public void doRMI(RemoteInterfaceClient skeleton, RemoteInterfaceServer stub){
        try {
            skeleton.aimCardInfoPassiveFirstRound(this);
        } catch (RemoteException e) {
            System.out.println("RMI call went wrong! message: " + e);
        }
    }
}

