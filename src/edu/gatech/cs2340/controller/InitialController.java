package edu.gatech.cs2340.controller;

/**
 * The first controller in the game.
 * @author Jon
 */

import java.awt.event.ActionEvent;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

import javax.swing.JOptionPane;

import edu.gatech.cs2340.model.Model;
import edu.gatech.cs2340.view.GameWindow;
import edu.gatech.cs2340.view.InitialView;

public class InitialController extends AbstractController {
	private Object selectedValue;
	private Object[] possibleValues = {"New Game", "Load Game"};
	private String filename = "saves/savegame.dat";

	/**
	 * Constructor of the controller.
	 * 
	 * @param model
	 *            The model.
	 */
	public InitialController(Model model) {
		super(model);
		view = new InitialView(this);
	}

	/**
	 * Updates the screen.
	 */
	public void update() {
		if (GameWindow.count > 119) {
			selectedValue = (String) JOptionPane.showInputDialog(null, "Choose one:",
				"Start Game", JOptionPane.INFORMATION_MESSAGE, null, possibleValues,
				possibleValues[0]);
		}
		if (selectedValue == "New Game") {
			GameWindow.setController(new SelectCharacterController(theModel));
			return;			
		}
		if (selectedValue == "Load Game") {
			loadGame(theModel);
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
	
	public void loadGame(Model model) {
		try {
			FileInputStream fileIn = new FileInputStream(filename);
			@SuppressWarnings("resource")
			ObjectInputStream objectIn = new ObjectInputStream(fileIn);
			model = (Model) objectIn.readObject();
			GameWindow.setController(new TrailController(model));
		}
		catch (Exception e) {
			e.printStackTrace();
			System.err.println ("Unable to load game data");
		}
	}
}