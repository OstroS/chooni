package pl.akiba.model.entities;

import java.math.BigDecimal;

/**
 * Entity represents user expense.
 * 
 * @author kostrows
 * @author sobczakt
 */
public class Expense {

    private int id;
    private BigDecimal amount;
    private Kind kind;
    private Profile profile;

    public Expense(int id, BigDecimal amount, Kind kind, Profile profile) {
        this.id = id;
        this.amount = amount;
        this.kind = kind;
        this.profile = profile;
    }

    public Expense() {
    }

    //only for test
    public Expense(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
        return "Expense [id=" + id + ", amount=" + amount + ", kind=" + kind + ", profile=" + profile + "]";
    }

}
