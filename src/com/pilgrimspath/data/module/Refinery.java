package com.pilgrimspath.data.module;

import com.pilgrimspath.data.Resource;
import com.pilgrimspath.data.ResourceBundle;
import com.pilgrimspath.data.Ship;

public class Refinery extends Module {

	public static final String NAME = "Refinery";
	
	public Refinery(Ship _container) {
		super(_container);
		id = Module.REFINERY;
	}

	@Override
	public ResourceBundle buildCost() {
		if (_buildCost == null) {
			_buildCost = new ResourceBundle();
			_buildCost.add(new Resource(Resource.POLYMER_NAME, 80));
		}
		return _buildCost;
	}

	@Override
	public ResourceBundle demolishReward() {
		if (_demolishReward == null) {
			_demolishReward = new ResourceBundle();
			_demolishReward.add(new Resource(Resource.POLYMER_NAME, 35));
		}
		return _demolishReward;
	}

	@Override
	public ResourceBundle operatingCost() {
		if (_operatingCost == null) {
			_operatingCost = new ResourceBundle();
			_operatingCost.add(new Resource(Resource.GAS_NAME, 10));
		}
		return _operatingCost;
	}

	@Override
	public ResourceBundle operatingReward() {
		if (_operatingReward == null) {
			_operatingReward = new ResourceBundle();
			_operatingReward.add(new Resource(Resource.GAS_NAME, 20));
		}
		return _operatingReward;
	}

	@Override public int powerCost() { return 4; }

	@Override public int laborCost() { return 3; }
	
	@Override public int spaceCost() { return 1; }

	@Override public String getName() { return NAME; }

}
