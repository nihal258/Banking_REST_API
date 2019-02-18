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

import banking.rest.api.data.ClientEntityRepository;
import banking.rest.api.data.entity.ClientEntity;
import banking.rest.api.service.impl.ClientServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class ClientServiceImplTest {
	
@InjectMocks
private ClientService clientService = new ClientServiceImpl();

@Mock
private ClientEntityRepository clientRepository;

@Test
public void testFindById() {
	ClientEntity clientEntity = new ClientEntity();
	clientEntity.setFirstName("Client");
	clientEntity.setLastName("Client");
	
	Mockito.when(clientRepository.save(clientEntity))
	        .thenAnswer(new Answer<ClientEntity>() {
	            public ClientEntity answer(InvocationOnMock invocation) throws Throwable {
	                ClientEntity client = (ClientEntity) invocation.getArguments()[0];
	                client.setId(1L);
	                return client;
	            }
	        });
	
	ClientEntity savedClientEntity = clientService.save(clientEntity);
	
	assertNotNull(savedClientEntity.getId());
	assertTrue(savedClientEntity.getId() > 0);
}

@Test
public void testFindAll() {
	ClientEntity clientEntity1 = new ClientEntity();
	clientEntity1.setFirstName("Client1");
	clientEntity1.setLastName("Client1");
	
	ClientEntity clientEntity2 = new ClientEntity();
	clientEntity2.setFirstName("Client2");
	clientEntity2.setLastName("Client2");
	
	ClientEntity clientEntity3 = new ClientEntity();
	clientEntity3.setFirstName("Client3");
	clientEntity3.setLastName("Client3");
	
	Mockito.when(clientRepository.findAll()).thenReturn(Arrays.asList(clientEntity1, clientEntity2, clientEntity3));
	
	List<ClientEntity> clients = clientService.findAll();
	
	assertTrue(clients.size() > 0);
}

@Test
public void testDeleteById() {
	ClientEntity clientEntity = new ClientEntity();
	clientEntity.setFirstName("Client");
	clientEntity.setLastName("Client");
	
	Mockito.when(clientRepository.save(clientEntity))
	        .thenAnswer(new Answer<ClientEntity>() {
	            public ClientEntity answer(InvocationOnMock invocation) throws Throwable {
	                ClientEntity client = (ClientEntity) invocation.getArguments()[0];
	                client.setId(1L);
	                return client;
	            }
	        });
	
	ClientEntity savedClientEntity = clientService.save(clientEntity);
	
    clientService.deleteById(savedClientEntity.getId());

    Mockito.verify(clientRepository, Mockito.times(1)).deleteById(savedClientEntity.getId());
}

}
