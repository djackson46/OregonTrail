package edu.gatech.cs2340.controller;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import edu.gatech.cs2340.model.Model;
import edu.gatech.cs2340.view.GameWindow;
import edu.gatech.cs2340.view.TravelerView;

public class TravelerController extends AbstractController {
	public final static byte ACCEPTTRADE = 0, REJECTTRADE = 1;
	public final static byte BOOLEANLENGTH = 32;

	/**
	 * The constructor of the controller.
	 * 
	 * @param model
	 *            the model
	 */
	public TravelerController(Model model) {
		super(model);
		theModel.createTrade();
		view = new TravelerView(this, model);
		status = new boolean[BOOLEANLENGTH];
	}

	/**
	 * updates the view.
	 */
	public void update() {
		view.drawScreen();
		view.paintScreen();
		if (status[ACCEPTTRADE]) {
			status[ACCEPTTRADE] = false;
			view.dispose();
			boolean temp = theModel.tradeItem();
			if (!temp) {
				JOptionPane.showMessageDialog(null, "Sorry, you do not have the supplies needed for this trade.");
			}
			theModel.everyday();
			GameWindow.setController(new TrailController(theModel));
		}
		if (status[REJECTTRADE]) {
			status[REJECTTRADE] = false;
			theModel.everyday();
			view.dispose();
			GameWindow.setController(new TrailController(theModel));
		}
	}

	/**
	 * Action Performed
	 * 
	 * @param ae
	 *            the action event.
	 */
	public void actionPerformed(ActionEvent ae) {
		String temp = ae.getActionCommand();
		if (temp.matches("ACCEPTTRADE")) {
			status[ACCEPTTRADE] = true;
		} else if (temp.matches("REJECTTRADE")) {
			status[REJECTTRADE] = true;
		}
	}
}
