package epam.labs.dzmitry.zorych.urlbuilder;

public class BadUrlApiException extends Exception {
    private static final long serialVersionUID = 2946267101568276417L;

    public BadUrlApiException() {
    }

    public BadUrlApiException(String message) {
        super(message);
    }

    public BadUrlApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadUrlApiException(Throwable cause) {
        super(cause);
    }
}
