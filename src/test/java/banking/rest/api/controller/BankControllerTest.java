package banking.rest.api.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
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
	
	@Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }
	
	@Test
	public void testAddBank() throws Exception {
		
		//Given
		Bank mockBank = new Bank(2, "SG");
		String URI = "/addNewBank";
		
		//When
		Mockito.when(bankService.save(mockBank)).thenReturn(mockBank);		
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post(URI)
				.contentType(MediaType.APPLICATION_JSON_VALUE);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		MockHttpServletResponse response = result.getResponse();
		
		String outputInJson = response.getContentAsString();
		
		//Then
		assertEquals(outputInJson, "Bank added Successfully");
		
		//And 	
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}
	
	@Test
	public void testGetBankById() throws Exception{
		
		//Given
		Bank mockBank = new Bank(1, "CIH");
		String URI = "/banks/"+mockBank.getId();
		
		//When
		Mockito.when(bankService.findById(mockBank.getId())).thenReturn(mockBank);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get(URI)
				.accept(MediaType.APPLICATION_JSON_VALUE);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		MockHttpServletResponse response = result.getResponse();
		
		String expectedJson = this.mapToJson(mockBank);
		String outputInJson = response.getContentAsString();
		
		//Then
		assertEquals(outputInJson, expectedJson);
	}
	
	@Test
	public void testDeleteBank() throws Exception {
		//Given
		Bank mockBank = new Bank(1, "CIH");
        
		//When
		bankService.deleteById(mockBank.getId());		
        final Bank removed = bankService.findById(mockBank.getId());
      
	   //Then
        assertNull(removed);
	}
	
	private String mapToJson(Object object) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}
}
