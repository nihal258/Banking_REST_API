package banking.rest.api.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import banking.rest.api.model.Bank;
import banking.rest.api.service.BankService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = BankController.class, secure = false)
public class BankControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private BankService bankService;
	
	@Test
	public void testCreateBank() throws Exception{
		
		Bank mockBank = new Bank();
		mockBank.setName("SG");
				
		String URI = "/addNewBank";
		
		Mockito.when(bankService.save(Mockito.any(Bank.class))).thenReturn(mockBank);		
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post(URI)
				.contentType(MediaType.APPLICATION_JSON_VALUE);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		MockHttpServletResponse response = result.getResponse();
		
		String outputInJson = response.getContentAsString();
		
		//To make this test successful
		assertEquals(outputInJson, "Bank added Successfully");
		
		//To make this test failed
		//assertEquals(outputInJson, "Bank added Successfully no");
		
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}
	
	@Test
	public void testGetBankById() throws Exception{
		
		Bank mockBank = new Bank(1, "CIH");
		
		//To make this test failed
		//Mockito.when(bankService.findById(1000)).thenReturn(mockBank);
		
		//To make this test successful
		Mockito.when(bankService.findById(Mockito.anyLong())).thenReturn(mockBank);
		
		String URI = "/banks/1";
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get(URI)
				.accept(MediaType.APPLICATION_JSON_VALUE);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		MockHttpServletResponse response = result.getResponse();
		
		String expectedJson = this.mapToJson(mockBank);
		String outputInJson = response.getContentAsString();
		
		assertEquals(outputInJson, expectedJson);
	}

	private String mapToJson(Object object) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}
}
