package edu.gatech.cs2340.model.location;

import java.util.ArrayList;
import java.util.HashMap;

import edu.gatech.cs2340.model.Vector2;

/**
 * Represents a generic location.
 * 
 * @author The Jetties
 * 
 */
public abstract class Location implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4966202837368262075L;
	protected String name;
	protected static HashMap<String, Location> locations;
	protected ArrayList<Trail> trails;
	private Vector2 position;

	/**
	 * Creates the location.
	 * 
	 * @param name
	 *            The location's name.
	 */
	public Location(String name) {
		this(name, 0, 0);
	}

	public Location(String name, int x, int y) {
		this.name = name;
		this.position = new Vector2(x, y);
		this.trails = new ArrayList<Trail>();
	}

	/**
	 * Gets the area's name.
	 * 
	 * @return The location's name.
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Creates the location of the towns.
	 */
	public static void createLocations() {
		if (Location.locations == null) {
			Location.locations = new HashMap<String, Location>();
			Location.locations.put("Independence",  
					new City("Independence", 74, 200));
			
			Location.locations.put("Courthouse Rock", 
					new City("Courthouse Rock", 206, 257));
			
			Location.locations.put("Blue River", 
					new River("Blue River", 8, 278, 283));
			
			Location.locations.put("Chimmney Rock", 
					new City("Chimmney Rock", 306, 295));
			
			Location.locations.put("Independence Rock", 
					new City("Independence Rock", 420, 352));
			
			Location.locations.put("Salt Lake", 
					new River("Salt Lake", 4, 476, 350));
			
			Location.locations.put("Fort Bridger", 
					new City("Fort Bridger", 531, 322));
			
			Location.locations.put("Soda Springs", 
					new City("Soda Springs", 551, 379));
			
			Location.locations.put("Fort Boise",
					new City("Fort Boise", 675, 434));
			
			Location.locations.put("Colorado River", 
					new River("Colorado River", 2, 699, 483));
			
			Location.locations.put("Whitman Mission",
					new City("Whitman Mission", 695, 556));
			
			Location.locations.put("Dalles", 
					new City("Dalles", 764, 547));
			
			Location.locations.put("Oregon City", 
					new City("Oregon City", 816, 569));
		}
	}

	/**
	 * This getter gets the trails
	 * 
	 * @return the trails
	 */
	public ArrayList<Trail> getTrails() {
		return this.trails;
	}

	/**
	 * This getter gets the location of a place
	 * 
	 * @param name
	 *            the name of a place
	 * @return the location
	 */
	public static Location getLocation(String name) {
		return Location.locations.get(name);
	}

	public double getX() {
		return this.position.x;
	}

	public double getY() {
		return this.position.y;
	}

	public Vector2 getPosition() {
		return this.position;
	}

	public String toString() {
		return this.name;
	}
}
