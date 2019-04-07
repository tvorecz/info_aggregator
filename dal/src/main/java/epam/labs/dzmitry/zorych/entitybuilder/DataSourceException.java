package epam.labs.dzmitry.zorych.entitybuilder;

public class DataSourceException extends Exception {
    private static final long serialVersionUID = 7176373004659096805L;

    public DataSourceException() {
        super();
    }

    public DataSourceException(String message) {
        super(message);
    }

    public DataSourceException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataSourceException(Throwable cause) {
        super(cause);
    }
}
