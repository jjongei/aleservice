package net.siriussoft.aleservice.common.exception;

public class NoQueryResultException extends Exception {

    private static final long serialVersionUID = 1L;

    public NoQueryResultException() {
        super();
    }

    public NoQueryResultException(String message, Throwable cause, boolean enableSuppression,
                                  boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public NoQueryResultException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoQueryResultException(String message) {
        super(message);
    }

    public NoQueryResultException(Throwable cause) {
        super(cause);
    }
}
