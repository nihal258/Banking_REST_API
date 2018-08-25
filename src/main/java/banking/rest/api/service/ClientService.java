package banking.rest.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import banking.rest.api.data.ClientRepository;
import banking.rest.api.model.Client;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository clientDAO;
	
	public Client save(Client client) {
		return clientDAO.save(client);
	}
	
	public List<Client> findAll() {
		return clientDAO.findAll();
	}
	
	public Client findById(long id) {
		Optional<Client> client = clientDAO.findById(id);

		if (!client.isPresent()) {
			System.err.println("Client not found");
			return null;}

		return client.get();
	}
	
	public void deleteById(long id) {
		clientDAO.deleteById(id);
	}
}
