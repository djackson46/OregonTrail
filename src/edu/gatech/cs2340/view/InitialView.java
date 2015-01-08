package edu.gatech.cs2340.view;

import java.awt.Graphics2D;
import java.awt.image.VolatileImage;

import edu.gatech.cs2340.controller.AbstractController;

/**
 * The Initial View
 * 
 * @author The Jetties
 * 
 */
public class InitialView extends AbstractView {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6207911448196131676L;

	VolatileImage splash;

	private static String OREGON_TRAIL_SPLASH = GameWindow.RESOURCEFOLDER + "oregon_trail_splash.png";
	
	/**
	 * This constructor creates the initial view
	 * 
	 * @param controller
	 *            the abstract controller
	 */
	public InitialView(AbstractController controller) {
		super(controller);
		splash = GameWindow.loadVIResource(OREGON_TRAIL_SPLASH);
	}

	/**
	 * draws the screen
	 */
	public void drawScreen() {
		Graphics2D g2 = getBufferGraphics();
		// Draw stuff here
		g2.drawImage(splash, GameWindow.WIDTH / 2 - splash.getWidth() / 2,
				GameWindow.HEIGHT / 2 - splash.getHeight() / 2, null);
		g2.dispose();
	}

	/**
	 * Checks status
	 * 
	 * @return a boolean array of the status
	 */
	public boolean[] checkStatus() {
		return null;
	}

	/**
	 * disposes the view
	 */
	public void dispose() {

	}
}
