package com.api.model;

public class MockApi {

	private String name;
	private String job;
	
	public MockApi(String name, String job) {
		this.name = name;
		this.job = job;
	}
	public String getName() {
		return name;
	}
	public String getJob() {
		return job;
	}
}
