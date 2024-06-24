package it.polimi.ingsw.am40.client.network.States.activeStates;

import it.polimi.ingsw.am40.client.ClientMessages.activeMessages.round.DrawMessage;
import it.polimi.ingsw.am40.client.network.Client;
import it.polimi.ingsw.am40.client.network.State;
import java.util.ArrayList;
import java.util.List;


public class ActiveDrawChoiceState implements State {

    @Override
    public void execute(Client context) {
        context.getViewManager().displayDrawChoice(context.getSmallModel().getCommonBoard());
    }

    @Override
    public void checkInput(Client context, String input) {
        List<String> possibleInputs = new ArrayList<>();
        possibleInputs.add("resdeck");
        possibleInputs.add("resplate1");
        possibleInputs.add("resplate2");
        possibleInputs.add("golddeck");
        possibleInputs.add("goldplate1");
        possibleInputs.add("goldplate2");

        if (!possibleInputs.contains(input.toLowerCase())) {
            System.out.println(">The position you choose is not an available position ");
        }
        else {
            if (input.equalsIgnoreCase("resdeck")) {
                context.getNetworkManager().send(new DrawMessage(context.getNickname(), "res",2));
            }
            else if (input.equalsIgnoreCase("resplate1")) {
                context.getNetworkManager().send(new DrawMessage(context.getNickname(), "res", 0));
            }
            else if (input.equalsIgnoreCase("resplate2")) {
                context.getNetworkManager().send(new DrawMessage(context.getNickname(), "res", 1));
            } else if (input.equalsIgnoreCase("golddeck")) {
                context.getNetworkManager().send(new DrawMessage(context.getNickname(), "gold", 2));
            } else if (input.equalsIgnoreCase("goldplate1")) {
                context.getNetworkManager().send(new DrawMessage(context.getNickname(), "gold", 0));
            } else if (input.equalsIgnoreCase("goldplate2")) {
                context.getNetworkManager().send(new DrawMessage(context.getNickname(), "gold", 1));
            }
        }

    }
}

