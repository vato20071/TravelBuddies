package core;

import java.util.Date;

public class Announcement {
	private Route route;
	private Date announceDate, startDate, endDate;
	private int availSlots, author, id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Announcement() {
		route = new Route();
		availSlots = 3;
	}
	public void setStartCity(String city) {
		route.setStartCity(city);
	}
	public void setEndCity(String city) {
		route.setEndCity(city);
	}
	public void addRouteCity(String city) {
		route.addCity(city);
	}
	public int getAuthor() {
		return author;
	}
	public Route getRoute() {
		return route;
	}
	public void setAuthor(int author) {
		this.author = author;
	}
	public Date getAnnounceDate() {
		return announceDate;
	}
	public void setAnnounceDate(Date announceDate) {
		this.announceDate = announceDate;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public int getAvailSlots() {
		return availSlots;
	}
	public void setAvailSlots(int availSlots) {
		this.availSlots = availSlots;
	} 
	
}
