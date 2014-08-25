package com.pilgrimspath.data.module;

import com.pilgrimspath.data.Resource;
import com.pilgrimspath.data.ResourceBundle;
import com.pilgrimspath.data.Ship;

public class IndustrialFacility extends Module {
	protected static ResourceBundle _buildCost;
	protected static ResourceBundle _demolishReward;
	protected static ResourceBundle _operatingCost;
	protected static ResourceBundle _operatingReward;

	public IndustrialFacility(Ship _container) {
		super(_container);
		id = Module.INDUSTRIAL_FACILITY;
	}

	@Override public int spaceCost() { return 8; }

	@Override public ResourceBundle buildCost() { 
		if (_buildCost == null) {
			_buildCost = new ResourceBundle();
			_buildCost.add(new Resource(Resource.POLYMER_NAME, 60));
			_buildCost.add(new Resource(Resource.METAL_NAME, 10));
			_buildCost.add(new Resource(Resource.ALLOYS_NAME, 5));
		}
		return _buildCost; 
	}

	@Override public ResourceBundle demolishReward() {
		if (_demolishReward == null) {
			_demolishReward = new ResourceBundle();
			_demolishReward.add(new Resource(Resource.POLYMER_NAME, 28));
			_demolishReward.add(new Resource(Resource.METAL_NAME, 4));
			_demolishReward.add(new Resource(Resource.ALLOYS_NAME, 2));
		}
		return _demolishReward;
	}

	@Override public ResourceBundle operatingCost() {
		if (_operatingCost == null) {
			_operatingCost = new ResourceBundle();
			_operatingCost.add(new Resource(Resource.GAS_NAME, 5));
			_operatingCost.add(new Resource(Resource.ORE_NAME, 5));
		}
		return _operatingCost;
	}

	@Override public ResourceBundle operatingReward() {
		if (_operatingReward == null) {
			_operatingReward = new ResourceBundle();
			_operatingReward.add(new Resource(Resource.POLYMER_NAME, 6));
			_operatingReward.add(new Resource(Resource.METAL_NAME, 6));
		}
		return _operatingReward;
	}

	@Override public int powerCost() { return 15; }

	@Override public int laborCost() { return 8; }

	@Override public String getName() { return "Industrial Facility"; }
	
	@Override public int buildUnits() { return 120; }
}
