package it.polimi.ingsw.am40.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import it.polimi.ingsw.am40.client.ClientMessages.InterruptedGameMessage;
import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.client.network.RMI.RemoteInterfaceClient;
import it.polimi.ingsw.am40.server.actions.Action;
import it.polimi.ingsw.am40.server.network.RMI.RemoteInterfaceServer;

import java.rmi.RemoteException;

/**
 * This data is sent to the client to interrupt the game
 */
@JsonTypeName("INTERRUPTED_GAME")
public class InterruptedGameData extends Data{


        //CONSTRUCTOR

         //Json constructor

    /**
     * Constructor for the InterruptedGameData
     * @param nickname is the name of the active client
     */
    @JsonCreator
        public InterruptedGameData(@JsonProperty("nickname") String nickname) {
            super("INTERRUPTED_GAME", nickname);
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
            return new InterruptedGameMessage(getNickname());
        }

        /**
         * Method which calls the right RMI interface method for each data (with override)
         * @param skeleton the client remote interface. Null if data active
         * @param stub the server remote interface. Null if data passive
         */
        @Override
        public void doRMI(RemoteInterfaceClient skeleton, RemoteInterfaceServer stub){
            try {
                skeleton.interruptedGame(this);
            } catch (RemoteException e) {
                System.out.println("RMI call went wrong! message: " + e);
            }
        }
    }


