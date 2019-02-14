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

import banking.rest.api.data.entity.ClientEntity;
import banking.rest.api.service.ClientService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ClientControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ClientService clientService;
	
	ClientEntity mockClient = new ClientEntity("Nihal", "Nihal");

	@Test
	public void testAddClient() throws Exception {
		//Given
		String URI = "/client";
		
		//When
		Mockito.when(clientService.save(mockClient)).thenReturn(mockClient);		
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post(URI)
				.contentType(MediaType.APPLICATION_JSON_VALUE);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		MockHttpServletResponse response = result.getResponse();
		
		String outputInJson = response.getContentAsString();
		
		//Then
		assertEquals(outputInJson, "Client added Successfully");
		
		//And 	
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}
	
	@Test
	public void testGetClientById() throws Exception {
		//Given
		String URI = "/clients/"+mockClient.getId();
		
		//When
		Mockito.when(clientService.findById(mockClient.getId())).thenReturn(mockClient);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get(URI)
				.accept(MediaType.APPLICATION_JSON_VALUE);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		MockHttpServletResponse response = result.getResponse();
		
		String expectedJson = this.mapToJson(mockClient);
		String outputInJson = response.getContentAsString();
		
		//Then
		assertEquals(outputInJson, expectedJson);
	}
	
	@Test
	public void testDeleteClient() throws Exception {
		//Given
        
		//When
		clientService.deleteById(mockClient.getId());		
        final ClientEntity removed = clientService.findById(mockClient.getId());
      
	   //Then
        assertNull(removed);
	}
	
	private String mapToJson(Object object) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}
}
