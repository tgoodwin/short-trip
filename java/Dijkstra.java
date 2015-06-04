/* Dijkstra.java by Timothy Goodwin
 * tlg2132
 * COMSW3134
 * 
 * This class implements Dijkstra's shortest path algorithm. the "path" method runs
 * Dijkstra's algorithm using a user-defined starting vertex. This class
 * uses the binary heap priority queue implementation. The class utilizes Weiss's binary heap
 * class. NOTE: I have not modified Weiss's code to contain a "decreaseKey" method. Rather, I
 * insert every "potential path Vertex" into the heap. If the Vertex is re-encountered with a 
 * lower path distance, it will be inserted again but will have a higher priority in the queue.
 * Every Vertex is declared "known" once popped off the stack, therefore the "redundant" or
 * outdated Vertex references in the heap will pop off, but will not be used in the algorithm.
 * 
 */
import java.util.HashMap;
import java.util.Collection;
import java.util.Set;
import java.util.Iterator;
import java.util.ArrayList;

public class Dijkstra {
	Vertex end;
	HashMap<String, Vertex> cities;
	BinaryHeap<Vertex> adj;
	Collection<Vertex> vertices;
	Iterator<Vertex> VertIterator;
	ArrayList<Edge> shortPath;
	Edge pathEdge;
	
	
	public Dijkstra( HashMap<String, Vertex> x){
		cities = x;
		adj = new BinaryHeap<>();

	}
	
	public void path(Vertex start){ 
	//calculates minimum paths in map from start vertex
		
		vertices = cities.values();
		VertIterator = vertices.iterator();
		Vertex v;
		Vertex w;
		Set<Double> distSet;
		
		for (int i = 0; i < vertices.size(); i++){
			v = VertIterator.next();
			v.updateDist(Double.POSITIVE_INFINITY);
			//set all distances to infinity in the beginning;
			v.known = false;
			v.path = null;

		}
		
		//Begin Algorithm
		
		start.updateDist(0);
		adj.insert(start);
		
		while(!adj.isEmpty()){ 
			v = adj.deleteMin();
			if (v.known ==false){
				v.known = true;
			
		
				double cvw;
				distSet = v.adjList.keySet();
				//Key is distance, value is Vertex
				// set of the distances to each neighbor
				
				for (double d : distSet){ //a set of distances
					
					w = v.adjList.get(d); //get each Vertex mapping for all the keys
					
					if (!w.known){
						cvw = d; //cost from v to w
						if(v.dist + cvw < w.dist){
							w.dist = v.dist + cvw;
							adj.insert(w); 
							
							/*even if w already is in heap, 
							this insertion will land in a different, higher spot
							as it's dist is lower than the old one */
							
							w.setPath(v);
						}
					}
				}
			}
			
		}		
	}
	
	private void print(Vertex v){
		if (v.path != null){
			print(v.path);

			System.out.print(v.path.name + " to " + v.name + '\n');
			pathEdge = new Edge(10.0, v.path, v);
			shortPath.add(pathEdge);
			
		}
	}
	public double printPath(Vertex v){
		shortPath = new ArrayList<>();
		System.out.println("Your shortest path:" + '\n');
		print(v);
		double path = v.dist;
		System.out.println('\n' + "Distance: " + v.dist + " linear units");
		System.out.println(shortPath.size() + " is the pathsize.");
		return path;
	}
	
	public ArrayList<Edge> getPath(){
		return shortPath;
	}
}