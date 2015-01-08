package edu.gatech.cs2340.view;

import java.awt.Font;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import edu.gatech.cs2340.controller.AbstractController;
import edu.gatech.cs2340.controller.CityController;
import edu.gatech.cs2340.model.GameImage;

/**
 * Represents a city. Has an overhead map with clickable stores and actions.
 * 
 * @author The Jetties
 */
public class CityView extends AbstractView {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7922705271778291999L;
	private ArrayList<GameImage> images;
	private JButton moveToTrail, moveToStore, moveToStable;
	private boolean won;
	private boolean checkedYet;
	
	private static String CITY_BACKGROUND = GameWindow.RESOURCEFOLDER + "city_background.png";
	private static String CITY_STABLE = GameWindow.RESOURCEFOLDER + "city_stable.png";
	private static String CITY_STABLE_ROLLOVER = GameWindow.RESOURCEFOLDER + "city_stable_rollover.png";
	private static String CITY_STORE = GameWindow.RESOURCEFOLDER + "city_store.png";
	private static String CITY_STORE_ROLLOVER = GameWindow.RESOURCEFOLDER + "city_store_rollover.png";
	private static String RIGHT_ARROW = GameWindow.RESOURCEFOLDER + "right_arrow.png";
	private static String RIGHT_ARROW_ROLLOVER = GameWindow.RESOURCEFOLDER + "right_arrow_rollover.png";

	public CityView(AbstractController controller) {
		super(controller);
		this.setLayout(null);

		this.won = true;
		this.checkedYet = false;
		ImageIcon temp;
		if (((CityController) control).trailRemaining()) {
			this.won = false;
			moveToTrail = new JButton();
			temp = new ImageIcon(RIGHT_ARROW);
			moveToTrail.setIcon(temp);
			moveToTrail.setRolloverIcon(new ImageIcon(RIGHT_ARROW_ROLLOVER));
			moveToTrail.setBounds(GameWindow.XOFFSET + 1150,
					GameWindow.YOFFSET + 588, temp.getIconWidth(), temp
							.getIconHeight());

			// This sets the button so that only the imageicon or text is
			// rendered
			moveToTrail.setOpaque(false);
			moveToTrail.setIgnoreRepaint(true);
			moveToTrail.setBorderPainted(false);
			moveToTrail.setContentAreaFilled(false);
			moveToTrail.setFocusable(false);
			// ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

			moveToTrail.setActionCommand("MOVETOTRAIL");
			moveToTrail.addActionListener(control);
			this.add(moveToTrail);
		}

		moveToStore = new JButton();
		temp = new ImageIcon(CITY_STORE);
		moveToStore.setIcon(temp);
		moveToStore.setRolloverIcon(new ImageIcon(CITY_STORE_ROLLOVER));
		moveToStore.setBounds(GameWindow.XOFFSET + 190,
				GameWindow.YOFFSET + 257, temp.getIconWidth(), temp
						.getIconHeight());

		moveToStore.setOpaque(false);
		moveToStore.setIgnoreRepaint(true);
		moveToStore.setBorderPainted(false);
		moveToStore.setContentAreaFilled(false);
		moveToStore.setFocusable(false);

		moveToStore.setActionCommand("MOVETOSTORE");
		moveToStore.addActionListener(control);
		this.add(moveToStore);

		moveToStable = new JButton();
		temp = new ImageIcon(CITY_STABLE);
		moveToStable.setIcon(temp);
		moveToStable.setRolloverIcon(new ImageIcon(CITY_STABLE_ROLLOVER));
		moveToStable.setBounds(GameWindow.XOFFSET + 725,
				GameWindow.YOFFSET + 293, temp.getIconWidth(), temp
						.getIconHeight());

		moveToStable.setOpaque(false);
		moveToStable.setIgnoreRepaint(true);
		moveToStable.setBorderPainted(false);
		moveToStable.setContentAreaFilled(false);
		moveToStable.setFocusable(false);

		moveToStable.setActionCommand("MOVETOSTABLE");
		moveToStable.addActionListener(control);
		this.add(moveToStable);
		
		JLabel cityName = new JLabel(((CityController) control).getCityName());
		cityName.setFont(new Font("Tahoma", Font.BOLD, 32));
		cityName.setBounds(GameWindow.XOFFSET + GameWindow.DEFAULTWIDTH
				- (cityName.getPreferredSize().width + 20),
				GameWindow.YOFFSET + 20, cityName.getPreferredSize().width,
				cityName.getPreferredSize().height);
		this.add(cityName);
		
		images = new ArrayList<GameImage>();
		GameImage backdrop = new GameImage(CITY_BACKGROUND, GameWindow.WIDTH / 2,
				GameWindow.HEIGHT / 2, 0);
		images.add(backdrop);
		Collections.sort(images);
	}

	/**
	 * draws the screen
	 */
	public void drawScreen() {
		Graphics2D g2 = getBufferGraphics();
		// Draw stuff here
		for (int i = 0; i < images.size(); i++) {
			images.get(i).draw(g2);
		}
		// render swing componenets here ( so they show up above all other
		// images)
		this.paintComponents(g2);
		g2.dispose();
		// It's ugly putting this here, but I don't see a way to do it so that
		// it's displayed after the city has been drawn.
		// Replacements welcomed
		// I really don't like this hack.
		if (this.won) {
			if (!this.checkedYet) {
				this.checkedYet = true;
			} else {
				this.displayMessage("Congratulations, you made it to Oregon City.");
				this.won = false;
			}
		}
	}

	/**
	 * checks the status
	 * 
	 * @return a boolean array of the status
	 */
	public boolean[] checkStatus() {
		boolean[] temp = (boolean[]) status.clone();
		status = new boolean[CityController.BOOLEANLENGTH];
		return temp;
	}

	/**
	 * gets rid of the images
	 */
	public void dispose() {
		images = null;
	}
}
