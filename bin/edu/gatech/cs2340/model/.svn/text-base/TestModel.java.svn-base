package edu.gatech.cs2340.model;

import junit.framework.TestCase;
import edu.gatech.cs2340.model.human.Human;
import edu.gatech.cs2340.model.human.Human.HUMANSTATE;
import edu.gatech.cs2340.model.location.City;
import edu.gatech.cs2340.model.location.Trail;

public class TestModel extends TestCase{

	public void testSellItem(){
		Model testmodel = new Model();
		testmodel.createSettler(0, "Big Money", Human.BANKER);
		
		assertEquals("Item should have been sold.", testmodel.sellItem(ItemType.FOOD, 75), true);
		assertEquals("Item not received correctly.", testmodel.getPurchasable(ItemType.FOOD).getCount(), 75);
		assertEquals("Item should not have been sold", testmodel.sellItem(ItemType.RIFLE, 5), false);
		assertEquals("Item added even though invalid sale", testmodel.getPurchasable(ItemType.RIFLE).getCount(), 0);
		
	}
	
	public void testSetRations(){
		Model testmodel = new Model();
		
		testmodel.setRations(-1);
		assertEquals("Rations defined incorrectly", testmodel.getRations(), 0);
		testmodel.setRations(3);
		assertEquals("Rations defined incorrectly", testmodel.getRations(), 3);
		testmodel.setRations(67);
		assertEquals("Rations defined incorrectly", testmodel.getRations(), 5);
	}
	
	public void testDaysDistance(){
		Model testmodel = new Model();
		testmodel.setCurrentTrail(new Trail(6, new City("Honey"), new City("Babe")));
		assertEquals("Incorrect pace", 3d, testmodel.getDaysDistance());
		testmodel.getPurchasable(ItemType.OX).add(5);
		assertEquals("Incorrect pace", 3.333333d, testmodel.getDaysDistance(), 0.005);
		testmodel.getPurchasable(ItemType.AXLE).add(-2);
		assertEquals("No movement should happen", 0d, testmodel.getDaysDistance());
	}
	
	public void testCreateSettler(){
			Model testmodel = new Model();
			
			testmodel.createSettler(0, "Alex", Human.CARPENTER);
			assertEquals("Carpenter not created correctly", testmodel.getSettlers().get(0).getName(), "Alex");
			assertEquals("Carpenter not created correctly", testmodel.getSettlers().get(0).getStartingFunds(), 160);
		
			testmodel.createSettler(1, "Bob", Human.SETTLER);
			assertEquals("Carpenter not created correctly", testmodel.getSettlers().get(1).getName(), "Bob");
			assertEquals("Carpenter not created correctly", testmodel.getSettlers().get(1).getStartingFunds(), 0);
			
			testmodel.createSettler(2, "Mr. Clean", Human.FARMER);
			assertEquals("Carpenter not created correctly", testmodel.getSettlers().get(2).getName(), "Mr. Clean");
			assertEquals("Carpenter not created correctly", testmodel.getSettlers().get(2).getStartingFunds(), 80);
			
			testmodel.createSettler(3, "Captain Crunch", Human.BANKER);
			assertEquals("Carpenter not created correctly", testmodel.getSettlers().get(3).getName(), "Captain Crunch");
			assertEquals("Carpenter not created correctly", testmodel.getSettlers().get(3).getStartingFunds(), 320);
	}
	
	public void testChangeHealth(){
		Human test = new Human("Kleck", 10, 80);
		test.changeHealth(5);
		assertEquals("Health not clamped properly", test.getHealth(), 100);
		test.changeHealth(-25);
		assertEquals("Health not clamped properly", test.getHealth(), 75);
		test.changeHealth(-200);
		assertEquals("Health not clamped properly", test.getHealthState(), HUMANSTATE.DEAD);
	}
}
