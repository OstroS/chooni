package pl.akiba.model.entities;

/**
 * 
 * @author OstroS
 */
public class Profile {

    private int id;
    private String name;
    private boolean def;
    private boolean active;

    public Profile(int id, String name, boolean def, boolean active) {
        this.id = id;
        this.name = name;
        this.def = def;
        this.active = active;
    }

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

    /**
     * @return is default profile
     */
    public boolean isDef() {
        return def;
    }

    public void setDef(boolean def) {
        this.def = def;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    //TODO
    public boolean isValid(OperationType operationType) {
        return true;
    }

    @Override
    public String toString() {
        return "Profile [id=" + id + ", name=" + name + ", default=" + def + ", active=" + active + "]";
    }

}