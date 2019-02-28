package banking.rest.api.data.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class OperationEntity {
	
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private long id;
	
	private long fromID;
	
	private long toID;
	
	private long amount;
	
	private char type;
	
	private String currency;
	
	private Date date;
	
	public OperationEntity() {
		super();
	}
	
	public OperationEntity(long fromID, long toID, long amount, char type, String currency) {
		super();
		this.fromID = fromID;
		this.toID = toID;
		this.amount = amount;
		this.type = type;
		this.currency = currency;
	}

	@Override
	public String toString() {
		return "Operation [id=" + id + ", From=" + fromID + ", To=" + toID + ", Type=" + type +", Amount =" + amount + " " + currency + ", Date=" + date + "]";
	}
}
