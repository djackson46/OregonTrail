package edu.gatech.cs2340.model;

/**
 * Represents a trade
 * 
 * @author Jon
 * 
 */
public class Trade {
	private ItemType tradeItem, requestItem;
	private int tradeCount, requestCount;

	private Trade(ItemType trade, ItemType request, int tradecount,
			int requestcount) {
		tradeItem = trade;
		requestItem = request;
		tradeCount = tradecount;
		requestCount = requestcount;
	}

	public static Trade getRandomTrade() {
		float rando = (float) Math.random();

		if (rando < .1) {
			return new Trade(ItemType.WHEEL, ItemType.FOOD, 1, 50);
		} else if (rando < .2) {
			return new Trade(ItemType.AXLE, ItemType.FOOD, 1, 50);
		} else if (rando < .3) {
			return new Trade(ItemType.TONGUE, ItemType.FOOD, 1, 50);
		} else if (rando < .4) {
			return new Trade(ItemType.MEDICINE, ItemType.FOOD, 1, 20);
		} else if (rando < .5) {
			return new Trade(ItemType.AMMO, ItemType.CLOTHES, 10, 1);
		} else if (rando < .6) {
			return new Trade(ItemType.CLOTHES, ItemType.FOOD, 1, 20);
		} else if (rando < .7) {
			return new Trade(ItemType.AXLE, ItemType.WHEEL, 1, 1);
		} else if (rando < .8) {
			return new Trade(ItemType.WHEEL, ItemType.TONGUE, 1, 1);
		} else if (rando < .9) {
			return new Trade(ItemType.TONGUE, ItemType.AXLE, 1, 1);
		} else {
			return new Trade(ItemType.AMMO, ItemType.FOOD, 1, 5);
		}
	}

	/**
	 * Returns the ItemType representing the item to give
	 * 
	 * @return
	 */
	public ItemType getTradeItem() {
		return tradeItem;
	}

	/**
	 * Returns the ItemType representing the item that the trader wants
	 * 
	 * @return
	 */
	public ItemType getRequestItem() {
		return requestItem;
	}

	/**
	 * Returns the number of items of ItemType to be given
	 * 
	 */
	public int getTradeCount() {
		return tradeCount;
	}

	/**
	 * Returns the number of items of ItemType that the trader wants
	 * 
	 * @return
	 */
	public int getRequestCount() {
		return requestCount;
	}
}
