/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.akiba.model.entities;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 
 * @author kostrows
 * @author sobczakt
 */
public class Expense implements Serializable {

	public static final long serialVersionUID = 6339363496614396735L;

	private int id;
	private BigDecimal amount;
	private Kind kind;
	private Profile profile;

	// only for test
	public Expense(int id) {
		this.id = id;
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

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Expense [id=" + id + ", amount=" + amount + ", kind=" + kind
				+ ", profile=" + profile + "]";
	}

}
