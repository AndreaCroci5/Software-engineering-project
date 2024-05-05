package it.polimi.ingsw.am40.server.network.virtual_view;


import java.util.ArrayList;
import java.util.List;

/**
 * Class which represents a party (lobby) on the server. It's used to control the
 * status of each active lobby on the server and the players who take part to them
 * Each party has a creator and has a status
 */
public class NetworkParty {


    //ATTRIBUTES

    /**
     * Identification number of game on the model.
     * Every time a new request for new game is incoming from the network,
     * a new ID is assigned as well
     */
    private final int partyID;

    /**
     * Number of total clients which the party should have, basing on the request
     * for a new game incoming from the network
     */
    private final int totalNumOfClients;

    /**
     * Number of current clients logged into a party
     */
    private int currentNumOfClients;

    /**
     * List of references to the clients actually connected
     */
    private final List<NetworkClient> clients;

    /**
     * Sequentially increasing number ID for party
     */
    private static int partyCounter = 0;

    /**
     * Object to avoid deadlock
     */
    private static final Object lock = new Object();






    //CONSTRUCTOR METHOD

    /**
     * Constructor for NetworkParty
     * @param totalNumOfClients number of clients required to start
     */
    public NetworkParty(int totalNumOfClients) {
        //More than one thread could access to this block of code, but each party must have a different ID
        synchronized (lock){
            NetworkParty.partyCounter++;
            this.partyID = NetworkParty.partyCounter;
        }

        this.totalNumOfClients = totalNumOfClients;
        this.clients = new ArrayList<>();
        this.currentNumOfClients = 0;
    }



    //GETTER METHODS

    /**
     * Getter for partyID
     * @return the partyID
     */
    public int getPartyID() {
        return partyID;
    }

    /**
     * Getter for totalNumOfClients
     * @return the number of total client of a party
     */
    public int getTotalNumOfClients() {
        return totalNumOfClients;
    }

    /**
     * Getter for currentNumOfClients
     * @return the number of current client of a party
     */
    public int getCurrentNumOfClients() {
        return currentNumOfClients;
    }

    /**
     * Getter for the list of clients logged in the lobby
     * @return the list of clients
     */
    public List<NetworkClient> getClients() {
        return clients;
    }



    //SETTER METHODS

    /**
     * Setter for currentNumOfPlayer
     * @param currentNumOfClients to set the number of logged clients
     */
    public void setCurrentNumOfClients(int currentNumOfClients) {
        this.currentNumOfClients = currentNumOfClients;
    }

    /**
     * It increases currentNumOfPlayer by one
     */
    public void increaseCurrentNumOfClients(){
        this.currentNumOfClients++;
    }



    //MANAGE PUBLIC METHODS FOR NETWORK CLIENT

    /**
     * This method logs a client into this party.
     * This method is sync in order to avoid that two clients on two different
     * threads log at the same time and the currNumOfClients is not updated correctly
     * @param client the reference to the client to log
     */
    synchronized void logClient(NetworkClient client) throws NonExistentClientException {
        //If the client is the first in the lobby, it's the creator.
       if(client != null)
       {
           this.currentNumOfClients++;
           if (this.currentNumOfClients == 1) {
               client.setCreator(true);
           }
           this.clients.add(client);
       }else throw new NonExistentClientException();
    }

    /**
     * This method remove a client from this party.
     * This method is sync in order to avoid that two clients on two different
     * threads log at the same time and the currNumOfClients is not set correctly
     */
    synchronized void removeClient(NetworkClient client) throws NonExistentClientException, CreatorRemotionException {
        if(client.isCreator())
            throw new CreatorRemotionException();

        if(client != null)
        {
            boolean existFlag = false;
            for (NetworkClient c : this.clients) {
                if(client == c){
                    this.currentNumOfClients--;
                    this.clients.remove(client);
                    existFlag = true;
                }
            }

            if (existFlag == false)
                throw new NonExistentClientException();

            this.clients.add(client);
        }else
            throw new NonExistentClientException();

    }


}
