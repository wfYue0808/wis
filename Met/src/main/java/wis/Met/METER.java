package wis.Met;

import com.fasterxml.jackson.annotation.JsonProperty;

public class METER {

	private String airportName;
	@JsonProperty("airtermin_id")
	private String shortName;
	private String lat;
	private String lon;
	private int year;
	private int mon;
	private int day;
	private int hour;
	private int min;
	private double tem;
	private double dpt;
	
	public double getTem() {
		return tem;
	}
	public void setTem(double tem) {
		this.tem = tem;
	}
	public double getDpt() {
		return dpt;
	}
	public void setDpt(double dpt) {
		this.dpt = dpt;
	}
	public String getAirportName() {
		return airportName;
	}
	public void setAirportName(String airportName) {
		this.airportName = airportName;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLon() {
		return lon;
	}
	public void setLon(String lon) {
		this.lon = lon;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getMon() {
		return mon;
	}
	public void setMon(int mon) {
		this.mon = mon;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public int getHour() {
		return hour;
	}
	public void setHour(int hour) {
		this.hour = hour;
	}
	public int getMin() {
		return min;
	}
	public void setMin(int min) {
		this.min = min;
	}
	@Override
	public String toString() {
		return "METER [airportName=" + airportName + ", shortName=" + shortName + ", lat=" + lat + ", lon=" + lon
				+ ", year=" + year + ", mon=" + mon + ", day=" + day + ", hour=" + hour + ", min=" + min + "]";
	}
	
	
	
	
	
}
