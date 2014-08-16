package com.pilgrimspath.data.module;

import com.pilgrimspath.data.Resource;
import com.pilgrimspath.data.ResourceBundle;
import com.pilgrimspath.data.Ship;

public class Hydroponics extends Module {
	
	public static final String NAME = "Hydroponics Lab";
	
	protected static ResourceBundle _buildCost;
	protected static ResourceBundle _demolishReward;
	protected static ResourceBundle _operatingCost;
	protected static ResourceBundle _operatingReward;
	
	
	
	public Hydroponics(Ship _container) {
		super(_container);
		id = Module.HYDROPONICS;
	}

	@Override
	public ResourceBundle buildCost() {
		if (_buildCost == null) { 
			_buildCost = new ResourceBundle();
			_buildCost.add(new Resource(Resource.POLYMER_NAME, 20));
		}
		return _buildCost;
	}

	@Override
	public ResourceBundle demolishReward() {
		// TODO Auto-generated method stub
		if (_demolishReward == null) {
			_demolishReward = new ResourceBundle();
			_demolishReward.add(new Resource(Resource.POLYMER_NAME, 8));
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
		if (_operatingReward == null) {
			_operatingReward = new ResourceBundle();
			_operatingReward.add(new Resource(Resource.FOOD_NAME, 2));
		}
		return _operatingReward;
	}

	@Override public int powerCost() { return 5; }

	@Override public int laborCost() { return 1; }
	
	@Override public int spaceCost() { return 1; }

	@Override
	public String getName() {
		return "Hydroponics";
	}
}
