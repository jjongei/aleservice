package net.siriussoft.aleservice.common.exception;

public class APIResultCodeException extends Exception {

    private static final long serialVersionUID = 1L;

    public APIResultCodeException() {
        super();
    }

    public APIResultCodeException(String message, Throwable cause, boolean enableSuppression,
                                  boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public APIResultCodeException(String message, Throwable cause) {
        super(message, cause);
    }

    public APIResultCodeException(String message) {
        super(message);
    }

    public APIResultCodeException(Throwable cause) {
        super(cause);
    }
}
