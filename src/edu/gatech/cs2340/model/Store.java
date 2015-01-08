package edu.gatech.cs2340.model;

import java.util.HashMap;

import edu.gatech.cs2340.view.GameWindow;

/**
 * A Store
 * 
 * @author The Jetties
 * 
 */
public class Store implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4267080740604550314L;
	private String backdropname;
	private String[] jbuttoniconnames, jbuttonrollovernames;
	private boolean[] buttonmasks;
	private ItemType[] itemmasks;
	public final int XOFFSET, YOFFSET, XJUMP, YJUMP;
	private static HashMap<StoreType, Store> storedata;

	/**
	 * This constructor creates a store
	 * 
	 * @param xoff
	 *            the x offset
	 * @param yoff
	 *            the y offset
	 * @param xjmp
	 *            the x jump
	 * @param yjmp
	 *            the y jump
	 */
	public Store(int xoff, int yoff, int xjmp, int yjmp) {
		XOFFSET = xoff;
		YOFFSET = yoff;
		XJUMP = xjmp;
		YJUMP = yjmp;
	}

	/**
	 * Initializes the StoreData collection. This, and other loaders like it,
	 * must be called as early as possible, in the model constructor, which is
	 * called before any v/c s are constructed
	 * 
	 * @param masks
	 *            the buttons
	 */
	public void setButtonMasks(boolean[] masks) {
		buttonmasks = masks;
	}

	/**
	 * This getter gets the button masks
	 * 
	 * @return the button masks
	 */
	public boolean[] getButtonMasks() {
		return buttonmasks;
	}

	/**
	 * This setter sets the items to itemmasks
	 * 
	 * @param masks
	 *            an array of items
	 */
	public void setItemMasks(ItemType[] masks) {
		itemmasks = masks;
	}

	/**
	 * This getter gets the itemmasks
	 * 
	 * @return the item masks
	 */
	public ItemType[] getItemMasks() {
		return itemmasks;
	}

	/**
	 * This setter sets the backdrop of the store.
	 * 
	 * @param backdrop
	 *            the backdrop of the store
	 */
	public void setBackdropFilename(String backdrop) {
		backdropname = backdrop;
	}

	/**
	 * This getter gets the name of the file of the backdrop.
	 * 
	 * @return the backdrop name
	 */
	public String getBackdropFilename() {
		return backdropname;
	}

	/**
	 * This setter sets the button file's name
	 * 
	 * @param instring
	 *            this is the button name
	 */
	public void setButtonFilenames(String[] instring) {
		jbuttoniconnames = instring;
	}

	/**
	 * This getter gets the jbutton icons' names
	 * 
	 * @return the jbutton icons names
	 */
	public String[] getButtonFilenames() {
		return jbuttoniconnames;
	}

	/**
	 * This setter sets the string array to jbutton rollovers
	 * 
	 * @param instring
	 *            the names of the jbutton rollovers
	 */
	public void setButtonRolloverFilenames(String[] instring) {
		jbuttonrollovernames = instring;
	}

	/**
	 * This getter gets jbutton rollover names
	 * 
	 * @return jbutton rollover names
	 */
	public String[] getButtonRolloverFilenames() {
		return jbuttonrollovernames;
	}

	/**
	 * loads the store properties
	 */
	public static void loadStoreData() {
		storedata = new HashMap<StoreType, Store>();

		Store temp = new Store(225, 215, 255, 230);
		temp.setBackdropFilename("store_background.png");
		String[] buttonnames = { "item_axle.png", "item_tongue.png",
				"item_wheel.png", "item_clothes.png", "item_food.png",
				"item_medicine.png", "item_rifle.png", "item_ammo.png" };
		temp.setButtonFilenames(buttonnames);
		String[] buttonrollovernames = { "item_axle_rollover.png",
				"item_tongue_rollover.png", "item_wheel_rollover.png",
				"item_clothes_rollover.png", "item_food_rollover.png",
				"item_medicine_rollover.png", "item_rifle_rollover.png",
				"item_ammo_rollover.png" };
		temp.setButtonRolloverFilenames(buttonrollovernames);
		ItemType[] itemtypes = new ItemType[8];
		itemtypes[0] = ItemType.AXLE;
		itemtypes[1] = ItemType.TONGUE;
		itemtypes[2] = ItemType.WHEEL;
		itemtypes[3] = ItemType.CLOTHES;
		itemtypes[4] = ItemType.FOOD;
		itemtypes[5] = ItemType.MEDICINE;
		itemtypes[6] = ItemType.RIFLE;
		itemtypes[7] = ItemType.AMMO;
		temp.setItemMasks(itemtypes);
		boolean[] masks = new boolean[32];
		masks[0] = masks[1] = masks[2] = masks[3] = masks[4] = masks[5] = masks[6] = masks[7] = true;
		temp.setButtonMasks(masks);
		storedata.put(StoreType.STORE, temp);
		
		temp = new Store(GameWindow.XOFFSET + 270, GameWindow.YOFFSET + 600,
				390, 300);
		temp.setBackdropFilename("stable_background.png");
		String[] stableButtonnames = { "stable_ox.png"};
		temp.setButtonFilenames(stableButtonnames);
		String[] stableRolloverNames = { "stable_ox_rollover.png"};
		temp.setButtonRolloverFilenames(stableRolloverNames);
		ItemType[] stableItemtypes = new ItemType[1];
		stableItemtypes[0] = ItemType.OX;
		temp.setItemMasks(stableItemtypes);
		boolean[] stableMasks = new boolean[32];
		stableMasks[0] = true;
		temp.setButtonMasks(stableMasks);
		storedata.put(StoreType.STABLE, temp);
	}

	/**
	 * Returns a particular Store instance
	 * 
	 * @param st
	 *            the type of store
	 * @return the store data
	 */
	public static Store getStoreInstance(StoreType st) {
		return storedata.get(st);
	}
}