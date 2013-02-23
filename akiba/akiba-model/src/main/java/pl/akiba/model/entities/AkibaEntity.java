package pl.akiba.model.entities;

import java.io.Serializable;

/**
 * 
 * @author sobczakt
 */
public abstract class AkibaEntity implements Serializable {

    private static final long serialVersionUID = -110894860475341931L;

    public abstract boolean isValid(OperationType type);

    public enum OperationType {

        GET,
        CREATE,
        UPDATE,
        DELETE

    }

}
