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

import banking.rest.api.data.entity.ClientEntity;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ClientEntityRepositoryTest {

	@Autowired
    private ClientEntityRepository clientEntityRepository;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
    public void should_store_a_client() {
    	//Given
    	ClientEntity clientInserted = new ClientEntity("Client", "Client");
    	
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
    	ClientEntity client1 = new ClientEntity("Client1", "Client1");
    	ClientEntity client2 = new ClientEntity("Client2", "Client2");
    	
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
		ClientEntity client1 = new ClientEntity("Client1", "Client1");
		ClientEntity client2 = new ClientEntity("Client2", "Client2");
		ClientEntity client3 = new ClientEntity("Client3", "Client3");
 
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
		ClientEntity client1 = new ClientEntity("Client1", "Client1");
		ClientEntity client2 = new ClientEntity("Client2", "Client2");
 
		//When
		this.entityManager.persist(client1);
    	this.entityManager.persist(client2);
    	
		ClientEntity foundClient = this.clientEntityRepository.findById(client1.getId()).get();
 
		//Then
		assertEquals(foundClient, client1);
	}
}
