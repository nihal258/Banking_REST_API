package banking.rest.api.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import banking.rest.api.data.BankEntityRepository;
import banking.rest.api.data.entity.BankEntity;
import banking.rest.api.service.BankService;

@Service
class BankServiceImpl implements BankService {
	
	@Autowired
	private BankEntityRepository bankDAO;

	public BankEntity save(BankEntity bankEntity) {
		return bankDAO.save(bankEntity);
	}
	
	public List<BankEntity> findAll() {
		return bankDAO.findAll();
	}
	
	public BankEntity findById(long id) {
		Optional<BankEntity> bankEntity = bankDAO.findById(id);

		if (!bankEntity.isPresent()) {
			System.err.println("Bank not found");
			return null;}

		return bankEntity.get();
	}
	
	public void deleteById(long id) {
		bankDAO.deleteById(id);
	}
}
