/* Edge.java by Timothy Goodwin
 * tlg2132
 * COMSW3134
 * 
 * Stores each edge as two cities (Vertex objects) and the distance between them. 
 * Edge objects are comparable by their distance.
 */

public class Edge implements Comparable<Edge>{
	
	double distance;
	Vertex city1;
	Vertex city2;
		
	public Edge(double d, Vertex u, Vertex v){
		distance = d; city1 = u; city2 = v;
	}
	
	public int compareTo(Edge e){
		if (this.distance > e.distance)
			return 1;
		else if (this.distance < e.distance)
			return -1;
		else
			return 0;
	}
		
}