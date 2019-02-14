package banking.rest.api.service;

import java.util.List;

import banking.rest.api.data.entity.OperationEntity;

public interface OperationService {
	
	public OperationEntity save(OperationEntity operationEntity);
	
	public List<OperationEntity> findAll();
	
	public OperationEntity findById(long id);
	
	public void deleteById(long id);
	
	public void doOperationOnAccount(long fromId, long toId, char type, long amount);
}
