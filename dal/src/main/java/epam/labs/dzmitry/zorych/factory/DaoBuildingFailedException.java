package epam.labs.dzmitry.zorych.factory;

public class DaoBuildingFailedException extends Exception {
    private static final long serialVersionUID = -7262799279734174428L;

    public DaoBuildingFailedException() {
        super();
    }

    public DaoBuildingFailedException(String message) {
        super(message);
    }

    public DaoBuildingFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public DaoBuildingFailedException(Throwable cause) {
        super(cause);
    }
}
