package com.pilgrimspath.data.module;

import com.pilgrimspath.data.Resource;
import com.pilgrimspath.data.ResourceBundle;
import com.pilgrimspath.data.Ship;

public class BasicHousing extends Module {

	public static final String NAME = "Basic Housing";
	public static final int HOUSING_UNITS = 8;
	
	public BasicHousing(Ship _container) {
		super(_container);
		id = Module.BASIC_HOUSING;
	}

	@Override
	public ResourceBundle buildCost() {
		if (_buildCost == null) {
			_buildCost = new ResourceBundle();
			_buildCost.add(new Resource(Resource.POLYMER_NAME, 40));
		}
		return _buildCost;
	}

	@Override
	public ResourceBundle demolishReward() {
		if (_demolishReward == null) {
			_demolishReward = new ResourceBundle();
			_demolishReward.add(new Resource(Resource.POLYMER_NAME, 14));
		}
		return _demolishReward;
	}

	@Override
	public ResourceBundle operatingCost() {
		if (_operatingCost == null) { _operatingCost = new ResourceBundle(); }
		return _operatingCost;
	}

	@Override
	public ResourceBundle operatingReward() {
		if (_operatingReward == null) { _operatingReward = new ResourceBundle(); }
		return _operatingReward;
	}

	@Override
	public int powerCost() { return 5; }

	@Override
	public int laborCost() { return 0; }

	@Override
	public String getName() {
		return NAME;
	}
	
	@Override public void onBuild(int count) {
		container.peeps.addHousing(count * HOUSING_UNITS);
	}
	
	@Override public void onDestroy(int count) {
		container.peeps.removeHousing(count * HOUSING_UNITS);
	}
}
