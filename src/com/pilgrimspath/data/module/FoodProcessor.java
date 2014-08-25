package com.pilgrimspath.data.module;

import com.pilgrimspath.data.Resource;
import com.pilgrimspath.data.ResourceBundle;
import com.pilgrimspath.data.Ship;

public class FoodProcessor extends Module {
	protected static ResourceBundle _buildCost;
	protected static ResourceBundle _demolishReward;
	protected static ResourceBundle _operatingCost;
	protected static ResourceBundle _operatingReward;

	public FoodProcessor(Ship _container) {
		super(_container);
		id = Module.FOOD_PROCESSOR;
	}

	@Override public int spaceCost() { return 10; }

	@Override public ResourceBundle buildCost() { 
		if (_buildCost == null) {
			_buildCost = new ResourceBundle();
			_buildCost.add(new Resource(Resource.POLYMER_NAME, 16));
			_buildCost.add(new Resource(Resource.METAL_NAME, 6));
		}
		return _buildCost; 
	}

	@Override public ResourceBundle demolishReward() {
		if (_demolishReward == null) {
			_demolishReward = new ResourceBundle();
			_demolishReward.add(new Resource(Resource.POLYMER_NAME, 7));
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
			_operatingReward.add(new Resource(Resource.FOOD_NAME, 50));
		}
		return _operatingReward;
	}

	@Override public int powerCost() { return 4; }

	@Override public int laborCost() { return 4; }

	@Override public String getName() { return "Food Processor"; }
	
	@Override public int buildUnits() { return 50; }
}
