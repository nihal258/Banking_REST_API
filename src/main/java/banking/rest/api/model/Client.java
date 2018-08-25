package banking.rest.api.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Client {
	
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private long id;

	private String firstName;
	
	private String lastName;
	
	private long bankId;
	
	private long balance;
	
	@OneToMany
	@JoinColumn(name = "fromID")
	private List<Operation> listOperations;

	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public long getBalance() {
		return balance;
	}

	public void setBalance(long balance) {
		this.balance = balance;
	}

	public List<Operation> getListOperations() {
		return listOperations;
	}

	public void setListOperations(List<Operation> listOperations) {
		this.listOperations = listOperations;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public long getBankId() {
		return bankId;
	}

	public void setBankId(long bankId) {
		this.bankId = bankId;
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", First Name=" + firstName + ", Last Name=" + lastName + ", Bank Id=" + bankId  + ", Balance=" + balance + ", List Operations=" + listOperations +"]";
	}

}


