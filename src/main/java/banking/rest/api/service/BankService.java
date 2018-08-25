package banking.rest.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import banking.rest.api.data.BankRepository;
import banking.rest.api.model.Bank;

@Service
public class BankService {
	
	@Autowired
	private BankRepository bankDAO;

	public Bank save(Bank bank) {
		return bankDAO.save(bank);
	}
	
	public List<Bank> findAll() {
		return bankDAO.findAll();
	}
	
	public Bank findById(long id) {
		Optional<Bank> bank = bankDAO.findById(id);

		if (!bank.isPresent()) {
			System.err.println("Bank not found");
			return null;}

		return bank.get();
	}
	
	public void deleteById(long id) {
		bankDAO.deleteById(id);
	}
}
