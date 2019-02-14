package banking.rest.api.service;

import java.util.List;

import banking.rest.api.data.entity.ClientEntity;

public interface ClientService {
	
	public ClientEntity save(ClientEntity clientEntity);
	
	public List<ClientEntity> findAll();
	
	public ClientEntity findById(long id);
	
	public void deleteById(long id) ;
}
