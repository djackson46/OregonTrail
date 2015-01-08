package edu.gatech.cs2340.model.item;

/**
 * Represents a thing of medicine. When used, raises the player's health. (This
 * is questionable. Need to come back once we have more time.
 * 
 * @author The Jetties
 * 
 */
public class Medicine extends Item {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7039114414460216932L;
	/** Value is the amount of HP the particular thing of medicine heals. **/
	private int value;

	/**
	 * Creates a package of medicine. Can be used count times before it's empty.
	 * 
	 * @param inprice
	 *            the price
	 * @param inweight
	 *            the weight
	 * @param value
	 *            the value of medicine
	 */
	public Medicine(double inprice, double inweight, int value) {
		super(inprice, inweight);
		this.value = value;
	}

	/**
	 * This getter gets the value of the medicine.
	 * 
	 * @return the value
	 */
	public int getValue() {
		return this.value;
	}
	
	public Medicine clone() {
		Medicine meds = new Medicine(this.price, this.weight, this.value);
		meds.count = count;
		return meds;
	}
}
