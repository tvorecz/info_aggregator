package epam.labs.dzmitry.zorych.service.mediator;

public class InvalidParameterException extends Exception {
    private static final long serialVersionUID = 1031221305950815696L;

    private int code;

    public int getCode() {
        return code;
    }

    public InvalidParameterException() {
        super();
    }

    public InvalidParameterException(String message, int code) {
        super(message);

        this.code = code;
    }

    public InvalidParameterException(String message) {
        super(message);
    }

    public InvalidParameterException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidParameterException(Throwable cause) {
        super(cause);
    }
}
