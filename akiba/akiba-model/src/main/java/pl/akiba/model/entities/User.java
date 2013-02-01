package pl.akiba.model.entities;

/**
 * Klasa do nadpisania przez model danych
 * @author kostrows
 */
public class User {
    Long id;
    String name;

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
    
    /**
     * Method should not be used - currently not supported.
     * Implemented only to be compliant with Spring Security standard.
     * @return Returns always FALSE
     */
    public Boolean isDisabled() {
    	return Boolean.FALSE;
    }
    
    /**
     * Method should not be used - currently not supported.
     * Implemented only to be compliant with Spring Security standard.
     * @return Returns always FALSE
     */  
    public Boolean isLocked() {
    	return Boolean.FALSE;
    }
    
    
    
    
}
