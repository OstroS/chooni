package pl.akiba.model.entities;

/**
 * 
 * @author OstroS
 */
public class Kind {

    private int id;
    private String name;

    public Kind(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Kind() {
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
        return "Kind{" + "id=" + id + ", name=" + name + '}';
    }

}
