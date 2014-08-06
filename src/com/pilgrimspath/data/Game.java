package com.pilgrimspath.data;

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
		playerFleet.addShip(starter);
		starter.dock.addShuttle(ShuttleStatManager.DIAMONDHEAD, 2);
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
