package com.pilgrimspath.data.module;

import com.pilgrimspath.data.Resource;
import com.pilgrimspath.data.ResourceBundle;
import com.pilgrimspath.data.Ship;

public class Electrolyter extends Module {
	protected static ResourceBundle _buildCost;
	protected static ResourceBundle _demolishReward;
	protected static ResourceBundle _operatingCost;
	protected static ResourceBundle _operatingReward;

	public Electrolyter(Ship _container) {
		super(_container);
		id = Module.ELECTROLYTER;
	}

	@Override public int spaceCost() { return 2; }

	@Override public ResourceBundle buildCost() { 
		if (_buildCost == null) {
			_buildCost = new ResourceBundle();
			_buildCost.add(new Resource(Resource.POLYMER_NAME, 16));
			_buildCost.add(new Resource(Resource.METAL_NAME, 4));
		}
		return _buildCost; 
	}

	@Override public ResourceBundle demolishReward() {
		if (_demolishReward == null) {
			_demolishReward = new ResourceBundle();
			_demolishReward.add(new Resource(Resource.POLYMER_NAME, 7));
			_demolishReward.add(new Resource(Resource.METAL_NAME, 2));
		}
		return _demolishReward;
	}

	@Override public ResourceBundle operatingCost() {
		if (_operatingCost == null) {
			_operatingCost = new ResourceBundle();
			_operatingCost.add(new Resource(Resource.FUEL_NAME, 2));
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

	@Override public int laborCost() { return 3; }

	@Override public String getName() { return "Electrolyter"; }
	
	@Override public int buildUnits() { return 48; }
	
	@Override public void onEnable(int count) {
		container.mods.adjustPower(25 * count);
	}
	
	@Override public void onDisable(int count) {
		container.mods.adjustPower(-25 * count);
	}
}
