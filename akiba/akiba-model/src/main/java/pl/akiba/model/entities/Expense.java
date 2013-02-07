package pl.akiba.model.entities;

import java.util.Date;

/**
 * Entity represents user expense.
 * 
 * @author kostrows
 * @author sobczakt
 */
public class Expense extends AkibaEntity {

    private int id;
    private double amount;
    private Kind kind;
    private Profile profile;
    private Date date;

    public Expense(int id, double amount, Kind kind, Profile profile, Date date) {
        this.id = id;
        this.amount = amount;
        this.kind = kind;
        this.profile = profile;
        this.date = date;
    }

    //only for test!
    public Expense(int id, double amount) {
        this.id = id;
        this.amount = amount;
    }

    public Expense() {
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

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean isValid(OperationType operationType) {
        if (operationType == OperationType.CREATE) {
            if (amount > 0 && profile != null && kind != null) {
                return true;
            }
        } else if (operationType == OperationType.UPDATE) {
            if (id > 0 && amount > 0 && profile != null && kind != null && date != null) {
                return true;
            }
        }

        return false;
    }

    @Override
    public String toString() {
        return "Expense [id=" + id + ", amount=" + amount + ", kind=" + kind + ", profile=" + profile + ", date="
                + date + "]";
    }

}
