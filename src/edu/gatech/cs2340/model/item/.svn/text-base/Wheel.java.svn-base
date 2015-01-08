package edu.gatech.cs2340.model.item;

/**
 * A wheel.
 * 
 * @author The Jetties
 * 
 */
public class Wheel extends Item {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8220878766827161303L;
	/**
	 * Creates a wheel
	 * 
	 * @param inprice
	 *            the price
	 * @param inweight
	 *            the weight
	 */
	public Wheel(double inprice, double inweight) {
		super(inprice, inweight);
	}
	public Wheel clone() {
		Wheel wheel = new Wheel(this.price, this.weight);
		wheel.count = count;
		return wheel;
	}
}
