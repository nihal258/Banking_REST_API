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

import banking.rest.api.model.Operation;
import banking.rest.api.service.OperationService;

@RestController
public class OperationController {
	
	@Autowired
	private OperationService operationService;

	@RequestMapping(value = "/addNewOperation", method = RequestMethod.POST)
	public String newOperation(Operation operation) {
		operationService.save(operation);
		return ("Operation added Successfully");
	}

	@RequestMapping(value = "/addNewOperation", method = RequestMethod.GET)
	public ModelAndView addNewOperation() {
		Operation operation = new Operation();
		return new ModelAndView("operation/newOperation", "form", operation);
	}

	@RequestMapping(value = "/listOperations", method = RequestMethod.GET)
	public ModelAndView operations() {
		List<Operation> allOperations = operationService.findAll();
		return new ModelAndView("operation/allOperations", "operations", allOperations);
	}
	
	@GetMapping("/operations/{id}")
	public Operation retrieveOperation(@PathVariable long id) {
		return operationService.findById(id);
	}

	@DeleteMapping("/operations/delete/{id}")
	public void deleteOperation(@PathVariable long id) {
		operationService.deleteById(id);
	}
}