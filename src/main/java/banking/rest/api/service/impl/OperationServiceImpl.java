package banking.rest.api.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import banking.rest.api.data.ClientEntityRepository;
import banking.rest.api.data.OperationEntityRepository;
import banking.rest.api.data.entity.ClientEntity;
import banking.rest.api.data.entity.OperationEntity;
import banking.rest.api.service.OperationService;

@Service
class OperationServiceImpl implements OperationService {
	
	@Autowired
	private OperationEntityRepository operationDAO;
	
	@Autowired
	private ClientEntityRepository clientDAO;
	

	public OperationEntity save(OperationEntity operationEntity) {
		operationEntity.setDate(new Date());
		operationDAO.save(operationEntity);
		
		doOperationOnAccount(operationEntity.getFromID(), operationEntity.getToID(), operationEntity.getType(), operationEntity.getAmount());
		
		return operationEntity;
	}
	
	public List<OperationEntity> findAll() {
		return operationDAO.findAll();
	}
	
	public OperationEntity findById(long id) {
		Optional<OperationEntity> operationEntity = operationDAO.findById(id);

		if (!operationEntity.isPresent()) {
			System.err.println("Operation not found");
			return null;}

		return operationEntity.get();
	}
	
	public void deleteById(long id) {
		operationDAO.deleteById(id);
	}
	
	public void doOperationOnAccount(long fromId, long toId, char type, long amount) {
		ClientEntity clientFrom = clientDAO.findById(fromId).get();
		ClientEntity clientTo = clientDAO.findById(toId).get();
		long actualBalanceFrom = clientFrom.getBalance();
		long actualBalanceTo = clientTo.getBalance();
		
		if(fromId==toId) {	
					
			switch (type) {
			
			case 'D' : 	actualBalanceFrom += amount;
						break;
			
			case 'W' :  actualBalanceFrom -= amount;
						break;					
			}
			
			clientFrom.setBalance(actualBalanceFrom);
			clientDAO.save(clientFrom);
		}
		else {		
			
			switch (type) {
			
			case 'D' : 	actualBalanceFrom -= amount;
						actualBalanceTo += amount;
						break;			
			}
			
			clientFrom.setBalance(actualBalanceFrom);
			clientDAO.save(clientFrom);
			
			clientTo.setBalance(actualBalanceTo);
			clientDAO.save(clientTo);
		}
	}
}
