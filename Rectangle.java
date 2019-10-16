package Assignment2;
import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

/**
	* @author Prechar Xiong
	* Date: 05/30/19
	* ICS-372-01 Object Oriented Design and Implementation
	* Creates the Rectangle object that stores an int x, int y, int height,
	* and an int width.
*/
public class Rectangle extends Figure implements Serializable {
	private static final long serialVersionUID = 1L;
	protected int x;
	protected int y;
	protected int height;
	protected int width;
	
	public Rectangle() {
		
	}
	
	public Rectangle(int x, int y, int width, int height, Color color) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
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

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

/**
	* Draws the Rectangle using the values of the Rectangle object
	* @param graphics the Graphics object for drawing the figure
*/
	public void draw(Graphics g) {
		g.setColor(color);
		g.fillRect(x, y, width, height);
	}

/**
 	* overrides the toString method of Object
 	* @param nothing
 	* @return String information of the Rectangle
*/	
	public String toString() {
		return getClass().getName() + " [x=" + x + ", y=" + y + ", width=" + width + ", height=" + height + ", color=" + color;
	}
}
