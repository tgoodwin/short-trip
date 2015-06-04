/* Vertex.java by Timothy Goodwin
 * tlg2132
 * 
 * This class allows data to be stored in Vertex objects as needed by Dijkstra's algorithm.
 * Each Vertex object holds information containing a city name, coordinates, if it is known
 * in Dijkstra's algorithm, and an integer value v to be used in Weiss's DisjSets class
 * when Kruskal's algorithm is being run. It also contains a distance value and a previous Vertex
 * variable all for Dijkstra's algorithm. 
 * 
 */
import java.util.HashMap;


public class Vertex implements Comparable<Vertex>{
	 String name;
	 int xCoord;
	 int yCoord;
	 public boolean known;
	 public double dist;
	 public Vertex path;
	 HashMap<Double, Vertex> adjList;
	 public int value; //for Disjoint Set class to use
		
	public Vertex (String n, int x, int y){
		this(n, x, y, false, 0);
	}
	
	public Vertex (String n, int x, int y, int v){
		this(n, x, y, false, v);
	}
		
	public Vertex(String n, int x, int y, boolean k, int v){
		name = n;
		xCoord = x;
		yCoord = y;
		known = k;
		adjList = new HashMap<Double, Vertex>();
		value = v;
		path = null;
	}
	
	public int getval(){
		return value;
	}
	
	public void updateDist(double d){
		dist = d;
	}
	
	public void updateKnown(boolean b){
		known = b;
	}
	
	public void setPath(Vertex p){
		path = p;
	}
	
	public int compareTo(Vertex v){
		if (this.dist > v.dist)
			return 1;
		else if (this.dist < v.dist)
			return -1;
		else
			return 0;
	}
		
}