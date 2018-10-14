package banking.rest.api.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Bank {

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private long id;

	private String name;
	
	@OneToMany
	@JoinColumn(name = "bankId")
	private List<Client> listClients;
	
	public Bank() {
		super();
	}

	public Bank(long id, String name) {
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
	
	public List<Client> getListClients() {
		return listClients;
	}

	public void setListClients(List<Client> listClients) {
		this.listClients = listClients;
	}

	@Override
	public String toString() {
		return "Bank [id=" + id + ", Name=" + name + ", List Clients=" +listClients + "]";
	}
}
