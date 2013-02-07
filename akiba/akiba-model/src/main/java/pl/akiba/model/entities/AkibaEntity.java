package pl.akiba.model.entities;

/**
 * 
 * @author sobczakt
 */
public abstract class AkibaEntity {

    public abstract boolean isValid(OperationType type);

    public enum OperationType {

        GET,
        CREATE,
        UPDATE,
        DELETE

    }

}
