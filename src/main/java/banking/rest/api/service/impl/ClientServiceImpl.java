package banking.rest.api.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import banking.rest.api.data.ClientEntityRepository;
import banking.rest.api.data.entity.ClientEntity;
import banking.rest.api.service.ClientService;

@Service
class ClientServiceImpl implements ClientService {
	
	@Autowired
	private ClientEntityRepository clientDAO;
	
	public ClientEntity save(ClientEntity clientEntity) {
		return clientDAO.save(clientEntity);
	}
	
	public List<ClientEntity> findAll() {
		return clientDAO.findAll();
	}
	
	public ClientEntity findById(long id) {
		Optional<ClientEntity> clientEntity = clientDAO.findById(id);

		if (!clientEntity.isPresent()) {
			System.err.println("Client not found");
			return null;}

		return clientEntity.get();
	}
	
	public void deleteById(long id) {
		clientDAO.deleteById(id);
	}
}
