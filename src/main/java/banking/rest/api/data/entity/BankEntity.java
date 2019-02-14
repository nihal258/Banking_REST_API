package banking.rest.api.data.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class BankEntity {

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private long id;

	private String name;
	
	@OneToMany
	@JoinColumn(name = "bankId")
	private List<ClientEntity> listClients;
	
	public BankEntity() {
		super();
	}
	
	public BankEntity(String name) {
		super();
		this.name = name;
	}
	
	public BankEntity(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public List<ClientEntity> getListClients() {
		return listClients;
	}

	public void setListClients(List<ClientEntity> listClients) {
		this.listClients = listClients;
	}

	@Override
	public String toString() {
		return "Bank [id=" + id + ", Name=" + name + ", List Clients=" +listClients + "]";
	}
}
