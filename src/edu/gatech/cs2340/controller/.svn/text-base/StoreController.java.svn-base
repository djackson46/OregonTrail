package edu.gatech.cs2340.controller;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import edu.gatech.cs2340.model.ItemType;
import edu.gatech.cs2340.model.Model;
import edu.gatech.cs2340.model.StoreType;
import edu.gatech.cs2340.view.GameWindow;
import edu.gatech.cs2340.view.StoreView;

/**
 * The controller of stores.
 * 
 * @author The Jetties
 * 
 */
public class StoreController extends AbstractController {
	// These should start at 10
	public final static byte MOVETOCITY = 10;
	public final static byte BOOLEANLENGTH = 32;
	private boolean[] masks;
	private ItemType[] itemtypes;

	/**
	 * 
	 * @param model
	 *            the model
	 * @param storenum
	 *            the number of the store
	 */
	public StoreController(Model model, StoreType storenum) {
		super(model);
		theModel.setStoreInstance(storenum);
		view = new StoreView(this, storenum, theModel);
		masks = theModel.getButtonMasks();
		status = new boolean[BOOLEANLENGTH];
		itemtypes = theModel.getItemMasks();
	}

	/**
	 * updates the view.
	 */
	public void update() {
		view.drawScreen();
		view.paintScreen();
		if (status[MOVETOCITY]) {
			GameWindow.setController(new CityController(theModel));
			status[MOVETOCITY] = false;
		} 
		else {
			for (int i = 0; i < theModel.getButtonFilenames().length; i++) {
				if (status[i]) {
					if (masks[i]) {
						String count = JOptionPane.showInputDialog(itemtypes[i].getName() + "\nCost: $" + theModel.getPurchasable(itemtypes[i]).getPrice()
								+ "\nWeight: " + theModel.getPurchasable(itemtypes[i]).getWeight() + " lbs.\nHow many do you want to buy?", 1);
						int quantity = 0;
						try{
								quantity = Integer.parseInt(count);
						} catch(NumberFormatException e){
							if(quantity > 0)
								JOptionPane.showMessageDialog(null, "Not a valid input.");
						}
						boolean sold = theModel.sellItem(itemtypes[i], quantity);
						if (!sold) {
							if (theModel.getRemainingWeight() < quantity*theModel.getPurchasable(itemtypes[i]).getWeight()) {
								view.displayMessage("Exceeded wagon capacity.");
							} else {
								view.displayMessage("Not enough money.");
							}
						}
						((StoreView) view).updateStatus(null);
					}
					status[i] = false;
				}
			}
		}
	}

	/**
	 * Action Performed.
	 * 
	 * @param ae
	 *            the action event.
	 */
	public void actionPerformed(ActionEvent ae) {
		String temp = ae.getActionCommand();
		if (temp.matches("MOVETOCITY")) {
			status[MOVETOCITY] = true;
		} else {
			for (int i = 0; i < theModel.getButtonFilenames().length; i++) {
				if (temp.matches("BUTTON_" + i)) {
					status[i] = true;
				}
			}
		}

	}
}