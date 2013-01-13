package pl.akiba.model.exception;

/**
 * 
 * @author sobczakt
 */
public class EntityIsNotValidException extends AkibaGeneralException {

    private static final long serialVersionUID = 9064625063810517568L;

    public EntityIsNotValidException(String message) {
        super(message);
    }

    public EntityIsNotValidException(String message, Throwable cause) {
        super(message, cause);
    }

    public EntityIsNotValidException(Throwable cause) {
        super(cause);
    }

}
