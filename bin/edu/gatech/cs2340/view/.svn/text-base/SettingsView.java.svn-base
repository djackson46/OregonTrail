package edu.gatech.cs2340.view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSlider;

import edu.gatech.cs2340.controller.AbstractController;
import edu.gatech.cs2340.controller.SettingsController;
import edu.gatech.cs2340.controller.TrailController;
import edu.gatech.cs2340.model.GameImage;
import edu.gatech.cs2340.model.ItemType;
import edu.gatech.cs2340.model.Model;
import edu.gatech.cs2340.model.Purchasable;
import edu.gatech.cs2340.model.Vector2;
import edu.gatech.cs2340.model.human.Human;

/**
 * Settings View
 * 
 * @author The Jetties
 * 
 */
public class SettingsView extends AbstractView {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5290597894725252940L;
	private ArrayList<GameImage> images;
	public JSlider pace;
	public JSlider rations;
	
	private static String SETTINGS_BACKGROUND = GameWindow.RESOURCEFOLDER + "settings_background.png";
	private static String SETTINGS_WAGON = GameWindow.RESOURCEFOLDER + "settings_wagon.png";
	
	/**
	 * This constructor creates settings view
	 * 
	 * @param ac
	 *            an abstracter controller
	 */
	public SettingsView(AbstractController ac, Model model) {
		super(ac);
		theModel = model;
		status = new boolean[SettingsController.BOOLEANLENGTH];
		setLayout(null);

		JLabel playerInfo = new JLabel("Player Info:");
		playerInfo.setFont(new Font("Tahoma", Font.BOLD, 14));
		playerInfo.setBounds(GameWindow.XOFFSET + 22, GameWindow.YOFFSET + 20,
				81, 17);
		this.add(playerInfo);

		JLabel playerNames = new JLabel("<html>");
		for (Human player : theModel.getSettlers()) {
			playerNames.setText(playerNames.getText() + player.getName()
					+ "<br/>");
		}
		playerNames.setText(playerNames.getText() + "</html>");
		Dimension dim = playerNames.getPreferredSize();
		playerNames.setBounds(GameWindow.XOFFSET + 22, GameWindow.YOFFSET + 45,
				dim.width, dim.height);
		this.add(playerNames);

		JLabel playerHealth = new JLabel("<html>");
		for (Human player : theModel.getSettlers()) {
			playerHealth.setText(playerHealth.getText() + "HP: "
					+ player.getHealth() + "<br/>");
		}
		playerHealth.setText(playerHealth.getText() + "</html>");
		dim = playerHealth.getPreferredSize();
		playerHealth.setBounds(GameWindow.XOFFSET + 150,
				GameWindow.YOFFSET + 45, dim.width, dim.height);
		this.add(playerHealth);

		JLabel playerStatus = new JLabel("<html>");
		for (Human player : theModel.getSettlers()) {
			playerStatus.setText(playerStatus.getText() + "Status: " + player.getHealthState()
					+ "<br/>");
		}
		playerStatus.setText(playerStatus.getText() + "</html>");
		dim = playerStatus.getPreferredSize();
		playerStatus.setBounds(GameWindow.XOFFSET + 225,
				GameWindow.YOFFSET + 45, dim.width, dim.height);
		this.add(playerStatus);

		JLabel inventory = new JLabel("Inventory:");
		inventory.setFont(new Font("Tahoma", Font.BOLD, 14));
		inventory.setBounds(GameWindow.XOFFSET + 22, GameWindow.YOFFSET + 145,
				75, 17);
		this.add(inventory);

		JLabel playerStuff = new JLabel("<html>Money: $" + theModel.getFunds()
				+ "<br>Remaining Wagon Capacity: "
				+ theModel.getRemainingWeight() + " lbs.");
		for (ItemType type : ItemType.values()) {
			Purchasable item = theModel.getPurchasable(type);
			playerStuff.setText(playerStuff.getText() + "<br>" + type.getName()
					+ ": " + item.getCount());
		}
		playerStuff.setText(playerStuff.getText() + "</html>");
		playerStuff.setBounds(GameWindow.XOFFSET + 22,
				GameWindow.YOFFSET + 170, playerStuff.getPreferredSize().width,
				playerStuff.getPreferredSize().height);
		this.add(playerStuff);

		JLabel lblPace = new JLabel("Pace:");
		lblPace.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPace.setBounds(GameWindow.XOFFSET + 22, GameWindow.YOFFSET + 500,
				37, 17);
		this.add(lblPace);

		JLabel lblRations = new JLabel("Rations:");
		lblRations.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblRations.setBounds(GameWindow.XOFFSET + 22, GameWindow.YOFFSET + 585,
				57, 17);
		this.add(lblRations);

		this.pace = new JSlider();
		pace.setSnapToTicks(true);
		pace.setPaintLabels(true);
		pace.setMajorTickSpacing(1);
		pace.setToolTipText("");
		pace.setPaintTicks(true);
		pace.setMaximum(Model.MAXPACE);
		pace.setValue(model.getPace());
		pace.setBounds(GameWindow.XOFFSET + 22, GameWindow.YOFFSET + 525, 300,
				45);
		this.add(pace);

		this.rations = new JSlider();
		rations.setSnapToTicks(true);
		rations.setPaintTicks(true);
		rations.setMajorTickSpacing(1);
		rations.setPaintLabels(true);
		rations.setMaximum(Model.MAXRATIONS);
		rations.setValue(model.getRations());
		rations.setBounds(GameWindow.XOFFSET + 22, GameWindow.YOFFSET + 610,
				300, 45);
		this.add(rations);

		JButton btnSet = new JButton("Set");
		btnSet.setActionCommand("SET");
		btnSet.addActionListener(control);
		btnSet.setBounds(GameWindow.XOFFSET + 75, GameWindow.YOFFSET + 676, 89,
				23);
		this.add(btnSet);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.setActionCommand("RETURNTOTRAIL");
		btnCancel.addActionListener(control);
		btnCancel.setBounds(GameWindow.XOFFSET + 175, GameWindow.YOFFSET + 676,
				89, 23);
		this.add(btnCancel);

		GameImage mapPicture = new GameImage(SETTINGS_BACKGROUND, GameWindow.WIDTH / 2,
				GameWindow.HEIGHT / 2, 0);
		images = new ArrayList<GameImage>();
		images.add(mapPicture);

		Vector2 wagonPos = theModel.getCurrentPosition();
		// System.out.println("In Settings view, the wagon's position is:" +
		// wagonPos);
		int mapRight = mapPicture.getBounds().x + mapPicture.getBounds().width;
		int wagonX = mapRight - ((int) wagonPos.x);

		int mapBottom = mapPicture.getBounds().y
				+ mapPicture.getBounds().height;
		int wagonY = mapBottom - ((int) wagonPos.y);
		Vector2 difference= wagonPos.vectorTo(theModel.getCurrentWayPoint());
		GameImage wagonIcon = new GameImage(SETTINGS_WAGON, wagonX, wagonY, 1);
		wagonIcon.setAngle(difference.angle());
		images.add(wagonIcon);
		java.util.Collections.sort(images);
	}

	/**
	 * Draws the screen
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
	 * Action Performed
	 * 
	 * @param ae
	 *            an action event
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
}