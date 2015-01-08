package edu.gatech.cs2340.view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import edu.gatech.cs2340.controller.AbstractController;
import edu.gatech.cs2340.controller.TrailController;
import edu.gatech.cs2340.model.GameImage;
import edu.gatech.cs2340.model.ItemType;
import edu.gatech.cs2340.model.Model;

public class RiverView extends AbstractView {
	/**
	 * 
	 */
	private static final long serialVersionUID = 735216704887356295L;
	private ArrayList<GameImage> images;
	private JLabel info;
	
	private static String RIVER_BACKGROUND = GameWindow.RESOURCEFOLDER + "river_background.png";
	private static String RIVER_FERRY = GameWindow.RESOURCEFOLDER + "river_ferry.png";
	private static String RIVER_FERRY_ROLLOVER = GameWindow.RESOURCEFOLDER + "river_ferry_rollover.png";
	private static String RIVER_FLOAT = GameWindow.RESOURCEFOLDER + "river_float.png";
	private static String RIVER_FLOAT_ROLLOVER = GameWindow.RESOURCEFOLDER + "river_float_rollover.png";
	private static String RIVER_FORD = GameWindow.RESOURCEFOLDER + "river_ford.png";
	private static String RIVER_FORD_ROLLOVER = GameWindow.RESOURCEFOLDER + "river_ford_rollover.png";
	
	/**
	 * This constructor creates the store view
	 * 
	 * @param controller
	 *            an abstract controller
	 * @param store
	 *            the store type
	 * @param model
	 *            the model
	 */
	public RiverView(AbstractController controller, Model model) {
		super(controller);
		this.theModel = model;
		status = new boolean[TrailController.BOOLEANLENGTH];
		theModel = model;
		
		JButton ferry = new JButton();
		ferry.setToolTipText("Take a Ferry");
		ferry.setIcon(new ImageIcon(RIVER_FERRY));
		ferry.setRolloverIcon(new ImageIcon(RIVER_FERRY_ROLLOVER));
		ferry.setBounds(GameWindow.XOFFSET + 175, GameWindow.YOFFSET + 345, 208, 175);
		ferry.setOpaque(false);
		ferry.setIgnoreRepaint(true);
		ferry.setBorderPainted(false);
		ferry.setContentAreaFilled(false);
		ferry.setFocusable(false);
		
		ferry.setActionCommand("FERRY");
		ferry.addActionListener(control);
		this.add(ferry);
		
		JButton ford = new JButton();
		ford.setToolTipText("Ford through the River");
		ford.setIcon(new ImageIcon(RIVER_FORD));
		ford.setRolloverIcon(new ImageIcon(RIVER_FORD_ROLLOVER));
		ford.setBounds(GameWindow.XOFFSET + 450, GameWindow.YOFFSET + 345, 382, 175);
		ford.setOpaque(false);
		ford.setIgnoreRepaint(true);
		ford.setBorderPainted(false);
		ford.setContentAreaFilled(false);
		ford.setFocusable(false);
		
		ford.setActionCommand("FORD");
		ford.addActionListener(control);
		this.add(ford);
		
		JButton floatLabel = new JButton();
		floatLabel.setToolTipText("Float across the River");
		floatLabel.setIcon(new ImageIcon(RIVER_FLOAT));
		floatLabel.setRolloverIcon(new ImageIcon(RIVER_FLOAT_ROLLOVER));
		floatLabel.setBounds(GameWindow.XOFFSET + 910, GameWindow.YOFFSET + 345, 208, 175);
		floatLabel.setOpaque(false);
		floatLabel.setIgnoreRepaint(true);
		floatLabel.setBorderPainted(false);
		floatLabel.setContentAreaFilled(false);
		floatLabel.setFocusable(false);

		floatLabel.setActionCommand("FLOAT");
		floatLabel.addActionListener(control);
		this.add(floatLabel);
		
		this.info = new JLabel("<html>Money: $" + theModel.getFunds()
				+ "<br>Remaining Wagon Capacity: "
				+ theModel.getRemainingWeight() + "<br>");
		ItemType[] types = ItemType.values();
		for (int k = 0; k < types.length; k++) {
			info.setText(info.getText() + "<br>" + types[k].getName() + ": "
					+ theModel.getPurchasable(types[k]).getCount());
		}
		info.setText(info.getText() + "</html>");
		info.setForeground(Color.WHITE);
		info.setBounds(GameWindow.XOFFSET + 13, GameWindow.YOFFSET + 20, 220,
				180);
		info.setVerticalAlignment(SwingConstants.TOP);
		this.add(info);

		GameImage gi = new GameImage(RIVER_BACKGROUND, GameWindow.WIDTH / 2,
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
		for (int i = 0; i < images.size(); i++) {
			images.get(i).draw(g2);
		}
		this.paintComponents(g2);
		g2.dispose();
	}

	/**
	 * disposes of the view
	 */
	public void dispose() {
		for (int i = 0; i < images.size(); i++) {
			images.get(i).dispose();
		}
	}

	/**
	 * checks the status
	 * 
	 * @return the status
	 */
	public boolean[] checkStatus() {
		return null;
	}

	/**
	 * updates the status
	 * 
	 * @param text
	 *            the text of the status
	 */
	public void updateStatus() {
		this.info.setText("<html>Money: $" + theModel.getFunds()
				+ "<br>Remaining Wagon Capacity: "
				+ theModel.getRemainingWeight() + "<br>");
		ItemType[] temp = ItemType.values();
		for (int k = 0; k < temp.length; k++) {
			info.setText(info.getText() + "<br>" + temp[k].getName() + ": "
					+ theModel.getPurchasable(temp[k]).getCount());
		}
		info.setText(info.getText() + "</html>");
	}
}