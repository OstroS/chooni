/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.akiba.model.entities;

import java.math.BigDecimal;

/**
 *
 * @author kostrows
 */
public class Expense {
    private BigDecimal amount;
    private String kind;
    private String profile;

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    
    
}
