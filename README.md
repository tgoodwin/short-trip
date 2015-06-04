# short-trip
Dijkstra Short Trip cross the US



To run the program:

1. Make sure the directory your programs are in contain the cityxy.txt file and the citypairs.dat file. You can use alternative files for these two files as well, but the data they contain must be identical to that in cityxy.txt and citypairs.java

2. Type this into the command line (after compiling):

	java DijkstraTest cityxy.txt citypairs.dat

##Primary Classes

Dijkstra.java

 This class implements Dijkstra's shortest path algorithm. the "path" method runs Dijkstra's algorithm using a user-defined starting vertex. This class
 uses the binary heap priority queue implementation. The class utilizes Weiss's binary heap class.
 NOTE: I have not modified Weiss's code to contain a "decreaseKey" method. Rather, I
 insert every "potential path Vertex" into the heap. If the Vertex is re-encountered with a lower path distance, it will be inserted again but will have a higher priority in the queue. Every Vertex is declared "known" once popped off the stack, therefore the "redundant" or outdated Vertex references in the heap will pop off, but will not be used in the algorithm.

 DijkstraTest.java (contains main method)

This class takes in two text files, one with a list of cities and their coordinates, and another with weighted distances between city pairs. The program then generates a GUI that displays a map of the cities and the weighted edges connecting them. The GUI contains an interface for the user to select two cities and view the path distance between their two cities.


##Supporting Classes

MapBuilder.java

This class handles the city coordinates data and weighted edge data passed in by the user. It prepares hashtables for use in the Dijkstra class and it creates Vertex and Edge objects for use across both Kruskal and Dijkstra classes.

Vertex.java

 This class allows data to be stored in Vertex objects as needed by Dijkstra's algorithm. Each Vertex object holds information containing a city name, coordinates, if it is known in Dijkstra's algorithm. It also contains a distance value and a previous Vertex variable all for Dijkstra's algorithm.

 Edge.java

This class stores each edge as two cities and the distance between them. Edge objects are
comparable by their distance.

##GUI Classes

MapShape.java contains the code to draw the maps and path/tree data.

MapComponent.java creates MapShape objects.

ComboListener.java contains the code that allows the user to select two cities from the GUI map while running DijkstraTest.java. It then allows the user to click a button to run Dijkstra's algorithm and calculate the path between the two selected cities.

##Weiss's Classes

BinaryHeap.java has been slightly modified to include a size() method that returns the size of the heap. This class is used to implement minheap priority queues both in MapBuilder.java and in Dijkstra.java

The DisjSets.java class has not been modified.

UnderflowException.java is an exception Weiss uses in his classes.

![interface] (/path.png)
