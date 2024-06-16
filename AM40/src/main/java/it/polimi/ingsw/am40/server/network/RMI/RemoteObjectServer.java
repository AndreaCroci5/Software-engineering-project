package it.polimi.ingsw.am40.server.network.RMI;

import it.polimi.ingsw.am40.client.network.RMI.RemoteInterfaceClient;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
//todo jdoc


public class RemoteObjectServer extends UnicastRemoteObject implements RemoteInterfaceServer {

    private ServerNetworkRMIManager manager;


    //CONSTRUCTOR METHODS

    /**
     * Creates and exports a new UnicastRemoteObject object using the
     * particular supplied port and socket factories.
     *
     * <p>Either socket factory may be {@code null}, in which case
     * the corresponding client or server socket creation method of
     * ... is used instead.
     *
     * @throws RemoteException if failed to export object
     * @since 1.2
     */
    protected RemoteObjectServer() throws RemoteException {
        super();
    }



    //REMOTE INTERFACE METHODS

    /**
     * Example method for RMI mechanics
     * @return a string of example
     * @throws RemoteException if the RMI communication fails
     */
    @Override
    public String example() throws RemoteException{
        System.out.println("PROVA");
        return "hello world;";
    }

    @Override
    public void registerClient(RemoteInterfaceClient client) {
        this.manager.newConnectedClientNotification(null, client);
    }

    //fixme differenza tra newconnection e reconnection







    //INTERNAL METHODS
    protected ServerNetworkRMIManager getManager() {
        return manager;
    }

    protected void setManager(ServerNetworkRMIManager manager) {
        this.manager = manager;
    }
}
