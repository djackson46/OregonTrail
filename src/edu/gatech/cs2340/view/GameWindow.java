package edu.gatech.cs2340.view;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.DisplayMode;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Transparency;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.awt.image.VolatileImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFrame;

import edu.gatech.cs2340.controller.AbstractController;
import edu.gatech.cs2340.controller.InitialController;
import edu.gatech.cs2340.model.Model;

/**
 * The Game Window
 * 
 * @author The Jetties
 * 
 */
public class GameWindow extends JFrame implements Runnable, ActionListener,
		WindowListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3765582816591470555L;
	public static final double FRAMETIME = 16.6;
	public static int count;
	public static final String RESOURCEFOLDER = "resources" + File.separator;
	public static final String SAVEFOLDER = "saves" + File.separator;
	private AbstractController gamecontroller;
	private static GameWindow mainframe;
	private boolean isrunning;
	private static Thread mainthread;
	public static int WIDTH, HEIGHT, DEFAULTWIDTH = 1280, DEFAULTHEIGHT = 800;
	public static int XOFFSET, YOFFSET;

	/**
	 * Main method
	 * 
	 * @param args
	 *            - String[] passed in from the console
	 */
	public static void main(String[] args) {
		GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		GraphicsDevice[] devices = graphicsEnvironment.getScreenDevices();
		DisplayMode dm = devices[0].getDisplayMode();
		HEIGHT = dm.getHeight();
		WIDTH = dm.getWidth();
		XOFFSET = (WIDTH - DEFAULTWIDTH) / 2;
		YOFFSET = (HEIGHT - DEFAULTHEIGHT) / 2;
		mainframe = new GameWindow();
		mainframe.addWindowListener(mainframe);
		mainthread = new Thread(mainframe);
		mainthread.start();
	}

	/**
	 * Creates the game window
	 */
	private GameWindow() {
		isrunning = true;
		this.setIgnoreRepaint(true);
		((JComponent) this.getContentPane()).setOpaque(false);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setBounds(0, 0, WIDTH, HEIGHT);
		setUndecorated(false);
		setResizable(false);
		Model model = new Model();
		// Change initial controller here
		gamecontroller = new InitialController(model);
		add(gamecontroller.getView());
		this.setVisible(true);
	}

	/**
	 * runs the game window
	 */
	public void run() {
		long count1, count2;
		double diff;
		count = 0;
		while (isrunning) // Master loop, clocked at 60 Hz This can be changed
							// through FRAMETIME
		{
			count1 = System.nanoTime();
			mainframe.gamecontroller.update();
			count2 = System.nanoTime();
			diff = FRAMETIME - ((count2 - count1) / 1000000L);
			if (diff > 0) {
				try {
					Thread.sleep((int) diff);
				} catch (InterruptedException ie) {
					System.out.println(ie.getMessage());
				}
			}
			count++;
		}
	}

	/**
	 * Sets the GameWindow controller
	 * 
	 * @param ac
	 *            - controller to change to
	 */
	public static void setController(AbstractController ac) {
		mainframe.remove(mainframe.gamecontroller.getView());
		mainframe.gamecontroller = ac;
		mainframe.add(ac.getView());
	}

	/**
	 * Action listener
	 * 
	 * @param e
	 *            an action event
	 */
	public void actionPerformed(ActionEvent e) {

	}

	/**
	 * window closing
	 * 
	 * @param e
	 *            a window event
	 */
	public void windowClosing(WindowEvent e) {
		isrunning = false;
		System.out.println(count);
		System.exit(0);
	}

	/**
	 * Triggered when the window is closed.
	 * 
	 * @param e
	 *            The event.
	 */
	public void windowClosed(WindowEvent e) {

	}

	/**
	 * Triggered when the window is opened.
	 * 
	 * @param e
	 *            The event.
	 */
	public void windowOpened(WindowEvent e) {

	}

	/**
	 * The window iconified
	 * 
	 * @param e
	 *            a windowevent
	 */
	public void windowIconified(WindowEvent e) {

	}

	/**
	 * Window deiconified
	 * 
	 * @param e
	 *            a windowevent
	 */
	public void windowDeiconified(WindowEvent e) {

	}

	/**
	 * Window Activated
	 * 
	 * @param e
	 *            a window event
	 */
	public void windowActivated(WindowEvent e) {

	}

	/**
	 * Window Deactivated
	 * 
	 * @param e
	 *            window event
	 */
	public void windowDeactivated(WindowEvent e) {

	}

	/**
	 * Window Gained Focus
	 * 
	 * @param e
	 *            a window event
	 */
	public void windowGainedFocus(WindowEvent e) {

	}

	/**
	 * Window Lost Focus
	 * 
	 * @param e
	 *            a window event
	 */
	public void windowLostFocus(WindowEvent e) {

	}

	/**
	 * Window State changed
	 * 
	 * @param e
	 *            a window event
	 */
	public void windowStateChanged(WindowEvent e) {

	}

	/**
	 * Loads a volatile image. Read Java API for more info about VolatileImage
	 * 
	 * @param filename
	 *            - full path to file
	 * @return The image.
	 */
	public static VolatileImage loadVIResource(String filename) {
		GraphicsEnvironment ge = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		GraphicsConfiguration gc = ge.getDefaultScreenDevice()
				.getDefaultConfiguration();
		try {
			BufferedImage buff = ImageIO.read(new File(filename));
			VolatileImage vimage = gc.createCompatibleVolatileImage(buff
					.getWidth(), buff.getHeight(), gc.getImageCapabilities());
			if (vimage.validate(gc) == VolatileImage.IMAGE_INCOMPATIBLE) {
				System.out.println("VolatileImage not Valid");
			}
			Graphics2D g2 = vimage.createGraphics();
			g2.drawImage(buff, 0, 0, null);
			g2.dispose();
			return vimage;
		} catch (Exception io) {
			System.out.println(io.getMessage());
		}
		return null;
	}

	/**
	 * Loads a volatile image with transparent background. Read Java API for
	 * more info about VolatileImage
	 * 
	 * @param filename
	 *            - full path to file
	 * @param transparent
	 *            - set to true for transparent background
	 */

	public static VolatileImage loadVIResource(String filename,
			boolean transparent) {
		GraphicsEnvironment ge = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		GraphicsConfiguration gc = ge.getDefaultScreenDevice()
				.getDefaultConfiguration();
		Graphics2D g2 = null;
		try {
			BufferedImage buff = ImageIO.read(new File(filename));
			VolatileImage vimage = gc.createCompatibleVolatileImage(buff
					.getWidth(), buff.getHeight(), gc.getImageCapabilities(),
					Transparency.TRANSLUCENT);
			if (vimage.validate(gc) == VolatileImage.IMAGE_INCOMPATIBLE) {
				System.out.println("VolatileImage not Valid");
			}
			g2 = vimage.createGraphics();
			if (transparent) {
				g2.setComposite(AlphaComposite.Src);
				g2.setColor(new Color(0, 0, 0, 0));
				g2.fillRect(0, 0, vimage.getWidth(), vimage.getHeight());
			}
			g2.drawImage(buff, 0, 0, null);
			return vimage;
		} catch (Exception io) {
			System.out.println(io.getMessage());
		} finally {
			g2.dispose();
		}
		return null;
	}
}