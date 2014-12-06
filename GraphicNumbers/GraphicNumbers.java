/*
 * GraphicNumbers.java
 * -------------------
 * This program shows how to use ArrayList of graphic objects.
 */
import acm.graphics.*;
import acm.program.*;
import java.awt.event.*;
import java.util.*;

public class GraphicNumbers extends GraphicsProgram {
	
	private static final double START_X = 50;
	private static final double START_Y = 100;
	
	public void run(){
		addMouseListeners();
	}
	
	public void mouseClicked(MouseEvent e){
		//Creat a new label
		GLabel lab = new GLabel("#" + labels.size());
		lab.setFont("Courier-18");
		
		//Move all existing labels down one row
		double dy = lab.getHeight();
		for(int i=0; i<labels.size(); i++){
			labels.get(i).move(0, dy);
		}
		
		add(lab,START_X,START_Y); //Add new label to canvas
		labels.add(lab);          //Add new label to ArrayList
	}
	
	private ArrayList<GLabel> labels = new ArrayList<GLabel>();

}
