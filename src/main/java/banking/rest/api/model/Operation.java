package banking.rest.api.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Operation {
	
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private long id;
	
	private long fromID;
	
	private long toID;
	
	private long amount;
	
	private char type;
	
	private String currency;
	
	private Date date;
	
	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public char getType() {
		return type;
	}

	public void setType(char type) {
		this.type = type;
	}

	public long getFromID() {
		return fromID;
	}

	public void setFromID(long fromID) {
		this.fromID = fromID;
	}

	public long getToID() {
		return toID;
	}

	public void setToID(long toID) {
		this.toID = toID;
	}

	@Override
	public String toString() {
		return "Operation [id=" + id + ", From=" + fromID + ", To=" + toID + ", Type=" + type +", Amount =" + amount + " " + currency + ", Date=" + date + "]";
	}
}
