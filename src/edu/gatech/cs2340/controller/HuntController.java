package edu.gatech.cs2340.controller;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Random;

import edu.gatech.cs2340.model.Model;
import edu.gatech.cs2340.model.Vector2;
import edu.gatech.cs2340.model.animal.HuntableAnimal;
import edu.gatech.cs2340.view.GameWindow;
import edu.gatech.cs2340.view.HuntView;

/**
 * The controller for hunting while on the trail.
 * 
 * @author The Jetties
 * 
 */
public class HuntController extends AbstractController implements
		MouseListener, MouseMotionListener {
	public final static byte MOVETOTRAIL = 0, FIRE = 1;
	public final static byte BOOLEANLENGTH = 32;
	public final static short TIMEOUT = 900;
	private final static int AVERAGETIME = 240;
	private int nextAnimalLoadTime;
	private int startTime;
	private Point currentPoint, fakePoint;
	private Vector2 sightVelVec, sightFakeVec;
	private Random rand;

	/**
	 * This constructor manages the hunting in the game.
	 * 
	 * @param model - The model.
	 */
	public HuntController(Model model) {
		super(model);
		view = new HuntView(this, model);
		status = new boolean[BOOLEANLENGTH];
		currentPoint = new Point(0, 0);
		fakePoint = new Point(0, 0);
		rand = new Random(System.currentTimeMillis()*System.nanoTime());
		sightVelVec = new Vector2(.035 + .04 * rand.nextDouble(), .035 + .04 * 
				rand.nextDouble());
		sightFakeVec = new Vector2();
		startTime = GameWindow.count;
		nextAnimalLoadTime = (int)(AVERAGETIME * (1.5 * rand.nextDouble() + .1)) + startTime;
	}

	/**
	 * Updates the view.
	 */
	public void update() {
		sightVelVec.x += .0015 * (rand.nextDouble() - .5);
		sightVelVec.y += .0015 * (rand.nextDouble() - .5);
		if (sightVelVec.magnitude() > .075) {
			sightVelVec.setMagnitude(.074);
		}
		if (sightVelVec.magnitude() < .035) {
			sightVelVec.setMagnitude(.036);
		}
		sightFakeVec.x += sightVelVec.x;
		sightFakeVec.y += sightVelVec.y;
		fakePoint.x = currentPoint.x
				+ (int) (theModel.getSettlerInaccuracy() * Math
						.cos(sightFakeVec.x));
		fakePoint.y = currentPoint.y
				+ (int) (theModel.getSettlerInaccuracy() * Math
						.sin(sightFakeVec.y));
		((HuntView) view).setState(fakePoint);
		ArrayList<Object> temp = theModel.moveHuntAnimals();
		if(temp != null)
		{
			((HuntView) view).removeImages(temp);
		}
		if(GameWindow.count > nextAnimalLoadTime)
		{
			nextAnimalLoadTime += (int)(AVERAGETIME * (1.5 * rand.nextDouble() + .1));
			HuntableAnimal ha = HuntableAnimal.getNewAnimal();
			theModel.addAnimal(ha);
			((HuntView) view).addImage(ha.getImage());
		}
		view.drawScreen();
		view.paintScreen();
		if (status[MOVETOTRAIL] || (GameWindow.count - startTime) > TIMEOUT) {
			status[MOVETOTRAIL] = false;
			view.dispose();
			GameWindow.setController(new TrailController(theModel));
		}
		if (status[FIRE]) {
			temp = theModel.shoot(fakePoint);
			if(temp != null)
			{
				((HuntView) view).removeImages(temp);
			}
			status[FIRE] = false;
		}
	}

	/**
	 * Lets the player go back to the trail.
	 * 
	 * @param ae
	 *            The action event.
	 */
	public void actionPerformed(ActionEvent ae) {
		String temp = ae.getActionCommand();
		if (temp.matches("MOVETOTRAIL")) {
			status[MOVETOTRAIL] = true;
		}
	}

	/**
	 * The mouse is clicked.
	 * 
	 * @param arg0
	 *            the mouse event.
	 */
	public void mouseClicked(MouseEvent arg0) {

	}

	/**
	 * The mouse has entered.
	 * 
	 * @param arg0
	 *            the mouse event.
	 */
	public void mouseEntered(MouseEvent arg0) {

	}

	/**
	 * The mouse has exited.
	 * 
	 * @param arg0
	 *            the mouse event.
	 */
	public void mouseExited(MouseEvent arg0) {

	}

	/**
	 * The gun will fire if the mouse is pressed.
	 * 
	 * @param arg0
	 *            the mouse event.
	 */
	public void mousePressed(MouseEvent arg0) {
		status[FIRE] = true;
	}

	/**
	 * The gun is not firing.
	 * 
	 * @param arg0
	 *            the mouse event.
	 */
	public void mouseReleased(MouseEvent arg0) {

	}

	/**
	 * The gun cursor moves where the mouse is dragged.
	 * 
	 * @param arg0
	 *            the mouse event.
	 */
	public void mouseDragged(MouseEvent arg0) {
		currentPoint = arg0.getPoint();
		arg0.consume();
	}

	/**
	 * The gun cursor moves where the mouse is dragged.
	 * 
	 * @param arg0
	 *            the mouse event.
	 */
	public void mouseMoved(MouseEvent arg0) {
		currentPoint = arg0.getPoint();
		arg0.consume();
	}
}
