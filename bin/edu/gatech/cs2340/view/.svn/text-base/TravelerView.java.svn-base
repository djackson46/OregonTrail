package edu.gatech.cs2340.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import edu.gatech.cs2340.controller.AbstractController;
import edu.gatech.cs2340.model.GameImage;
import edu.gatech.cs2340.model.ItemType;
import edu.gatech.cs2340.model.Model;

public class TravelerView extends AbstractView {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7525487228030308042L;
	private ArrayList<GameImage> images;
	private JLabel info;
	
	private static String TRAVELER_BACKGROUND = GameWindow.RESOURCEFOLDER + "traveler_background.png";
	private static String TRAVELER_NO = GameWindow.RESOURCEFOLDER + "traveler_no.png";
	private static String TRAVELER_YES = GameWindow.RESOURCEFOLDER + "traveler_yes.png";
	
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
	public TravelerView(AbstractController controller, Model model) {
		super(controller);
		setLayout(null);
		theModel = model;

		JButton yes = new JButton();
		ImageIcon temp = new ImageIcon(TRAVELER_YES);
		yes.setIcon(temp);
		yes.setBounds(GameWindow.XOFFSET + 616, GameWindow.YOFFSET + 481, temp
				.getIconWidth(), temp.getIconHeight());
		yes.setActionCommand("ACCEPTTRADE");
		yes.addActionListener(control);
		this.add(yes);

		JButton no = new JButton();
		temp = new ImageIcon(TRAVELER_NO);
		no.setIcon(temp);
		no.setBounds(GameWindow.XOFFSET + 836, GameWindow.YOFFSET + 481, temp
				.getIconWidth(), temp.getIconHeight());
		no.setActionCommand("REJECTTRADE");
		no.addActionListener(control);
		this.add(no);

		JLabel trade = new JLabel();
		if (theModel.getItemToGive().getName() == "Axle") {
			if (theModel.getItemToReceive().getName() == "Tongue" || theModel.getItemToReceive().getName() == "Wheel") {
				trade.setText("<html>Would you like to trade an "
						+ theModel.getItemToGive().getName() + "<br>for a "
						+ theModel.getItemToReceive().getName() + "?</html>");
			}
			else {
				trade.setText("<html>Would you like to trade an "
						+ theModel.getItemToGive().getName() + "<br>for "
						+ theModel.getItemToReceive().getName() + "?</html>");
			}
		}
		else if (theModel.getItemToGive().getName() == "Tongue" || theModel.getItemToGive().getName() == "Wheel") {
			if (theModel.getItemToReceive().getName() == "Axle") {
				trade.setText("<html>Would you like to trade a "
						+ theModel.getItemToGive().getName() + "<br>for an "
						+ theModel.getItemToReceive().getName() + "?</html>");
			}
			else if (theModel.getItemToReceive().getName() == "Tongue" || theModel.getItemToReceive().getName() == "Wheel") {
				trade.setText("<html>Would you like to trade a "
						+ theModel.getItemToGive().getName() + "<br>for a "
						+ theModel.getItemToReceive().getName() + "?</html>");
			}
			else {
				trade.setText("<html>Would you like to trade a "
						+ theModel.getItemToGive().getName() + "<br>for "
						+ theModel.getItemToReceive().getName() + "?</html>");
			}
		}
		else {
			if (theModel.getItemToReceive().getName() == "Axle") {
				trade.setText("<html>Would you like to trade "
						+ theModel.getItemToGive().getName() + "<br>for an "
						+ theModel.getItemToReceive().getName() + "?</html>");
			}
			else if (theModel.getItemToReceive().getName() == "Tongue" || theModel.getItemToReceive().getName() == "Wheel") {
				trade.setText("<html>Would you like to trade "
						+ theModel.getItemToGive().getName() + "<br>for a "
						+ theModel.getItemToReceive().getName() + "?</html>");
			}
			else {
				trade.setText("<html>Would you like to trade "
						+ theModel.getItemToGive().getName() + "<br>for "
						+ theModel.getItemToReceive().getName() + "?</html>");
			}
		}
		trade.setFont(new Font("Tahoma", Font.BOLD, 36));
		trade.setBounds(GameWindow.XOFFSET + 637, GameWindow.YOFFSET + 172,
				333, 216);
		this.add(trade);

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

		GameImage gi = new GameImage(TRAVELER_BACKGROUND, GameWindow.WIDTH / 2,
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
