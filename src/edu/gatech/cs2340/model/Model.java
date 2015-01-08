package edu.gatech.cs2340.model;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.Map;
import java.util.Random;

import javax.swing.JOptionPane;

import edu.gatech.cs2340.model.animal.CartAnimal;
import edu.gatech.cs2340.model.animal.HuntableAnimal;
import edu.gatech.cs2340.model.human.Banker;
import edu.gatech.cs2340.model.human.Carpenter;
import edu.gatech.cs2340.model.human.Farmer;
import edu.gatech.cs2340.model.human.Human;
import edu.gatech.cs2340.model.item.Ammo;
import edu.gatech.cs2340.model.item.Axle;
import edu.gatech.cs2340.model.item.Clothing;
import edu.gatech.cs2340.model.item.Food;
import edu.gatech.cs2340.model.item.Gun;
import edu.gatech.cs2340.model.item.Item;
import edu.gatech.cs2340.model.item.Medicine;
import edu.gatech.cs2340.model.item.Tongue;
import edu.gatech.cs2340.model.item.Wheel;
import edu.gatech.cs2340.model.location.Location;
import edu.gatech.cs2340.model.location.River;
import edu.gatech.cs2340.model.location.Trail;
import edu.gatech.cs2340.view.GameWindow;

/**
 * Wrapper and container class for all model objects
 * 
 * @author The Jetties
 * 
 */
public final class Model implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1343470799923283918L;
	/** MAXPLAYERS: The number of players per party. **/
	public static final byte MAXPLAYERS = 5;
	/** MAXPACE: The maximum pace the player can set. **/
	public static final int MAXPACE = 15;
	/** MAXRATIONS: The maximum rations setting the player can set. **/
	public static final byte MAXRATIONS = 5;
	public static final int FERRYPRICE = 100;

	public static enum FERRYMETHOD {
		FLOAT, FORD, FERRY
	};

	private static double ROBBERY_PROBABILITY = .05;
	private static double WAGON_BROKE_PROBABILITY = .1;
	private static double ITEMSLOSSRATE = .1;
	private static int MAXITEMSTOFIND = 10;
	private static double FOUND_STUFF_PROBABILITY = .15;
	private static double HUMANSICKNESSPROBABILITY = .02;
	private static double OXENDEATHRATE = .015;

	private Wagon wagon;
	private ArrayList<Human> settlers;
	private Store currentStore;

	private static String HUNT_RIFLE_CURSOR = GameWindow.RESOURCEFOLDER
			+ "hunt_rifle_cursor.png";

	private ArrayList<HuntableAnimal> huntableAnimals;
	/**
	 * The current gun, which must be set before the player enters the hunt v/c
	 */
	private Gun currentGun;
	/** The player's items **/
	private EnumMap<ItemType, Purchasable> items;
	private static Random RNG = new Random(System.currentTimeMillis()
			* System.nanoTime());

	private int pace, rations, funds;
	private Traveler baseTraveler;
	private Trail currentTrail;
	/**
	 * The current location of the wagon, excluding Trail. It's null only when
	 * the wagon is currently on the trail. Used in the trail's controller, to
	 * determine, after moving, whether to activate another trail controller or
	 * one of the other location subclasses' controllers.
	 */
	private Location currentLocation;
	private Vector2 currentWayPoint;
	private Vector2 position;
	/**
	 * The total distance the wagon has traveled so far.
	 **/
	private double distanceTraveled;

	/**
	 * This constructor creates the model.
	 */
	public Model() {
		wagon = new Wagon();
		pace = 5;
		// Set the date to some arbitrary date. It'd be nice to make this
		// configurable by the player.
		Date.setup(1852, 3, 2);
		rations = funds = 0;
		huntableAnimals = new ArrayList<HuntableAnimal>(3);
		// Creates a dummy trail. Used for now, until we finalize the trail
		// creation and design.
		// Only initialized this way to allow it to compile.
		baseTraveler = new Traveler();
		this.currentTrail = null;
		this.distanceTraveled = 0;
		settlers = new ArrayList<Human>(MAXPLAYERS);
		/*
		 * for(int i = 0; i < MAXPLAYERS; i++) { createSettler(i, "NAME_" + i,
		 * Human.BANKER); }
		 */
		items = new EnumMap<ItemType, Purchasable>(ItemType.class);
		this.createItems();

		Store.loadStoreData();
		Location.createLocations();
		Trail.createTrails();
		this.setCurrentLocation(Location.getLocation("Independence"));
		this.currentWayPoint = Location.getLocation("Independence")
				.getPosition();
		this.position = new Vector2(this.currentLocation.getX(),
				this.currentLocation.getY());
		currentGun = (Gun) (items.get(ItemType.RIFLE));
		// More object creation here
	}

	// Code to access objects goes here
	/**
	 * Creates the items.
	 */
	private void createItems() {
		this.items.put(ItemType.AMMO, new Ammo(2, 3));
		Item axle = new Axle(10, 125);
		axle.add(2);
		this.items.put(ItemType.AXLE, axle);
		Item clothes = new Clothing(10, 2);
		clothes.add(5);
		this.items.put(ItemType.CLOTHES, clothes);
		this.items.put(ItemType.FOOD, new Food(1, 1, 1));
		this.items.put(ItemType.RIFLE, new Gun(75, 10, 150, 10,
				HUNT_RIFLE_CURSOR, 1, 60));
		this.items.put(ItemType.MEDICINE, new Medicine(30, 4, 30));
		Item tongue = new Tongue(10, 100);
		tongue.add(1);
		this.items.put(ItemType.TONGUE, tongue);
		Item wheel = new Wheel(10, 75);
		wheel.add(4);
		this.items.put(ItemType.WHEEL, wheel);
		CartAnimal ox = new CartAnimal(100, 50, 1f);
		ox.add(1);
		this.items.put(ItemType.OX, ox);
	}

	/**
	 * Gets an object of the requested type from the inventory. Assumes the
	 * inventory only has one value for each member of ItemType.
	 * 
	 * @param type
	 *            The kind of object to look for. A member of the ItemType
	 *            enumeration.
	 * @return The item of the requested type.
	 */
	public Item getItem(ItemType type) {
		Item temp = null;
		try {
			temp = (Item) this.items.get(type);
		} catch (ClassCastException ce) {
			System.err.println("Cast exception in getItem in model");
			System.err.println("ItemType: " + type);
		}
		return temp;
	}

	public Purchasable getPurchasable(ItemType type) {
		return this.items.get(type);
	}

	/**
	 * Makes the store sell [count] items of type [type].
	 * 
	 * @param type
	 *            The key from ItemTypes used to get the right item.
	 * @param count
	 *            The number of items to add.
	 * @return Whether the purchase was completed or not.
	 */
	public boolean sellItem(ItemType type, int count) {
		Purchasable addedItem = this.items.get(type);
		double withdrawal = addedItem.getPrice() * count;
		double newWeight = addedItem.getWeight() * count;
		if (this.funds >= withdrawal && wagon.canAddWeight(newWeight)) {
			addedItem.add(count);
			this.wagon.changeWeight(newWeight);
			this.funds -= withdrawal;
			if(addedItem == this.getPurchasable(ItemType.OX)){
				//The more oxen you have, the smaller the oxen death rate and
				//Less of a chance of wagon parts breaking
				OXENDEATHRATE = .015 * (4/count);
				WAGON_BROKE_PROBABILITY = .1 * (4/count);
			}
			return true;
		}
		return false;
	}

	/**
	 * Creates a settler, with checks for the array's bounds
	 * 
	 * @param position
	 *            - position in array to put settler in
	 * @param name
	 *            - name of settler
	 * @param profession
	 *            - one of Human's profession types
	 */
	public void createSettler(int position, String name, int profession){
		switch (profession) {
		case Human.FARMER:
			settlers.add(position, new Farmer(name));
			funds += settlers.get(position).getStartingFunds();
			break;
		case Human.BANKER:
			settlers.add(position, new Banker(name));
			funds += settlers.get(position).getStartingFunds();
			break;
		case Human.CARPENTER:
			settlers.add(position, new Carpenter(name));
			funds += settlers.get(position).getStartingFunds();
			break;
		case Human.SETTLER:
			settlers.add(position, new Human(name, 20, 0));
			funds += settlers.get(position).getStartingFunds();
			break;
		default:
			System.err.println("Error with settler enums");
			System.exit(-1);
		}
	}

	/**
	 * Distributes the rations to the settlers. If there isn't enough to feed
	 * everyone, the feeding is done in order of health, sickest first.
	 */
	private void feedSettlers() {
		for (int i = 0; i < settlers.size(); i++) {
			settlers.get(i).updateHealth();
		}
		Item food = this.getItem(ItemType.FOOD);
		Human[] theSettlers = settlers.toArray(new Human[settlers.size()]);
		for (int i = 0; i < settlers.size(); i++) {
			theSettlers[i] = settlers.get(i);
		}
		java.util.Arrays.sort(theSettlers);
		boolean haveEnough = true;
		for (int index = 0; index < settlers.size(); index++) {
			int amountGiven = this.rations;
			if (haveEnough) {
				if (food.getCount() < this.rations) {
					amountGiven = food.getCount();
					haveEnough = false;
				}
				food.use(amountGiven);
				this.wagon.changeWeight(-food.getWeight() * amountGiven);
				theSettlers[index].changeHealth(food.getValue() * amountGiven);
			}
		}
		int count = 0;
		while(count < settlers.size())
		{
			if(settlers.get(count).getHealthState() == Human.HUMANSTATE.DEAD)
			{
				settlers.remove(count);
				count--;
			}
			count++;
		}
	}

	private Vector2 getTarget(double distance) {
		Vector2 dPos = null;
		// System.out.println(this.currentTrail);
		// System.out.println(this.currentWayPoint);
		// System.out.println("That was the waypoint");
		// If we're about to pass the current way point,
		if (this.position.vectorTo(this.currentWayPoint).magnitude() < distance) {
			// System.out.println((this.position.vectorTo(this.currentWayPoint).magnitude()
			// < distance)+ "HER");
			// System.out.println(this.position + " " + this.currentWayPoint);
			// System.out.println(this.position.vectorTo(this.currentWayPoint).magnitude()
			// + " " + distance);
			dPos = this.position.vectorTo(this.currentWayPoint);
			Vector2 oldWayPoint = this.currentWayPoint;
			this.currentWayPoint = this.currentTrail.getCurrentWayPoint();
			if (this.currentWayPoint != null) {
				/**Vector2 leftoverMotion = oldWayPoint.vectorTo(this.currentWayPoint);
				leftoverMotion.setMagnitude(Math.min(distance, dPos.magnitude()));
				System.out.println(distance + " " + dPos.magnitude() + "DIST");
				 System.out.println("I managed to move" + (dPos.magnitude()));**/
				Vector2 leftoverMotion = oldWayPoint
						.vectorTo(this.currentWayPoint);
				leftoverMotion.setMagnitude(distance - dPos.magnitude()); 
				// leftoverMotion.magnitude()) + "miles this turn.");
				dPos.x = dPos.x + leftoverMotion.x;
				dPos.y = dPos.y + leftoverMotion.y;
			}
		}
		// otherwise,
		else {
			dPos = this.position.vectorTo(this.currentWayPoint);
			dPos.setMagnitude(distance);
			
			 System.out.println(dPos.magnitude() +" " +distance);
			 System.out.println(dPos.magnitude() - distance + "HEHEHEHE");
			// System.out.println("I'm moving towards a way point.");
			// System.out.println(this.position.vectorTo(this.currentWayPoint).magnitude()
			// + "The distance to thge waypoint");
		}

		return dPos;
	}

	/**
	 * Moves the wagon, increasing distance traveled and determining how far to
	 * go. Will be changed as the trail/movement design changes.
	 */
	private void move() {
		// System.out.println("Beginning a move call.");
		// System.out.println(this.currentTrail.getName() + ": NAME");
		// System.out.println(this.currentTrail.getRemainingLength() +
		// "miles remain.");
		double distance = this.getDaysDistance();
		// If you're not going to finish the trail, just add the distance to
		// the total distance and subtract that from the distance left on the
		// current trail.
		double remainingDistance = currentTrail.getRemainingLength();
		if (distance < remainingDistance) {
			this.distanceTraveled += distance;
			currentTrail.move(distance);
			// System.out.println("I'm moving" + distance + "miles.");
			// System.out.println(this.currentWayPoint+"LOLOL");
			Vector2 dPos = this.getTarget(distance);
			// System.out.println(dPos == null);
			// System.out.println(dPos.x + " " + dPos.y);
			this.position.x = this.position.x + dPos.x;
			this.position.y = this.position.y + dPos.y;
			// System.out.println(this.getCurrentPosition());
			@SuppressWarnings("unused")
			Vector2 indep = new Vector2(74, 200);
			// System.out.println("DISPLACEMENT:" +
			// indep.vectorTo(this.position).magnitude()+ "Claimed:" +
			// this.getDistanceTraveled());
			// System.out.println("LOCATION:" + this.position.x + " " +
			// this.position.y);
		}
		// If the wagon's going to go further than the remaining distance to be
		// traveled along
		// the current trail, just place the wagon at the next location.
		else {
			 System.out.println("I've finished this trail.");
			 System.out.println(this.position.vectorTo(this.currentWayPoint).magnitude());
			this.distanceTraveled += remainingDistance;
			this.currentLocation = this.currentTrail.getDestination();
			this.position = this.currentLocation.getPosition();
			this.currentTrail = null;
		}
	}

	/**
	 * Simply says whether the wagon is currently on the trail or not. Used in
	 * the trail controller to determine whether to show another trail view or
	 * not.
	 * 
	 * @return Whether the wagon's on a trail or not.
	 */
	public boolean onTrail() {
		return this.currentTrail != null;
	}

	/**
	 * Returns the number of miles the wagon's able to travel at the current
	 * time.
	 * 
	 * @return See above.
	 */
	public double getDaysDistance() {
		if (this.items.get(ItemType.AXLE).getCount() == 0
				|| this.items.get(ItemType.TONGUE).getCount() == 0
				|| this.items.get(ItemType.WHEEL).getCount() == 0)
		{
			return 0;
		}
		if(this.getPurchasable(ItemType.OX).getCount() > 4)
		{
			
			return ((Model.MAXPACE - (this.currentTrail.getDifficulty() - 1)) / ((double) Model.MAXPACE)) * this.pace;
		}
		return ((Model.MAXPACE - (this.currentTrail.getDifficulty())) / ((double) Model.MAXPACE)) * this.pace;
	}

	/**
	 * Advances the turn.
	 * 
	 * Responsibilities: -Advance the date -Feed the settlers -Triggering and
	 * handling random events (NOT IMPLEMENTED) -Updating the wagon's position
	 * and the total distance traveled (position put off for now until design is
	 * completed)
	 * 
	 * 
	 */
	public String nextTurn() {
		String event = everyday();
		move();
		return event;
	}

	/**
	 * Advances the turn after a trade.
	 * 
	 * Responsibilities: -Advance the date -Feed the settlers -Triggering and
	 * handling random events (NOT IMPLEMENTED)
	 * 
	 */
	public String everyday() {
		Date.increment();
		feedSettlers();
		feedAnimals();
		return checkForEvents();
	}

	private String checkForEvents() {
		String report = "";
		if (RNG.nextDouble() < Model.ROBBERY_PROBABILITY) {
			String sad_string = "You were robbed! You lost: \n";
			Map<ItemType, Integer> itemsStolen = this.loseItems(ITEMSLOSSRATE);
			for (ItemType type : itemsStolen.keySet()) {
				sad_string = sad_string + type.getName() + ": "
						+ itemsStolen.get(type) + "\n";
			}
			if(!sad_string.equalsIgnoreCase(""))
			{
				report = sad_string;
			}
		}
		else if (RNG.nextDouble() < Model.WAGON_BROKE_PROBABILITY) {
			String sad_string = "";
			ItemType[] wagonTypes = { ItemType.AXLE, ItemType.WHEEL,
					ItemType.TONGUE };
			ItemType broken_part_type = wagonTypes[Model.RNG
					.nextInt(wagonTypes.length)];
			Item broken_part = ((Item) this.items.get(broken_part_type));

			if (broken_part.use(1)) {
				sad_string = sad_string
						+ "Your wagon broke. Luckily, you had a spare "
						+ broken_part_type.getName() + " so everything's fine.";
			} else {

				sad_string = "Your wagon broke. :( You can't move until you get a new "
						+ broken_part_type.getName();
			}
			report = sad_string;

		}
		else if (Model.RNG.nextDouble() <= Model.FOUND_STUFF_PROBABILITY) {
			String happy_string = "As you were rolling along, you stumbled upon the remains of a failed settler party."
					+ " After you threw its inhabitants out, you found some items they left behind. You got: ";
			Map<ItemType, Integer> foundItems = this.findItems(Model.RNG
					.nextInt(MAXITEMSTOFIND));
			for (ItemType type : foundItems.keySet()) {
				happy_string = happy_string + type.getName() + ": "
						+ foundItems.get(type) + "\n";
			}
			report = happy_string;
		}
		for(int i = 0; i < settlers.size(); i++)
		{
			if(RNG.nextDouble() < HUMANSICKNESSPROBABILITY)
			{
				settlers.get(i).setHealthState(true);
			}
		}
		Purchasable ox = items.get(ItemType.OX);
		int oxCount = ox.getCount();
		for(int i = 0; i < oxCount; i++)
		{
			if(RNG.nextDouble() < OXENDEATHRATE)
			{
				ox.add(-1);
			}
		}
		return report;
	}

	private void feedAnimals() {
		;
	}

	public Object crossRiver(FERRYMETHOD method) {
		switch (method) {
		case FLOAT: {
			// Changes to this formula welcome. I just want wagon weight, weight
			// limit, and river depth considered.
			// Flipping should decrease as the wagon diverges from its weight
			// limit and as the river becomes deeper (to make
			// fording and floating different)
			boolean itFlipped = Model.RNG.nextBoolean();
			/**
			 * boolean itFlipped = ((int) ((this.wagon.weight * (Model.rng
			 * .nextDouble() + .5)) / ((this.wagon.weightLimit/2) * ((River)
			 * this.currentLocation) .getDepth()))) > 0;
			 **/
			if (itFlipped) {
				// The deeper the river, the harder to retrieve items.
				return this.loseItems(.40);
				// return this.loseItems(1 - (1.0 / ((River)
				// this.currentLocation)
				// .getDepth()));
			}

			else {
				return new EnumMap<ItemType, Integer>(ItemType.class);
			}
		}
		case FORD: {
			@SuppressWarnings("unused")
			double weightLoad = ((this.wagon.getWeight() * (Model.RNG
					.nextDouble() + .5)) / this.wagon.weightLimit);
			@SuppressWarnings("unused")
			int heightModifier;
			if (this.wagon.height < ((River) this.currentLocation).getDepth()) {
				heightModifier = 2 * (this.wagon.height - ((River) this.currentLocation)
						.getDepth());
			} else {
				heightModifier = 1;
			}
			// boolean itFlooded = ((int) (weightLoad * heightModifier)) > 0;
			boolean itFlooded = Model.RNG.nextBoolean();
			if (itFlooded) {
				return this.loseItems(.40);
				// return this.loseItems(1 - (1.0 / heightModifier));
			} else {
				return new EnumMap<ItemType, Integer>(ItemType.class);
			}
		}
		case FERRY: {
			if (this.funds > Model.FERRYPRICE) {
				System.out
						.println("LOLOL. The player is a pansy. Good thing he doesn't have a console.");
				this.funds = this.funds - Model.FERRYPRICE;
				return true;
			} else {
				return false;
			}
		}
		default: {
			System.err.println("Error in Model.crossRiver : Incorrect ");
			System.exit(-1);
			return null;
		}
		}
	}

	/**
	 * Loses (count) items (used for the river). Assumes cart animals have zero
	 * weight. (Currently always true.)
	 **/
	public Map<ItemType, Integer> loseItems(double probability) {
		ItemType[] types = ItemType.values();
		Map<ItemType, Integer> lostItemCounts = new EnumMap<ItemType, Integer>(
				ItemType.class);
		for (ItemType type : types) {
			lostItemCounts.put(type, 0);
		}
		for (int i = 0; i < types.length; i++) {
			Purchasable tempItem = items.get(types[i]);
			int tempcount = tempItem.getCount();
			if (tempcount > 0) {
				for (int k = 0; k < tempcount; k++) {
					if (RNG.nextDouble() < probability) {
						tempItem.add(-1);
						this.wagon.changeWeight(-tempItem.getWeight());
						lostItemCounts.put(types[i], lostItemCounts
								.get(types[i]) + 1);
					}
				}
			}
		}
		return lostItemCounts;
	}

	public Map<ItemType, Integer> findItems(int count) {
		ItemType[] types = ItemType.values();
		@SuppressWarnings("unused")
		int totalItemCounts = 0;
		Map<ItemType, Integer> foundItemCounts = new EnumMap<ItemType, Integer>(
				ItemType.class);
		for (ItemType type : types) {
			foundItemCounts.put(type, 0);
		}
		int stillToFind = count;
		@SuppressWarnings("unused")
		Collection<Purchasable> items = (Collection<Purchasable>) this.items
				.values();
		while ((stillToFind > 0 && this.wagon.canAddWeight(1))) {
			ItemType type = types[Model.RNG.nextInt(types.length)];
			Purchasable thing = this.items.get(type);
			int numberAdded = Model.RNG.nextInt(Math.min(10, stillToFind) + 1);
			double addedWeight;
			try {
				if (thing instanceof Ammo) {
					addedWeight = (int) (numberAdded);
				} else {
					addedWeight = thing.getWeight() * numberAdded;
				}
				if (this.wagon.canAddWeight(addedWeight)) {
					((Item) thing).add(numberAdded);
					this.wagon.changeWeight(addedWeight);
					stillToFind = stillToFind - numberAdded;
					foundItemCounts.put(type, foundItemCounts.get(type)
							+ numberAdded);
				}
			}
			// Thrown if it isn't an item and thus can't be used.
			catch (ClassCastException e) {
				;
			}
		}
		return foundItemCounts;
	}

	/**
	 * Sets the model's store instance to the instance specified by the
	 * StoreType parameter
	 * 
	 * @param st
	 *            - store instance to retrieve
	 */
	public void setStoreInstance(StoreType st) {
		currentStore = Store.getStoreInstance(st);
	}

	/**
	 * This getter gets the starting x coordinate for a store's item positioning
	 * 
	 * @return the starting x coordinate for a store's item positioning
	 */
	public int getStoreXOffset() {
		return currentStore.XOFFSET;
	}

	/**
	 * This getter gets the starting y coordinate for a store's item positioning
	 * 
	 * @return the starting y coordinate for a store's item positioning
	 */
	public int getStoreYOffset() {
		return currentStore.YOFFSET;
	}

	/**
	 * This getter gets the x spacing between items for a store's item
	 * positioning
	 * 
	 * @return the x spacing between items for a store's item positioning
	 */
	public int getStoreXJump() {
		return currentStore.XJUMP;
	}

	/**
	 * This getter gets the y spacing between items for a store's item
	 * positioning
	 * 
	 * @return the y spacing between items for a store's item positioning
	 */
	public int getStoreYJump() {
		return currentStore.YJUMP;
	}

	/**
	 * This getter gets an array of items purchasable from the current store
	 * 
	 * @return an array of items purchasable from the current store
	 */
	public ItemType[] getItemMasks() {
		return currentStore.getItemMasks();
	}

	/**
	 * This getter gets an array of masks used to turn buttons on and off in the
	 * Store v/c
	 * 
	 * @return an array of masks used to turn buttons on and off in the Store
	 *         v/c
	 */
	public boolean[] getButtonMasks() {
		return currentStore.getButtonMasks();
	}

	/**
	 * This getter gets a string containing the name of the file to be used as a
	 * backdrop for the current store
	 * 
	 * @return a string containing the name of the file to be used as a backdrop
	 *         for the current store
	 */
	public String getBackdropFilename() {
		return currentStore.getBackdropFilename();
	}

	/**
	 * This getter gets an array of strings containing the name(s) of the
	 * file(s) to be used as icons for buttons in the store v/c
	 * 
	 * @return an array of strings containing the name(s) of the file(s) to be
	 *         used as icons for buttons in the store v/c
	 */
	public String[] getButtonFilenames() {
		return currentStore.getButtonFilenames();
	}

	/**
	 * This getter gets an array of strings containing the name(s) of the
	 * file(s) to be used as rollover icons for buttons in the store v/c
	 * 
	 * @return an array of strings containing the name(s) of the file(s) to be
	 *         used as rollover icons for buttons in the store v/c
	 */
	public String[] getButtonRolloverFilenames() {
		return currentStore.getButtonRolloverFilenames();
	}

	/**
	 * Sets the pace of the party
	 * 
	 * @param possiblepace
	 */
	public void setPace(int possiblepace) {
		if (possiblepace < 0) {
			pace = 0;
		} else if (possiblepace > MAXPACE) {
			pace = MAXPACE;
		} else {
			pace = possiblepace;
		}
	}

	/**
	 * This getter gets the current pace
	 * 
	 * @return the current pace
	 */
	public int getPace() {
		return pace;
	}

	/**
	 * Sets the rations for the party. This applies to all settlers
	 * 
	 * @param possiblerations
	 */
	public void setRations(int possiblerations) {
		if (possiblerations < 0) {
			rations = 0;
		} else if (possiblerations > MAXRATIONS) {
			rations = MAXRATIONS;
		} else {
			rations = possiblerations;
		}
	}

	/**
	 * This getter gets the current rations
	 * 
	 * @return the current rations
	 */
	public int getRations() {
		return rations;
	}

	/**
	 * This getter gets the funds.
	 * 
	 * @return funds.
	 */
	public int getFunds() {
		return this.funds;
	}

	/**
	 * This getter gets the number of living settlers.
	 * 
	 * @return number of living settlers.
	 */
	public int getLivingSettlerCount() {
		return this.settlers.size();
	}

	public ArrayList<Human> getSettlers() {
		return this.settlers;
	}

	/**
	 * This getter gets the accuracy of the main settler
	 * 
	 * @return the accuracy of the main settler
	 */

	public double getDistanceTraveled() {
		return this.distanceTraveled;
	}

	/**
	 * This getter gets the number of oxen
	 * 
	 * @return the number of oxen
	 */
	public int getOxenCount() {
		return ((CartAnimal) this.items.get(ItemType.OX)).getCount();
	}

	/**
	 * This getter gets the remaining weight of the wagon
	 * 
	 * @return the remaining weight of the wagon.
	 */
	public double getRemainingWeight() {
		return this.wagon.weightLimit - this.wagon.getWeight();
	}

	/**
	 * This getter gets the settler inaccuracy.
	 * 
	 * @return the settler inaccuracy.
	 */
	public int getSettlerInaccuracy() {
		return settlers.get(0).getInaccuracy();
	}

	/**
	 * Sets the current gun to the gun specified by the ItemType, if the player
	 * owns that particular weapon
	 * 
	 * @param it
	 *            the item type.
	 */
	public void setCurrentGun(ItemType it) {
		currentGun = (Gun) items.get(it);
	}

	/**
	 * This getter gets the current gun's cursor image
	 * 
	 * @return the current gun's cursor image
	 */
	public GameImage getGunCursor() {
		return currentGun.getGunCursor();
	}

	/**
	 * Shoots the current gun, generating a set of points where the bullet or
	 * shots went
	 * 
	 * @return an ArrayList containing references for any images that need to be
	 *         removed from the view
	 */
	public ArrayList<Object> shoot(Point centerPoint) {
		Ammo gunAmmo = (Ammo) this.getPurchasable(ItemType.AMMO);
		if (gunAmmo.getCount() <= 0) {
			JOptionPane.showMessageDialog(null, "Out of Ammo", "Warning",
					JOptionPane.WARNING_MESSAGE);
			return null;
		} else {
			gunAmmo.use(1);
		}
		Vector2[] temp = currentGun.calcShots();
		Point[] points = new Point[temp.length];
		for (int i = 0; i < temp.length; i++) {
			points[i] = new Point();
			points[i].x = (int) temp[i].x;
			points[i].y = (int) temp[i].y;
			points[i].x += centerPoint.x;
			points[i].y += centerPoint.y;
		}
		for (int i = 0; i < huntableAnimals.size(); i++) {
			HuntableAnimal curAnimal = huntableAnimals.get(i);
			Rectangle bounds = curAnimal.getBounds();
			for (int k = 0; k < points.length; k++) {
				if (points[k].x > bounds.x
						&& points[k].x < bounds.x + bounds.width
						&& points[k].y > bounds.y
						&& points[k].y < bounds.y + bounds.height) {
					if (curAnimal.checkCollision(points[k])) {
						curAnimal.changeHP(-(int) currentGun.getPower());
					}
				}
			}
		}
		ArrayList<Object> obArr = new ArrayList<Object>(3);
		int i = 0;
		while (i < huntableAnimals.size()) {
			if (huntableAnimals.get(i).isDead()) {
				Food foo = (Food) this.getPurchasable(ItemType.FOOD);
				foo.add(huntableAnimals.get(i).getFood());
				obArr.add(huntableAnimals.get(i).getImage());
				huntableAnimals.remove(i);
				i--;
			}
			i++;
		}
		return obArr;
	}

	/**
	 * Moves the animals in the hunt v/c
	 * 
	 * @return an ArrayList containing references for any images that need to be
	 *         removed from the view
	 */
	public ArrayList<Object> moveHuntAnimals() {
		for (int i = 0; i < huntableAnimals.size(); i++) {
			huntableAnimals.get(i).move();
		}
		ArrayList<Object> obArr = new ArrayList<Object>(3);
		int i = 0;
		while (i < huntableAnimals.size()) {
			if (huntableAnimals.get(i).isDead()) {
				obArr.add(huntableAnimals.get(i).getImage());
				huntableAnimals.remove(i);
				i--;
			}
			i++;
		}
		return obArr;
	}

	public Location getCurrentLocation() {
		return currentLocation;
	}

	public Vector2 getCurrentPosition() {
		return this.position;
	}

	public Vector2 getCurrentWayPoint() {
		return this.currentWayPoint;
	}

	public void setCurrentLocation(Location inloc) {
		currentLocation = inloc;
	}

	public void setCurrentTrail(Trail trail) {
		currentTrail = trail;
	}

	/**
	 * Reinitializes a traveler object
	 */
	public void createTrade() {
		baseTraveler.createNewTrade();
	}

	public String getTravelerBackdrop() {
		return baseTraveler.getBackdropFilename();
	}

	public String[] getTravelerButtonNames() {
		return baseTraveler.getButtonFilenames();
	}

	public String[] getTravelerRolloverButtonNames() {
		return baseTraveler.getButtonRolloverFilenames();
	}

	public boolean tradeItem() {
		Trade tempTrade = baseTraveler.getCurrentTrade();
		Purchasable requestedItem = this.items.get(tempTrade.getRequestItem());
		int requestedQuantity = tempTrade.getRequestCount();
		int actualQuantity = requestedItem.getCount();
		Purchasable tradeItem = this.items.get(tempTrade.getTradeItem());
		int tradeQuantity = tempTrade.getTradeCount();
		if (actualQuantity >= requestedQuantity) {
			requestedItem.add(-requestedQuantity);
			this.wagon
					.changeWeight(-(requestedItem.getWeight() * requestedQuantity));
			tradeItem.add(tradeQuantity);
			this.wagon.changeWeight(tradeItem.getWeight() * tradeQuantity);
			return true;
		}
		return false;
	}

	public ItemType getItemToGive() {
		return baseTraveler.getCurrentTrade().getTradeItem();
	}

	public ItemType getItemToReceive() {
		return baseTraveler.getCurrentTrade().getRequestItem();
	}

	public void addAnimal(HuntableAnimal ha) {
		huntableAnimals.add(ha);
	}
}
