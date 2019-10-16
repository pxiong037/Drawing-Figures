package Assignment2;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.Canvas;
import java.awt.Button;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.Serializable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.File;

/**
 	* @author Prechar Xiong
 	* Date: 05/30/19
 	* ICS-372-01 Object Oriented Design and Implementation
 	* Create the FigureGUI application. For this assignment
 	* I used mouse pressed to get the first point to decide
 	* where to start drawing the figure and I used mouse released 
 	* to finish and paint the figure, we talked in class and you
 	* said it was fine.
*/
class FigureGUI implements Serializable{
	private static final long serialVersionUID = 1L;
/**
 	* used to temporarily store the color
 */
	public Color color;
	
/**
	* used to temporarily store the figure
*/
	public Figure figure;

/**
	* @param figures, takes in an ArrayList<Figure> figures to draw on the 
	* canvas using each figure from the figures list. On the canvas, it uses 
	* the users mouse events to create and store more figures into the figures
	* list then draws them and prints the figure's toString method to the text
	* area. It also creates buttons for the user to interact with to select the
	* color and shape of the object to be drawn. The exit button allows the 
	* user to serialize the ArrayList<Figure> figures for later use.
*/
	public FigureGUI(ArrayList<Figure> figures) {
		Frame f = new Frame();	
		f.setTitle("Figures GUI");
		f.setSize(1500,800);
		f.setVisible(true);
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent){
				System.exit(0);
			}        
		}); 

		Panel panel = new Panel();
		Panel canvasPanel = new Panel();
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
		LocalDate localDate = LocalDate.now();
		Label date = new Label(dateFormat.format(localDate));
		TextArea text = new TextArea();	

/**
 	* This ensures that the toString of the deserialized figure are printed to
 	* the text area first. 
 */
		for(Figure f1 : figures) {
			if(f1 instanceof Circle) {
				text.append(((Circle) f1).toString() + "\n");
			} else if(f1 instanceof Rectangle) {
				text.append(((Rectangle) f1).toString() + "\n");
			}
		}
		
		/**
		* Creates the Red button to create a new color object and store it
		* into color.
	*/
			Button red = new Button("Red");
			red.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					color = Color.RED;
				}
			});

	/**
		 * Creates the Green button to create a new color object and store it
		 * into color.
	 */
			Button green = new Button("Green");
			green.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					color = Color.GREEN;
				}
			});

	/**
	 	* Creates the Blue button to create a new color object and store it
	 	* into color.
	 */
			Button blue = new Button("Blue");
			blue.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					color = Color.BLUE;
				}
			});

/**
 	* Creates a canvas that paints each figure in figures. The paint method
 	* is overridden to allow the figures to be painted.
 */
		Canvas canvas = new Canvas() {
			private static final long serialVersionUID = 1L;
			@Override
			public void paint(Graphics g) {
				for(Figure figure : figures) {
					figure.draw(g);
				}
			}
		};

		canvas.setBounds(0,0,400,600);

/**
 	* Adds a mouse listener to the canvas so that the user can create figures
 	* with the mouse and store them into the figures ArrayList. Mouse pressed
 	* and mouse released both are override to allow the user to draw shapes
 	* based on the mouse clicks and the buttons selected. It also prints the to
 	* String method of the figure to the textArea.
 */
		canvas.addMouseListener(new MouseAdapter() {
			@Override		
			public void mousePressed(MouseEvent e) {
				if(figure instanceof Circle) {
					((Circle) figure).setX(e.getX());
					((Circle) figure).setY(e.getY());
					((Circle) figure).setColor(color);
				} else if(figure instanceof Rectangle) {
					((Rectangle) figure).setX(e.getX());
					((Rectangle) figure).setY(e.getY());
					((Rectangle) figure).setColor(color);
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				try{
					if(figure.color == null) {
						text.append("Please select a Color to continue \n");
					} else {
						if(figure instanceof Circle) {			
							int x2 = (int)Math.pow((e.getX() 
									- ((Circle) figure).x), 2);
							int y2 = (int)Math.pow((e.getY()  
									- ((Circle) figure).y), 2);
							int z2 = (int) (Math.sqrt(x2+y2));
							((Circle) figure).setRadius(z2);
							((Circle) figure).setX(((Circle) figure).x  
									- (((Circle) figure).radius));
							((Circle) figure).setY(((Circle) figure).y  
									- (((Circle) figure).radius));
							text.append(((Circle) figure).toString() + "\n");
							figures.add(figure);
							canvas.repaint();
						} else if(figure instanceof Rectangle) {
							((Rectangle) figure).setWidth(e.getX()  
									- ((Rectangle) figure).x);
							((Rectangle) figure).setHeight(e.getY()  
									- ((Rectangle) figure).y);
							text.append(((Rectangle) figure).toString() + "\n");
							figures.add(figure);
							canvas.repaint();
						} 
					}
				}catch(Exception exception) {
					text.append("Please select a Shape to continue \n");
				}
			}
		});

/**
	* Creates the Circle button that creates a new circle object to store
	* into figure.
*/
		Button circle = new Button("Circle");
		circle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				figure = new Circle();
			}
		});

/**
 	* Creates the Rectangle button that creates a rectangle object to store
 	* into figure.
 */
		Button rectangle = new Button("Rectangle");
		rectangle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				figure = new Rectangle();		
			}
		});

/**
 	* Creates the exit button that exits the GUI and serializes the figures 
 	* ArrayList, so that it can be reused later.
*/
		Button exit = new Button("Exit");
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String filename = "figures.txt";
				// save the object to file
				try {
					FileOutputStream fos = new FileOutputStream(filename);
					ObjectOutputStream  out = new ObjectOutputStream(fos);
					out.writeObject(figures);
					out.close();
					fos.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				System.exit(0);
			}        
		});   

		canvasPanel.setLayout(new BorderLayout(3,3));
		canvasPanel.add(canvas, BorderLayout.CENTER);
		canvasPanel.add(date, BorderLayout.SOUTH);

		panel.setLayout(new GridLayout(2,3,3,3));
		panel.add(red);
		panel.add(green);
		panel.add(blue);
		panel.add(circle);
		panel.add(rectangle);
		panel.add(exit);

		f.setLayout(new BorderLayout(3,3));
		f.add(canvasPanel , BorderLayout.WEST);
		f.add(panel, BorderLayout.CENTER);
		f.add(text, BorderLayout.EAST);
	}

/**
 	* The main method is used to deserialize the figures ArrayList to pass into
 	* the FigureGUI constructor and let the user continue where the last user 
 	* last left off and instantiate the FigureGUI so that it can be displayed.
 	* If the file exists it will deserialize the file, if not it will pass
 	* an empty ArrayList of figures to the constructor.
 */
	public static void main(String args[]){    
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					String filename = "figures.txt";
					ArrayList<Figure> figures = new ArrayList<Figure>();
					try {
						File tmpDir = new File(filename);
						if(tmpDir.exists()) {
							FileInputStream fis = new FileInputStream(filename);
							ObjectInputStream in = new ObjectInputStream(fis);
							figures = (ArrayList<Figure>) in.readObject();
							in.close();
							fis.close();
						}              
					} catch (Exception ex) {
						ex.printStackTrace();
					}
					FigureGUI f = new FigureGUI(figures);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}