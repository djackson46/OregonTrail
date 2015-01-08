package edu.gatech.cs2340.controller;

import java.awt.event.ActionEvent;

import edu.gatech.cs2340.model.Model;
import edu.gatech.cs2340.view.GameWindow;
import edu.gatech.cs2340.view.SettingsView;

/**
 * The controller for adjusting settings on the trail.
 * 
 * @author The Jetties
 * 
 */
public class SettingsController extends AbstractController {
	public final static byte RETURNTOTRAIL = 0;
	public final static byte SET = 1;
	public final static byte BOOLEANLENGTH = 32;

	/**
	 * 
	 * @param model
	 *            the model
	 */
	public SettingsController(Model model) {
		super(model);
		view = new SettingsView(this, model);
		status = new boolean[BOOLEANLENGTH];
	}

	/**
	 * updates the view.
	 */
	public void update() {
		view.drawScreen();
		view.paintScreen();
		if (status[RETURNTOTRAIL]) {
			status[RETURNTOTRAIL] = false;
			// Need to have a check whether it arrives at a city/river/etc to
			// handle that correctly (eventually)
			view.dispose();
			GameWindow.setController(new TrailController(theModel));
		} else if (status[SET]) {
			status[SET] = false;
			theModel.setPace(((SettingsView) view).pace.getValue());
			theModel.setRations(((SettingsView) view).rations.getValue());
			view.dispose();
			GameWindow.setController(new TrailController(theModel));
		}
	}

	/**
	 * Action performed
	 * 
	 * @param ae
	 *            the action event.
	 */
	public void actionPerformed(ActionEvent ae) {
		String temp = ae.getActionCommand();
		if (temp.matches("RETURNTOTRAIL")) {
			status[RETURNTOTRAIL] = true;
		}

		if (temp.matches("SET")) {
			status[SET] = true;
		}
	}
}
