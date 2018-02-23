package info233v2018.labuke8;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class NetworkTwoTest {
    private NetworkTwo network;

    @BeforeEach
    void setup() {
        network = new NetworkTwo();
    }

    @Test
    void lookupPerson() {
        PersonTwo aage = network.lookupPerson("Aage");
        assertEquals("Aage", aage.getName());
    }

    @Test
    void friends() {
        ArrayList<String> friends = network.friends("Aage");
        assertEquals(friends.get(0),"Bård");
        assertEquals(friends.get(1), "Dag");
    }
}