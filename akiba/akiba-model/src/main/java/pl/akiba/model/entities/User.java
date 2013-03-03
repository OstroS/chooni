package pl.akiba.model.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author kostrows
 * @author sobczakt
 */
public class User extends AkibaEntity {

    private static final long serialVersionUID = 956451699793082534L;

    protected Long id;
    protected final List<ROLE> authorities = new ArrayList<ROLE>(0);
    protected Boolean disabled = Boolean.FALSE;
    protected Boolean locked = Boolean.FALSE;

    public User(Long id, ROLE authority) {
        this.id = id;
        this.authorities.add(authority);
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ROLE> getAuthorities() {
        return authorities;
    }

    public User addAuthority(ROLE authority) {
        authorities.add(authority);
        return this;
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
    
    public void setDisabled(Boolean disabled) {
    	this.disabled = disabled;
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
    
    public void setLocked(Boolean locked) {
    	this.locked  = locked;
    }

    @Override
    public boolean isValid(OperationType type) {
        //TODO
        return true;
    }

    public enum ROLE {

        ROLE_USER;

        public static ROLE getRole(String strRole) {
            for (ROLE role : values()) {
                if (strRole.equalsIgnoreCase(role.name())) {
                    return role;
                }
            }

            return null;
        }
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", authorities=" + authorities + "]";
    }

}
