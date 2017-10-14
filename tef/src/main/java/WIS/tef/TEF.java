package WIS.tef;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TEF {
    
	@JsonProperty("Lat")
	private String lat;
	@JsonProperty("Lon")
	private String lon;
	@JsonProperty("Validtime")
	private String validtime;
	@JsonProperty("TEF0")
	private String tef0;
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
	public String getValidtime() {
		return validtime;
	}
	public void setValidtime(String validtime) {
		this.validtime = validtime;
	}
	public String getTef0() {
		return tef0;
	}
	public void setTef0(String tef0) {
		this.tef0 = tef0;
	}
	public TEF(String lat, String lon, String validtime, String tef0) {
		super();
		this.lat = lat;
		this.lon = lon;
		this.validtime = validtime;
		this.tef0 = tef0;
	}
	public TEF() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
