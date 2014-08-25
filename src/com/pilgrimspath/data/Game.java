package com.pilgrimspath.data;

import com.pilgrimspath.data.module.Module;
import com.pilgrimspath.data.shuttle.ShuttleStatManager;

/*
 * This class stores information about a current game
 */
public class Game {
	public static Fleet playerFleet;
	private static boolean initialized = false;
	
	// creates a new game
	public static void StartNewGame() {
		playerFleet = new Fleet();
		Ship starter = new Ship(playerFleet, "Noobmobile", "Noobmobile");
		starter.dock.awardShuttle(ShuttleStatManager.DIAMONDHEAD, 2);
		starter.mods.rewardModules(Module.CREW_BARRACKS, 4);
		starter.mods.rewardModules(Module.HYDROPONICS, 2);
		starter.mods.rewardModules(Module.POWER_STATION, 4);
		starter.mods.rewardModules(Module.REFINERY, 1);
		starter.mods.rewardModules(Module.POLYMER_FACTORY, 1);
		starter.peeps.adjustPopulation(30);
		playerFleet.addShip(starter);
		playerFleet.resources.add(new Resource(Resource.POLYMER_NAME, 200));
		playerFleet.resources.add(new Resource(Resource.FUEL_NAME, 500));
		playerFleet.resources.add(new Resource(Resource.FOOD_NAME, 500));
		playerFleet.resources.add(new Resource(Resource.ALLOYS_NAME, 25));
		Thread t = new Thread(new Ticker());
		t.start();
	}
	
	// loads from save file
	public static boolean LoadGame(String saveName) {
		return false;
	}
	
	public static boolean SaveGame(String saveName) {
		return false;
	}
	
	public static void Tick() {
		if (!initialized) { return; }
		playerFleet.tick();
	}
}
