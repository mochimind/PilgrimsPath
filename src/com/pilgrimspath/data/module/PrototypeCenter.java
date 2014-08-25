package com.pilgrimspath.data.module;

import com.pilgrimspath.data.Resource;
import com.pilgrimspath.data.ResourceBundle;
import com.pilgrimspath.data.Ship;
import com.pilgrimspath.data.research.ResearchFile.ProjectType;

public class PrototypeCenter extends Module {
	protected static ResourceBundle _buildCost;
	protected static ResourceBundle _demolishReward;
	protected static ResourceBundle _operatingCost;
	protected static ResourceBundle _operatingReward;

	public PrototypeCenter(Ship _container) {
		super(_container);
		id = Module.PROTOTYPE_CENTER;
	}

	@Override public int spaceCost() { return 20; }

	@Override public ResourceBundle buildCost() { 
		if (_buildCost == null) {
			_buildCost = new ResourceBundle();
			_buildCost.add(new Resource(Resource.POLYMER_NAME, 130));
			_buildCost.add(new Resource(Resource.METAL_NAME, 30));
			_buildCost.add(new Resource(Resource.ALLOYS_NAME, 8));
		}
		return _buildCost; 
	}

	@Override public ResourceBundle demolishReward() {
		if (_demolishReward == null) {
			_demolishReward = new ResourceBundle();
			_demolishReward.add(new Resource(Resource.POLYMER_NAME, 62));
			_demolishReward.add(new Resource(Resource.METAL_NAME, 14));
			_demolishReward.add(new Resource(Resource.ALLOYS_NAME, 3));
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

	@Override public int powerCost() { return 14; }

	@Override public int laborCost() { return 6; }

	@Override public String getName() { return "Prototype Center"; }
	
	@Override public int buildUnits() { return 300; }
	
	@Override public void onEnable(int count) {
		container.research.adjustResearchPerTick(count * 12, ProjectType.Prototype);
	}
	
	@Override public void onDisable(int count) {
		container.research.adjustResearchPerTick(-count * 12, ProjectType.Prototype);
	}
}
