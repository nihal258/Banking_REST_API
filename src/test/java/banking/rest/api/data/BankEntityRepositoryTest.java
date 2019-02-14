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

@RunWith(SpringRunner.class)
@DataJpaTest
public class BankEntityRepositoryTest {
	
	@Autowired
    private BankEntityRepository bankEntityRepository;
	
	@Autowired
	private TestEntityManager entityManager;
 
    @Test
    public void should_store_a_bank() {
    	//Given
    	BankEntity bankInserted = new BankEntity("CIH");
    	
    	//When
    	this.entityManager.persist(bankInserted);    	
		BankEntity bankRetreived = this.bankEntityRepository.findById(bankInserted.getId()).get();
		
		//Then
		assertEquals(bankRetreived.getName(), "CIH");
    }
    
	@Test
	public void should_find_no_banks_if_repository_is_empty() {		
		//When
		List<BankEntity> banks = bankEntityRepository.findAll();
		
		//Then
		assertEquals(banks.size(), 0);
	}
	
	@Test
	public void should_delete_all_banks() {
		//Given
    	BankEntity bankInserted1 = new BankEntity("CIH");
    	BankEntity bankInserted2 = new BankEntity("BP");
    	
    	//When
    	this.entityManager.persist(bankInserted1);
    	this.entityManager.persist(bankInserted2); 
 
    	this.bankEntityRepository.deleteAll();
 
    	//Then
		assertEquals(this.bankEntityRepository.findAll().size(), 0);
	}
	
	@Test
	public void should_find_all_banks() {
		//Given
		BankEntity bank1 = new BankEntity("CIH");
		BankEntity bank2 = new BankEntity("SG");
		BankEntity bank3 = new BankEntity("BP");
 
		//When
		entityManager.persist(bank1);
		entityManager.persist(bank2);
		entityManager.persist(bank3);
		
		List<BankEntity> banks = this.bankEntityRepository.findAll();
		
		//Then
		assertEquals(banks.size(), 3);
		//And
		assertTrue(this.bankEntityRepository.findAll().containsAll(banks));
	}
	
	@Test
	public void should_find_bank_by_id() {		
		//Given
		BankEntity bank1 = new BankEntity("CIH");
		BankEntity bank2 = new BankEntity("SG");
		
		//When
		entityManager.persist(bank1);
		entityManager.persist(bank2);
		
		BankEntity foundBank = this.bankEntityRepository.findById(bank1.getId()).get();
 
		//Then
		assertEquals(foundBank, bank1);
	}
}
