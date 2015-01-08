package edu.gatech.cs2340.view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import edu.gatech.cs2340.controller.AbstractController;
import edu.gatech.cs2340.model.GameImage;
import edu.gatech.cs2340.model.ItemType;
import edu.gatech.cs2340.model.Model;
import edu.gatech.cs2340.model.StoreType;

/**
 * Store View
 * 
 * @author The Jetties
 * 
 */
public class StoreView extends AbstractView {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7281901158576100491L;
	private ArrayList<GameImage> images;
	private DecimalFormat fmt = new DecimalFormat("#.##");
	private JButton moveToCity;
	private JButton[] itemButtons;
	private JLabel info;
	
	private static String RIGHT_ARROW = GameWindow.RESOURCEFOLDER + "right_arrow.png";
	private static String RIGHT_ARROW_ROLLOVER = GameWindow.RESOURCEFOLDER + "right_arrow_rollover.png";
	
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
	public StoreView(AbstractController controller, StoreType store, Model model) {
		super(controller);
		setLayout(null);
		theModel = model;
		String[] buttonfilenames = theModel.getButtonFilenames();
		String[] buttonrollovernames = theModel.getButtonRolloverFilenames();
		itemButtons = new JButton[buttonfilenames.length];
		int i = 0;
		while (i < buttonfilenames.length) {
			for (int k = 0; k < 3; k++) {
				for (int m = 0; m < 3; m++) {
					ImageIcon tempicon = new ImageIcon(GameWindow.RESOURCEFOLDER + buttonfilenames[i]);
					itemButtons[i] = new JButton();
					itemButtons[i].setIcon(tempicon);
					ImageIcon rollover = new ImageIcon(GameWindow.RESOURCEFOLDER + buttonrollovernames[i]);
					itemButtons[i].setRolloverIcon(rollover);
					itemButtons[i].setBounds(theModel.getStoreXOffset()
							+ GameWindow.XOFFSET + m * theModel.getStoreXJump()
							- tempicon.getIconWidth()/2 + 90, theModel
							.getStoreYOffset()
							+ GameWindow.YOFFSET
							- tempicon.getIconHeight()
							+ k
							* theModel.getStoreYJump() + 5,
							tempicon.getIconWidth(), tempicon.getIconHeight());

					itemButtons[i].setOpaque(false);
					itemButtons[i].setIgnoreRepaint(true);
					itemButtons[i].setBorderPainted(false);
					itemButtons[i].setContentAreaFilled(false);
					itemButtons[i].setFocusable(false);

					itemButtons[i].setActionCommand("BUTTON_" + i);
					itemButtons[i].addActionListener(control);
					this.add(itemButtons[i]);
					i++;
					if (i >= buttonfilenames.length) {
						break;
					}
				}
				if (i >= buttonfilenames.length) {
					break;
				}
			}
		}
		ImageIcon tempicon = new ImageIcon(RIGHT_ARROW);
		moveToCity = new JButton();
		moveToCity.setIcon(tempicon);
		moveToCity.setRolloverIcon(new ImageIcon(RIGHT_ARROW_ROLLOVER));
		moveToCity.setBounds(GameWindow.WIDTH - 180, GameWindow.HEIGHT - 160,
				tempicon.getIconWidth(), tempicon.getIconHeight());

		moveToCity.setOpaque(false);
		moveToCity.setIgnoreRepaint(true);
		moveToCity.setBorderPainted(false);
		moveToCity.setContentAreaFilled(false);
		moveToCity.setFocusable(false);

		moveToCity.setActionCommand("MOVETOCITY");
		moveToCity.addActionListener(control);
		this.add(moveToCity);

		this.info = new JLabel("<html>Money: $" + theModel.getFunds()
				+ "<br>Remaining Wagon Capacity: "
				+ fmt.format(theModel.getRemainingWeight()) + " lbs.<br>");
		ItemType[] temp = theModel.getItemMasks();
		for (int k = 0; k < temp.length; k++) {
			info.setText(info.getText() + "<br>" + temp[k].getName() + ": "
					+ theModel.getPurchasable(temp[k]).getCount());
		}
		info.setText(info.getText() + "</html>");
		info.setForeground(Color.WHITE);
		info.setBounds(GameWindow.XOFFSET + 13, GameWindow.YOFFSET + 20, 230,
				190);
		info.setVerticalAlignment(SwingConstants.TOP);
		this.add(info);

		images = new ArrayList<GameImage>();
		images.add(new GameImage(GameWindow.RESOURCEFOLDER + theModel.getBackdropFilename(),
				GameWindow.WIDTH / 2, GameWindow.HEIGHT / 2, 0));
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
	public void updateStatus(String text) {
		this.info.setText("<html>Money: $" + theModel.getFunds()
				+ "<br>Remaining Wagon Capacity: "
				+ fmt.format(theModel.getRemainingWeight()) + " lbs.<br>");
		ItemType[] temp = theModel.getItemMasks();
		for (int k = 0; k < temp.length; k++) {
			info.setText(info.getText() + "<br>" + temp[k].getName() + ": "
					+ theModel.getPurchasable(temp[k]).getCount());
		}
		info.setText(info.getText() + "</html>");
	}
}
