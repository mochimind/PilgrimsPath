package com.pilgrimspath.data.module;

import com.pilgrimspath.data.Resource;
import com.pilgrimspath.data.ResourceBundle;
import com.pilgrimspath.data.Ship;

public class FoodVat extends Module {
	protected static ResourceBundle _buildCost;
	protected static ResourceBundle _demolishReward;
	protected static ResourceBundle _operatingCost;
	protected static ResourceBundle _operatingReward;

	public FoodVat(Ship _container) {
		super(_container);
		id = Module.FOOD_VAT;
	}

	@Override public int spaceCost() { return 8; }

	@Override public ResourceBundle buildCost() { 
		if (_buildCost == null) {
			_buildCost = new ResourceBundle();
			_buildCost.add(new Resource(Resource.POLYMER_NAME, 25));
			_buildCost.add(new Resource(Resource.METAL_NAME, 15));
			_buildCost.add(new Resource(Resource.ALLOYS_NAME, 1));
		}
		return _buildCost; 
	}

	@Override public ResourceBundle demolishReward() {
		if (_demolishReward == null) {
			_demolishReward = new ResourceBundle();
			_demolishReward.add(new Resource(Resource.POLYMER_NAME, 12));
			_demolishReward.add(new Resource(Resource.METAL_NAME, 7));
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
			_operatingReward.add(new Resource(Resource.FOOD_NAME, 90));
		}
		return _operatingReward;
	}

	@Override public int powerCost() { return 6; }

	@Override public int laborCost() { return 6; }

	@Override public String getName() { return "Food Vat"; }

	@Override public int buildUnits() { return 125; }
}
