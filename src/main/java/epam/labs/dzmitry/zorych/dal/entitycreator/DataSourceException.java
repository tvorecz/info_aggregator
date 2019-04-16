package epam.labs.dzmitry.zorych.dal.entitycreator;

public class DataSourceException extends Exception {
    private static final long serialVersionUID = 7176373004659096805L;

    private int code;

    public int getCode() {
        return code;
    }

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

    public DataSourceException(String message, int code){
        super(message);
        this.code = code;
    }
}
