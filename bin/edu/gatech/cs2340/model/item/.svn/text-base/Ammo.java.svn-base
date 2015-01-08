package edu.gatech.cs2340.model.item;

/** Represents a bullet. **/
public class Ammo extends Item {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6936948872071044450L;

	/**
	 * Creates [count] bullets and sets their price to [inprice].
	 * 
	 * @param count
	 *            - the number of bullets to make.
	 * @param inprice
	 */
	public Ammo(double inprice, double inweight) {
		super(inprice, inweight);
	}

	public Ammo clone() {
		Ammo ammo = new Ammo(this.price, this.weight);
		ammo.count = count;
		return ammo;
	}

	public int getCount() {
		return count;
	}
}
