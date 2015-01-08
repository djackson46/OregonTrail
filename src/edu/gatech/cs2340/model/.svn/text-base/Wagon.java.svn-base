package edu.gatech.cs2340.model;

/**
 * A wagon.
 * 
 * @author The Jetties
 * 
 */
public class Wagon implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4584115575113167104L;
	private double weight;
	protected int height;
	protected int weightLimit;
	/**
	 * The height of the wagon. Used when fording across a river. The taller it
	 * is, the less likely it is to flood. Could be made different for differnet
	 * types of wagon. Constant for now.
	 */
	private final static int WEIGHTLIMIT = 3500;
	private final static int DEFAULTHEIGHT = 3;

	/**
	 * Wagon constructor with people and item quantities in item
	 * 
	 * @param party
	 *            Human array of people in party
	 * @param quantities
	 *            Quantity of items in wagon for each item
	 */
	public Wagon() {
		this.weight = 0;
		this.height = Wagon.DEFAULTHEIGHT;
		this.weightLimit = WEIGHTLIMIT;
	}

	/**
	 * Checks to see if the wagon can hold the added weight.
	 * 
	 * @param newWeight
	 *            The amount of weight to add.
	 * @return True if the wagon can hold the extra weight
	 */
	public boolean canAddWeight(double newWeight) {
		if (this.weight + newWeight <= this.weightLimit) {
			return true;
		}

		return false;
	}

	/**
	 * Actually adds the weight.
	 * 
	 * @param newWeight
	 *            The amount of weight to add.
	 */
	public void changeWeight(double newWeight) {
		this.weight += newWeight;
	}
	
	public double getWeight()
	{
		return weight;
	}
}
