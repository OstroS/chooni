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
    private Kind kind;
    private Profile profile;

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Kind getKind() {
        return kind;
    }

    public void setKind(Kind kind) {
        this.kind = kind;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Expense{" + "amount=" + amount + ", kind=" + kind + ", profile=" + profile + '}';
    }
    
}
