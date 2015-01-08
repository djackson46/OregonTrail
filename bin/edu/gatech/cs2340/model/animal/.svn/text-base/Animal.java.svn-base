package edu.gatech.cs2340.model.animal;

/**
 * Represents an animal.
 * 
 * @author The Jetties
 * 
 */
public abstract class Animal implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5837461948638241392L;
	/** The health of an animal. **/
	protected int currentHealth, maxHealth;
	protected boolean isdead = false;
	/**
	 * Creates an animal at full health.
	 * 
	 * @param maxhealth
	 *            the maximum health an animal intially has.
	 */
	public Animal(int maxhealth) {
		this.currentHealth = maxHealth = maxhealth;
	}

	/**
	 * Gets the animal's health.
	 * 
	 * @return The animal's health.
	 */
	public int getHealth() {
		return currentHealth;
	}

	/**
	 * Heals an animal by the specified amount.
	 * 
	 * @param HP
	 *            The amount of health to increase by.
	 */
	public void changeHP(int HP) {
		currentHealth += HP;
		if (currentHealth > maxHealth) {
			currentHealth = maxHealth;
		}
		if(currentHealth <= 0) {
			isdead = true;
		}
	}
	
	public boolean isDead()
	{
		return isdead;
	}
}
