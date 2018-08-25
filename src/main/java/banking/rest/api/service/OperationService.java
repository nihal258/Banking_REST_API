package banking.rest.api.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import banking.rest.api.data.ClientRepository;
import banking.rest.api.data.OperationRepository;
import banking.rest.api.model.Client;
import banking.rest.api.model.Operation;

@Service
public class OperationService {
	
	@Autowired
	private OperationRepository operationDAO;
	
	@Autowired
	private ClientRepository clientDAO;
	

	public Operation save(Operation operation) {
		operation.setDate(new Date());
		operationDAO.save(operation);
		
		doOperationOnAccount(operation.getFromID(), operation.getToID(), operation.getType(), operation.getAmount());
		
		return operation;
	}
	
	public List<Operation> findAll() {
		return operationDAO.findAll();
	}
	
	public Operation findById(long id) {
		Optional<Operation> operation = operationDAO.findById(id);

		if (!operation.isPresent()) {
			System.err.println("Operation not found");
			return null;}

		return operation.get();
	}
	
	public void deleteById(long id) {
		operationDAO.deleteById(id);
	}
	
	private void doOperationOnAccount(long fromId, long toId, char type, long amount) {
		Client clientFrom = clientDAO.findById(fromId).get();
		Client clientTo = clientDAO.findById(toId).get();
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
