package wis.airport;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Airport implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3387372930708691695L;
	
	private String airportName;
	@JsonProperty("airtermin_id")
	private String shortname;
	private int year;
	private int mon;
	private int day;
	private int hour;
	private int min;
	private String lat;
	private String lon;
	private double tem;
	private double dpt;
	public String getAirportName() {
		return airportName;
	}
	public void setAirportName(String airportName) {
		this.airportName = airportName;
	}
	public String getShortname() {
		return shortname;
	}
	public void setShortname(String shortname) {
		this.shortname = shortname;
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
	@Override
	public String toString() {
		return "Airport [airportName=" + airportName + ", shortname=" + shortname + ", year=" + year + ", mon=" + mon
				+ ", day=" + day + ", hour=" + hour + ", min=" + min + ", lat=" + lat + ", lon=" + lon + ", tem=" + tem
				+ ", dpt=" + dpt + "]";
	}
	
	
}
