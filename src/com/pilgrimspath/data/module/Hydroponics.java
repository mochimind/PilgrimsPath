package com.pilgrimspath.data.module;

import com.pilgrimspath.data.resource.Resource;
import com.pilgrimspath.data.resource.ResourceBundle;

public class Hydroponics extends Module {

	private static ResourceBundle _buildCost = null;
	private static ResourceBundle _demolishReward = null;
	
	@Override
	public String name() { return Module.HYDROPONICS; }
	
	@Override 
	public ResourceBundle buildCost() {
		if (_buildCost == null) {
			_buildCost = new ResourceBundle();
			_buildCost.add(new Resource(Resource.POLYMER_NAME, 15));
		}
		return _buildCost;
	}
	
	@Override
	public ResourceBundle demolishReward() {
		if (_demolishReward == null) {
			_demolishReward = new ResourceBundle();
			_demolishReward.add(new Resource(Resource.POLYMER_NAME, 4));
		}
		return _demolishReward;
	}
	
	@Override
	public int laborRequired() { return 1; }
	
	@Override
	public int powerRequired() { return 2; }
}
