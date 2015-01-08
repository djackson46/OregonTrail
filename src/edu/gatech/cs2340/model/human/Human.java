package edu.gatech.cs2340.model.human;

/**
 * Represents a human.
 * 
 * @author The Jetties
 */
public class Human implements Comparable<Object>,java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3602512708293501454L;
	/**
	 * Health is expressed in the range from 0 to 100. At 0, the person is dead.
	 **/
	protected int health;
	/** Their name and careers. **/
	protected String name, career;
	/** Whether the Settler is clothed **/
	protected boolean clothed;
	protected int inaccuracy;
	protected int profession = SETTLER;
	protected int startingFunds;
	public final static int SETTLER = 0, BANKER = 1, CARPENTER = 2, FARMER = 3;
	public final static int ENCROACHINGDEATH = 3, SICKNESSSTRENGTH = 20;
	public static enum HUMANSTATE{WELL, SICK, DEAD};
	private HUMANSTATE state = HUMANSTATE.WELL;
	/**
	 * Creates a human.
	 * 
	 * @param name
	 *            - their name.
	 */
	public Human(String name, int inaccuracy, int startingmoney) {
		this.name = name;
		this.health = 100;
		this.clothed = false;
		career = "Settler";
		this.inaccuracy = inaccuracy;
		startingFunds = startingmoney;
	}

	/**
	 * Returns the player's health.
	 * 
	 * @return The player's health as an integer from 0 to 100.
	 **/
	public int getHealth() {
		return this.health;
	}

	/**
	 * Sets the player's health.
	 * 
	 * @param changeHealth
	 *            - the player's health as an integer from 0 to 100.
	 **/
	public void changeHealth(int changeHealth) {
		health += changeHealth;
		if (health < 0) {
			state = HUMANSTATE.DEAD;
		} else if (health > 100) {
			health = 100;
		}
	}
	/**
	 * Updates the human's health
	 */
	public void updateHealth()
	{
		if(state == HUMANSTATE.SICK)
		{
			changeHealth(-ENCROACHINGDEATH - SICKNESSSTRENGTH);
		}
		else
		{
			changeHealth(-ENCROACHINGDEATH);
		}
		
	}

	/**
	 * Gets the human's name.
	 * 
	 * @return The name.
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Returns the settler's inaccuracy
	 * 
	 * @return
	 */
	public int getInaccuracy() {
		return inaccuracy;
	}

	/**
	 * This getter gets the starting funds.
	 * 
	 * @return the starting funds
	 */
	public int getStartingFunds() {
		return startingFunds;
	}

	/**
	 * Sets whether the human is sick
	 * @param ISSICK
	 */
	public void setHealthState(boolean isSick)
	{
		if(isSick)
		{
			state = HUMANSTATE.SICK;
		}
		else
		{
			state = HUMANSTATE.WELL;
		}
	}
	
	/**
	 * Returns the 
	 * @return
	 */
	public HUMANSTATE getHealthState()
	{
		return state;
	}
	/**
	 * Compares objects like humans.
	 * 
	 * @param obj
	 *            an object
	 * @return an int whether the human passed in is healthier than health.
	 */
	public int compareTo(Object obj) {
		Human hu = (Human) obj;
		if (health < hu.getHealth()) {
			return -1;
		}
		if (health > hu.getHealth()) {
			return 1;
		}
		return 0;
	}
}
