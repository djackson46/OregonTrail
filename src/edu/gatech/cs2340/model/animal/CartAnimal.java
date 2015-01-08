package edu.gatech.cs2340.model.animal;

import edu.gatech.cs2340.model.Purchasable;

/**
 * Represents an ox.
 * 
 * @author The Jetties
 * 
 */
public class CartAnimal extends Animal implements Purchasable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9122367145147398649L;
	private int weight;
	private int price;
	private int count;
	private float pullingStrength;

	/**
	 * Creates animal that will pull a cart
	 * 
	 * @param health
	 *            - the maximum health of the animal
	 * @param inprice
	 *            - the price of the animal
	 */
	public CartAnimal(int health, int inprice, float strength) {
		super(health);
		this.weight = 0;
		this.price = inprice;
		this.count = 0;
		this.pullingStrength = strength;
	}

	/**
	 * This getter gets the ox's weight
	 * 
	 * @return the weight
	 */
	public double getWeight() {
		return this.weight;
	}

	/**
	 * This getter gets the price of an ox
	 * 
	 * @return the price of an ox
	 */
	public double getPrice() {
		return this.price;
	}

	/**
	 * Increments the total number of ox.
	 * 
	 * @param count
	 *            the total number of ox
	 */
	public void add(int count) {
		this.count = this.count + count;
	}

	/**
	 * This getter gets the total ox.
	 * 
	 * @return the total number of ox
	 */
	public int getCount() {
		return this.count;
	}

	/**
	 * Returns the animal's strength
	 * 
	 * @return strength
	 */
	public float getStrength() {
		return pullingStrength;
	}
	
	public CartAnimal clone() {
		CartAnimal animal = new CartAnimal(this.price, 
				this.weight, this.pullingStrength);
		animal.count = count;
		return animal;
	}
}