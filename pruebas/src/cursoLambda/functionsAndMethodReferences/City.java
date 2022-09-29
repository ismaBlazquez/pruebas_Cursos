package cursoLambda.functionsAndMethodReferences;

//This is class is for the sake of the demo only. Don't pay attention to it.
public class City {
	private double longitude;
	private double latitude;
	
	public double getLogitude() {
		return longitude;
	}
	public void setLogitude(double longitude) {
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	
	public City (double longitude, double latitude) {
		this.longitude = longitude;
		this.latitude = latitude;
	}
	
}