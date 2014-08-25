package com.pilgrimspath.data.module;

import com.pilgrimspath.data.Resource;
import com.pilgrimspath.data.ResourceBundle;
import com.pilgrimspath.data.Ship;

public class Workshop extends Module {
	protected static ResourceBundle _buildCost;
	protected static ResourceBundle _demolishReward;
	protected static ResourceBundle _operatingCost;
	protected static ResourceBundle _operatingReward;

	public Workshop(Ship _container) {
		super(_container);
		id = Module.WORKSHOP;
	}

	@Override public int spaceCost() { return 5; }

	@Override public ResourceBundle buildCost() { 
		if (_buildCost == null) {
			_buildCost = new ResourceBundle();
			_buildCost.add(new Resource(Resource.POLYMER_NAME, 40));
			_buildCost.add(new Resource(Resource.METAL_NAME, 8));
		}
		return _buildCost; 
	}

	@Override public ResourceBundle demolishReward() {
		if (_demolishReward == null) {
			_demolishReward = new ResourceBundle();
			_demolishReward.add(new Resource(Resource.POLYMER_NAME, 18));
			_demolishReward.add(new Resource(Resource.METAL_NAME, 3));
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

	@Override public int powerCost() { return 15; }

	@Override public int laborCost() { return 4; }

	@Override public String getName() { return "Workshop"; }
	
	@Override public int buildUnits() { return 80; }
	
	@Override public void onEnable(int count) {
		container.mods.adjustBuildPoints(count * 6);
	}
	
	@Override public void onDisable(int count) {
		container.mods.adjustBuildPoints(-count * 6);
	}
	
}
