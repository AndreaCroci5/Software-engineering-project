package it.polimi.ingsw.am40.server.model;

import it.polimi.ingsw.am40.server.model.Coordinates;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CoordinatesTest {

    @Test
    void testEquals() {
        Coordinates c1 = new Coordinates(1, 2);
        Coordinates c2 = new Coordinates(1, 2);

        assertTrue(c1.equals(c2));
    }
}