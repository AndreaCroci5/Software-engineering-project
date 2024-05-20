package it.polimi.ingsw.am40.server.network.TCP;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

//todo doc
public class ClientHandlerTCP implements Runnable{

    private final Socket socket;

    private final ServerNetworkTCPManager TCPManager;


    public ClientHandlerTCP(ServerNetworkTCPManager serverNetworkTCPManager, Socket clientSocket) {
        this.socket = clientSocket;
        this.TCPManager = serverNetworkTCPManager;
    }

    /**
     * Runs this operation.
     */
    @Override
    public void run() {
        try {
            Scanner in = new Scanner(socket.getInputStream());
            PrintWriter out = new PrintWriter(socket.getOutputStream());

            while (true) {
                String line = in.nextLine();
                if (line.equals("quit")) {
                    break;
                } else {
                    //todo logica + max connection
                    this.getTCPManager().parseJSONStringTCP(line, this.socket, out);
                    out.flush();
                }
            }
// Chiudo gli stream e il socket
            TCPManager.disconnectedClientNotification(socket, in, out);

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public ServerNetworkTCPManager getTCPManager() {
        return TCPManager;
    }
}
