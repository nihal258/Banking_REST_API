package banking.rest.api.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
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

import banking.rest.api.data.entity.BankEntity;
import banking.rest.api.service.BankService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BankControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private BankService bankService;
	
	BankEntity mockBank = new BankEntity("SG");
	
	@Test
	public void should_return_bank_added_ok_when_adding_new_bank() throws Exception {

		String URI = "/bank";
		
		Mockito.when(bankService.save(mockBank)).thenReturn(mockBank);		
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post(URI)
				.contentType(MediaType.APPLICATION_JSON_VALUE);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		MockHttpServletResponse response = result.getResponse();
		
		String outputInJson = response.getContentAsString();
		
		assertEquals(outputInJson, "Bank added Successfully");
	}
	
	@Test
	public void should_return_http_status_200_when_adding_new_bank() throws Exception {

		String URI = "/bank";
		
		Mockito.when(bankService.save(mockBank)).thenReturn(mockBank);		
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post(URI)
				.contentType(MediaType.APPLICATION_JSON_VALUE);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		MockHttpServletResponse response = result.getResponse();
		
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}
	
	@Test
	public void should_return_existant_bank_when_retrieving_by_id() throws Exception {
		
		String URI = "/banks/"+mockBank.getId();
		
		Mockito.when(bankService.findById(mockBank.getId())).thenReturn(mockBank);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get(URI)
				.accept(MediaType.APPLICATION_JSON_VALUE);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		MockHttpServletResponse response = result.getResponse();
		
		String expectedJson = this.mapToJson(mockBank);
		String outputInJson = response.getContentAsString();
		
		assertEquals(outputInJson, expectedJson);
	}
	
	@Test
	public void should_delete_bank_when_retrieving_by_id() throws Exception {
        
		bankService.deleteById(mockBank.getId());		
        final BankEntity removed = bankService.findById(mockBank.getId());
      
        assertNull(removed);
	}
	
	private String mapToJson(Object object) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}
}
