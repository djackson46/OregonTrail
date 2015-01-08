package edu.gatech.cs2340.model;

/**
 * A Store
 * 
 * @author The Jetties
 * 
 */
public class Traveler implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4217455100957671204L;
	private String backdropname;
	private String[] jbuttonnames, jbuttonrollovernames;
	private Trade trade;
	
	private static String ACCEPT_TRADE = "resources/accept_trade.png";
	private static String ACCEPT_TRADE_ROLLOVER = "resources/accept_trade_rollover.png";
	private static String REJECT_TRADE = "resources/reject_trade.png";
	private static String REJECT_TRADE_ROLLOVER = "resources/reject_trade_rollover.png";
	private static String TRAVELER_BACKGROUND = "resources/traveler_background.png";

	public Traveler() {
		backdropname = TRAVELER_BACKGROUND;
		jbuttonnames = new String[2];
		jbuttonnames[0] = ACCEPT_TRADE;
		jbuttonnames[1] = REJECT_TRADE;
		jbuttonrollovernames = new String[] {ACCEPT_TRADE_ROLLOVER, REJECT_TRADE_ROLLOVER};
	}

	/**
	 * Sets up a distinct traveler
	 */
	public void createNewTrade() {
		trade = Trade.getRandomTrade();
	}

	/**
	 * This getter gets the current trade.
	 * 
	 * @return the current trade
	 */
	public Trade getCurrentTrade() {
		return trade;
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
	 * This getter gets the jbutton icons' names
	 * 
	 * @return the jbutton icons names
	 */
	public String[] getButtonFilenames() {
		return jbuttonnames;
	}

	/**
	 * This getter gets jbutton rollover names
	 * 
	 * @return jbutton rollover names
	 */
	public String[] getButtonRolloverFilenames() {
		return jbuttonrollovernames;
	}
}
