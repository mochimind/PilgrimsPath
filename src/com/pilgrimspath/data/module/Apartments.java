package com.pilgrimspath.data.module;

import com.pilgrimspath.data.Resource;
import com.pilgrimspath.data.ResourceBundle;
import com.pilgrimspath.data.Ship;

public class Apartments extends Module {
	protected static ResourceBundle _buildCost;
	protected static ResourceBundle _demolishReward;
	protected static ResourceBundle _operatingCost;
	protected static ResourceBundle _operatingReward;

	public Apartments(Ship _container) {
		super(_container);
		id = Module.APARTMENTS;
	}

	@Override public int spaceCost() { return 1; }

	@Override public ResourceBundle buildCost() { 
		if (_buildCost == null) {
			_buildCost = new ResourceBundle();
			_buildCost.add(new Resource(Resource.POLYMER_NAME, 8));
			_buildCost.add(new Resource(Resource.METAL_NAME, 1));
		}
		return _buildCost; 
	}

	@Override public ResourceBundle demolishReward() {
		if (_demolishReward == null) {
			_demolishReward = new ResourceBundle();
			_demolishReward.add(new Resource(Resource.POLYMER_NAME, 3));
		}
		return _demolishReward;
	}

	@Override public ResourceBundle operatingCost() {
		if (_operatingCost == null) {
			_operatingCost = new ResourceBundle();
		}
		return _operatingCost;
	}

	@Override public ResourceBundle operatingReward() {
		if (_operatingReward == null) {
			_operatingReward = new ResourceBundle();
		}
		return _operatingReward;
	}

	@Override public int powerCost() { return 2; }

	@Override public int laborCost() { return 0; }

	@Override public String getName() { return "Apartments"; }
	
	@Override public int buildUnits() { return 10; }
	
	@Override public void onEnable(int count) {
		container.mods.adjustBuildPoints(count);
	}
	
	@Override public void onDisable(int count) {
		container.mods.adjustBuildPoints(-count);
	}
	
}
