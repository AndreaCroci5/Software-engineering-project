package it.polimi.ingsw.am40.server.network.TCP;

import it.polimi.ingsw.am40.server.network.NetworkManagerServer;
import it.polimi.ingsw.am40.server.network.virtual_view.Protocol;
import it.polimi.ingsw.am40.server.network.virtual_view.VVServer;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ServerNetworkTCPManager is the class which manage communications (server-side) through TCP Socket protocol
 */
public class ServerNetworkTCPManager implements NetworkManagerServer {

    //ATTRIBUTES

    /**
     * Name of the server host on the network
     */
    private String hostName;

    /**
     * Port on which server accept incoming connection requests
     */
    private int portNumber;

    /**
     * The reference to the VVServer, which is the class that manage all the incoming requests and is part of the MVC
     */
    private final VVServer mainServerClass;

    /**
     * Attribute introduced to avoid that a great number of clients are connected in the same time
     */
    public static int activeTCPConnections = 0;

    /**
     * Static attribute which indicates the maximum limit of active simultaneously connections with TCP
     */
    public static final int maxActiveTCPConnections = 10;








    //CONSTRUCTOR METHOD

    /**
     * Constructor for ServerNetworkTCPManager
     */
    public ServerNetworkTCPManager(VVServer server){
        this.mainServerClass = server;
    }



    //GETTER METHODS

    /**
     * Getter for portNumber
     * @return the port number of the server
     */
    private int getPortNumber() {
        return portNumber;
    }

    /**
     * Getter for hostName
     * @return the host name of the server
     */
    private String getHostName() {
        return hostName;
    }

    /**
     * Getter for server reference
     * @return the VVServer as reference
     */
    public VVServer getMainServerClass() {
        return mainServerClass;
    }





    //SETTER METHODS

    /**
     * Setter for host name
     * @param hostName the host name of the server
     */
    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    /**
     * Setter for portNumber
     * @param portNumber the port num to set
     */
    public void setPortNumber(int portNumber) {
        this.portNumber = portNumber;
    }





    //NETWORK MANAGER METHODS

    /**
     * Method to start communications with a specific protocol.
     * This method initializes the TCP modules to communicate with the TCP clients
     */
    @Override
    public void initCommunication(int port, String hostName) {

        //Set the attributes
        this.setPortNumber(port);
        this.setHostName(hostName);

        //Calls runListeningLogic
        Thread listeningTCPThread = new Thread(() -> {
            this.runListeningLogic(port);
        });
        listeningTCPThread.start();
        //fixme

    }

    /**
     * Method to stop the communication with the clients (server not reachable on the network)
     */
    @Override
    public void stopCommunication() {

    }

    /**
     * Method to init the ping logic in order to know if clients are still connected
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


    //OTHER METHODS

    /**
     * Method to parse the strings serialized with JSON file from the client.
     * It calls the methods on the Server class in order to handle the network message
     * @param line the message to pars as serialized string
     */
    public synchronized void parseJSONStringTCP(String line, Socket socket, PrintWriter out) {
        HashMap<String, String> m = new HashMap<>();
        switch (line){
            case "ex": {
                m.put("hello", this.hostName);
                this.sendJSONMessageTCP(m, socket, out);
                break;
            }

        }
    }

    public void sendJSONMessageTCP(Map<String, String> parameters, Socket socket, PrintWriter out){
        JSONObject jsonObject = new JSONObject();

        for (String key : parameters.keySet())
        {
            jsonObject.put( key , parameters.get(key));
        }

        String jsonString = jsonObject.toJSONString();
        out.println(jsonString);
    }


    /**
     * Private method to run the listening logic.
     * It uses a thread from an ExecutorService or creates a new Thread for each incoming
     * connection request
     * @param port the listening port of the server from prefs
     */
    private void runListeningLogic(int port){


        try {
            ExecutorService executor = Executors.newCachedThreadPool();
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("TCP Server is running on port " + port);

            //runningListening = true;

            while (/*runningListening*/true) {

                try {
                    Socket clientSocket = serverSocket.accept();

                    System.out.println("Accepting..");

                    // Delegate client handling to ClientHandler

                    ClientHandlerTCP clientHandler = new ClientHandlerTCP(this, clientSocket);
                    executor.submit(clientHandler);

                    this.connectedClientNotification(clientSocket/*, this.*/);

                } catch (IOException e) {
                    System.out.println("Something went wrong with the server socket acceptation");
                    break;
                    //throw new RuntimeException(e);
                }
            }

            executor.shutdown();


        } catch (IOException e) {
            System.out.println("Something went wrong with the server socket initialization on the port " + port);
            e.printStackTrace();
        }
    }

//fixme
    public synchronized void disconnectClient(Socket clientSocket){
       /* // Implementa la logica per disconnettere il client, ad esempio chiudendo il socket
        try {
            ping.removePonger(clientSocket);
            clientSocket.close();
            activeTCPConnections--;
            System.out.println("Client disconnected: " + clientSocket.getInetAddress());
            System.out.println(activeTCPConnections + " clients are logged with TCP now");

        } catch (Exception e) {
            System.out.println("Error disconnecting client: " + e.getMessage());
        }
        //this.mainServerClass.setClientOffline(clientSocket);*/
    }

    private synchronized void connectedClientNotification(Socket clientSocket/*, PingPongTCP pingPong*/){
        //pingPong.addPonger(clientSocket);
        this.mainServerClass.createNewOrphanClient(Protocol.TCP, clientSocket);
       activeTCPConnections++;
        System.out.println("Accepted");
        System.out.println(activeTCPConnections + " clients are logged with TCP now");
    }

    public synchronized void disconnectedClientNotification(Socket socket, Scanner in, PrintWriter out) throws IOException {
        ServerNetworkTCPManager.activeTCPConnections--;
        System.out.println("1 client has disconnected from TCP");
        System.out.println(ServerNetworkTCPManager.activeTCPConnections + " clients are logged with TCP now");
        in.close();
        out.close();
        socket.close();
    }
}

