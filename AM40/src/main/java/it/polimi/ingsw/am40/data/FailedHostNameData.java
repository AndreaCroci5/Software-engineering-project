package it.polimi.ingsw.am40.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import it.polimi.ingsw.am40.client.ClientMessages.FailedHostNameMessage;
import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.client.network.RMI.RemoteInterfaceClient;
import it.polimi.ingsw.am40.server.network.RMI.RemoteInterfaceServer;

import java.rmi.RemoteException;

@JsonTypeName("FAILED_HOST_NAME")
public class FailedHostNameData extends Data {

    @JsonCreator
    public FailedHostNameData(@JsonProperty("hostname") String hostname) {
        super("FAILED_HOST_NAME", hostname);
    }

    @Override
    public Message onClient() {
        return new FailedHostNameMessage(this.getNickname());
    }

    @Override
    public void doRMI(RemoteInterfaceClient skeleton, RemoteInterfaceServer stub){
        try {
            skeleton.FailedHostNamePassiveFlow(this);
        } catch (RemoteException e) {
            System.out.println("RMI call went wrong! message: " + e);
        }
    }
}
