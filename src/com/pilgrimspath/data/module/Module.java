package com.pilgrimspath.data.module;

import com.pilgrimspath.data.ResourceBundle;
import com.pilgrimspath.data.Ship;

public class Module {
	public int built = 0;
	public int powered = 0;
	public int staffed = 0;
	public int disabled = 0;	// disabled by the user
	public int lastOperated = 0;
	
	private Ship container;
	private static ResourceBundle _buildCost;
	private static ResourceBundle _demolishReward;
	private static ResourceBundle _operatingCost;
	private static ResourceBundle _operatingReward;
	
	public static final String NAME = "";
	
	public Module(Ship _container) {
		container = _container;
	}
	
	public int spaceCost() { return 0; }
	public ResourceBundle buildCost() { 
		if (_buildCost == null) { _buildCost = new ResourceBundle(); }
		return _buildCost;
	}
	public ResourceBundle demolishReward() {
		if (_demolishReward == null) { _demolishReward = new ResourceBundle(); }
		return _demolishReward;
	}
	public ResourceBundle operatingCost() {
		if (_operatingCost == null) { _operatingCost = new ResourceBundle(); }
		return _operatingCost;
	}
	public ResourceBundle operatingReward() {
		if (_operatingReward == null) { _operatingReward = new ResourceBundle(); }
		return _operatingReward;
	}
	public int powerCost() { return 0; }
	public int laborCost() { return 0; }
	public String getName() { return NAME; }
	
	public int build(int count) {
		// check how many we can build with the space we have
		int maxBuildable = container.mods.checkSpace(count, spaceCost());
		// check how many we can build weth the resources we have
		maxBuildable = container.fleet.resources.checkAvailability(maxBuildable, buildCost());
		
		// build what we can
		// TODO: check whether there is a concurrency issue here
		container.mods.useSpace(maxBuildable, spaceCost());
		container.fleet.resources.remove(maxBuildable, buildCost());
		built += maxBuildable;
		
		return maxBuildable;
	}
	
	public int destroy(int count) {
		int destroyed = (int) Math.min(count, built);
		container.fleet.resources.add(destroyed, demolishReward());
		
		return destroyed;
	}
	
	public void tick() {
		int reserveAmount = built - disabled;
		// try to reserve power for module
		if (powerCost() != 0) { reserveAmount = container.mods.checkPower(reserveAmount, powerCost()); }
		// try to reserve labor for module
		if (laborCost() != 0) { reserveAmount = container.peeps.checkLabor(reserveAmount, laborCost()); }
		// try to reserve resources for production
		if (operatingCost().resources.size() != 0) { 
			reserveAmount = container.fleet.resources.checkAvailability(reserveAmount, operatingCost());
		}
		
		lastOperated = reserveAmount;
		// consume resources
		container.fleet.resources.remove(lastOperated, operatingCost());
		// reward resources
		container.fleet.resources.add(lastOperated, operatingReward());
	}
}
