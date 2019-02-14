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

import banking.rest.api.data.entity.OperationEntity;

@RunWith(SpringRunner.class)
@DataJpaTest
public class OperationEntityRepositoryTest {
	
	@Autowired
    private OperationEntityRepository operationEntityRepository;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
    public void should_store_an_operation() {
    	//Given
    	OperationEntity operationInserted = new OperationEntity(1, 1, 100, 'D', "MAD");
    	
    	//When
    	this.entityManager.persist(operationInserted);    	
		OperationEntity operationRetreived = this.operationEntityRepository.findById(operationInserted.getId()).get();
		
		//Then
		assertEquals(operationRetreived.getAmount(),100);
		//And
		assertEquals(operationRetreived.getType(),'D');
		//And
		assertEquals(operationRetreived.getCurrency(),"MAD");
    }

	public void should_find_no_operations_if_repository_is_empty() {		
		//When
		List<OperationEntity> operations = operationEntityRepository.findAll();
		
		//Then
		assertEquals(operations.size(), 0);
	}
	
	@Test
	public void should_delete_all_operations() {
		//Given
		OperationEntity operation1 = new OperationEntity(1, 1, 100, 'D', "MAD");
		OperationEntity operation2 = new OperationEntity(1, 1, 200, 'R', "USD");
    	
    	//When
    	this.entityManager.persist(operation1);
    	this.entityManager.persist(operation2); 
 
    	this.operationEntityRepository.deleteAll();
    	
    	//Then 
		assertEquals(this.operationEntityRepository.findAll().size(), 0);
	}
	
	@Test
	public void should_find_all_operations() {
		//Given
		OperationEntity operation1 = new OperationEntity(1, 1, 100, 'D', "MAD");
		OperationEntity operation2 = new OperationEntity(1, 1, 200, 'R', "USD");
		OperationEntity operation3 = new OperationEntity(1, 1, 300, 'R', "EUR");
 
		//When
		this.entityManager.persist(operation1);
    	this.entityManager.persist(operation2);
    	this.entityManager.persist(operation3);
    	
		List<OperationEntity> operations = this.operationEntityRepository.findAll();
		
		//Then
		assertEquals(operations.size(), 3);
		//And
		assertTrue(this.operationEntityRepository.findAll().containsAll(operations));
	}
	
	@Test
	public void should_find_operation_by_id() {		
		//Given
		OperationEntity operation1 = new OperationEntity(1, 1, 100, 'D', "MAD");
		OperationEntity operation2 = new OperationEntity(1, 1, 200, 'R', "USD");
 
		//When
		this.entityManager.persist(operation1);
    	this.entityManager.persist(operation2);
    	
		OperationEntity foundOperation = this.operationEntityRepository.findById(operation1.getId()).get();
 
		//Then
		assertEquals(foundOperation, operation1);
	}
}
