package pl.akiba.model.exception;

public class NotFoundStatusException extends StatusException {

    private static final long serialVersionUID = 4441588132428576156L;

    public NotFoundStatusException(String message) {
        super(message);
    }

    public NotFoundStatusException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundStatusException(Throwable cause) {
        super(cause);
    }

}
