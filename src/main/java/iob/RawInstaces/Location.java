package iob.RawInstaces;

public class Location {
	private Float lat;
	private Float lng;
	
	
	public Location() {
		super();
	}


	public Location(Float lattitude, Float longtitude) {
		super();
		this.lat = lattitude;
		this.lng = longtitude;
	}


	public Float getLat() {
		return lat;
	}


	public void setLat(Float lat) {
		this.lat = lat;
	}


	public Float getLng() {
		return lng;
	}


	public void setLng(Float lng) {
		this.lng = lng;
	}
	
}
