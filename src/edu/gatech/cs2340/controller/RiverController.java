package edu.gatech.cs2340.controller;

import java.awt.event.ActionEvent;
import java.util.Map;

import edu.gatech.cs2340.model.ItemType;
import edu.gatech.cs2340.model.Model;
import edu.gatech.cs2340.view.GameWindow;
import edu.gatech.cs2340.view.RiverView;

/**
 * The controller of the river.
 * 
 * @author The Jetties
 * 
 */
public class RiverController extends AbstractController {
	public final static byte FERRY = 0, FORD = 1, FLOAT = 2;
	public final static byte BOOLEANLENGTH = 32;

	/**
	 * The constructor of the controller.
	 * 
	 * @param model
	 *            the model
	 */
	public RiverController(Model model) {
		super(model);
		theModel = model;
		view = new RiverView(this, model);
		status = new boolean[BOOLEANLENGTH];
	}

	/**
	 * updates the view.
	 */
	@SuppressWarnings("unchecked")
	public void update() {
		view.drawScreen();
		view.paintScreen();
		if (status[FERRY]) {
			status[FERRY] = false;
			if (theModel.getFunds() > Model.FERRYPRICE) {
				Object crossed = theModel.crossRiver(Model.FERRYMETHOD.FERRY); 
				if ((Boolean) crossed) {
					view.displayMessage("You paid $" + Model.FERRYPRICE + "to cross the river.");
					view.dispose();
					GameWindow.setController(new TrailController(theModel));
				}
				else {
					view.displayMessage("You need $" + Model.FERRYPRICE + "to use the ferry. You're too poor.");
				}
			}
		}
		if (status[FORD]) {
			status[FORD] = false;
			System.out.println("Controller here. He's fording across like a real settler.");
			
			Object itemsLost = theModel.crossRiver(Model.FERRYMETHOD.FORD);
			String message = "Oh...Bad news. The wagon sank. That's what you get" +
			"for moronically just plowing through a river. You lost:\n";
			if (!((Map<ItemType, Integer>) itemsLost).isEmpty()) {
				for (ItemType type: ((Map<ItemType, Integer>) itemsLost).keySet()) {
					message = message + type.getName() + ": " + ((Map<ItemType, Integer>) itemsLost).get(type) +"\n";
					System.out.println(((Map<ItemType, Integer>) itemsLost).get(type));
				}
				view.displayMessage(message);
			}
			view.dispose();
			GameWindow.setController(new TrailController(theModel));
		}
		if (status[FLOAT]) {
			status[FLOAT] = false;
			Object itemsLost = theModel.crossRiver(Model.FERRYMETHOD.FLOAT);
			if (!((Map<ItemType, Integer>) itemsLost).isEmpty()) {
				String message = "You flipped. You lost: \n";
				for (ItemType type: ((Map<ItemType, Integer>) itemsLost).keySet()) {
					message = message + type.getName() + ": " + ((Map<ItemType, Integer>) itemsLost).get(type) +"\n";
				}
				view.displayMessage(message);
			}
			view.dispose();
			GameWindow.setController(new TrailController(theModel));
		}
		theModel.setCurrentTrail(theModel.getCurrentLocation().getTrails().get(0));
	}

	/**
	 * Action Performed
	 * 
	 * @param ae
	 *            the action event.
	 */
	public void actionPerformed(ActionEvent ae) {
		String temp = ae.getActionCommand();
		if (temp.matches("FERRY")) {
			status[FERRY] = true;
		} else if (temp.matches("FORD")) {
			status[FORD] = true;
		} else if (temp.matches("FLOAT")) {
			status[FLOAT] = true;
		}
	}
}