package br.com.mapfood.ApiMatrixGoogleMaps;

import java.util.List;

public class ObjectRotasSteps {

	private String start_address;
	private String end_address;
	private List<String> steps;
	
	
	
	public ObjectRotasSteps() {}
	
	public ObjectRotasSteps(String start_address, String end_address, List<String> steps) {
		super();
		this.start_address = start_address;
		this.end_address = end_address;
		this.steps = steps;
	}
	public String getStart_address() {
		return start_address;
	}
	public void setStart_address(String start_address) {
		this.start_address = start_address;
	}
	public String getEnd_address() {
		return end_address;
	}
	public void setEnd_address(String end_address) {
		this.end_address = end_address;
	}
	public List<String> getSteps() {
		return steps;
	}
	public void setSteps(List<String> steps) {
		this.steps = steps;
	}
	
	
	
}
