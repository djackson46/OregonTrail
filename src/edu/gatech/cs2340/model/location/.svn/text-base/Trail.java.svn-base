package edu.gatech.cs2340.model.location;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

import edu.gatech.cs2340.model.Vector2;

/**
 * Represents a trail.
 * 
 * @author The Jetties
 */
public class Trail extends Location {
	/**
	 * 
	 */
	private static ArrayList<Trail> trails = new ArrayList<Trail>();
	private static final long serialVersionUID = -5114990438949820862L;
	private int difficulty;
	private Location start;
	private Location destination;
	private double lengthLeft;
	private Deque<Vector2> waypoints;

	/**
	 * Creates a trail with the given name, climate, and difficulty.
	 * 
	 * @param name
	 *            The name of its source and destination, in the form
	 *            StartName-DestName
	 * @param difficulty
	 *            The difficulty of the trail, in the range [0, 10]
	 */
	public Trail(int difficulty, Location start, Location destination) {
		super(start.getName() + "-" + destination.getName());
		this.difficulty = difficulty;
		this.start = start;
		this.start.trails.add(this);
		this.destination = destination;
		this.lengthLeft = 0;
		this.waypoints = new LinkedList<Vector2>();
		Trail.trails.add(this);
	}
	
	public Trail(int difficulty, String startName, String targetName) {
		this(difficulty, Location.getLocation(startName), Location.getLocation(targetName));
	}

	/**
	 * Creates the trails.
	 */
	public static void createTrails() {
		Trail indepCourt = new Trail(2, "Independence", "Courthouse Rock");
		indepCourt.addWayPoint(50, -6);
		indepCourt.addWayPoint(72, 26);
		indepCourt.addWayPoint(132, 57);
		new Trail(6, "Courthouse Rock", "Blue River");
		new Trail(8, "Blue River", "Chimmney Rock");
		new Trail(9, "Chimmney Rock", "Independence Rock");
		new Trail(7, "Independence Rock", "Salt Lake");
		new Trail(6,  "Salt Lake", "Fort Bridger");
		new Trail(10, "Fort Bridger", "Soda Springs");
		new Trail(6, "Soda Springs", "Fort Boise");
		new Trail(4, "Fort Boise", "Colorado River");
		new Trail(7, "Colorado River", "Whitman Mission");
		new Trail(8, "Whitman Mission", "Dalles");
		new Trail(10, "Dalles", "Oregon City");
		for (Trail trail : Trail.trails) {
			trail.finish();
		}
	}
	/**
	 * Returns the trail's difficulty
	 * 
	 * @return
	 */
	public int getDifficulty() {
		return difficulty;
	}

	/**
	 * Returns the remaining distance on the trail
	 * 
	 * @return
	 */
	public double getRemainingLength() {
		return lengthLeft;
	}

	/** Adds a waypoint with displacement dx, dy from the start. **/
	public void addWayPoint(int dx, int dy) {
		Vector2 previousWayPoint;
		Vector2 newWayPoint = new Vector2(this.start.getX() + dx, this.start.getY() + dy);
		if (!this.waypoints.isEmpty()) {
			previousWayPoint = this.waypoints.getLast();
		}
		else {
			previousWayPoint = this.start.getPosition();
		}
		this.lengthLeft = this.lengthLeft + 
		previousWayPoint.vectorTo(newWayPoint).magnitude();
		this.waypoints.add(newWayPoint);
	}
	
	public void finish() {
		Vector2 lastStretch = this.start.getPosition().vectorTo(
				this.destination.getPosition());
		this.addWayPoint((int) lastStretch.x, (int) lastStretch.y);
	}

	/**
	 * Returns the destination location
	 * 
	 * @return This is an example of stupid javadocing.
	 */
	public Location getDestination() {
		return destination;
	}

	public Vector2 getCurrentWayPoint() {
		if (!this.waypoints.isEmpty()) {
			return this.waypoints.poll();
		}
		else {
			return this.destination.getPosition();
		}
	}

	/**
	 * Changes the distance along the trail
	 * 
	 * @param dist
	 */
	public void move(double dist) {
		lengthLeft -= dist;
	}
}
