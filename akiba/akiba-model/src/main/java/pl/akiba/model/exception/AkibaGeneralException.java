package pl.akiba.model.exception;

/**
 * 
 * @author sobczakt
 */
public class AkibaGeneralException extends RuntimeException {

    private static final long serialVersionUID = -2954650085420641090L;

    public AkibaGeneralException(String message) {
        super(message);
    }

    public AkibaGeneralException(String message, Throwable cause) {
        super(message, cause);
    }

    public AkibaGeneralException(Throwable cause) {
        super(cause);
    }

}
