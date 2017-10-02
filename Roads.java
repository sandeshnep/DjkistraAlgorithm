public class Roads implements Comparable<Roads>{
		
	public String ID;
	public Intersections a, b;
	public double weight;

	public Roads(String ID, Intersections a, Intersections b){
		this(ID, a,b,1);
	}

	public Roads(String ID, Intersections a, Intersections b, double weight) {
		this.a = (a.getintID().compareTo(b.getintID()) <=0) ? a:b;
		this.b = (this.a == a) ? b : a;
		this.ID = ID;
		this.weight = weight;
	}
	
	
	//returns the neighbor of the Road
	public Intersections getNeighbor(Intersections current){
		if(!(current.equals(a)|| current.equals(b))){
			return null;
		}
		return (current.equals(a))? a:b;
	}
	
	public Intersections getOne(){
		return this.a;
	}
	
	public Intersections getTwo(){
		return this.b;
	}
	
	public double getWeight(){
		return this.weight;
	}
	
	public void setWeight(double d){
		this.weight = d;
	}
	
	public double compareto(Roads other){
		return this.weight-other.weight;
	}
	
	public String toString(){
		return "({" + a + ", " + b + "}, " + weight + ")";
	}
	
	public int hashCode(){
		return (a.getintID() + b.getintID()).hashCode();
	}

	@Override
	public int compareTo(Roads arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	

}