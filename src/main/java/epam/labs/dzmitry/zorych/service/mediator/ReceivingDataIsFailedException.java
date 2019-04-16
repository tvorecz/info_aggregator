package epam.labs.dzmitry.zorych.service.mediator;

public class ReceivingDataIsFailedException extends Exception {
    private static final long serialVersionUID = 3273157503023226886L;

    private int code;

    public int getCode() {
        return code;
    }

    public ReceivingDataIsFailedException() {
        super();
    }

    public ReceivingDataIsFailedException(String message, int code) {
        super(message);

        this.code = code;
    }

    public ReceivingDataIsFailedException(String message, Throwable cause, int code) {
        super(message, cause);

        this.code = code;
    }

    public ReceivingDataIsFailedException(String message) {
        super(message);
    }

    public ReceivingDataIsFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public ReceivingDataIsFailedException(Throwable cause) {
        super(cause);
    }
}
