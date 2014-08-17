package com.pilgrimspath.data.shuttle;

import com.pilgrimspath.data.ResourceBundle;

public class ShuttleStat {
	private String _name;
	private String _description;
	private int _size;
	private int _orePerTick;
	private int _gasPerTick;
	private int _crewRequired;
	public ResourceBundle buildCost;
	public ResourceBundle destroyReward;
	
	public ShuttleStat(String inName, String inDesc, int inSize, int inOrePT,
			int inGasPT, int inCrew, ResourceBundle inBuildCost, ResourceBundle inDestroyReward) {
		_name = inName;
		_description = inDesc;
		_size = inSize;
		_orePerTick = inOrePT;
		_gasPerTick = inGasPT;
		_crewRequired = inCrew;
		buildCost = inBuildCost;
		destroyReward = inDestroyReward;
	}
	
	public String name() { return _name; }
	public String description() { return _description; }
	public int size() { return _size; }
	public int orePerTick() { return _orePerTick; }
	public int gasPerTick() { return _gasPerTick; }
	public int crewRequired() { return _crewRequired; }
}
