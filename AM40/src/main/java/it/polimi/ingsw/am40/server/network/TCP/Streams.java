package it.polimi.ingsw.am40.server.network.TCP;

import java.io.PrintWriter;
import java.util.Scanner;

public class Streams {
    private Scanner in;
    private PrintWriter out;

    public Streams(Scanner in, PrintWriter out){
        this.in = in;
        this.out = out;
    }

    public Scanner getIn() {
        return in;
    }

    public void setIn(Scanner in) {
        this.in = in;
    }

    public PrintWriter getOut() {
        return out;
    }

    public void setOut(PrintWriter out) {
        this.out = out;
    }
}
