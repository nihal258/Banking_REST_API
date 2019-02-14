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

import banking.rest.api.data.entity.BankEntity;
import banking.rest.api.service.BankService;

@RestController
public class BankController {
	
	@Autowired
	private BankService bankService;
	
	@RequestMapping(value = "/bank")
	public ModelAndView addNewBank() {
		BankEntity bankEntity = new BankEntity();
		return new ModelAndView("bank/newBank", "form", bankEntity);
	}
	
	@RequestMapping(value = "/bank", method = RequestMethod.POST)
	public String newBank(BankEntity bankEntity) {
		bankService.save(bankEntity);
		return ("Bank added Successfully");
	}
	
	@RequestMapping(value = "/banks")
	public ModelAndView banks() {
		List<BankEntity> allBanks = bankService.findAll();
		return new ModelAndView("bank/allBanks", "banks", allBanks);
	}
	
	@GetMapping("/banks/{id}")
	public BankEntity retrieveBank(@PathVariable long id) {
		return bankService.findById(id);
	}
	
	@DeleteMapping("/banks/{id}")
	public void deleteBank(@PathVariable long id) {
		bankService.deleteById(id);
	}	
}
