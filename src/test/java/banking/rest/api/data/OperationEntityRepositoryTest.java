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
import banking.rest.api.data.entity.OperationEntity;

@RunWith(SpringRunner.class)
@DataJpaTest
public class OperationEntityRepositoryTest {
	
	@Autowired
    private OperationEntityRepository operationEntityRepository;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
    private BankEntityRepository bankEntityRepository;
	
	@Autowired
    private ClientEntityRepository clientEntityRepository;
	
	private BankEntity getBankInfos() {
		BankEntity bankInserted = new BankEntity("CIH");
    	this.entityManager.persist(bankInserted);    	
    	return this.bankEntityRepository.findById(bankInserted.getId()).get();
	}
	
	private ClientEntity getClient1Infos() {  	
    	BankEntity bank =  this.getBankInfos();
    	
    	ClientEntity client1Inserted = new ClientEntity("Client1", "Client1", bank.getId());
    	this.entityManager.persist(client1Inserted);    	
    	return this.clientEntityRepository.findById(client1Inserted.getId()).get();
	}
	
	private ClientEntity getClient2Infos() {
		BankEntity bank =  this.getBankInfos();
		
		ClientEntity client2Inserted = new ClientEntity("Client2", "Client2", bank.getId());
    	this.entityManager.persist(client2Inserted);    	
    	return this.clientEntityRepository.findById(client2Inserted.getId()).get();
	}
	
	@Test
    public void should_store_an_operation() {

		ClientEntity client1 = this.getClient1Infos();
		ClientEntity client2 = this.getClient2Infos();
    	OperationEntity operationInserted = new OperationEntity(client1.getId(), client2.getId(), 100, 'D', "MAD");
    	
    	this.entityManager.persist(operationInserted);    	
		OperationEntity operationRetreived = this.operationEntityRepository.findById(operationInserted.getId()).get();
		
		assertEquals(operationRetreived.getAmount(),100);
    }

	public void should_find_no_operations_if_repository_is_empty() {		

		List<OperationEntity> operations = operationEntityRepository.findAll();
		
		assertEquals(operations.size(), 0);
	}
	
	@Test
	public void should_delete_all_operations() {

		ClientEntity client1 = this.getClient1Infos();
		ClientEntity client2 = this.getClient2Infos();
		
		OperationEntity operation1 = new OperationEntity(client1.getId(), client2.getId(), 100, 'D', "MAD");
		OperationEntity operation2 = new OperationEntity(client1.getId(), client2.getId(), 200, 'R', "USD");
    	
    	this.entityManager.persist(operation1);
    	this.entityManager.persist(operation2); 
 
    	this.operationEntityRepository.deleteAll();
    	
		assertEquals(this.operationEntityRepository.findAll().size(), 0);
	}
	
	@Test
	public void should_find_all_operations() {

		ClientEntity client1 = this.getClient1Infos();
		ClientEntity client2 = this.getClient2Infos();
		
		OperationEntity operation1 = new OperationEntity(client1.getId(), client2.getId(), 100, 'D', "MAD");
		OperationEntity operation2 = new OperationEntity(client1.getId(), client2.getId(), 200, 'R', "USD");
		OperationEntity operation3 = new OperationEntity(client1.getId(), client2.getId(), 300, 'R', "EUR");
 
		this.entityManager.persist(operation1);
    	this.entityManager.persist(operation2);
    	this.entityManager.persist(operation3);
    	
		List<OperationEntity> operations = this.operationEntityRepository.findAll();
		
		assertTrue(this.operationEntityRepository.findAll().containsAll(operations));
	}
	
	@Test
	public void should_find_operation_by_id() {		

		ClientEntity client1 = this.getClient1Infos();
		ClientEntity client2 = this.getClient2Infos();
		
		OperationEntity operation1 = new OperationEntity(client1.getId(), client2.getId(), 100, 'D', "MAD");
		OperationEntity operation2 = new OperationEntity(client1.getId(), client2.getId(), 200, 'R', "USD");
 
		this.entityManager.persist(operation1);
    	this.entityManager.persist(operation2);
    	
		OperationEntity foundOperation = this.operationEntityRepository.findById(operation1.getId()).get();
 
		assertEquals(foundOperation, operation1);
	}
}
