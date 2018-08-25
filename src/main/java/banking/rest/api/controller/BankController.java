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

import banking.rest.api.model.Bank;
import banking.rest.api.service.BankService;

@RestController
public class BankController {
	
	@Autowired
	private BankService bankService;
	
	@RequestMapping(value = "/addNewBank", method = RequestMethod.POST)
	public String newBank(Bank bank) {
		bankService.save(bank);
		return ("Bank added Successfully");
	}
	
	@RequestMapping(value = "/addNewBank", method = RequestMethod.GET)
	public ModelAndView addNewBank() {
		Bank bank = new Bank();
		return new ModelAndView("bank/newBank", "form", bank);
	}
	
	@RequestMapping(value = "/listBanks", method = RequestMethod.GET)
	public ModelAndView banks() {
		List<Bank> allBanks = bankService.findAll();
		return new ModelAndView("bank/allBanks", "banks", allBanks);
	}
	
	@GetMapping("/banks/{id}")
	public Bank retrieveBank(@PathVariable long id) {
		return bankService.findById(id);
	}
	
	@DeleteMapping("/banks/delete/{id}")
	public void deleteBank(@PathVariable long id) {
		bankService.deleteById(id);
	}
}
