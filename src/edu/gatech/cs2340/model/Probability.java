package edu.gatech.cs2340.model;

/**
 * Thinking class. With complimentary thinking grenades. Something along these
 * lines should be used to categorize probabilities of events that happen every
 * day. Should either be a c style data structure, or have some kind of
 * probability affecting function that makes the game harder or easier based on
 * a number of variables (such as various stocks of items, including food,
 * medicine, and the current state of settlers) in order to dynamically vary the
 * difficulty
 * 
 * @author Jon
 * 
 */
public class Probability {
	public float[] conditionProb;
	public final static byte RAIN = 0, SNOW = 1;

	Probability() {
		conditionProb = new float[3];
		conditionProb[RAIN] = .2f;
		conditionProb[SNOW] = .05f;
		// so on
	}
}
