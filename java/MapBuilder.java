/* MapBuilder.java by Timothy Goodwin
 * tlg2132
 * This class handles the city coordinates data and weighted edge data
 * passed in by the user. It prepares hashtables for use in the Dijkstra class
 * and it creates Vertex and Edge objects for use across both Kruskal and Dijkstra
 * classes.
 * 
 */
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.HashMap;
import java.util.Collection;
import java.util.Iterator;

public class MapBuilder {
	
	BinaryHeap<Edge> edgeHeap = new BinaryHeap<>();
	public HashMap<String, Vertex> cityMap;
	HashMap<String, Vertex> finalMap;
	public HashMap<Double, Edge> edgeList;
	
	public MapBuilder(){
		edgeList = new HashMap<Double, Edge>();
		cityMap = new HashMap<String, Vertex>();
		finalMap = new HashMap<String, Vertex>();
	}
	
	public HashMap<String, Vertex> getCityMap(){
		return finalMap;
	}
	
	public HashMap<String, Vertex> getBigMap(){
		return cityMap;
	}
	
	public HashMap<Double, Edge> getEdgeList(){
		return edgeList;
	}
	
	//creates Vertex objects for each city, adds them all to a HashMap
	public int cityCount(Scanner v){
		StringTokenizer line;
		String name;
		int xCity;
		int yCity;
		int count = 0;
				
		while (v.hasNextLine()){
			line = new StringTokenizer(v.nextLine(), " ");
			name = line.nextToken();
			xCity = Integer.parseInt(line.nextToken());
			yCity = Integer.parseInt(line.nextToken());
			Vertex c = new Vertex(name, xCity, yCity, count++);
			cityMap.put(name, c);
		}
		return count;
	}
	
	//Determines every distance between every city pair
	//Creates Edge object for each distance and adds it to a Hashmap
	public BinaryHeap<Edge> FullConnector(){
		
		Collection<Vertex> fullCity = cityMap.values();
		Collection<Vertex> fullCity2 = cityMap.values();
		Iterator<Vertex> fullItr = fullCity.iterator();
		Iterator<Vertex> second;
		
		BinaryHeap<Edge> fullHeap = new BinaryHeap<>();
		
		Vertex A;
		Vertex B;
		
		double x1; double y1;
		double x2; double y2;
		double distance;
		Edge e;
		for (int i = 0; i < fullCity.size(); i++){
			A = fullItr .next();
			x1 = A.xCoord; y1 = A.yCoord;
			second = fullCity2.iterator();
			
			for (int j = 0; j < fullCity2.size(); j++){
				B = second.next();
				x2 = B.xCoord; y2 = B.yCoord;
				distance = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
				
				if (distance != 0.0){ //exclude same city case
					e = new Edge(distance, A, B);
					fullHeap.insert(e);
				}
			}
				
		}
		return fullHeap;
	}
	
	public BinaryHeap<Edge> EdgeHandler(Scanner e){ //read city pair edge data
		StringTokenizer line;
		double weight;
		String city1;
		String city2;
		Edge edge;
		while (e.hasNextLine()){
			line = new StringTokenizer(e.nextLine(), " ");
			city1 = line.nextToken(); //1
			city2 = line.nextToken(); //2
			weight = Double.parseDouble(line.nextToken());
			
			if (cityMap.containsKey(city1) && cityMap.containsKey(city2)) {
				
				Vertex cityA = cityMap.get(city1);
				
				cityA.adjList.put(weight, cityMap.get(city2));
				cityMap.remove(city1); //update cityMap
				cityMap.put(city1, cityA);
				finalMap.put(city1, cityA);
				
				Vertex cityB = cityMap.get(city2);
				cityB.adjList.put(weight, cityMap.get(city1));
				cityMap.remove(city2);
				cityMap.put(city2, cityB);
				finalMap.put(city2, cityB);
				
				edge = new Edge(weight, finalMap.get(city1), finalMap.get(city2));
				edgeList.put(weight, edge);
				edgeHeap.insert(edge);
				
			}
		}

		return edgeHeap;
	}
	
}
