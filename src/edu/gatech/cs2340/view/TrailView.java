package edu.gatech.cs2340.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import edu.gatech.cs2340.controller.AbstractController;
import edu.gatech.cs2340.controller.TrailController;
import edu.gatech.cs2340.model.GameImage;
import edu.gatech.cs2340.model.ItemType;
import edu.gatech.cs2340.model.Model;

/**
 * The Trail View
 * 
 * @author The Jetties
 * 
 */
public class TrailView extends AbstractView {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9071858511432593217L;
	private ArrayList<GameImage> images;
	private JLabel info;
	
	private static String RIGHT_ARROW = GameWindow.RESOURCEFOLDER + "right_arrow.png";
	private static String RIGHT_ARROW_ROLLOVER = GameWindow.RESOURCEFOLDER + "right_arrow_rollover.png";
	private static String TRAIL_BACKGROUND = GameWindow.RESOURCEFOLDER + "trail_background.png";
	private static String TRAIL_HUNT = GameWindow.RESOURCEFOLDER + "trail_hunt.png";
	private static String TRAIL_HUNT_ROLLOVER = GameWindow.RESOURCEFOLDER + "trail_hunt_rollover.png";
	private static String TRAIL_SAVE = GameWindow.RESOURCEFOLDER + "trail_save.png";
	private static String TRAIL_SAVE_ROLLOVER = GameWindow.RESOURCEFOLDER + "trail_save_rollover.png";
	private static String TRAIL_SETTINGS = GameWindow.RESOURCEFOLDER + "trail_settings.png";
	private static String TRAIL_SETTINGS_ROLLOVER = GameWindow.RESOURCEFOLDER + "trail_settings_rollover.png";
	private static String TRAIL_TRAVELER = GameWindow.RESOURCEFOLDER + "trail_traveler.png";
	private static String TRAIL_TRAVELER_ROLLOVER = GameWindow.RESOURCEFOLDER + "trail_traveler_rollover.png";
	
	/**
	 * This constructor creates a trail view
	 * 
	 * @param ac
	 *            the abstract controller
	 * @param model
	 *            the model
	 */
	public TrailView(AbstractController ac, Model model) {
		super(ac);
		this.theModel = model;
		status = new boolean[TrailController.BOOLEANLENGTH];
		setLayout(null);
		
		JButton traveler = new JButton();
		traveler.setIcon(new ImageIcon(TRAIL_TRAVELER));
		traveler.setRolloverIcon(new ImageIcon(TRAIL_TRAVELER_ROLLOVER));
		traveler.setBounds(GameWindow.XOFFSET + 610, GameWindow.YOFFSET + 20,
				67, 100);
		traveler.setOpaque(false);
		traveler.setIgnoreRepaint(true);
		traveler.setBorderPainted(false);
		traveler.setContentAreaFilled(false);
		traveler.setFocusable(false);
		traveler.setActionCommand("TRAVELERTRADE");
		traveler.addActionListener(control);
		this.add(traveler);
		
		JButton hunt = new JButton();
		hunt.setIcon(new ImageIcon(TRAIL_HUNT));
		hunt.setRolloverIcon(new ImageIcon(TRAIL_HUNT_ROLLOVER));
		hunt.setBounds(GameWindow.XOFFSET + 690, GameWindow.YOFFSET + 20, 184,
				100);
		hunt.setOpaque(false);
		hunt.setIgnoreRepaint(true);
		hunt.setBorderPainted(false);
		hunt.setContentAreaFilled(false);
		hunt.setFocusable(false);
		hunt.setActionCommand("HUNT");
		hunt.addActionListener(ac);
		this.add(hunt);
		
		JButton save = new JButton();
		save.setIcon(new ImageIcon(TRAIL_SAVE));
		save.setRolloverIcon(new ImageIcon(TRAIL_SAVE_ROLLOVER));
		save.setBounds(GameWindow.XOFFSET + 850, GameWindow.YOFFSET + 20, 184,
				100);
		save.setOpaque(false);
		save.setIgnoreRepaint(true);
		save.setBorderPainted(false);
		save.setContentAreaFilled(false);
		save.setFocusable(false);
		save.setActionCommand("SAVE");
		save.addActionListener(control);
		this.add(save);
		
		JButton settings = new JButton();
		settings.setIcon(new ImageIcon(TRAIL_SETTINGS));
		settings.setRolloverIcon(new ImageIcon(TRAIL_SETTINGS_ROLLOVER));
		settings.setBounds(GameWindow.XOFFSET + 1010, GameWindow.YOFFSET + 20,
				103, 100);
		settings.setOpaque(false);
		settings.setIgnoreRepaint(true);
		settings.setBorderPainted(false);
		settings.setContentAreaFilled(false);
		settings.setFocusable(false);
		settings.setActionCommand("CHANGESETTINGS");
		settings.addActionListener(control);
		this.add(settings);
		
		JButton nextTurn = new JButton();
		nextTurn.setIcon(new ImageIcon(RIGHT_ARROW));
		nextTurn.setRolloverIcon(new ImageIcon(RIGHT_ARROW_ROLLOVER));
		nextTurn.setBounds(GameWindow.XOFFSET + 1125, GameWindow.YOFFSET + 20,
				125, 100);
		nextTurn.setOpaque(false);
		nextTurn.setIgnoreRepaint(true);
		nextTurn.setBorderPainted(false);
		nextTurn.setContentAreaFilled(false);
		nextTurn.setFocusable(false);
		nextTurn.setActionCommand("MOVEALONGTRAIL");
		nextTurn.addActionListener(control);
		this.add(nextTurn);

		this.info = new JLabel();
		this.info.setForeground(Color.WHITE);
		this.info.setFont(new Font("Tahoma", Font.BOLD, 14));
		this.info.setBounds(GameWindow.XOFFSET + 25, GameWindow.YOFFSET + 25,
				225, 136);
		updateInfo();
		this.add(this.info);

		GameImage gi = new GameImage(TRAIL_BACKGROUND, GameWindow.WIDTH / 2,
				GameWindow.HEIGHT / 2, 0);
		images = new ArrayList<GameImage>();
		images.add(gi);
		java.util.Collections.sort(images);
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
		this.paintComponents(g2);
		g2.dispose();
	}

	/**
	 * checks the status
	 * 
	 * @return the status
	 */
	public boolean[] checkStatus() {
		boolean[] temp = status;
		status = new boolean[TrailController.BOOLEANLENGTH];
		return temp;
	}

	/**
	 * action performed
	 * 
	 * @param ae
	 *            action event
	 */
	public void actionPerformed(ActionEvent ae) {
		@SuppressWarnings("unused")
		String temp = ae.getActionCommand();
	}

	/**
	 * disposes of the view
	 */
	public void dispose() {

	}

	public void updateInfo() {
		this.info.setText("<html>Settlers: " + theModel.getLivingSettlerCount()
				+ "<br>Oxen: " + theModel.getOxenCount()
				+ "<br><br>Distance Traveled: "
				+ String.format("%.2f", theModel.getDistanceTraveled())
				+ "<br>Pace: " + theModel.getPace()
				+ "<br><br>Rations Remaining: "
				+ theModel.getItem(ItemType.FOOD).getCount()
				+ "<br>Rations Consumed Per Turn: " + theModel.getRations()
				+ "</html>");
	}
}
