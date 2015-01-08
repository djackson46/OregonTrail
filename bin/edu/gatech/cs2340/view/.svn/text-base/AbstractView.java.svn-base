package edu.gatech.cs2340.view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.image.VolatileImage;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import edu.gatech.cs2340.controller.AbstractController;
import edu.gatech.cs2340.model.Model;

/**
 * Represents an abstract view.
 * 
 * @author The Jetties
 */
public abstract class AbstractView extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6380706152336232160L;
	VolatileImage bufferimage;
	boolean[] status;
	AbstractController control;
	Model theModel;

	/**
	 * Creates the view.
	 * 
	 * @param controller
	 *            - the controller that controls the view.
	 */
	public AbstractView(AbstractController controller) {
		super();
		// Sets up Swing junk
		setDoubleBuffered(true);
		setIgnoreRepaint(true);
		setOpaque(false);
		setBounds(0, 0, GameWindow.WIDTH, GameWindow.HEIGHT);
		control = controller;
	}

	/**
	 * Paints the buffer image to the JPanel
	 * 
	 */
	public final void paintScreen() {
		try {
			Graphics g = this.getGraphics();
			if (g != null && bufferimage != null) {
				g.drawImage(bufferimage, 0, 0, null);
			}
			g.dispose();
			bufferimage.flush();
		} catch (Exception e) {
			System.out.println("paintscreen() Currentcount: "
					+ GameWindow.count);
		}
	}

	/**
	 * Returns a Graphics2D object representing the buffer image.
	 * 
	 * @return The buffer image.
	 */
	public Graphics2D getBufferGraphics() {
		GraphicsEnvironment ge = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		GraphicsConfiguration gc = ge.getDefaultScreenDevice()
				.getDefaultConfiguration();
		try {
			bufferimage = gc.createCompatibleVolatileImage(GameWindow.WIDTH,
					GameWindow.HEIGHT, gc.getImageCapabilities());
			if (bufferimage.validate(gc) == VolatileImage.IMAGE_INCOMPATIBLE) {
				System.out.println("VolatileImage not Valid");
			}
			if (bufferimage.validate(gc) == VolatileImage.IMAGE_RESTORED) {
				System.out.println("VolatileImage restored");
			}
		} catch (Exception io) {
			System.out.println(io.getMessage());
		}
		return (Graphics2D) bufferimage.getGraphics();
	}

	/**
	 * All drawing operations on the buffer should be done in this method
	 */
	public abstract void drawScreen();

	/**
	 * Returns an array of boolean values representing the state of the view,
	 * including user input. Refer to SelectCharacterController for usage
	 * 
	 * @return boolean[] containing 0 or more status values
	 */
	public abstract boolean[] checkStatus();

	public void displayMessage(String message) {
		JOptionPane.showMessageDialog(this, message);
	}

	/**
	 * Called to finalize a view by its controller. It should release Swing and
	 * graphics resources
	 */
	public abstract void dispose();
}
