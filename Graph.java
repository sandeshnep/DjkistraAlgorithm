import java.util.ArrayList;
import java.util.HashMap;

public class Graph {
	public HashMap<String, Intersections> intersections;
	public HashMap<Integer, Roads> roads;
	
	public Graph(){
		this.intersections = new HashMap<String, Intersections>();
		this.roads = new HashMap<Integer, Roads>();
	}
	
	public Graph(ArrayList<Intersections> intersections){
		this.intersections = new HashMap<String, Intersections>();
		this.roads = new HashMap<Integer, Roads>();
		
		for(Intersections i : intersections){
			this.intersections.put(i.getintID(), i);
		}
	}
	
	//adds roads between the intersections a and b of weight1 if no roads between the Intersections already exist in the Graph
	public boolean addRoad(String ID, Intersections a, Intersections b){
		return addRoad(ID, a,b, 1);
	}
	
	//accepts two Intersections and a weight, and adds the road if no road relating to a and b exist in the Graph
	public boolean addRoad(String ID, Intersections a , Intersections b, int weight){
		if(a.equals(b)){
			return false;
		}
		
		Roads e = new Roads(ID, a,b, weight);
		if(roads.containsKey(e.hashCode())){
			return false;
		}
		
		//if the road isnt already incident to one of the Intersections
		else if(b.containsNeighbor(e)||b.containsNeighbor(e)){
			return false;
		}
		
		roads.put(e.hashCode(), e);
		a.addNeighbor(e);
		b.addNeighbor(e);
		return true;
	}
	
	//returns true if the graph contains edge e
	public boolean containsRoad(Roads e){
		if(e.getOne()==null || e.getTwo()==null){
			return false;
		}
		
		return this.roads.containsKey(e.hashCode());
	}
	
	//the Intersections to lookup
	public boolean containsInter(Intersections intersection){
		return this.intersections.get(intersection.getintID())!=null;
	}
	public Intersections getInter(String ID){
		return intersections.get(ID);
	}
	
	//adds Intersections
	public boolean addIntersection(Intersections intersection){
		intersections.put(intersection.getintID(), intersection);
		return true;
	}

}
