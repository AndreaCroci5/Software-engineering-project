package it.polimi.ingsw.am40.client.network;

import org.json.simple.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Map;

/**
 * Concrete network protocol implementation for TCP
 */
public class ClientNetworkTCPManager implements NetworkManagerClient{

    //ATTRIBUTES

    /**
     * Reference to the main client class
     */
    private final Client client;

    /**
     * Boolean which says if the TCP communication is active on the client
     */
    private boolean communicationOn;
//fixme hostname on client (rmi too)
    /**
     * Host name for the client
     */
    private String hostName;


    //CONSTRUCTOR METHOD

    /**
     * Constructor for ClientNetworkTCPManager
     * @param client the reference to the Client class
     */
    public ClientNetworkTCPManager(Client client){
        this.client = client;
    }



    //GETTER METHODS

    /**
     * Getter for client
     * @return client as reference
     */
    @Override
    public Client getClient() {
        return this.client;
    }

    /**
     * Getter for boolean communicationOn
     * @return true if the listening logic is running, false otherwise
     */
    public boolean isCommunicationOn() {
        return communicationOn;
    }



    //SETTER METHODS

    /**
     * Setter for the communicationOn attribute
     * @param communicationOn true if client can communicate with TCP, false otherwise
     */
    public void setCommunicationOn(boolean communicationOn) {
        this.communicationOn = communicationOn;
    }



    //NETWORK MANAGER METHODS IMPLEMENTATION

    /**
     * Method to initialize the entire communication protocol and to set initial parameters
     */
    @Override
    public void initCommunication(int port, String hostName, String serverAddress) {
        try {

            //Trying to connect with the server by Socket
            Socket socket = new Socket(serverAddress, port);

            this.hostName = hostName;

            //Creation of the output and input stream
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            //Initialize the thread which listens to the server
            Thread listeningThread = new Thread(() -> {
                this.runListeningLogic(in, out);
            });

           //fixme close + communicationOn false
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method which handles the incoming messages from the server by giving an immediate response or by calling
     * other method on Client class
     * @param in input stream from server socket
     * @param out output stream to the server socket
     */
    private void runListeningLogic(BufferedReader in, PrintWriter out){
        this.communicationOn = true;
        while(communicationOn){
            try {
                String response = in.readLine();
                switch(response){
                    case "ping":
                        out.println("pong");
                        break;
                    case "message":

                        break;
                    default:
                        break;
                }
            } catch (IOException e) {
                System.out.println("Disconnected from the server");
                try {
                    in.close();
                } catch (IOException ex) {
                    break;
                    //fixme
                }
                out.close();
            }


        }
    }


    /**
     * Method which sends network messages to the server using JSON serialization
     * @param message the network message (as object), which will be converted in a JSON (jackson) string
     * @param out the output socket stream
     */
    private synchronized void sendSerializedParameters(Message message, PrintWriter out){
        ObjectMapper objectMapper = new ObjectMapper();

        String json = objectMapper.writeValueAsString(message);
        out.println(json);
    }
}
