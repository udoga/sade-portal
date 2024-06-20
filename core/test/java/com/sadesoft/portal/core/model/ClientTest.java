package com.sadesoft.portal.core.model;

import com.sadesoft.portal.core.sample.ClientSamples;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ClientTest {
    private Client validClient;

    @BeforeEach
    public void setup() {
        validClient= ClientSamples.validClient;
    }

    @Test
    public void initializesWithoutFields() {
        Client client = new Client();
        assertNull(client.id);
        assertNull(client.name);
    }

    @Test
    public void printsFields() {
        assertEquals("Client[id=null" +
                " name=Sample Firm" +
                "]", validClient.toString());
    }

    @Test
    public void clonesWithFields() {
        assertNull(validClient.withId(null).id);
        assertNull(validClient.withName(null).name);
    }

    @Test
    public void checksEquality() {
        assertEquals(validClient, validClient.copy());
        assertNotEquals(validClient, validClient.withId("1"));
        assertNotEquals(validClient, validClient.withName(null));
    }
}

