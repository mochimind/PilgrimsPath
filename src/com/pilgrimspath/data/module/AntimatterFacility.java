package com.pilgrimspath.data.module;

import com.pilgrimspath.data.Resource;
import com.pilgrimspath.data.ResourceBundle;
import com.pilgrimspath.data.Ship;

public class AntimatterFacility extends Module {
	protected static ResourceBundle _buildCost;
	protected static ResourceBundle _demolishReward;
	protected static ResourceBundle _operatingCost;
	protected static ResourceBundle _operatingReward;

	public AntimatterFacility(Ship _container) {
		super(_container);
		id = Module.ANTIMATTER_FACILITY;
	}

	@Override public int spaceCost() { return 14; }

	@Override public ResourceBundle buildCost() { 
		if (_buildCost == null) {
			_buildCost = new ResourceBundle();
			_buildCost.add(new Resource(Resource.POLYMER_NAME, 90));
			_buildCost.add(new Resource(Resource.METAL_NAME, 20));
			_buildCost.add(new Resource(Resource.ALLOYS_NAME, 10));
		}
		return _buildCost; 
	}

	@Override public ResourceBundle demolishReward() {
		if (_demolishReward == null) {
			_demolishReward = new ResourceBundle();
			_demolishReward.add(new Resource(Resource.POLYMER_NAME, 40));
			_demolishReward.add(new Resource(Resource.METAL_NAME, 8));
			_demolishReward.add(new Resource(Resource.METAL_NAME, 4));
		}
		return _demolishReward;
	}

	@Override public ResourceBundle operatingCost() {
		if (_operatingCost == null) {
			_operatingCost = new ResourceBundle();
			_operatingCost.add(new Resource(Resource.FUEL_NAME, 8));
		}
		return _operatingCost;
	}

	@Override public ResourceBundle operatingReward() {
		if (_operatingReward == null) {
			_operatingReward = new ResourceBundle();
		}
		return _operatingReward;
	}

	@Override public int powerCost() { return 0; }

	@Override public int laborCost() { return 12; }

	@Override public String getName() { return "Antimatter Facility"; }
	
	@Override public int buildUnits() { return 550; }

	@Override public void onEnable(int count) {
		container.mods.adjustPower(200 * count);
	}
	
	@Override public void onDisable(int count) {
		container.mods.adjustPower(-200 * count);
	}
	
}
