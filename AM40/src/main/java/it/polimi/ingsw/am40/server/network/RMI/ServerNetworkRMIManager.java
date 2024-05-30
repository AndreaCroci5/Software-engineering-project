package it.polimi.ingsw.am40.server.network.RMI;

import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.server.network.NetworkManagerServer;
import it.polimi.ingsw.am40.server.network.virtual_view.NetworkClient;
import it.polimi.ingsw.am40.server.network.virtual_view.Protocol;
import it.polimi.ingsw.am40.server.network.virtual_view.VVServer;

import java.io.IOException;
import java.net.Socket;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServerNetworkRMIManager implements NetworkManagerServer{

    //ATTRIBUTES

    /**
     * The reference to the VVServer, which is the class that manage all the incoming requests and is part of the MVC
     */
    private final VVServer mainServerClass;

    private final Protocol usedProtocol = Protocol.RMI;

    private int port;

    private String hostName;



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
     *
     * @return
     */
    @Override
    public void initCommunication() {


            Thread listeningThreadRMI = new Thread(() -> {
                try {
                    //Creation of the RMI registry
                    Registry registry = LocateRegistry.createRegistry(this.port);
                    RemoteInterfaceServer obj = new RemoteObjectServer();

                    //Binding of the remote object in RMI registry
                    registry.rebind("ServerRemote", obj);

                    System.out.println("Server RMI is running at port " + this.port);
                } catch (RemoteException e) {
                    System.out.println("Something went wrong with the RMI server initialization");
                    e.printStackTrace();
                    throw new RuntimeException(e);//fixme relaunch/protected jdoc
                }
                //fixme thread
            });

            listeningThreadRMI.start();

    }


    /** //todo
     *
     */
   @Override
    public void initPing() {

    }

    @Override
    public void setPort(int port) {

    }

    @Override
    public int getPort() {
        return 0;
    }

    @Override
    public void setHostName(String hostName) {

    }

    @Override
    public String getHostName() {
        return null;
    }

    @Override
    public Protocol getUsedProtocol() {
        return null;
    }

    @Override
    public int connectedClientNotification(Socket socket) {
        return 0;
    }

    @Override
    public void disconnectedClientNotification(NetworkClient client) throws IOException {

    }

    @Override
    public void sendSerializedMessage(Data message, NetworkClient client) {

    }


}
