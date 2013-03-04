package pl.akiba.model.exception;

/**
 * 
 * @author sobczakt
 */
public class EmptyResultException extends AkibaGeneralException {

    private static final long serialVersionUID = 1754880034434451426L;

    public EmptyResultException(String message) {
        super(message);
    }

    public EmptyResultException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmptyResultException(Throwable cause) {
        super(cause);
    }

}
