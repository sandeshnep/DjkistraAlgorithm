import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Intersections {
	
	public String ID;
	public float Latitude;
	public float Longitude;
	public ArrayList<Roads> neighborhood;
	
	public Intersections(String ID, float Latitude, float Longitude){
		this.ID = ID;
		this.Latitude = Latitude;
		this.Longitude = Longitude;
		this.neighborhood = new ArrayList<Roads>();
	}
	
	//adds edges to the node
	public void addNeighbor(Roads road){
		if(this.neighborhood.contains(road)){
			return;
		}
		
		this.neighborhood.add(road);
	}
	
	//searches if there is another edge contained in this node
	public boolean containsNeighbor(Roads next){
		return this.neighborhood.contains(next);
	}
	
	//index of the road 
	public Roads getNeighbor(int index){
		return this.neighborhood.get(index);
	}
	//gets the number of the roads in this intersection
	public int getRoadscount(){
		return this.neighborhood.size();
	}
	
	//returns the id of this intersection
	public String getintID(){
		return this.ID;
	}
	
	public ArrayList<Roads> getNeighbors(){
		return new ArrayList<Roads>(this.neighborhood);
	}
	

}
