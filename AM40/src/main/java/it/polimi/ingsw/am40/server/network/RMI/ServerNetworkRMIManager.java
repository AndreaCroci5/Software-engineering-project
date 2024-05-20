package it.polimi.ingsw.am40.server.network.RMI;

import it.polimi.ingsw.am40.server.network.NetworkManagerServer;
import it.polimi.ingsw.am40.server.network.virtual_view.VVServer;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServerNetworkRMIManager implements NetworkManagerServer{

    //ATTRIBUTES

    /**
     * The reference to the VVServer, which is the class that manage all the incoming requests and is part of the MVC
     */
    private final VVServer mainServerClass;





    //CONSTRUCTOR METHODS

    /**
     * Constructor for ServerNetworkRMIManager
     */
    public ServerNetworkRMIManager(VVServer server){
        this.mainServerClass = server;
    }



    //GETTER METHODS

    /**
     * Getter for server reference
     * @return the VVServer as reference
     */
    public VVServer getMainServerClass() {
        return mainServerClass;
    }


    //NETWORK MANAGER METHODS

    /**
     * Method to start communications with a specific protocol
     */
    @Override
    public void initCommunication(int port, String hostName) {


            Thread listeningThreadRMI = new Thread(() -> {
                try {
                    //Creation of the RMI registry
                    Registry registry = LocateRegistry.createRegistry(port);
                    RemoteInterfaceServer obj = new RemoteObjectServer();

                    //Binding of the remote object in RMI registry
                    registry.rebind("ServerRemote", obj);

                    System.out.println("Server RMI is running at port " + port);
                } catch (RemoteException e) {
                    System.out.println("Something went wrong with the RMI server initialization");
                    e.printStackTrace();
                    throw new RuntimeException(e);//fixme relaunch/protected jdoc
                }
                //fixme thread
            });

            listeningThreadRMI.start();

    }

    /**
     * Method to stop the communication with the clients (server not reachable on the network)
     */
    @Override
    public void stopCommunication() {

    }

    /** //todo
     *
     */
   @Override
    public void initPing() {

    }

    /**
     * Method to stop the ping logic
     */
    @Override
    public void stopPing() {

    }

}
