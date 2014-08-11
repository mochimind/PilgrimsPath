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
	protected int id;
	
	protected static ResourceBundle _buildCost;
	protected static ResourceBundle _demolishReward;
	protected static ResourceBundle _operatingCost;
	protected static ResourceBundle _operatingReward;
	
	public static final int BASIC_HOUSING = 31000;
	public static final int HYDROPONICS = 31010;
	public static final int POLYMER_FACTORY = 31020;
	public static final int POWER_STATION = 31030;
	public static final int REFINERY = 31040;
	
	public Module(Ship _container) {
		container = _container;
		id = 0;
	}
	
	public abstract int spaceCost();
	public abstract ResourceBundle buildCost();
	public abstract ResourceBundle demolishReward();
	public abstract ResourceBundle operatingCost();
	public abstract ResourceBundle operatingReward();
	public abstract int powerCost();
	public abstract int laborCost();
	public abstract String getName(); 
	
	public int build(int count) {
		// check how many we can build with the space we have
		int maxBuildable = container.mods.checkSpace(count, spaceCost());
		// check how many we can build with the resources we have
		maxBuildable = container.fleet.resources.checkAvailability(maxBuildable, buildCost());
		
		// build what we can
		// TODO: check whether there is a concurrency issue here
		container.mods.useSpace(maxBuildable, spaceCost());
		container.fleet.resources.remove(maxBuildable, buildCost());
		built += maxBuildable;
		
		onBuild(maxBuildable);
		
		return maxBuildable;
	}
	
	// doesn't need any resources to build
	public int reward(int count) {
		int maxBuildable = container.mods.checkSpace(count, spaceCost());
		container.mods.useSpace(maxBuildable, spaceCost());
		built += maxBuildable;
		onBuild(maxBuildable);
		return maxBuildable;
	}
	
	public void onBuild(int count) {}
	public void onDestroy(int count) {}
	
	public int destroy(int count) {
		int destroyed = (int) Math.min(count, built);
		container.fleet.resources.add(destroyed, demolishReward());
		
		onDestroy(destroyed);
		return destroyed;
	}
	
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
		
		lastOperated = reserveAmount;
		// consume resources
		container.fleet.resources.remove(lastOperated, operatingCost());
		// reward resources
		container.fleet.resources.add(lastOperated, operatingReward());
		
		// update metrics
		powered = iterPowered;
		staffed = iterCrewed;
		resourced = iterResourced;
	}
}
