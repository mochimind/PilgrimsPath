package com.pilgrimspath.data.module;

import com.pilgrimspath.data.Resource;
import com.pilgrimspath.data.ResourceBundle;
import com.pilgrimspath.data.Ship;

public class FusionReactor extends Module {
	protected static ResourceBundle _buildCost;
	protected static ResourceBundle _demolishReward;
	protected static ResourceBundle _operatingCost;
	protected static ResourceBundle _operatingReward;

	public FusionReactor(Ship _container) {
		super(_container);
		id = Module.FUSION_REACTOR;
	}

	@Override public int spaceCost() { return 8; }

	@Override public ResourceBundle buildCost() { 
		if (_buildCost == null) {
			_buildCost = new ResourceBundle();
			_buildCost.add(new Resource(Resource.POLYMER_NAME, 55));
			_buildCost.add(new Resource(Resource.METAL_NAME, 10));
			_buildCost.add(new Resource(Resource.ALLOYS_NAME, 2));
		}
		return _buildCost; 
	}

	@Override public ResourceBundle demolishReward() {
		if (_demolishReward == null) {
			_demolishReward = new ResourceBundle();
			_demolishReward.add(new Resource(Resource.POLYMER_NAME, 27));
			_demolishReward.add(new Resource(Resource.METAL_NAME, 4));
			_demolishReward.add(new Resource(Resource.ALLOYS_NAME, 1));
		}
		return _demolishReward;
	}

	@Override public ResourceBundle operatingCost() {
		if (_operatingCost == null) {
			_operatingCost = new ResourceBundle();
			_operatingCost.add(new Resource(Resource.FUEL_NAME, 5));
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

	@Override public int laborCost() { return 8; }

	@Override public String getName() { return "Fusion Reactor"; }
	
	@Override public int buildUnits() { return 210; }
	
	@Override public void onEnable(int count) {
		container.mods.adjustPower(count * 100);
	}
	
	@Override public void onDisable(int count) {
		container.mods.adjustPower(-count * 100);
	}
}
