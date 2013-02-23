package pl.akiba.model.entities;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * @author OstroS
 */
public class Kind extends AkibaEntity {

    private static final long serialVersionUID = 4580476173597389489L;

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
    public boolean isValid(OperationType type) {
        if (type == OperationType.CREATE) {
            if (StringUtils.isNotBlank(name)) {
                return true;
            }
        } else if (type == OperationType.UPDATE) {
            if (id > 0 && StringUtils.isNotBlank(name)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public String toString() {
        return "Kind [id=" + id + ", name=" + name + "]";
    }

}
