package banking.rest.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import banking.rest.api.data.entity.ClientEntity;
import banking.rest.api.service.ClientService;

@RestController
public class ClientController {

	@Autowired
	private ClientService clientService;

	@RequestMapping(value = "/client")
	public ModelAndView addNewClient() {
		ClientEntity clientEntity = new ClientEntity();
		return new ModelAndView("client/newClient", "form", clientEntity);
	}
	
	@RequestMapping(value = "/client", method = RequestMethod.POST)
	public String newEmployee(ClientEntity clientEntity) {
		clientService.save(clientEntity);
		return ("Client added Successfully");
	}	

	@RequestMapping(value = "/clients")
	public ModelAndView clients() {
		List<ClientEntity> allClients = clientService.findAll();
		return new ModelAndView("client/allClients", "clients", allClients);
	}
	
	@GetMapping("/clients/{id}")
	public ClientEntity retrieveClient(@PathVariable long id) {
		return clientService.findById(id);
	}

	@DeleteMapping("/clients/delete/{id}")
	public void deleteClient(@PathVariable long id) {
		clientService.deleteById(id);
	}
}
