package pl.akiba.model.exception;

/**
 * 
 * @author sobczakt
 */
public class StatusException extends AkibaGeneralException {

    private static final long serialVersionUID = 9014327794638653739L;

    public StatusException(String message) {
        super(message);
    }

    public StatusException(String message, Throwable cause) {
        super(message, cause);
    }

    public StatusException(Throwable cause) {
        super(cause);
    }

}
