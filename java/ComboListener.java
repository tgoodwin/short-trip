/* ComboListener.java by Timothy Goodwin
 * tlg2132
 * COMSW3134
 * 
 * This class calls the methods of a Dijkstra object with Vertex objects taken from the
 * inputs of two JComboBox objects. The class then updates a MapShape object, and repaints
 * the component found in the DijkstraTest class. Thus, this is the class that allows the
 * user to select two cities and view the Dijkstra-calculated shortest path between them.
 * 
 */
import java.awt.event.*;
import javax.swing.*;
import java.util.HashMap;
import java.util.ArrayList;

public class ComboListener implements ActionListener {
	private String startChoice;
	private String endChoice;
	
	private JComboBox<String> box1;
	private JComboBox<String> box2;
	
	private Dijkstra d;
	private HashMap<String, Vertex> x;
	private HashMap<Double, Edge> y;
	private ArrayList<Edge> path;
	private JTextField j;
	Vertex vStart;
	Vertex vEnd;
	double trip;
	private MapComponent mc;
	private MapShape ms;
	
	public ComboListener(JComboBox<String> b1, JComboBox<String> b2,
		Dijkstra dk, HashMap<String, Vertex> x, HashMap<Double, Edge> y, 
		JTextField j, MapComponent mc, MapShape ms){
		box1 = b1;
		box2 = b2;
		this.d = dk;
		this.x = x;
		this.y = y;
		this.mc = mc;
		this.ms = ms;
		this.j = j;
		
	}
	public void actionPerformed(ActionEvent ae){
		
		startChoice = (String) box1.getSelectedItem();
		vStart = x.get(startChoice); //Start vertex
		
		endChoice = (String) box2.getSelectedItem();
		vEnd = x.get(endChoice); //End vertex
		
		d.path(vStart); //takes in start vertex, calculates path
		
		trip = d.printPath(vEnd); //Prints the path to the end vertex from the console
		//dependent on what was passed into d.path(Vertex st)
		
		path = d.getPath(); //arrayList
		ms.setPath(path);
		mc.repaint();
		j.setText(Double.toString(trip) + " linear units");

	}
}
