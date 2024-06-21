package it.polimi.ingsw.am40.data.passive.flow;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.client.ClientMessages.passiveMessages.flow.JoinResponseMessage;
import it.polimi.ingsw.am40.data.Data;

import java.util.ArrayList;
import java.util.Map;

@JsonTypeName("JOIN_RESPONSE")
public class JoinResponseData extends Data {
    //ATTRIBUTES
    /**
     * Reference to the Map containing as Keys an Integer that refers to the respective activePartyID.
     * The corresponding values refer to an ArrayList that contains in the first position the current number of client
     * that have joined and in the second position the total size decided for the party dimension
     */
    private Map<Integer, ArrayList<Integer>> currentParties;

    public Map<Integer, ArrayList<Integer>> getCurrentParties() {
        return currentParties;
    }

    public void setCurrentParties(Map<Integer, ArrayList<Integer>> currentParties) {
        this.currentParties = currentParties;
    }

    @JsonCreator
    public JoinResponseData(@JsonProperty("nickname") String nickname,
                            @JsonProperty("currentParties") Map<Integer, ArrayList<Integer>> currentParties) {
        super("JOIN_RESPONSE", nickname);
        this.currentParties = currentParties;
    }


    public Message onClient() {
        return new JoinResponseMessage(getNickname(), this.currentParties);
    }
}