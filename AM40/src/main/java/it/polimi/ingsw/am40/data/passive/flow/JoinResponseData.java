package it.polimi.ingsw.am40.data.passive.flow;

import com.fasterxml.jackson.annotation.JsonTypeName;
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


    public JoinResponseData(String nickname, Map<Integer, ArrayList<Integer>> currentParties) {
        super("JOIN_RESPONSE", nickname);
        this.currentParties = currentParties;
    }

    public JoinResponseData() {
    }
}
