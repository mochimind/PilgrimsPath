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
		starter.dock.addShuttle(ShuttleStatManager.DIAMONDHEAD, 2);
		starter.mods.rewardModules(Module.BASIC_HOUSING, 2);
		starter.mods.rewardModules(Module.HYDROPONICS, 2);
		starter.mods.rewardModules(Module.POWER_STATION, 2);
		playerFleet.addShip(starter);
		playerFleet.resources.add(new Resource(Resource.POLYMER_NAME, 1000));
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
