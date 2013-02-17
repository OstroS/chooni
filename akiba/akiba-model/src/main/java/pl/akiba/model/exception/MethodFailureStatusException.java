package pl.akiba.model.exception;

public class MethodFailureStatusException extends StatusException {

    private static final long serialVersionUID = 4441588132428576156L;

    public MethodFailureStatusException(String message) {
        super(message);
    }

    public MethodFailureStatusException(String message, Throwable cause) {
        super(message, cause);
    }

    public MethodFailureStatusException(Throwable cause) {
        super(cause);
    }

}
