package com.pilgrimspath.data.module;

import com.pilgrimspath.data.Resource;
import com.pilgrimspath.data.ResourceBundle;
import com.pilgrimspath.data.Ship;

public class Fracker extends Module {
	protected static ResourceBundle _buildCost;
	protected static ResourceBundle _demolishReward;
	protected static ResourceBundle _operatingCost;
	protected static ResourceBundle _operatingReward;

	public Fracker(Ship _container) {
		super(_container);
		id = Module.FRACKER;
	}

	@Override public int spaceCost() { return 4; }

	@Override public ResourceBundle buildCost() { 
		if (_buildCost == null) {
			_buildCost = new ResourceBundle();
			_buildCost.add(new Resource(Resource.POLYMER_NAME, 28));
			_buildCost.add(new Resource(Resource.METAL_NAME, 6));
			_buildCost.add(new Resource(Resource.ALLOYS_NAME, 3));
		}
		return _buildCost; 
	}

	@Override public ResourceBundle demolishReward() {
		if (_demolishReward == null) {
			_demolishReward = new ResourceBundle();
			_demolishReward.add(new Resource(Resource.POLYMER_NAME, 13));
			_demolishReward.add(new Resource(Resource.METAL_NAME, 2));
			_demolishReward.add(new Resource(Resource.ALLOYS_NAME, 1));
		}
		return _demolishReward;
	}

	@Override public ResourceBundle operatingCost() {
		if (_operatingCost == null) {
			_operatingCost = new ResourceBundle();
			_operatingCost.add(new Resource(Resource.GAS_NAME,4));
		}
		return _operatingCost;
	}

	@Override public ResourceBundle operatingReward() {
		if (_operatingReward == null) {
			_operatingReward = new ResourceBundle();
			_operatingReward.add(new Resource(Resource.FUEL_NAME, 15));
		}
		return _operatingReward;
	}

	@Override public int powerCost() { return 10; }

	@Override public int laborCost() { return 6; }

	@Override public String getName() { return "Fracker"; }

	@Override public int buildUnits() { return 60; }
}
