package pl.akiba.model.entities;

/**
 * 
 * Do zastapienia przez model danych
 * @author OstroS
 */
public class Profile {

    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Profile(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Profile{" + "id=" + id + ", name=" + name + '}';
    }
}