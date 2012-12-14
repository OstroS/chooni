package pl.akiba.model.entities;

/**
 * Klasa do nadpisania przez model danych
 *
 * @author kostrows
 */
public class User {

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    Long id;
    String name;

    public boolean isLocked() {
        return false;
    }

    public boolean isDisabled() {
        return false;
    }
}
