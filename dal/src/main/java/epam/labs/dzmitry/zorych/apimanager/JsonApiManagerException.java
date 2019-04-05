package epam.labs.dzmitry.zorych.apimanager;

public class JsonApiManagerException extends Exception {
    private static final long serialVersionUID = -1247161274872141594L;

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
}
