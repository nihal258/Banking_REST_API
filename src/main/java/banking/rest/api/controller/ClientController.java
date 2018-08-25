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

import banking.rest.api.model.Client;
import banking.rest.api.service.ClientService;

@RestController
public class ClientController {

	@Autowired
	private ClientService clientService;

	@RequestMapping(value = "/addNewClient", method = RequestMethod.POST)
	public String newEmployee(Client client) {
		clientService.save(client);
		return ("Client added Successfully");
	}

	@RequestMapping(value = "/addNewClient", method = RequestMethod.GET)
	public ModelAndView addNewClient() {
		Client client = new Client();
		return new ModelAndView("client/newClient", "form", client);
	}

	@RequestMapping(value = "/listClients", method = RequestMethod.GET)
	public ModelAndView clients() {
		List<Client> allClients = clientService.findAll();
		return new ModelAndView("client/allClients", "clients", allClients);
	}
	
	@GetMapping("/clients/{id}")
	public Client retrieveClient(@PathVariable long id) {
		return clientService.findById(id);
	}

	@DeleteMapping("/clients/delete/{id}")
	public void deleteClient(@PathVariable long id) {
		clientService.deleteById(id);
	}
}
