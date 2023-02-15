package org.client.util.exceptions;

public class ClientWithSuchICPExists extends RuntimeException {
    public ClientWithSuchICPExists(String message) {
        super(message);
    }
}
