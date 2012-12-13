package pl.akiba.model.entities;

/**
 * Klasa do nadpisania przez model danych
 *
 * @author kostrows
 */
public class User {

    public boolean isLocked() {
        return false;
    }

    public boolean isDisabled() {
        return false;
    }
}
