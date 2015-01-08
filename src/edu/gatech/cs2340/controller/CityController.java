package edu.gatech.cs2340.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.gatech.cs2340.model.Model;
import edu.gatech.cs2340.model.StoreType;
import edu.gatech.cs2340.view.CityView;
import edu.gatech.cs2340.view.GameWindow;

/**
 * The controller than manages the game while inside a city.
 * 
 * @author The Jetties
 */
public class CityController extends AbstractController implements
		ActionListener {
	// State constants. These are specific to this view/controller
	/** Indicates that the player is going back onto the trail. **/
	public final static byte MOVETOTRAIL = 0;
	/** Indicates the player is entering a store. **/
	public final static byte MOVETOSTORE = 1;
	/** Indicates a player is entering a stable. **/
	public final static byte MOVETOSTABLE = 2;
	/** Constant used to define the max number of states. **/
	public final static byte BOOLEANLENGTH = 32;

	/**
	 * The constructor of the controller.
	 * 
	 * @param model
	 *            The model of the city.
	 */
	public CityController(Model model) {
		super(model);
		view = new CityView(this);
		status = new boolean[BOOLEANLENGTH];
	}

	public String getCityName() {
		return theModel.getCurrentLocation().getName();
	}

	public boolean trailRemaining() {
		if (theModel.getCurrentLocation().getTrails().size() == 0) {
			return false;
		}
		return true;
	}

	/**
	 * Updates the view.
	 */
	public void update() {
		view.drawScreen();
		view.paintScreen();
		if (status[MOVETOTRAIL]) {
			status[MOVETOTRAIL] = false;
			view.dispose();
			// Should make a pop-up allowing the player to choose. I just didn't
			// want to make it.

			theModel.setCurrentTrail(theModel.getCurrentLocation().getTrails()
					.get(0));
			theModel.setCurrentLocation(null);
			GameWindow.setController(new TrailController(theModel));
		}
		if (status[MOVETOSTORE]) {
			status[MOVETOSTORE] = false;
			view.dispose();
			GameWindow.setController(new StoreController(theModel,
					StoreType.STORE));
		}
		if (status[MOVETOSTABLE]) {
			status[MOVETOSTABLE] = false;
			view.dispose();
			GameWindow.setController(new StoreController(theModel,
					StoreType.STABLE));
		}
	}

	/**
	 * Actions Performed in the City.
	 * 
	 * @param ae
	 *            The action event.
	 */
	public void actionPerformed(ActionEvent ae) {
		String temp = ae.getActionCommand();
		if (temp.matches("MOVETOTRAIL")) {
			status[MOVETOTRAIL] = true;
		} else if (temp.matches("MOVETOSTORE")) {
			status[MOVETOSTORE] = true;
		} else if (temp.matches("MOVETOSTABLE")) {
			status[MOVETOSTABLE] = true;
		}
	}
}
