/* MapComponent class by Timothy Goodwin
 * tlg2132
 * COMSW3134
 * 
 * This class creates a MapShape object, sets its size.
 * 
 */
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.*;

public class MapComponent extends JComponent {

    private MapShape m;
   // private ArrayList<Edge> p;
    
    public MapComponent(MapShape m) {
	   this.m = m;

	   
	   setPreferredSize(m.getSize());
    }

    public void paintComponent(Graphics g) {

	Graphics2D g2 = (Graphics2D)g;
	
	m.drawCities(g2);
	
	Graphics2D g3 = (Graphics2D)g;
	m.drawPath(g3);
		
    }
}
