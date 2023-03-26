package org.client.util;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ArchivedClientException extends RuntimeException {

    public ArchivedClientException(String message){
        super(message);
    }
}
