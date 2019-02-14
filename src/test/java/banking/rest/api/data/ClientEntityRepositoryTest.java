package banking.rest.api.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import banking.rest.api.data.entity.BankEntity;
import banking.rest.api.data.entity.ClientEntity;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ClientEntityRepositoryTest {

	@Autowired
    private ClientEntityRepository clientEntityRepository;
	
	@Autowired
    private BankEntityRepository bankEntityRepository;
	
	@Autowired
	private TestEntityManager entityManager;
	
	private BankEntity getBankInfos() {
		BankEntity bankInserted = new BankEntity("CIH");
    	this.entityManager.persist(bankInserted);    	
    	return this.bankEntityRepository.findById(bankInserted.getId()).get();
	}
	
	@Test
    public void should_store_a_client() {
    	//Given
		BankEntity bank = this.getBankInfos();
    	ClientEntity clientInserted = new ClientEntity("Client", "Client", bank.getId());
    	
    	//When
    	this.entityManager.persist(clientInserted);    	
    	ClientEntity clientRetreived = this.clientEntityRepository.findById(clientInserted.getId()).get();
		
		//Then
		assertEquals(clientRetreived.getFirstName(), "Client");
		
		//And
		assertEquals(clientRetreived.getLastName(), "Client");
    }
	
	public void should_find_no_clients_if_repository_is_empty() {		
		//When
		List<ClientEntity> clients = clientEntityRepository.findAll();
		
		//Then
		assertEquals(clients.size(), 0);
	}
	
	@Test
	public void should_delete_all_clients() {
		//Given
		BankEntity bank = this.getBankInfos();
		
    	ClientEntity client1 = new ClientEntity("Client1", "Client1", bank.getId());
    	ClientEntity client2 = new ClientEntity("Client2", "Client2", bank.getId());
    	
    	//When
    	this.entityManager.persist(client1);
    	this.entityManager.persist(client2); 
 
    	this.clientEntityRepository.deleteAll();
    	
    	//Then 
		assertEquals(this.clientEntityRepository.findAll().size(), 0);
	}
	
	@Test
	public void should_find_all_clients() {
		//Given
		BankEntity bank = this.getBankInfos();
		
		ClientEntity client1 = new ClientEntity("Client1", "Client1", bank.getId());
		ClientEntity client2 = new ClientEntity("Client2", "Client2", bank.getId());
		ClientEntity client3 = new ClientEntity("Client3", "Client3", bank.getId());
 
		//When		
		this.entityManager.persist(client1);
    	this.entityManager.persist(client2);
    	this.entityManager.persist(client3);
    	
		List<ClientEntity> clients = this.clientEntityRepository.findAll();
		
		//Then
		assertEquals(clients.size(), 3);
		//And
		assertTrue(this.clientEntityRepository.findAll().containsAll(clients));
	}
	
	@Test
	public void should_find_client_by_id() {		
		//Given
		BankEntity bank = this.getBankInfos();
		
		ClientEntity client1 = new ClientEntity("Client1", "Client1", bank.getId());
		ClientEntity client2 = new ClientEntity("Client2", "Client2", bank.getId());
 
		//When
		this.entityManager.persist(client1);
    	this.entityManager.persist(client2);
    	
		ClientEntity foundClient = this.clientEntityRepository.findById(client1.getId()).get();
 
		//Then
		assertEquals(foundClient, client1);
	}
}
