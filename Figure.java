package Assignment2;
import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

/**
	* @author Prechar Xiong
	* Date: 05/30/19
	* ICS-372-01 Object Oriented Design and Implementation
	* Serves as a type for all figures in the simple
	* drawing program. 
*/
public abstract class Figure implements Serializable {
	private static final long serialVersionUID = 1L;
	protected Color color;
	
/**
	* Draws the figure using the given Graphics parameter
	* @param graphics the Graphics object for drawing the figure
*/
	public void draw(Graphics graphics) {
		graphics.setColor(color);
	}
	public Color getColor() {
		return color;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
}
