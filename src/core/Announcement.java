package core;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Announcement {
	private List<String> cityList;
	private Date announceDate, startDate, endDate;
	private int availSlots;
	public Announcement() {
		cityList = new ArrayList<>();
		availSlots = 3;
	}
	public List<String> getCityList() {
		return cityList;
	}
	public void setCityList(List<String> cityList) {
		this.cityList = cityList;
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
	
}
