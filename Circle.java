package Assignment2;
import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

/**
 	* @author Prechar Xiong
 	* Date: 05/30/19
 	* ICS-372-01 Object Oriented Design and Implementation
 	* Creates the Circle object that stores an int x, int y and an int radius.
*/
public class Circle extends Figure implements Serializable{
	private static final long serialVersionUID = 1L;
	protected int x;
	protected int y;
	protected int radius;
	
	public Circle() {
		
	}
	
	public Circle(int x, int y, int radius, Color color ) {
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.color = color;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

/**
	* Draws the Circle using the values of the Circle object
	* @param graphics the Graphics object for drawing the figure
*/
	public void draw(Graphics g) {
		g.setColor(color);
		g.fillOval(x, y, radius * 2, radius * 2);
	}

/**
 	* overrides the toString method of Object
 	* @param nothing
 	* @return String information of the Circle
*/	
	public String toString() {
		return getClass().getName() + " [x=" + x + ", y=" + y + ", radius=" + radius + ", color=" + color;
	}
}
