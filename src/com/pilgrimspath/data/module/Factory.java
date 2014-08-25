package com.pilgrimspath.data.module;

import com.pilgrimspath.data.Resource;
import com.pilgrimspath.data.ResourceBundle;
import com.pilgrimspath.data.Ship;

public class Factory extends Module {
	protected static ResourceBundle _buildCost;
	protected static ResourceBundle _demolishReward;
	protected static ResourceBundle _operatingCost;
	protected static ResourceBundle _operatingReward;

	public Factory(Ship _container) {
		super(_container);
		id = Module.FACTORY;
	}

	@Override public int spaceCost() { return 10; }

	@Override public ResourceBundle buildCost() { 
		if (_buildCost == null) {
			_buildCost = new ResourceBundle();
			_buildCost.add(new Resource(Resource.POLYMER_NAME, 77));
			_buildCost.add(new Resource(Resource.METAL_NAME, 15));
			_buildCost.add(new Resource(Resource.ALLOYS_NAME, 3));
		}
		return _buildCost; 
	}

	@Override public ResourceBundle demolishReward() {
		if (_demolishReward == null) {
			_demolishReward = new ResourceBundle();
			_demolishReward.add(new Resource(Resource.POLYMER_NAME, 35));
			_demolishReward.add(new Resource(Resource.METAL_NAME, 7));
			_demolishReward.add(new Resource(Resource.ALLOYS_NAME, 1));
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

	@Override public int powerCost() { return 65; }

	@Override public int laborCost() { return 30; }

	@Override public String getName() { return "Factory"; }
	
	@Override public int buildUnits() { return 250; }
	
	@Override public void onEnable(int count) {
		container.mods.adjustBuildPoints(count * 40);
	}
	
	@Override public void onDisable(int count) {
		container.mods.adjustBuildPoints(-count * 40);
	}
}
