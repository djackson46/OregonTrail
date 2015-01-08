package edu.gatech.cs2340.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import edu.gatech.cs2340.controller.AbstractController;
import edu.gatech.cs2340.controller.SelectCharacterController;

/**
 * Select Character View
 * 
 * @author The Jetties
 * 
 */
public class SelectCharacterView extends AbstractView {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4698563179636876907L;
	private ConfigurationFrame frame;
	private float textx = 500, texty = 500, textdx, textdy, textxoff = 575,
			textyoff = 75;

	/**
	 * This constructor creates the view
	 * 
	 * @param controller
	 *            an abstract controller
	 */
	public SelectCharacterView(AbstractController controller) {
		super(controller);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new ConfigurationFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		status = new boolean[SelectCharacterController.BOOLEANLENGTH];
		textdx = (float) Math.sqrt(2);
		textdy = (float) Math.sqrt(2);
	}

	/**
	 * draws the screen
	 */
	public void drawScreen() {

		Graphics2D g2 = getBufferGraphics();
		// Draw stuff here
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		textx += textdx;
		texty += textdy;
		if (textx + textxoff > GameWindow.WIDTH || textx < 0) {
			textdx = -(float) (textdx + .4 * (Math.random() - .5));
		}
		if (texty > GameWindow.HEIGHT || texty - textyoff < 0) {
			textdy = -(float) (textdy + .4 * (Math.random() - .5));
		}
		g2.setFont(new Font(Font.SERIF, Font.PLAIN, 110));
		g2.setColor(new Color(190 + (int) (64 * Math
				.sin((double) GameWindow.count / 27)), 65 + (int) (64 * Math
				.sin((double) GameWindow.count / 37)), 0));
		g2.drawString("Oregon Trail", textx, texty);
		g2.dispose();
	}

	/**
	 * checks the status
	 * 
	 * @return a boolean array of the status
	 */
	public boolean[] checkStatus() {
		if (frame != null) {
			status[SelectCharacterController.CONFIGURATIONFRAMESTATUS] = frame
					.checkStatus();
		}
		boolean[] temp = (boolean[]) status.clone();
		status = new boolean[SelectCharacterController.BOOLEANLENGTH];
		return temp;
	}

	/**
	 * This getter gets the character's names
	 * 
	 * @return the character's names
	 */
	public String[] getCharacterNames() {
		return frame.getCharacterNames();
	}

	/**
	 * This getter gets the professions of the characters
	 * 
	 * @return the professions
	 */
	public int[] getProfessions() {
		return frame.getProfessions();
	}

	/**
	 * disposes of the view
	 */
	public void dispose() {
		frame.setVisible(false);
		frame.dispose();
	}
}
