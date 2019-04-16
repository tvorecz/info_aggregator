package epam.labs.dzmitry.zorych.service.commonservice;

public class CannotGetDataException extends Exception{
    private static final long serialVersionUID = -290118922810539289L;

    private int code;

    public int getCode() {
        return code;
    }

    public CannotGetDataException() {
        super();
    }

    public CannotGetDataException(String message) {
        super(message);
    }

    public CannotGetDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public CannotGetDataException(Throwable cause) {
        super(cause);
    }

    public CannotGetDataException(String message, int code){
        super(message);
        this.code = code;
    }

    public CannotGetDataException(String message, Throwable cause, int code){
        super(message, cause);
        this.code = code;
    }
}
