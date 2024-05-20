package it.polimi.ingsw.am40.server.network.RMI;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
//todo jdoc


public class RemoteObjectServer extends UnicastRemoteObject implements RemoteInterfaceServer {

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
}
