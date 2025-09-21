package com.api.stepdefinition;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.api.utils.MockServer;

import io.cucumber.java.*;
import io.cucumber.java.Scenario;

public class Hooks {

	private static final Logger LOG = LogManager.getLogger(Hooks.class);
	
	@Before
	public void testStart(Scenario scenario) {
//		MockServer.startMockServer();
//        MockServer.createStub();
		LOG.info("*****************************************************************************************");
		LOG.info("	Scenario: "+scenario.getName());
		LOG.info("*****************************************************************************************");
	}
//	  @After
//	    public void tearDown() {
//	        MockServer.stopMockServer();
//	    }
}
