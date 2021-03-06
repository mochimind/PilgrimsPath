package com.pilgrimspath.data.module;

import com.pilgrimspath.data.ResourceBundle;
import com.pilgrimspath.data.Ship;

public abstract class Module {
	public int built = 0;
	public int powered = 0;
	public int staffed = 0;
	public int resourced = 0;
	public int disabled = 0;	// disabled by the user
	public int damaged = 0;
	public int lastOperated = 0;
	
	protected Ship container;
	public int id;

	// ID for modules. NOTE: sorted by priority
	public static final int POWER_STATION = 1001;
	public static final int POWER_PLANT = 1020;
	public static final int ELECTROLYTER = 1030;
	public static final int FUSION_REACTOR = 1040;
	public static final int ANTIMATTER_FACILITY = 1050;
	
	public static final int CREW_BARRACKS = 2001;
	public static final int QUARTERS = 2010;
	public static final int APARTMENTS = 2020;
	public static final int COMPLEX = 2030;
	public static final int LUXURY_QUARTERS = 2040;

	public static final int HYDROPONICS = 3001;
	public static final int AGRO_PODS = 3010;
	public static final int FOOD_PROCESSOR = 3020;
	public static final int FOOD_VAT = 3030;
	
	public static final int REFINERY = 4001;
	public static final int HEAVY_REFINERY = 4010;
	public static final int FRACKER = 4020;
	
	public static final int POLYMER_FACTORY = 5001;
	public static final int ORE_PROCESSOR = 5010;
	public static final int INDUSTRIAL_FACILITY = 5020;
	
	public static final int PRINT_SHOP = 6001;
	public static final int WORKSHOP = 6010;
	public static final int MANUFACTORY = 6020;
	public static final int FACTORY = 6030;
	public static final int ASSEMBLER = 6040;
	
	public static final int READING_ROOM = 7001;
	public static final int SKUNKWORKS = 7010;
	public static final int SIMULATRON = 7020;
	public static final int PROTOTYPE_CENTER = 7030;
	public static final int CAFE = 7040;
	public static final int FORUM = 7050;
	public static final int ASSEMBLY = 7060;
	public static final int LAB = 7070;
	public static final int OBSERVATORY = 7080;
	public static final int HIGH_ENERGY_LAB = 7090;
	
	public abstract int spaceCost();
	public abstract ResourceBundle buildCost();
	public abstract ResourceBundle demolishReward();
	public abstract ResourceBundle operatingCost();
	public abstract ResourceBundle operatingReward();
	public abstract int powerCost();
	public abstract int laborCost();
	public abstract int buildUnits();
	public abstract String getName();
	
	public Module(Ship _container) {
		container = _container;
		id = 0;
	}

	
	public synchronized int build(int count) {
		// check how many we can build with the space we have
		int maxBuildable = container.mods.checkSpace(count, spaceCost());
		// check how many we can build with the resources we have
		maxBuildable = container.fleet.resources.checkAvailability(maxBuildable, buildCost());
		
		// build what we can
		// TODO: check whether there is a concurrency issue here
		container.mods.useSpace(maxBuildable, spaceCost());
		container.fleet.resources.remove(maxBuildable, buildCost());
		built += maxBuildable;
		
		onEnable(maxBuildable);
		lastOperated += maxBuildable;
		
		return maxBuildable;
	}
	
	// doesn't need any resources to build
	public synchronized int reward(int count) {
		int maxBuildable = container.mods.checkSpace(count, spaceCost());
		container.mods.useSpace(maxBuildable, spaceCost());
		built += maxBuildable;
		onEnable(maxBuildable);
		lastOperated += maxBuildable;
		
		return maxBuildable;
	}
	
	public void onDisable(int count) {}
	public void onEnable(int count) {}
	
	public synchronized int destroy(int count) {
		int destroyed = (int) Math.min(count, built);
		container.fleet.resources.add(destroyed, demolishReward());
		
		built -= destroyed;
		onDisable(destroyed);
		if (disabled > 0) { disabled -= destroyed; }
		if (disabled < 0) {
			lastOperated += disabled;
			disabled = 0;
		}
		if (lastOperated < 0) { lastOperated = 0; }
		return destroyed;
	}
	
	// turns a ship on if there are some disabled, otherwise, builds a new one
	public synchronized void increment(int count) {
		if (disabled != 0) {
			disabled--;
		} else {
			build(1);
		}
	}
	
	// turns a ship off if there are some enabled, otherwise, destroys a ship
	public synchronized void decrement(int count) {
		if (built > disabled) {
			disabled++;
		} else {
			destroy(1);
		}
	}
	
	// TODO: ability to build modules that aren't on the ship yet
	// TODO: One way to implement this is: all ships show all modules that are unlocked
	public void tick() {
		int reserveAmount = built - disabled;
		int iterPowered, iterCrewed, iterResourced;
		// try to reserve power for module
		if (powerCost() != 0) { 
			iterPowered = container.mods.checkPower(reserveAmount, powerCost());
			reserveAmount = (int) Math.min(reserveAmount, iterPowered);
		} else {
			iterPowered = reserveAmount;
		}
		// try to reserve labor for module
		if (laborCost() != 0) { 
			iterCrewed = container.peeps.checkLabor(reserveAmount, laborCost());
			reserveAmount = (int) Math.min(reserveAmount, iterCrewed);
		} else {
			iterCrewed = reserveAmount;
		}
		// try to reserve resources for production
		if (operatingCost().resources.size() != 0) { 
			iterResourced = container.fleet.resources.checkAvailability(reserveAmount, operatingCost());
			reserveAmount = (int) Math.min(reserveAmount, iterResourced);
		} else {
			iterResourced = reserveAmount;
		}
		
		if (lastOperated > reserveAmount) {
			onDisable(lastOperated - reserveAmount);
		} else if (lastOperated < reserveAmount) { 
			onEnable(reserveAmount - lastOperated); 
		}
		
		lastOperated = reserveAmount;
		// consume resources
		container.fleet.resources.remove(lastOperated, operatingCost());
		// reward resources
		container.fleet.resources.add(lastOperated, operatingReward());
		if (laborCost() != 0) { container.peeps.requestLabor(reserveAmount, laborCost()); }
		if (powerCost() != 0) { container.mods.requestPower(reserveAmount, powerCost()); }
		
		// update metrics
		powered = iterPowered;
		staffed = iterCrewed;
		resourced = iterResourced;
	}
}
