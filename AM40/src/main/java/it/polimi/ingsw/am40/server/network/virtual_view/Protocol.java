package it.polimi.ingsw.am40.server.network.virtual_view;

/**
 * Enum to define how each client is connected to the server
 */
public enum Protocol {
    /**
     * Protocol which uses sockets and serialization via JSON file messages
     */
    TCP,
    /**
     * Protocol which uses remote calls to methods for information communication
     */
    RMI
}
