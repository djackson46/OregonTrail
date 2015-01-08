package edu.gatech.cs2340.controller;

/**
 * The controller that rules them all.
 * @author Jon
 */
//AbstractController shouldn't depend on any view except the abstract one.
import java.awt.event.ActionListener;

import edu.gatech.cs2340.model.Model;
import edu.gatech.cs2340.view.AbstractView;

//It's going to need a reference to the gameWindow so it can change the window's
//active controller before it gets deleted.
//That's accomplished by calling GameWindow.setController(AbstractController)
/** Represents a controller. (Pointless comment, I know.) **/
public abstract class AbstractController implements ActionListener {
	AbstractView view;
	Model theModel;
	// What kinds of values can this take on? Does it represent whether it's on
	// or off?
	// If so, perhaps it'd be better named "isOn"?
	boolean[] status;

	/**
	 * Creates the controller attached to the given model.
	 * 
	 * @param model
	 *            The model to control.
	 **/
	public AbstractController(Model model) {
		this.theModel = model;
	}

	/**
	 * updates the view.
	 */
	public abstract void update();

	/**
	 * Gets the view.
	 * 
	 * @return The view.
	 */
	public AbstractView getView() {
		return this.view;
	}
}
