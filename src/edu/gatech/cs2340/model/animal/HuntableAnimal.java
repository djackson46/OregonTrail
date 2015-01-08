package edu.gatech.cs2340.model.animal;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import edu.gatech.cs2340.model.GameImage;
import edu.gatech.cs2340.model.Vector2;
import edu.gatech.cs2340.view.GameWindow;

/**
 * Represents a deer. To be used in the hunting game.
 * 
 * @author The Jetties
 * 
 */
public class HuntableAnimal extends Animal {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6686872509145533753L;
	/** The minimum alpha value for which a pixel will be determined as part 
	 * of the animal **/
	public final static int MINALPHA = 5;
	private GameImage gi;
	private int food;
	private Vector2 velocity, position;
	private BufferedImage bi;
	
	private static String BIG_DEER = GameWindow.RESOURCEFOLDER + "hunt_deer_large.png";
	private static String REGULAR_DEER = GameWindow.RESOURCEFOLDER + "hunt_deer_small.png";
	private static String CROW = GameWindow.RESOURCEFOLDER + "hunt_crow.png";
	private static String SWALLOW = GameWindow.RESOURCEFOLDER + "hunt_swallow.png";
	private static String FLYINGPIG = GameWindow.RESOURCEFOLDER + "hunt_pig.png";
	
	/**
	 * Creates a deer.
	 * @param health - the deer's health.
	 * @param foodBase - the food content of the deer.
	 * @param filename - String name of the file of the deer.
	 * @param starty - Starting y position
	 * @param startVelocity - Starting velocity of the animal
	 */
	private HuntableAnimal(int health, int foodBase, String filename, int starty, double startVelocity) {
		super(health);
		this.food = (int) ((Math.random() + 1) * foodBase);
		int temp = (int) Math.round(Math.random());
		gi = new GameImage(filename,
				 temp * GameWindow.WIDTH, starty, 5);
		velocity = new Vector2();
		velocity.x = temp == 1? -startVelocity : startVelocity;
		position = new Vector2(temp * GameWindow.WIDTH, starty);
		if(temp == 1)
		{
			gi.flip(true);
			try
			{
				bi = ImageIO.read(new File(filename));
			}
			catch(IOException io)
			{
				System.err.println("Cannot load image: " + filename);
			}
			BufferedImage other = null;
			try
			{
				other = ImageIO.read(new File(filename));
			}
			catch(IOException io)
			{
				System.err.println("Cannot load image: " + filename);
			}
			Graphics2D g2 = bi.createGraphics();
			g2.setComposite(AlphaComposite.Src);
			g2.setColor(new Color(0, 0, 0, 0));
			g2.fillRect(0, 0, bi.getWidth(), bi.getHeight());
			g2.scale(-1, 1);
			g2.drawImage(other, -other.getWidth(), 0, null);
		}
		else
		{
			try
			{
				bi = ImageIO.read(new File(filename));
			}
			catch(IOException io)
			{
				System.err.println("Cannot load image: " + filename);
			}
		}
	}

	/**
	 * Returns the amount of food the deer was worth.
	 * 
	 * @return The amount of food the deer was worth.
	 */
	public int getFood() {
		return this.food;
	}

	/**
	 * Creates a new HuntableAnimal randomly from a set of animals (deer of
	 * different sizes, birds, parakoopas...)
	 * 
	 * @return an animal to hunt.
	 */
	public static HuntableAnimal getNewAnimal() {
		double rand = Math.random();
		if (rand < .21) {
			return new HuntableAnimal(150, 25, REGULAR_DEER,
					GameWindow.YOFFSET + (int) (.4*GameWindow.DEFAULTHEIGHT * 
					Math.random() + .5 * GameWindow.DEFAULTHEIGHT), 5 + Math.random());
		} 
		else if(rand < .35) {
			return new HuntableAnimal(350, 35, BIG_DEER,
					GameWindow.YOFFSET + (int) (.4*GameWindow.DEFAULTHEIGHT * 
					Math.random() + .5 * GameWindow.DEFAULTHEIGHT), 5 + Math.random());
		}
		else if(rand < .70) {
			return new HuntableAnimal(40, 8, SWALLOW,
					GameWindow.YOFFSET + (int) (.4*GameWindow.DEFAULTHEIGHT * 
					Math.random() + .1 * GameWindow.DEFAULTHEIGHT), 7.5 + Math.random());
		}
		else if(rand < .99){
			return new HuntableAnimal(50, 11, CROW,
					GameWindow.YOFFSET + (int) (.3*GameWindow.DEFAULTHEIGHT * 
					Math.random() + .1 * GameWindow.DEFAULTHEIGHT), 7 + Math.random());
		}
		else {
			return new HuntableAnimal(500, 100, FLYINGPIG,
					GameWindow.YOFFSET + (int) (.3*GameWindow.DEFAULTHEIGHT * 
					Math.random() + .1 * GameWindow.DEFAULTHEIGHT), 4 + Math.random());
		}
	}

	/**
	 * Moves the HuntableAnimal
	 */
	public void move()
	{
		position.x += velocity.x;
		position.y += velocity.y;
		gi.setX((int)position.x);
		gi.setY((int)position.y);
		if(position.x > GameWindow.WIDTH || position.x < 0)
		{
			isdead = true;
		}
	}
	
	/**
	 * This getter gets the bounds on the hunt.
	 * @return the area of the bounds
	 */
	public Rectangle getBounds() {
		return gi.getBounds();
	}
	
	/**
	 * Checks whether a given point collides with the animal
	 * @param p - point to check in image
	 * @return
	 */
	public boolean checkCollision(Point p)
	{
		WritableRaster ar = bi.getAlphaRaster();
		int[] pixel = null;
		pixel = ar.getPixel((int) (p.x - position.x + ar.getWidth()/2), (int)(p.y - position.y + ar.getHeight()/2), pixel);
		if(pixel[0] < MINALPHA)
		{
			return false;
		}
		return true;
	}

	public GameImage getImage()
	{
		return gi;
	}
}
