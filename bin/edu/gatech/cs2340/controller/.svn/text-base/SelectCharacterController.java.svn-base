package edu.gatech.cs2340.controller;

import java.awt.event.ActionEvent;

import edu.gatech.cs2340.model.Model;
import edu.gatech.cs2340.view.GameWindow;
import edu.gatech.cs2340.view.SelectCharacterView;

/**
 * The controller for selecting the characters.
 * 
 * @author
 * 
 */
public class SelectCharacterController extends AbstractController {
	public final static byte CONFIGURATIONFRAMESTATUS = 0;
	public final static byte BOOLEANLENGTH = 1;

	/**
	 * 
	 * @param model
	 *            the model.
	 */
	public SelectCharacterController(Model model) {
		super(model);
		view = new SelectCharacterView(this);
	}

	/**
	 * updates the screen.
	 */
	public void update() {
		boolean[] status = view.checkStatus();
		if (status[CONFIGURATIONFRAMESTATUS]) {
			String[] characterNames = ((SelectCharacterView) view)
					.getCharacterNames();
			int[] professions = ((SelectCharacterView) view).getProfessions();
			for (int i = 0; i < characterNames.length; i++) {
				characterNames[i] = characterNames[i].trim();
				// Additional checks can be done here, such as removing numbers
				// and symbols from names
				theModel.createSettler(i, characterNames[i], professions[i]);
			}
			theModel.setRations(professions[Model.MAXPLAYERS]);
			theModel.setPace(professions[Model.MAXPLAYERS + 1]);
			GameWindow.setController(new CityController(theModel));
			view.dispose();
		}
		view.drawScreen();
		view.paintScreen();
	}

	/**
	 * Action performed.
	 * 
	 * @param ae
	 *            the action event.
	 */
	public void actionPerformed(ActionEvent ae) {

	}
}
