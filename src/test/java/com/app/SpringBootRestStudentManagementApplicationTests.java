package com.app;

import static org.assertj.core.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@AutoConfigureMockMvc
@TestPropertySource("classpath:application.properties")
public class SpringBootRestStudentManagementApplicationTests {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	@Disabled
	@DisplayName("TESTING STUDENT SAVE OPERATIONS")
	@Order(1)
	public void testSaveStudent() throws Exception {
		
		//1. Create Mock Request
		MockHttpServletRequestBuilder request =
				MockMvcRequestBuilders.post("http://localhost:9090/v1/api/student/create")
					.contentType(MediaType.APPLICATION_JSON)
					.content("{ \"stdName\" : \"Fatima\", \"stdGender\" : \"Female\", \"stdEmail\" : \"fatima@gmail.com\", \"stdPhone\" : 9638527410, \"stdAddress\" : \"Amritsar, Punjab\", \"stdDob\" : \"07/01/1995\"}");
		
		//2. Execute it and Read Result(request + response + exception)
		MvcResult result = mockMvc.perform(request).andReturn();
		
		//3. Read response from result
		MockHttpServletResponse response = result.getResponse();
		
		//4. Assert Result
		assertEquals(HttpStatus.CREATED.value(), response.getStatus());
		assertNotNull(response.getContentAsString());
		if(! response.getContentAsString().contains("created")) {
			fail("STUDENT NOT CREATED");
		}
		
	}
	
	/*
	 * @Test
	 * 
	 * @DisplayName("TESTING STUDENT SAVE OPERATIONS IN SHORT")
	 * 
	 * @Order(1) public void testSaveStudentShort() throws Exception {
	 * 
	 * //1. Create Mock Request mockMvc.perform(
	 * post("http://localhost:9090/v1/api/student/create")
	 * .contentType(MediaType.APPLICATION_JSON)
	 * .content("{ \"stdName\" : \"Eshwari\", \"stdGender\" : \"Female\", \"stdEmail\" : \"eshwari@gmail.com\", \"stdPhone\" : 8529637410, \"stdAddress\" : \"Kolkata, West Bengal\", \"stdDob\" : \"08/01/1996\"}"
	 * )) .andExpect( status().isCreated() );
	 * 
	 * }
	 */
	
	@Test
	@DisplayName("TESTING STUDENT GET ALL OPERATION")
	@Order(2)
	public void testGetAllStudents() throws Exception {
		//1. create request
		MockHttpServletRequestBuilder request = 
				MockMvcRequestBuilders.get("http://localhost:9090/v1/api/student/all");
		// 2. execute request
		MvcResult result = mockMvc.perform(request).andReturn();
		//3. read response
		MockHttpServletResponse response = result.getResponse();
		//4. assert result
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		assertNotNull(response.getContentAsString());
	}
	
	@Test
	@Order(3)
	@DisplayName("TESTING SINGLE STUDENT RECORD OPERATION")
	public void testGetOneStudent() throws Exception{
		//1. create request
		MockHttpServletRequestBuilder request = 
				MockMvcRequestBuilders.get("http://localhost:9090/v1/api/student/fetch/{id}", 5);
		//2. execute it
		MvcResult result = mockMvc.perform(request).andReturn();
		//3. read response
		MockHttpServletResponse response = result.getResponse();
		//4. assert response
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		assertNotNull(response.getContentAsString());
	}
	
	@Test
	@Disabled
	@DisplayName("TESTING DELETE OPERATION")
	@Order(4)
	public void testDeleteStudent() throws Exception {
		//1. create request
		MockHttpServletRequestBuilder request = 
				MockMvcRequestBuilders.delete("http://localhost:9090/v1/api/student/delete/{id}", 5);
		//2. execute it
			MvcResult result = mockMvc.perform(request).andReturn();
		//3. read response
			MockHttpServletResponse response = result.getResponse();
		//4. assert response
			assertEquals(HttpStatus.OK.value(), response.getStatus());
			assertNotNull(response.getContentAsString());
			if(! response.getContentAsString().contains("deleted")) {
				fail("DELETE FAILED");
			}
	}
	
	@Test
	@DisplayName("TESTING STUDENT UPDATE OPERATION")
	@Order(5)
	public void testUpdateStudent() throws Exception {
		MockHttpServletRequestBuilder request =
		MockMvcRequestBuilders.put("http://localhost:9090/v1/api/student/update")
			.contentType(MediaType.APPLICATION_JSON)
			.content("{ \"stdId\" : \"5\",\"stdName\" : \"Eshwar\", \"stdGender\" : \"Male\", \"stdEmail\" : \"eshwar@gmail.com\", \"stdPhone\" : 8529637410, \"stdAddress\" : \"Kolkata, West Bengal\", \"stdDob\" : \"08/01/1996\"}");
		
		MvcResult result = mockMvc.perform(request).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		assertNotNull(response.getContentAsString());
		if (! response.getContentAsString().contains("updated")) {
			fail("Update failed");
		}
	}
}
