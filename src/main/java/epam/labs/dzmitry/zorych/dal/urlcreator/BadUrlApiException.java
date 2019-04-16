package epam.labs.dzmitry.zorych.dal.urlcreator;

public class BadUrlApiException extends Exception {
    private static final long serialVersionUID = 2946267101568276417L;

    private int code;

    public int getCode() {
        return code;
    }

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

    public BadUrlApiException(String message, int code) {
        super(message);
        this.code = code;
    }

    public BadUrlApiException(String message, Throwable cause, int code) {
        super(message, cause);
        this.code = code;
    }
}
