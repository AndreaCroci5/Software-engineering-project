package it.polimi.ingsw.am40.client.network;

public class NetworkProblemException extends Exception {
    public NetworkProblemException(){
        super("Problem with the server connection!");
    }

    public NetworkProblemException(String message){
        super(message);
    }
}
