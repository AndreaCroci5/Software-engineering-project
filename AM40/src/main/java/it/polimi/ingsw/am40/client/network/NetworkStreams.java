package it.polimi.ingsw.am40.client.network;

import java.io.BufferedReader;
import java.io.PrintWriter;

public class NetworkStreams {
    private PrintWriter out = null;

    private BufferedReader in = null;

    public NetworkStreams(){

    }
    public NetworkStreams(PrintWriter out, BufferedReader in){
        this.out = out;
        this.in = in;
    }

    public PrintWriter getOut() {
        return out;
    }

    public void setOut(PrintWriter out) {
        this.out = out;
    }

    public BufferedReader getIn() {
        return in;
    }

    public void setIn(BufferedReader in) {
        this.in = in;
    }
}
