import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

//--------------------PLEASE CHECK THE PAINT METHOD, SCROLL DOWN FOR THE DIJKSTRA'S ALGORITHM----------------------------------------

public class Main extends JFrame {
	static int counter=0;
	static String ID;
	static String Latitude;
	static String Longitude;
	static int winheight=1000;
	static int winwidth = 800;
	
	static boolean intersection = false;
	static boolean road = false;
	static ArrayList arraylines = new ArrayList<>();
	static Graph graph = new Graph();
	

	
	//Reads file and stores them-------------------------------------------------------------------------------
		public static void readfile(){
			try {
				//READ THE MAP TEXT FILES HERE
				Scanner in = new Scanner(new FileReader ("monroe.txt"));
				while(in.hasNextLine()){
					arraylines.add(in.nextLine());
				}
				
				
			} catch (FileNotFoundException e) {
				System.out.println("error: " + e.getMessage() + " cannot be opened");
			}
		}
		//--------------------------------------------------------------------------------------------------------

	public static void main(String[] args) {
		readfile();
		
		for(int i = 0; i<arraylines.size(); i++){
			String Type;
			String ID;
			String L1;
			String L2;
			
			Scanner s = new Scanner(arraylines.get(i).toString()).useDelimiter("\\t");
			Type= s.next();
			ID = s.next();
			L1 = s.next();
			L2 = s.next();
			if(Type.equals("i")){
				graph.addIntersection(new Intersections(ID, Float.parseFloat(L1), Float.parseFloat(L2)));}
			else
				graph.addRoad(ID, graph.getInter(L1), graph.getInter(L2), 5);
				
				 
		}
		
		Size.calc();
	
		
		//GUI-----------------------------------------
				SwingUtilities.invokeLater(new Runnable() {
		            @Override
		            public void run() {
		                new Main().setVisible(true);
		            }
		        });
		//--------------------------------------------
				
				
		
	}
	
	
	//------------------------------------------GUI-------------------------------------------
	public Main(){
		super("Map");
		setSize(800,1000);
		winheight = Toolkit.getDefaultToolkit().getScreenSize().height;
		winwidth = Toolkit.getDefaultToolkit().getScreenSize().width;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		
	}
	
	void drawLines(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		
		for(Integer key: graph.roads.keySet()){
			
			float aLat = graph.roads.get(key).a.Latitude;
			float aLon = graph.roads.get(key).a.Longitude;
			float bLat = graph.roads.get(key).b.Latitude;
			float bLon = graph.roads.get(key).b.Longitude;
			
			
			int x1 = (int) (getWidth()-(Size.cordgenx(aLon)*getWidth()));
			int y1 = (int) (getHeight()-(Size.cordgeny(aLat)*getHeight()));
			
			int x2 = (int) (getWidth()-(Size.cordgenx(bLon)*getWidth()));
			int y2 = (int) (getHeight()-(Size.cordgeny(bLat)*getHeight()));
			
			graph.roads.get(key).setWeight(Size.distance(aLat, aLon, bLat, bLon));
			if(graph.roads.get(key).ID.equals("r77")){
			g2d.drawLine(x1+1, y1+1, x2+1, y2+1);
			}
			g2d.drawLine(x1, y1, x2, y2);
		}
		
		//interates over intersections
		for(String key: graph.intersections.keySet()){
			int xcord = (int) (getWidth()-(Size.cordgenx(graph.intersections.get(key).Longitude)*getWidth()));
			int ycord = (int) (getHeight()-(Size.cordgeny(graph.intersections.get(key).Latitude)*(getHeight())));
			
			//System.out.println(" x : " + xcord + " y : " + ycord);
			
			g2d.fillOval(xcord, ycord, getWidth()/300, getHeight()/300);
			
		}
		
		
	}
	
	
	public void paint(Graphics g){
		super.paint(g);
		drawLines(g);
		
		
		//printing out the intersections
		System.out.println("VISITED INTERSECTIONS");
		for(int i = 0; i<Dijkstra.visited.size(); i++){
			System.out.println(Dijkstra.visited.get(i).ID);
		}
		
		//printing out the roads
		System.out.println("VISITED ROADS");
		for(int i = 0; i<Dijkstra.pathRoad.size(); i++){
			System.out.println(Dijkstra.pathRoad.get(i).ID);
		}
	
		
	}
	
	//GUI------------------------------------------------------------------------------------------


}