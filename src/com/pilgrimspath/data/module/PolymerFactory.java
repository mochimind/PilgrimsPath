package com.pilgrimspath.data.module;

import com.pilgrimspath.data.Resource;
import com.pilgrimspath.data.ResourceBundle;
import com.pilgrimspath.data.Ship;

public class PolymerFactory extends Module {

	public static final String NAME = "Polymer Factory";
	
	public PolymerFactory(Ship _container) {
		super(_container);
		id = Module.POLYMER_FACTORY;
	}

	@Override
	public ResourceBundle buildCost() {
		if (_buildCost == null) {
			_buildCost = new ResourceBundle();
			_buildCost.add(new Resource(Resource.POLYMER_NAME, 100));
		}
		return _buildCost;
	}

	@Override
	public ResourceBundle demolishReward() {
		if (_demolishReward == null) {
			_demolishReward = new ResourceBundle();
			_demolishReward.add(new Resource(Resource.POLYMER_NAME, 40));
		}
		return _demolishReward;
	}

	@Override
	public ResourceBundle operatingCost() {
		if (_operatingCost == null) {
			_operatingCost = new ResourceBundle();
			_operatingCost.add(new Resource(Resource.GAS_NAME, 4));
		}
		return _operatingCost;
	}

	@Override
	public ResourceBundle operatingReward() {
		if (_operatingReward == null) {
			_operatingReward = new ResourceBundle();
			_operatingReward.add(new Resource(Resource.POLYMER_NAME, 4));
		}
		return _operatingReward;
	}

	@Override public int powerCost() { return 4; }

	@Override public int laborCost() { return 2; }

	@Override public String getName() { return NAME; }
}
