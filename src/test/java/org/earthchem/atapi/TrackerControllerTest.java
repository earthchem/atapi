package org.earthchem.atapi;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.earthchem.atapi.controller.TrackerController;
import org.earthchem.atapi.model.Tracker;
import org.earthchem.atapi.repository.TrackerRepository;
import org.earthchem.atapi.service.TrackerService;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

//@RunWith(SpringRunner.class)
@WebMvcTest (value=TrackerController.class,secure=false)
@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootConfiguration(classes = MockServletContext.class)
@WebAppConfiguration
public class TrackerControllerTest {

	@Autowired
	private MockMvc mockMvc;

    @Autowired
    @MockBean
    private TrackerRepository repo;

	@MockBean
	private TrackerService service;
	
	private static Logger logger = LoggerFactory.getLogger("TrackerController");
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testTracker() {
		
		//Tracker mock = new Tracker(dataValue, nsfAwardNum, atUser, dataCollection, fundingSource, submitStatusBean);
		//Mockito.when(service.getTrackerInfo(Mockito.anyLong())).thenReturn(mock);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/tracker/id/{id}",1336L).accept(MediaType.APPLICATION_JSON);

		//RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/tracker/id").param("id", "1336").accept(MediaType.APPLICATION_JSON);
		MvcResult result;
		try {
			//result = mockMvc.perform(requestBuilder).andExpect(status().isOk()).andReturn();
			result = mockMvc.perform(requestBuilder).andReturn();
			System.out.println("Http Response Content = " + result.getResponse().getContentAsString());
            System.out.println("Http Response Status Code = " + result.getResponse().getStatus());            
		    
            MockHttpServletRequest request = result.getRequest();	
        	Long pi = (Long) request.getAttribute("trackId");
        	System.out.println(request.toString());
        	
        	
            String expected = "{\"trackId\":1336,\"dataValue\":\"1226\",\"lastStatusTimeChange\":null,\"nsfAwardNum\":\"0841006\",\"submittedTime\":1536158652666}";

		    JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info(e.getMessage());
		}
	}

}
