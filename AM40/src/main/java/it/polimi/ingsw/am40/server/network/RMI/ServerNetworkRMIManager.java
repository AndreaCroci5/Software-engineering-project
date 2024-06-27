package it.polimi.ingsw.am40.server.network.RMI;

import it.polimi.ingsw.am40.client.network.RMI.RemoteInterfaceClient;
import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.server.network.NetworkManagerServer;
import it.polimi.ingsw.am40.server.network.virtual_view.NetworkClient;
import it.polimi.ingsw.am40.server.network.virtual_view.NonExistentClientException;
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

    /**
     * The protocol used by the manager. In this case RMI
     */
    private final Protocol usedProtocol = Protocol.RMI;

    /**
     * The port number on which the server accepts incoming connection requests
     */
    private int port;

    /**
     *The host name of the server
     */
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


    /**
     * Getter for host name
     * @return the host name of the server on the network
     */
    @Override
    public String getHostName() {
        return this.hostName;
    }

    /**
     * Getter for the used protocol
     * @return the protocol used for the communications
     */
    @Override
    public Protocol getUsedProtocol() {
        return this.usedProtocol;
    }

    /**
     * Getter for port
     *
     * @return the port number used for the communications
     */
    @Override
    public int getPort() {
        return this.port;
    }



    //SETTER METHODS


    /**
     * Setter the attribute port
     * @param port the port number
     */
    @Override
    public void setPort(int port) {
        this.port = port;
    }

    /**
     * Setter for the host name
     * @param hostName the name of the server on the network
     */
    @Override
    public void setHostName(String hostName) {
        this.hostName = hostName;
    }



    //NETWORK MANAGER METHODS


    /**
     * Method to start communications with a specific protocol. In this case RMI
     */
    @Override
    public void initCommunication() {

            Thread listeningThreadRMI = new Thread(() -> {
                try {
                    //Creation of the RMI registry
                    Registry registry = LocateRegistry.createRegistry(this.port);
                    RemoteObjectServer obj = new RemoteObjectServer();
                    RemoteInterfaceServer remInterface = obj;
                    obj.setManager(this);

                    //Binding of the remote object in RMI registry
                    registry.rebind("ServerRemote", remInterface);

                    System.out.println("Server RMI is running at port " + this.port);
                } catch (RemoteException e) {
                    System.out.println("Something went wrong with the RMI server initialization");
                    e.printStackTrace();
                    throw new RuntimeException(e);//fixme protected jdoc
                }
            });

            listeningThreadRMI.start();

    }

    /**
     * Method to init the ping logic in order to know if clients are still connected
     */
    @Override
    public void initPing() {
        Thread pingThreadRMI = new Thread(() -> {
            while(true){
                for (NetworkClient c: this.mainServerClass.getAllRMIClients()) {

                }
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        });
    }


    /**
     * Method to notify VVServer that a connection with a new client has been accepted
     *
     * @param socket the socket of the client
     * @return the ID of the client
     */
    @Override
    public int newConnectedClientNotification(Socket socket, RemoteInterfaceClient remInterface) {
        int clientID = this.mainServerClass.createNewOrphanClient(this.usedProtocol, null, remInterface);
        try {
            this.mainServerClass.setClientOnline(this.mainServerClass.getClientByID(clientID));
            this.mainServerClass.getClientByID(clientID).setStreams(null);
        } catch (NonExistentClientException e) {
            System.out.println("Client doesn't exist");
        }
        VVServer.activeConnections++;
        System.out.println("Accepted RMI client");
        System.out.println(VVServer.activeConnections + " clients are logged now");
        //fixme bind socket e gestione riconnessione client con nickname

        //fixme per fine partita devo fare in modo che si sciolga il party
        return clientID;
    }

    /**
     * Method to notify the VVServer that a client has been reconnected
     *
     * @param clientName the host name of the client
     */
    @Override
    public void reconnectionNotification(String clientName) {

    }

    /**
     * Method to notify the VVServer of a client disconnection
     *
     * @param client
     * @throws IOException
     */
    @Override
    public void disconnectedClientNotification(NetworkClient client) throws IOException {

    }

    @Override
    public void removeClientNotification() {

    }

    /**
     * Method to send to a specific client a network message
     *
     * @param message the message to send
     * @param client  the recipient
     */
    @Override
    public void sendSerializedMessage(Data message, NetworkClient client) {
        message.doRMI(client.getRemoteInterface(), null);
    }








}
