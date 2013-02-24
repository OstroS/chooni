package pl.akiba.model.entities;

import java.util.List;



/**
 * Klasa do nadpisania przez model danych
 * 
 * @author kostrows
 */
public class User extends AkibaEntity {

    private static final long serialVersionUID = 956451699793082534L;

    protected Long id;
    protected String name;
    protected List<String> authorities;
    
    public List<String> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<String> authorities) {
		this.authorities = authorities;
	}

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
     * Method should not be used - currently not supported. Implemented only to be compliant with Spring Security
     * standard.
     * 
     * @return Returns always FALSE
     */
    public Boolean isDisabled() {
        return Boolean.FALSE;
    }

    /**
     * Method should not be used - currently not supported. Implemented only to be compliant with Spring Security
     * standard.
     * 
     * @return Returns always FALSE
     */
    public Boolean isLocked() {
        return Boolean.FALSE;
    }

    @Override
    //TODO
    public boolean isValid(OperationType type) {
        return true;
    }
    
    
}
