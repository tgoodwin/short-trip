/* MapShape class by Timothy Goodwin
 * tlg2132
 * COMSW3134
 * 
 * This class draws the map of the cities using their coordinates, as well as draws
 * the edges between them as defined in the city pairs data file. The class also draws
 * the shortest path as determined by Dijkstra's algorithm when the user selects a start
 * and end city in the DijkstraTest class.
 * 
 */

import java.awt.*;
import java.awt.geom.*;
import java.util.*;
import java.util.ArrayList;

public class MapShape {
	
	private int x;
	private int y;
	private int width;
	private int height;
	private HashMap<Double, Edge> edges;
	private ArrayList<Edge> path;
	
	public MapShape(int x, int y, int xMax, int yMax,
		HashMap<Double, Edge> e) {
		this.x = x;
		this.y = y; 
		width = xMax + 30;
		height = yMax + 30;
		edges = e;
	}

	public Dimension getSize(){
		return new Dimension(width, height);
	}
	
	public void setPath(ArrayList<Edge> p){
		path = p;
	}
	
	//draws the map with edges
	public void drawCities(Graphics2D g2){
		int Ux; int Vx;
		int Uy; int Vy;

		
		Ellipse2D.Double uDot;
		Ellipse2D.Double vDot;
		Line2D.Double l;
		Collection<Edge> edgeSet = edges.values();
		Iterator<Edge> edgeIterator = edgeSet.iterator();
		
		Edge myEdge;
		Vertex u;
		Vertex v;
		
		
		for(int i = 0; i < edgeSet.size(); i++){
			myEdge = edgeIterator.next();
			u = myEdge.city1; //Vertex
			v = myEdge.city2; //Vertex
			Ux = u.xCoord/2; Vx = v.xCoord/2;
			Ux +=10; Vx +=10;
			Uy = u.yCoord/2; Vy = v.yCoord/2;
	
			Uy = height - Uy - 10; Vy = height - Vy - 10;
			
			uDot = new Ellipse2D.Double(Ux - 3, Uy - 3, 6, 6);
			vDot = new Ellipse2D.Double(Vx - 3, Vy - 3, 6, 6);
			l = new Line2D.Double(Ux, Uy, Vx, Vy);
			g2.setColor(Color.BLACK);
			g2.draw(uDot); g2.fill(uDot);
			g2.drawString(u.name, Ux + 15, Uy + 5);
			g2.draw(vDot); g2.fill(vDot);
			g2.drawString(v.name, Vx + 15, Vy + 5);
			g2.draw(l);
		}
	}
	
	public void drawPath(Graphics2D g3){
	//draws the shortest path based on user input
		final BasicStroke wideStroke = new BasicStroke(2.0f);
		
		if(path!=null){
			int Ax; int Ay;
			int Bx; int By;
			Line2D.Double pathLine;
			
			for (Edge e : path){
				Ax = (e.city1.xCoord/2) + 10;
				Ay = height - (e.city1.yCoord/2) - 10;
				Bx = (e.city2.xCoord)/2 + 10;
				By = height - (e.city2.yCoord)/2 - 10;
				pathLine = new Line2D.Double(Ax, Ay, Bx, By);
				g3.setColor(Color.RED);
				g3.setStroke(wideStroke);
				g3.draw(pathLine);
			}
		}
	}
}