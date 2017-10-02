import java.security.KeyStore.Entry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Dijkstra {
	public static ArrayList<Roads> neighbors = new ArrayList<Roads>();	
	public static ArrayList<Intersections> visited = new ArrayList<Intersections>();
	public static ArrayList<Roads> pathRoad = new ArrayList<Roads>();
	public static HashMap<Intersections, Float> pathHash = new HashMap<Intersections, Float>();
	public static int roadmatch = -1;
	public static int counter=0;
	
	public static Intersections destination = new Intersections("null", (float) 0, (float) 0);
	
	public static Roads rMinWeight = new Roads("null" , new Intersections("null", (float) 0, (float)0), new Intersections("null", (float) 0, (float)0), 100000000);

	public static float mincost = (float) 0;
	
	
	//The following is a method that returns all the connected intersections and the weight that is not itself
	public static void PathGen(Intersections a){
		counter++;
		visited.add(a);

		System.out.println("Origin : " + a.ID);

		if(counter==1){
		for(int i = 0; i<a.getRoadscount(); i++){
			pathHash.put(getOtherInter(a, a.getNeighbor(i)), (float) a.getNeighbor(i).weight);
		 }
		}
		
		
		
		float minWeight = (float) 100000000;
		Intersections minInter = new Intersections("null", (float) 0, (float) 0);
		//searches the minimum Intersect in the pathHash
		for(Intersections inte: pathHash.keySet()){
			if(pathHash.get(inte)<minWeight){
				minWeight = pathHash.get(inte);
				minInter = inte;
			}
		}
		mincost+=minWeight;
		PathGen(minInter);
	
	}
	
	
	
	
	//Returns the correct intersection out of the two intersections that the roads connect
	public static Intersections getOtherInter(Intersections i, Roads r){
				//MinInter is the intersection with the minimon cost travel
				Intersections otherInter = new Intersections("null", (float) 0, (float) 0);
				if(r.a.ID.equals(i.ID)){
					otherInter = r.b;
					otherInter.neighborhood= r.b.getNeighbors();
				}
				else{
					otherInter = r.a;
					otherInter.neighborhood = r.a.getNeighbors();
					}
				
				return otherInter;
	}
}
