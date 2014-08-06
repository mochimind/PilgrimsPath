package com.pilgrimspath.data.shuttle;

public class ShuttleStat {
	private String _name;
	private String _description;
	private int _size;
	private int _orePerTick;
	private int _gasPerTick;
	private int _crewRequired;
	
	public ShuttleStat(String inName, String inDesc, int inSize, int inOrePT,
			int inGasPT, int inCrew) {
		_name = inName;
		_description = inDesc;
		_size = inSize;
		_orePerTick = inOrePT;
		_gasPerTick = inGasPT;
		_crewRequired = inCrew;
	}
	
	public String name() { return _name; }
	public String description() { return _description; }
	public int size() { return _size; }
	public int orePerTick() { return _orePerTick; }
	public int gasPerTick() { return _gasPerTick; }
	public int crewRequired() { return _crewRequired; }
}
