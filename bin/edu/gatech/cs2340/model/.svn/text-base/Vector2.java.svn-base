package edu.gatech.cs2340.model;

/**
 * Class similar to Point, with double representation and basic functionality
 * 
 * @author Jon
 */
public class Vector2 implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -508456083166623039L;
	public double x, y;

	/**
	 * This constructor sets the vector2 to the origin.
	 */
	public Vector2() {
		x = 0;
		y = 0;
	}

	/**
	 * This constructor sets the vector2 to the paramaters
	 * 
	 * @param x
	 *            the x coordinate
	 * @param y
	 *            the y coordinate
	 */
	public Vector2(double x, double y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Magnitude of vector2
	 * 
	 * @return the magnitude of the vector2
	 */
	public double magnitude() {
		return Math.sqrt(x * x + y * y);
	}

	/**
	 * 
	 * @return the angle of the vector2
	 */
	public double angle() {
		return Math.atan2(y, x);
	}

	public Vector2 vectorTo(Vector2 destination) {
		Vector2 dPos = new Vector2(destination.x - this.x, destination.y
				- this.y);
		return dPos;
	}

	public Vector2 vectorTo(double x, double y) {
		Vector2 dPos = new Vector2(x - this.x, y - this.y);
		return dPos;
	}

	/**
	 * Sets the magnitude
	 * 
	 * @param length
	 *            the length of the vector2
	 */
	public void setMagnitude(double length) {
		double curlength = Math.sqrt(x * x + y * y);
		x *= length / curlength;
		y *= length / curlength;
	}

	public String toString() {
		return "Vector(" + this.x + ", " + this.y + ")";
	}
}
