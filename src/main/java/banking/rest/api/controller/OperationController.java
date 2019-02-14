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

import banking.rest.api.data.entity.OperationEntity;
import banking.rest.api.service.OperationService;

@RestController
public class OperationController {
	
	@Autowired
	private OperationService operationService;

	@RequestMapping(value = "/operation")
	public ModelAndView addNewOperation() {
		OperationEntity operationEntity = new OperationEntity();
		return new ModelAndView("operation/newOperation", "form", operationEntity);
	}
	
	@RequestMapping(value = "/operation", method = RequestMethod.POST)
	public String newOperation(OperationEntity operationEntity) {
		operationService.save(operationEntity);
		return ("Operation added Successfully");
	}	

	@RequestMapping(value = "/operations")
	public ModelAndView operations() {
		List<OperationEntity> allOperations = operationService.findAll();
		return new ModelAndView("operation/allOperations", "operations", allOperations);
	}
	
	@GetMapping("/operations/{id}")
	public OperationEntity retrieveOperation(@PathVariable long id) {
		return operationService.findById(id);
	}

	@DeleteMapping("/operations/delete/{id}")
	public void deleteOperation(@PathVariable long id) {
		operationService.deleteById(id);
	}
}