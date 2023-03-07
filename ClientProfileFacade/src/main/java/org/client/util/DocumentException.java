package org.client.util;

import javax.print.Doc;

public class DocumentException extends RuntimeException {
    private String message;

    public DocumentException() {

    }
    public DocumentException(String message) {
        this.message = message;
    }
}
