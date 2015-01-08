package edu.gatech.cs2340.controller;

import java.awt.event.ActionEvent;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import javax.swing.JOptionPane;

import edu.gatech.cs2340.model.Model;
import edu.gatech.cs2340.model.location.City;
import edu.gatech.cs2340.model.location.River;
import edu.gatech.cs2340.view.GameWindow;
import edu.gatech.cs2340.view.TrailView;

/**
 * The controller of the trail.
 * 
 * @author The Jetties
 * 
 */
public class TrailController extends AbstractController {
	public final static byte MOVEALONGTRAIL = 0, HUNT = 1, CHANGESETTINGS = 2,
			TRAVELERTRADE = 3, SAVE = 4;
	public final static byte BOOLEANLENGTH = 32;
	private String filename = "saves/savegame.dat";

	/**
	 * The constructor of the controller.
	 * 
	 * @param model
	 *            the model
	 */
	public TrailController(Model model) {
		super(model);
		theModel = model;
		view = new TrailView(this, model);
		status = new boolean[BOOLEANLENGTH];
	}

	/**
	 * updates the view.
	 */
	public void update() {
		view.drawScreen();
		view.paintScreen();
		if (status[MOVEALONGTRAIL]) {
			status[MOVEALONGTRAIL] = false;
			view.dispose();
			String message = theModel.nextTurn();
			
			
			if (theModel.onTrail()) {
				((TrailView) view).updateInfo();
				if (message != "") {
					view.displayMessage(message);
				}
			} else {
				if (theModel.getCurrentLocation() instanceof City) {
					GameWindow.setController(new CityController(theModel));
				} else if (theModel.getCurrentLocation() instanceof River) {
					/**theModel.crossRiver("ferry");
					theModel.setCurrentTrail(theModel.getCurrentLocation()
							.getTrails().get(0));
					theModel.setCurrentLocation(null);**/
					 GameWindow.setController(new RiverController(theModel));
				}
			}
		}
		if (status[HUNT]) {
			status[HUNT] = false;
			view.dispose();
			GameWindow.setController(new HuntController(theModel));
		}
		if (status[CHANGESETTINGS]) {
			status[CHANGESETTINGS] = false;
			view.dispose();
			GameWindow.setController(new SettingsController(theModel));
		}
		if (status[TRAVELERTRADE]) {
			status[TRAVELERTRADE] = false;
			view.dispose();
			GameWindow.setController(new TravelerController(theModel));
		}
		if (status[SAVE]) {
			status[SAVE] = false;
			saveGame(theModel);
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
		if (temp.matches("MOVEALONGTRAIL")) {
			status[MOVEALONGTRAIL] = true;
		} else if (temp.matches("HUNT")) {
			status[HUNT] = true;
		} else if (temp.matches("CHANGESETTINGS")) {
			status[CHANGESETTINGS] = true;
		} else if (temp.matches("TRAVELERTRADE")) {
			status[TRAVELERTRADE] = true;
		} else if (temp.matches("SAVE")) {
			status[SAVE] = true;
		}
	}
	
	public void saveGame(Model model) {
		try {
			FileOutputStream fileOut = new FileOutputStream(filename);
			ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeObject(model);
			objectOut.close();
			JOptionPane.showMessageDialog(null, "Game saved.", "Save Game",
					JOptionPane.INFORMATION_MESSAGE);
			
		}
		catch (Exception e) {
			System.err.println("Unable to save game data");
			e.printStackTrace();
		}
	}
}