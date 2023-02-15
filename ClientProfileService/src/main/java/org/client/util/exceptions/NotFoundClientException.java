package org.client.util.exceptions;

public class NotFoundClientException extends RuntimeException{
    public NotFoundClientException(String message) {
        super(message);
    }
}
