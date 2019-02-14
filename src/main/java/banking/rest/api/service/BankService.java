package banking.rest.api.service;

import java.util.List;

import banking.rest.api.data.entity.BankEntity;

public interface BankService {
	
	public BankEntity save(BankEntity bankEntity);
	
	public List<BankEntity> findAll();
	
	public BankEntity findById(long id);
	
	public void deleteById(long id);
}
