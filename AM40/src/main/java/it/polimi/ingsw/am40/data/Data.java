package it.polimi.ingsw.am40.data;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.data.active.firstRound.AimCardData;
import it.polimi.ingsw.am40.data.active.firstRound.StartingCardData;
import it.polimi.ingsw.am40.data.active.firstRound.TokenData;
import it.polimi.ingsw.am40.data.active.round.DrawData;
import it.polimi.ingsw.am40.data.active.round.PlacingData;
import it.polimi.ingsw.am40.server.actions.Action;

//TODO In every subclass add the default constructor for Jackson parsing from Json and javadoc everything

//These Annotations serve as a mean to give to the Jackson Parser an orientation to the correct Object creation based
//on polymorphism
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = AimCardData.class, name = "AIM_CARD_SELECTION"),
        @JsonSubTypes.Type(value = StartingCardData.class, name = "STARTING_CARD_SELECTION"),
        @JsonSubTypes.Type(value = TokenData.class, name = "TOKEN_SELECTION"),
        @JsonSubTypes.Type(value = TokenData.class, name = "CARD_DISTRIBUTION_MESSAGE"),
        @JsonSubTypes.Type(value = PlacingData.class, name = "PLACING"),
        @JsonSubTypes.Type(value = DrawData.class, name = "DRAW"),
        @JsonSubTypes.Type(value = DrawData.class, name = "CHANGE_TURN")
})

public abstract class Data {

    //ATTRIBUTES
    /**
     * Description of the action. It describes what an action does in order to be used in listener system (onEvent)
     */
    private String description;


    //CONSTRUCTOR
    //Logic constructor for subclasses
    public Data(String description) {
        this.description = description;
    }

    //JsonConstructor
    public Data(){
    }



    //PUBLIC METHODS

    public Action onServer(){
        return null;
    }

    public Message onClient() {
        return null;
    }
}
