package edu.gatech.cs2340.model.item;

/**
 * Creates a piece of food.
 * 
 * @author The Jetties
 */
public class Food extends Item {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1824316941670264229L;
	/** How much HP to heal. **/
	private int value;

	/**
	 * Creates food information holder
	 * 
	 * @param count The number of pieces of food to create.
	 */
	public Food(double inprice, double inweight, int value) {
		super(inprice, inweight);
		this.value = value;
	}

	/**
	 * @return the amount of HP one Food heals.
	 * 
	 */
	public int getValue() {
		return this.value;
	}
	
	public Food clone() {
		Food food = new Food(this.price, this.weight, this.value);
		food.count = count;
		return food;
	}
}
