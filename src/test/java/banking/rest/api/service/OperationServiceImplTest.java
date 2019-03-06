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

import banking.rest.api.data.OperationEntityRepository;
import banking.rest.api.data.entity.OperationEntity;
import banking.rest.api.service.impl.OperationServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class OperationServiceImplTest {

@InjectMocks
private OperationService operationService = new OperationServiceImpl();

@Mock
private OperationEntityRepository operationRepository;

@Test
public void should_return_not_null_id_when_saving_new_operation() {
	
	OperationEntity operationEntity = new OperationEntity();
	operationEntity.setFromID(98);
	operationEntity.setToID(97);
	operationEntity.setAmount(20);
	operationEntity.setCurrency("USD");
	operationEntity.setType('D');
	
	Mockito.when(operationRepository.save(operationEntity))
	        .thenAnswer(new Answer<OperationEntity>() {
	            public OperationEntity answer(InvocationOnMock invocation) throws Throwable {
	                OperationEntity operation = (OperationEntity) invocation.getArguments()[0];
	                operation.setId(1L);
	                return operation;
	            }
	        });
	
	OperationEntity savedOperationEntity = operationService.save(operationEntity);
	
	assertNotNull(savedOperationEntity.getId());
}

@Test
public void should_return_operations_size_greater_than_0_where_finding_all_operations () {
	
	OperationEntity operationEntity1 = new OperationEntity();
	operationEntity1.setFromID(98);
	operationEntity1.setToID(97);
	operationEntity1.setAmount(20);
	operationEntity1.setCurrency("USD");
	operationEntity1.setType('D');
	
	OperationEntity operationEntity2 = new OperationEntity();
	operationEntity2.setFromID(98);
	operationEntity2.setToID(97);
	operationEntity2.setAmount(20);
	operationEntity2.setCurrency("USD");
	operationEntity2.setType('D');
	
	OperationEntity operationEntity3 = new OperationEntity();
	operationEntity3.setFromID(98);
	operationEntity3.setToID(97);
	operationEntity3.setAmount(20);
	operationEntity3.setCurrency("USD");
	operationEntity3.setType('D');
	
	Mockito.when(operationRepository.findAll()).thenReturn(Arrays.asList(operationEntity1, operationEntity2, operationEntity3));
	
	List<OperationEntity> operations = operationService.findAll();
	
	assertTrue(operations.size() > 0);
}

@Test
public void should_verify_deleted_operation() {
	
	OperationEntity operationEntity = new OperationEntity();
	operationEntity.setFromID(98);
	operationEntity.setToID(97);
	operationEntity.setAmount(20);
	operationEntity.setCurrency("USD");
	operationEntity.setType('D');
	
	Mockito.when(operationRepository.save(operationEntity))
	        .thenAnswer(new Answer<OperationEntity>() {
	            public OperationEntity answer(InvocationOnMock invocation) throws Throwable {
	            	OperationEntity operation = (OperationEntity) invocation.getArguments()[0];
	                operation.setId(1L);
	                return operation;
	            }
	        });
	
	OperationEntity savedOperationEntity = operationService.save(operationEntity);
	
    operationService.deleteById(savedOperationEntity.getId());

    Mockito.verify(operationRepository, Mockito.times(1)).deleteById(savedOperationEntity.getId());
}

}
