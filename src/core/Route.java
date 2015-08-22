package core;

import java.util.ArrayList;
import java.util.List;

public class Route {
	private String startCity, endCity;
	private List<String> cities;
	
	public Route() {
		cities = new ArrayList<String>();
	}

	public String getStartCity() {
		return startCity;
	}

	public void setStartCity(String startCity) {
		this.startCity = startCity;
	}

	public String getEndCity() {
		return endCity;
	}

	public void setEndCity(String endCity) {
		this.endCity = endCity;
	}

	public List<String> getCities() {
		return cities;
	}

	public void setCities(List<String> cities) {
		this.cities = cities;
	}
	
	public void addCity(String city) {
		cities.add(city);
	}
	
	public void removeCity(String city) {
		cities.remove(city);
	}
}
