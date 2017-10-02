import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.math.BigDecimal;
import java.math.MathContext;




public class Size extends Main {
	static float maxlat = (float) 0.00000000;
	static float minlat = (float) 10000000.0000; 
	static float maxlon = (float) 000000.0000;
	static float minlon = (float) 10000000.00000;
	
	public static void calc(){
		
	//Max min calculator
	for(String key: graph.intersections.keySet()){
		float interlat = graph.getInter(key).Latitude;
		float interlong = -1*graph.getInter(key).Longitude;
		
		if(maxlat<interlat){
			maxlat =interlat;
		}
		if(minlat>interlat){
			minlat = interlat;
		}
		if(maxlon<interlong){
			maxlon =interlong;
		}
		if(minlon>interlong){
			minlon = interlong;
		}
		
	
	}
	
}
	
	public static double cordgenx(float lon){
		double x = ((((-1*lon)-minlon)/(maxlon-minlon)));
		return x;
	}
	
	public static double cordgeny(float lat){
		double y = (((lat-minlat)/(maxlon-minlon)));
		return y;
	}
	
	//---------------------CODE DERIVED FROM http://www.geodatasource.com/developers/java-----------------------------------------------------------------
	public static double distance(float lat1, float lon1, float lat2, float lon2) {
		double theta = lon1 - lon2;
		double dist = Math.sin(d2r(lat1)) * Math.sin(d2r(lat2)) + Math.cos(d2r(lat1)) * Math.cos(d2r(lat2)) * Math.cos(d2r(theta));
		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515;
		return (dist);
	}

	//degrees to radians
	public static double d2r(double deg) {
		return (deg * Math.PI / 180.0);
	}

	//radians to degrees
	public static double rad2deg(double rad) {
		return (rad * 180 / Math.PI);
	}
	//--------------------------------------------------------------------------------------------------------------------------------------------------------

}