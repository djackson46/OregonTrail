package edu.gatech.cs2340.model.item;

import edu.gatech.cs2340.model.Purchasable;

/**
 * Represents a collection of items. Probably should rename the uncountable
 * items to collection names. (E.g. foodPile)
 * 
 * @author The Jetties
 * 
 */
public abstract class Item implements Purchasable, java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6593111256968268074L;
	/** The number of items left in the collection. **/
	protected int count;
	protected double price, weight;

	/**
	 * This constructor creates an item.
	 * 
	 * @param inprice
	 *            the price of the item
	 * @param inweight
	 *            the weight of the item
	 */
	public Item(double inprice, double inweight) {
		this.count = 0;
		this.weight = inweight;
		this.price = inprice;
	}

	/**
	 * Should only be called if there are any items left. If there are not
	 * enough, none are used.
	 * 
	 * @return Whether there were enough or not.
	 */
	public boolean use(int number) {
		if (this.count >= number) {
			this.count = this.count - number;
			return true;
		}
		return false;
	}

	/**
	 * Adds count new items to the collection.
	 * 
	 * @param count
	 *            The number of new items to add to the collection.
	 */
	public void add(int count) {
		this.count = this.count + count;
	}

	/**
	 * Make count read-only for external classes. (This allows the store to be
	 * able to tell the user how many there are.
	 * 
	 * @return the count
	 */
	public int getCount() {
		return count;
	}

	/**
	 * This getter gets the price of an item.
	 * 
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * This getter gets the weight of an item.
	 * 
	 * @return the weight.
	 */
	public double getWeight() {
		return weight;
	}

	/**
	 * Intended to be used for items like food and medicine. Allows their values
	 * to be exposed without having to cast to their actual types.
	 * 
	 * @return The value of one of the item.
	 */
	public int getValue() {
		return 0;
	}
	public abstract Item clone();
}
