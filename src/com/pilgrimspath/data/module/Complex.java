package com.pilgrimspath.data.module;

import com.pilgrimspath.data.Resource;
import com.pilgrimspath.data.ResourceBundle;
import com.pilgrimspath.data.Ship;

public class Complex extends Module {
	protected static ResourceBundle _buildCost;
	protected static ResourceBundle _demolishReward;
	protected static ResourceBundle _operatingCost;
	protected static ResourceBundle _operatingReward;

	public Complex(Ship _container) {
		super(_container);
		id = Module.COMPLEX;
	}

	@Override public int spaceCost() { return 4; }

	@Override public ResourceBundle buildCost() { 
		if (_buildCost == null) {
			_buildCost = new ResourceBundle();
			_buildCost.add(new Resource(Resource.POLYMER_NAME, 30));
			_buildCost.add(new Resource(Resource.METAL_NAME, 4));
			_buildCost.add(new Resource(Resource.ALLOYS_NAME, 1));
		}
		return _buildCost; 
	}

	@Override public ResourceBundle demolishReward() {
		if (_demolishReward == null) {
			_demolishReward = new ResourceBundle();
			_demolishReward.add(new Resource(Resource.POLYMER_NAME, 10));
			_demolishReward.add(new Resource(Resource.METAL_NAME, 1));
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

	@Override public int powerCost() { return 6; }

	@Override public int laborCost() { return 0; }

	@Override public String getName() { return "Complex"; }
	
	@Override public int buildUnits() { return 40; }
	
	@Override public void onEnable(int count) {
		container.peeps.addHousing(count * 20);
	}
	
	@Override public void onDisable(int count) {
		container.peeps.removeHousing(count * 20);
	}
}
