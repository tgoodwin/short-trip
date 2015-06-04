/* DijkstraTest.java by Timothy Goodwin
 * tlg2132
 * 
 * This class takes in two text files, one with a list of cities and their coordinates, 
 * and another with weighted distances between city pairs.
 * The program then generates a GUI that displays a map of the cities and the weighted edges
 * connecting them. The GUI contains an interface for the user to select two cities and
 * view the path distance between their two cities.
 * 
 */
import java.util.Scanner;
import java.io.*;
import java.awt.*;

import javax.swing.*;

import java.util.HashMap;
import java.util.Collection;
import java.util.Iterator;

public class DijkstraTest {
	public static MapShape ms;
	public static MapComponent mc;
	private static JFrame frame;
	private static JPanel panel;
	public static HashMap<String, Vertex> x;
	public static HashMap<Double, Edge> y;
	
	public static void main(String[] args){
		try{
			File f1 = new File(args[0]); //cityxy.txt
			File f2 = new File(args[1]); //citypairs.dat
			Scanner a = new Scanner(f1);
			Scanner b = new Scanner(f2);
			MapBuilder m = new MapBuilder();
			int numVert = m.cityCount(a);
			m.EdgeHandler(b);
			x = m.getCityMap();
			y = m.getEdgeList();
			
			//Generate choices for ComboBoxes using the City HashTable
			Collection<Vertex> citySet = x.values();
			Iterator<Vertex> cityItr = citySet.iterator();
			String[] choices = new String[citySet.size()];
			Vertex n;
			for (int i = 0; i < citySet.size(); i++){
				n = cityItr.next();
				choices[i] = n.name;
			}
					
			JComboBox<String> starter = new JComboBox<String>(choices);
						
			JComboBox<String> ender = new JComboBox<String>(choices);

			Dijkstra dk = new Dijkstra(x);
			JButton calc = new JButton("Find my way");
			ms = new MapShape(0,0,1320,750,y);
			//window values hard coded, these values are based on the maximum coordinate
			//values found in cityxy.txt
			
			frame = new JFrame("Dijkstra's Shortest Trip - Timothy Goodwin");
			mc = new MapComponent(ms);
			
			JTextField dist = new JTextField(20);
			calc.addActionListener(new ComboListener(starter, ender, dk, x, y, dist, mc, ms));
				
			panel = new JPanel();
			panel.setLayout(new FlowLayout());
			panel.add(starter);
			panel.add(ender);
			panel.add(calc);
			panel.add(dist);
			
			frame.setLayout(new BorderLayout());
			frame.add(mc, BorderLayout.CENTER);
			frame.add(panel, BorderLayout.SOUTH);
			JScrollPane scroll = new JScrollPane(mc);
			scroll.setPreferredSize(ms.getSize());
			frame.add(scroll, BorderLayout.CENTER);
			
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.pack();
			frame.setVisible(true);
		}
		catch(FileNotFoundException e){
			System.out.println("file(s) not found, try again.");
		}
		catch(UnderflowException e){
			System.out.println("Error in data handling.");
		}
		catch(ArrayIndexOutOfBoundsException e){
			System.out.println("You must include two input text files.");
		}
	}
	
	
}
