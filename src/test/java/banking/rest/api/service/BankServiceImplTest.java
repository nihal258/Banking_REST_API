package banking.rest.api.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import banking.rest.api.data.BankEntityRepository;
import banking.rest.api.data.entity.BankEntity;
import banking.rest.api.service.impl.BankServiceImpl;


@RunWith(MockitoJUnitRunner.class)
public class BankServiceImplTest {

@InjectMocks
private BankService bankService = new BankServiceImpl();

@Mock
private BankEntityRepository bankRepository;

@Test
public void should_return_not_null_id_when_saving_new_bank() {
	
	BankEntity bankEntity = new BankEntity();
	bankEntity.setName("BNPP");
	
	Mockito.when(bankRepository.save(bankEntity))
	        .thenAnswer(new Answer<BankEntity>() {
	            public BankEntity answer(InvocationOnMock invocation) throws Throwable {
	                BankEntity bank = (BankEntity) invocation.getArguments()[0];
	                bank.setId(1L);
	                return bank;
	            }
	        });
	
	BankEntity savedBankEntity = bankService.save(bankEntity);
	
	assertNotNull(savedBankEntity.getId());
}

@Test
public void should_return_banks_size_greater_than_0_where_finding_all_banks() {
	
	BankEntity bankEntity1 = new BankEntity();
	bankEntity1.setName("BNPP");
	
	BankEntity bankEntity2 = new BankEntity();
	bankEntity2.setName("CIH");
	
	BankEntity bankEntity3 = new BankEntity();
	bankEntity3.setName("SG");
	
	Mockito.when(bankRepository.findAll()).thenReturn(Arrays.asList(bankEntity1, bankEntity2, bankEntity3));
	
	List<BankEntity> banks = bankService.findAll();
	
	assertTrue(banks.size() > 0);
}

@Test
public void should_verify_deleted_bank() {
	
	BankEntity bankEntity = new BankEntity();
	bankEntity.setName("BNPP");
	
	Mockito.when(bankRepository.save(bankEntity))
	        .thenAnswer(new Answer<BankEntity>() {
	            public BankEntity answer(InvocationOnMock invocation) throws Throwable {
	                BankEntity bank = (BankEntity) invocation.getArguments()[0];
	                bank.setId(1L);
	                return bank;
	            }
	        });
	
	BankEntity savedBankEntity = bankService.save(bankEntity);
	
    bankService.deleteById(savedBankEntity.getId());

    Mockito.verify(bankRepository, Mockito.times(1)).deleteById(savedBankEntity.getId());
}

}
