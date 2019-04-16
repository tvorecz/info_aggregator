package epam.labs.dzmitry.zorych.dal.apimanager;

public class JsonApiManagerException extends Exception {
    private static final long serialVersionUID = -1247161274872141594L;

    private int code;

    public int getCode() {
        return code;
    }

    public JsonApiManagerException() {
        super();
    }

    public JsonApiManagerException(String message) {
        super(message);
    }

    public JsonApiManagerException(String message, Throwable cause) {
        super(message, cause);
    }

    public JsonApiManagerException(Throwable cause) {
        super(cause);
    }

    public JsonApiManagerException(String message, int code) {
        super(message);
        this.code = code;
    }

    public JsonApiManagerException(String message, Throwable cause, int code) {
        super(message, cause);
        this.code = code;
    }
}
