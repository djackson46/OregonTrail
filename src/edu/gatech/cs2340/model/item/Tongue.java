package edu.gatech.cs2340.model.item;

/**
 * A wagon tongue
 * 
 * @author The Jetties
 * 
 */
public class Tongue extends Item {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3921089312738472940L;
	/**
	 * Creates a tongue
	 * 
	 * @param inprice
	 *            the price
	 * @param inweight
	 *            the weight
	 */
	public Tongue(double inprice, double inweight) {
		super(inprice, inweight);
	}
	public Tongue clone() {
		Tongue tongue = new Tongue(this.price, this.weight);
		tongue.count = count;
		return tongue;
	}
}
