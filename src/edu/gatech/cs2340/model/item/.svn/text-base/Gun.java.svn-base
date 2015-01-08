package edu.gatech.cs2340.model.item;

import java.util.Random;

import edu.gatech.cs2340.model.GameImage;
import edu.gatech.cs2340.model.Vector2;

/**
 * Represents a gun.
 * 
 * @author The Jetties
 * 
 */
public class Gun extends Item {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3351759436560536320L;
	private float spread;
	private Random rand;
	/**
	 * Represents the general power of the gun. 1.0 would indicate it's a 1-hit
	 * kill
	 **/
	private float power;
	private int shotsPerRound;
	private String cursorName;
	private int recoilTime;

	/**
	 * This constructor creates a gun.
	 * 
	 * @param inprice
	 *            The price of the gun.
	 * @param inweight
	 *            The weight of the gun.
	 * @param inpower
	 *            The power of the gun.
	 * @param inspread
	 *            The spread of the gunshot.
	 * @param cursorname
	 *            The name of the type of cursor of the gun.
	 * @param shots
	 *            The shots in the gun.
	 * @param recoil
	 *            The recoil of the gun.
	 */
	public Gun(double inprice, double inweight, float inpower, float inspread,
			String cursorname, int shots, int recoil) {
		super(inprice, inweight);
		this.spread = inspread;
		this.power = inpower;
		this.shotsPerRound = shots;
		cursorName = cursorname;
		rand = new Random(System.nanoTime());
		recoilTime = recoil;
	}

	/**
	 * This constructor creates a gun.
	 * 
	 * @param inprice
	 *            The price of the gun.
	 * @param inweight
	 *            The weight of the gun.
	 * @param inpower
	 *            The power of the gun.
	 * @param inspread
	 *            The spread of the gunshot.
	 * @param cursorname
	 *            The name of the type of cursor of the gun.
	 */
	public Gun(int inprice, int inweight, float inpower, float inspread,
			String cursorname) {
		this(inprice, inweight, inpower, inspread, cursorname, 1, 45);
	}

	/**
	 * 
	 * @return The amount of damage the gun does to its target, if any.
	 */
	public float getPower() {
		return power;
	}

	/**
	 * Calculates where the shots will hit around the point (0,0)
	 * 
	 * @return a vector of shots.
	 */
	public Vector2[] calcShots() {
		Vector2[] shots = new Vector2[shotsPerRound];
		for (int i = 0; i < shotsPerRound; i++) {
			double magnitude = rand.nextDouble();
			magnitude = spread
					* Math.sqrt(Math.sqrt(magnitude * magnitude * magnitude));
			double angle = rand.nextDouble() * Math.PI * 2;
			Vector2 shot = new Vector2();
			shot.x = Math.cos(angle) * magnitude;
			shot.y = Math.sin(angle) * magnitude;
			shots[i] = shot;
		}
		return shots;
	}

	/**
	 * Returns the gun's cursor image
	 * 
	 * @return the cursor image.
	 */
	public GameImage getGunCursor() {
		return new GameImage(cursorName, 0, 0, 10);
	}
	
	public Gun clone() {
		Gun gun = new Gun(this.price, this.weight, this.power,
				this.spread, this.cursorName, this.shotsPerRound, this.recoilTime);
		gun.rand = rand;
		return gun;
	}
}
