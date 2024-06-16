package it.polimi.ingsw.am40.client.network.RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interface RemoteInterfaceClients is used by the server to call methods on the client (RMI).
 * These methods are "callable" in the network.
 */
public interface RemoteInterfaceClient extends Remote {
    void pingClient() throws RemoteException;
}
