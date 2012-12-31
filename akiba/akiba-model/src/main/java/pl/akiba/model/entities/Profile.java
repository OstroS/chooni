package pl.akiba.model.entities;

/**
 * 
 * @author OstroS
 */
public class Profile {

    private int id;
    private String name;

    public Profile(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Profile() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Profile{" + "id=" + id + ", name=" + name + '}';
    }
}