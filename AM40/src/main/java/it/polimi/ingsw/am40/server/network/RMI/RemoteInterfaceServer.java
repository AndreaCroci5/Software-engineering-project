package it.polimi.ingsw.am40.server.network.RMI;

import it.polimi.ingsw.am40.client.network.RMI.RemoteInterfaceClient;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interface RemoteInterfaceServer is used by the client to call methods on the server (RMI).
 * These methods are "callable" in the network.
 */
public interface RemoteInterfaceServer extends Remote{
    String example() throws RemoteException;

    void registerClient(RemoteInterfaceClient client);
}
