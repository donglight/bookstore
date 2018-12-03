package org.zdd.bookstore.exception;

public class BSException extends Exception {

    public BSException() {
    }

    public BSException(String message) {
        super(message);
    }

    public BSException(String message, Throwable cause) {
        super(message, cause);
    }

    public BSException(Throwable cause) {
        super(cause);
    }

    public BSException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
