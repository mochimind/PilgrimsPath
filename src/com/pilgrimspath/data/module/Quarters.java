package com.pilgrimspath.data.module;

import com.pilgrimspath.data.Resource;
import com.pilgrimspath.data.ResourceBundle;
import com.pilgrimspath.data.Ship;

public class Quarters extends Module {
	protected static ResourceBundle _buildCost;
	protected static ResourceBundle _demolishReward;
	protected static ResourceBundle _operatingCost;
	protected static ResourceBundle _operatingReward;

	public Quarters(Ship _container) {
		super(_container);
		id = Module.QUARTERS;
	}

	@Override public int spaceCost() { return 2; }

	@Override public ResourceBundle buildCost() { 
		if (_buildCost == null) {
			_buildCost = new ResourceBundle();
			_buildCost.add(new Resource(Resource.POLYMER_NAME, 14));
			_buildCost.add(new Resource(Resource.METAL_NAME, 2));
		}
		return _buildCost; 
	}

	@Override public ResourceBundle demolishReward() {
		if (_demolishReward == null) {
			_demolishReward = new ResourceBundle();
			_demolishReward.add(new Resource(Resource.POLYMER_NAME, 6));
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

	@Override public int powerCost() { return 2; }

	@Override public int laborCost() { return 0; }

	@Override public String getName() { return "Quarters"; }
	
	@Override public int buildUnits() { return 15; }
	
	@Override public void onEnable(int count) {
		container.peeps.addHousing(count * 16);
	}
	
	@Override public void onDisable(int count) {
		container.peeps.removeHousing(count * 16);
	}
}
