package it.polimi.ingsw.am40.server.network.TCP;

//todo javadoc + logic
public class PingPongTCP implements Runnable {
/*
    private List<Socket> pongers;

    private final ServerNetworkTCPManager manager;

    public PingPongTCP(ServerNetworkTCPManager manager){

        this.pongers = new ArrayList<>();
        this.manager = manager;
    }
    //todo jdoc
    public synchronized void addPonger(Socket socket){
        this.pongers.add(socket);
    }

    public synchronized void removePonger(Socket socket){
        this.pongers.remove(socket);
    }

    /**
     * Runs this operation.
     */
    @Override
    public void run() {
       /* try {

//todo remove ponger
            while (!Thread.currentThread().isInterrupted()) {
                if (!pongers.isEmpty()) {
                    for (Socket s : this.pongers) {
                        try {
                            Scanner in = new Scanner(s.getInputStream());
                            PrintWriter out = new PrintWriter(s.getOutputStream(), true);
                            out.println("ping");
                            Thread.sleep(3000L);
                            if (!isResponseArrived(in)){
                                //fixme
                                System.out.println("Client disconnected");
                                manager.disconnectClient(s);
                            }
                            in.close();
                            out.close();


                        } catch (Exception e) {
                            // Se non è possibile inviare il ping, disconnetti il client
                            System.out.println("Client disconnected");
                            manager.disconnectClient(s);
                        }


                    }

                }
                Thread.sleep(5000L);
            }
        } catch (InterruptedException ex) {
            System.out.println("PingPong ERROR!!!");
            throw new RuntimeException(ex);
        }

    }


    private boolean isResponseArrived(Scanner in){
        String response = null;
        response = in.nextLine();

        // Se hai ricevuto la risposta desiderata, imposta il flag a true
        if (response != null && response.equals("pong")) {
            System.out.println("pong received");
            return true;
        }
        return false;*/
    }
}