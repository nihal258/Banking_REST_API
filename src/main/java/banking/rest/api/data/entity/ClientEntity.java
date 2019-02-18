package banking.rest.api.data.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class ClientEntity {
	
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private long id;

	private String firstName;
	
	private String lastName;
	
	private long bankId;
	
	private long balance;
	
	@OneToMany
	@JoinColumn(name = "fromID")
	private List<OperationEntity> listOperations;
	
	public ClientEntity() {
		super();
	}

	public ClientEntity(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public ClientEntity(String firstName, String lastName, long bankId) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.bankId = bankId;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

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

	public List<OperationEntity> getListOperations() {
		return listOperations;
	}

	public void setListOperations(List<OperationEntity> listOperations) {
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


