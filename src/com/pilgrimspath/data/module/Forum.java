package com.pilgrimspath.data.module;

import com.pilgrimspath.data.Resource;
import com.pilgrimspath.data.ResourceBundle;
import com.pilgrimspath.data.Ship;
import com.pilgrimspath.data.research.ResearchFile.ProjectType;

public class Forum extends Module {
	protected static ResourceBundle _buildCost;
	protected static ResourceBundle _demolishReward;
	protected static ResourceBundle _operatingCost;
	protected static ResourceBundle _operatingReward;

	public Forum(Ship _container) {
		super(_container);
		id = Module.FORUM;
	}

	@Override public int spaceCost() { return 3; }

	@Override public ResourceBundle buildCost() { 
		if (_buildCost == null) {
			_buildCost = new ResourceBundle();
			_buildCost.add(new Resource(Resource.POLYMER_NAME, 24));
			_buildCost.add(new Resource(Resource.METAL_NAME, 1));
		}
		return _buildCost; 
	}

	@Override public ResourceBundle demolishReward() {
		if (_demolishReward == null) {
			_demolishReward = new ResourceBundle();
			_demolishReward.add(new Resource(Resource.POLYMER_NAME, 11));
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

	@Override public int powerCost() { return 3; }

	@Override public int laborCost() { return 20; }

	@Override public String getName() { return "Forum"; }
	
	@Override public int buildUnits() { return 40; }
	
	@Override public void onEnable(int count) {
		container.research.adjustResearchPerTick(count * 4, ProjectType.Civic);
	}
	
	@Override public void onDisable(int count) {
		container.research.adjustResearchPerTick(-count * 4, ProjectType.Civic);
	}
}
