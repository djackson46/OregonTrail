package edu.gatech.cs2340.view;

import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import edu.gatech.cs2340.controller.AbstractController;
import edu.gatech.cs2340.controller.HuntController;
import edu.gatech.cs2340.model.GameImage;
import edu.gatech.cs2340.model.Model;

/**
 * Hunt View
 * 
 * @author The Jetties
 * 
 */
public class HuntView extends AbstractView {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2216563207614449004L;
	private ArrayList<GameImage> images;
	private GameImage sight;
	private Point temp;
	
	private static String HUNT_BACKGROUND = GameWindow.RESOURCEFOLDER + "hunt_background.png";
	private static String RIGHT_ARROW = GameWindow.RESOURCEFOLDER + "right_arrow.png";
	private static String RIGHT_ARROW_ROLLOVER = GameWindow.RESOURCEFOLDER + "right_arrow_rollover.png";

	/**
	 * This constructor creates the hunt view
	 * 
	 * @param controller
	 *            the abstract controller
	 * @param model
	 *            the model
	 */
	public HuntView(AbstractController controller, Model model) {
		super(controller);
		theModel = model;
		setLayout(null);
		temp = new Point();
		JButton moveToCity = new JButton();
		moveToCity.setIcon(new ImageIcon(RIGHT_ARROW));
		moveToCity.setRolloverIcon(new ImageIcon(RIGHT_ARROW_ROLLOVER));
		moveToCity.setBounds(GameWindow.XOFFSET + 1125, GameWindow.YOFFSET + 20,
				125, 100);
		moveToCity.setOpaque(false);
		moveToCity.setIgnoreRepaint(true);
		moveToCity.setBorderPainted(false);
		moveToCity.setContentAreaFilled(false);
		moveToCity.setFocusable(false);
		moveToCity.setActionCommand("MOVETOTRAIL");
		moveToCity.addActionListener(control);
		this.add(moveToCity);

		sight = theModel.getGunCursor();
		this.addMouseListener((HuntController) control);
		this.addMouseMotionListener((HuntController) control);
		
		GameImage gi = new GameImage(HUNT_BACKGROUND, GameWindow.WIDTH / 2,
				GameWindow.HEIGHT / 2, 0);
		images = new ArrayList<GameImage>();
		images.add(gi);
		images.add(sight);
		java.util.Collections.sort(images);
		Cursor invisibleCursor = Toolkit.getDefaultToolkit()
		.createCustomCursor(Toolkit.getDefaultToolkit().getImage(""), 
				new Point(0, 0), "invisible");
		this.setCursor(invisibleCursor);
	}

	/**
	 * draws the screen
	 */
	public void drawScreen() {
		Graphics2D g2 = getBufferGraphics();
		for (int i = 0; i < images.size(); i++) {
			images.get(i).draw(g2);
		}
		g2.drawString("X: " + temp.x, 30, 30);
		g2.drawString("Y: " + temp.y, 30, 40);
		this.paintComponents(g2);
		g2.dispose();
	}

	/**
	 * Sets the state
	 * 
	 * @param inPoint
	 *            a point
	 */
	public void setState(Point inPoint) {
		sight.setX(inPoint.x);
		sight.setY(inPoint.y);
		temp.x = inPoint.x;
		temp.y = inPoint.y;
	}

	/**
	 * disposes of the view
	 */
	public void dispose() {

	}

	/**
	 * 
	 * @return a boolean array of the status
	 */
	public boolean[] checkStatus() {
		return null;
	}
	
	public void addImage(GameImage gi)
	{
		images.add(gi);
		java.util.Collections.sort(images);
	}

	public void removeImages(ArrayList<Object> objArr) {
		for(int i = 0; i < objArr.size(); i++)
		{
			images.remove(objArr.get(i));
		}
	}
}