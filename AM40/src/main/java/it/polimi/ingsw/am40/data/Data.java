package it.polimi.ingsw.am40.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.client.network.RMI.RemoteInterfaceClient;
import it.polimi.ingsw.am40.data.active.firstRound.*;
import it.polimi.ingsw.am40.data.active.flow.*;
import it.polimi.ingsw.am40.data.active.round.DrawData;
import it.polimi.ingsw.am40.data.active.round.PlacingData;
import it.polimi.ingsw.am40.data.passive.firstRound.*;
import it.polimi.ingsw.am40.data.passive.flow.*;
import it.polimi.ingsw.am40.data.passive.round.*;
import it.polimi.ingsw.am40.server.actions.Action;
import it.polimi.ingsw.am40.server.network.RMI.RemoteInterfaceServer;

import java.io.Serializable;

//TODO JAVADOC in the subclasses and add nicknames in passive Data
//FIXME CHECK if all json types and annotations are correct and put final all attributes if needed in subclasses,
// WARNING playerID and GameId must not be put final because they change because once the Data arrives on Server it needs a Set
// of these attributes from VVserver or net interface

/**
 * This class contains the information that will be carried by being sent on the network as a TCP message.
 * This class is also the bridge to the specular MVC mechanism in both Client and Server that is handled by the
 * Messages and the Actions respectively
 */
//These Annotations serve as a mean to give to the Jackson Parser an orientation to the correct Object creation based
//on polymorphism
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({

        @JsonSubTypes.Type(value = PingData.class, name = "PING"),

        //Active Data

        @JsonSubTypes.Type(value = CreateRequestData.class, name = "CREATE_GAME"),
        @JsonSubTypes.Type(value = GameIDChoiceData.class, name = "GAME_ID_CHOICE"),
        @JsonSubTypes.Type(value = JoinRequestData.class, name = "JOIN_GAME"),
        @JsonSubTypes.Type(value = ReadyToPlayData.class, name = "READY_TO_PLAY"),

        @JsonSubTypes.Type(value = StartingCardRequestData.class, name = "STARTING_CARD_REQUEST"),
        @JsonSubTypes.Type(value = StartingCardChoiceData.class, name = "STARTING_CARD_CHOICE"),
        @JsonSubTypes.Type(value = TokenChoiceData.class, name = "TOKEN_SELECTION"),
        @JsonSubTypes.Type(value = TokenRequestData.class, name = "TOKEN_REQUEST"),
        @JsonSubTypes.Type(value = DealCardsData.class, name = "CARDS_DEAL"),
        @JsonSubTypes.Type(value = AimCardRequestData.class, name = "AIM_CARD_REQUEST"),
        @JsonSubTypes.Type(value = AimCardChoiceData.class, name = "AIM_CARD_SELECTION"),
        @JsonSubTypes.Type(value = PlayersOrderRequestData.class, name = "PLAYERS_ORDER_REQUEST"),

        @JsonSubTypes.Type(value = PlacingData.class, name = "PLACING"),
        @JsonSubTypes.Type(value = DrawData.class, name = "DRAW"),
        @JsonSubTypes.Type(value = ChangeTurnRequestData.class, name = "CHANGE_TURN"),

        //Passive Data

        @JsonSubTypes.Type(value = CreateResultData.class, name = "CREATE_RESULT"),
        @JsonSubTypes.Type(value = FailedCreationData.class, name = "FAILED_CREATION"),
        @JsonSubTypes.Type(value = JoinResponseData.class, name = "JOIN_RESPONSE"),
        @JsonSubTypes.Type(value = NoActivePartiesData.class, name = "NO_ACTIVE_PARTIES"),
        @JsonSubTypes.Type(value = GameIDResultData.class, name = "GAME_ID_RESULT"),
        @JsonSubTypes.Type(value = FailedGameIDData.class, name = "FAILED_GAME_ID"),
        @JsonSubTypes.Type(value = GameInitResultData.class, name = "GAME_INIT_RESULT"),
        @JsonSubTypes.Type(value = NotEnoughPlayersData.class, name = "NOT_ENOUGH_PLAYERS"),


        @JsonSubTypes.Type(value = StartingCardInfoData.class, name = "STARTING_INFO"),
        @JsonSubTypes.Type(value = StartingCardResultData.class, name = "POSITIVE_STARTING_CARD"),
        @JsonSubTypes.Type(value = TokenInfoData.class, name = "TOKEN_INFO"),
        @JsonSubTypes.Type(value = PositiveTokenColorData.class, name = "POSITIVE_TOKEN_COLOR"),
        @JsonSubTypes.Type(value = DealCardsResultData.class, name = "CARDS_DEAL_RESULT"),
        @JsonSubTypes.Type(value = AimCardInfoData.class, name = "AIM_CARD_INFO"),
        @JsonSubTypes.Type(value = AimCardResultData.class, name = "AIM_CARD_SELECTED"),
        @JsonSubTypes.Type(value = PlayersOrderInfoData.class, name = "PLAYERS_ORDER_INFO"),
        @JsonSubTypes.Type(value = PositivePlacingData.class, name = "POSITIVE_PLACING"),
        @JsonSubTypes.Type(value = RepeatPlacingData.class, name = "REPEAT_PLACING"),
        @JsonSubTypes.Type(value = PositiveDrawData.class, name = "POSITIVE_DRAW"),
        @JsonSubTypes.Type(value = RepeatDrawData.class, name = "REPEAT_DRAW"),
        @JsonSubTypes.Type(value = ChangeTurnInfoData.class, name = "CHANGE_TURN_INFO"),
        @JsonSubTypes.Type(value = LastRoundsInfoData.class, name = "LAST_ROUNDS"),
        @JsonSubTypes.Type(value = EndGameData.class, name = "ENDGAME"),
        @JsonSubTypes.Type(value = EndGameData.class, name = "PING")

})
public abstract class Data implements Serializable {

    //ATTRIBUTES
    /**
     * Description of the action. It describes what an action does in order to be used in listener system (onEvent)
     */
    private String description;

    private String nickname;

    //Attributes used to orientate on the server, not useful for the Data itself, only for the Action creation
    private int gameID;
    private int playerID;


    //CONSTRUCTOR

    //Logic constructor for subclasses

    //Json Constructor
    public Data(){

    }

    @JsonCreator
    public Data(@JsonProperty ("description") String description,
                @JsonProperty ("nickname") String nickname) {
        this.nickname = nickname;
        this.description = description;
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
        return null;
    }


    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    //GETTER AND SETTERS
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }



    //RMI METHOD

    /**
     * Method which calls the right RMI interface method for each data (with override)
     * @param skeleton the client remote interface. Null if data active
     * @param stub the server remote interface. Null if data passive
     */
    public synchronized void doRMI(RemoteInterfaceClient skeleton, RemoteInterfaceServer stub){}

}
