package com.pilgrimspath.data;

import com.pilgrimspath.data.module.ModuleManager;

public class Ship {
	public ModuleManager mods;
	public PeopleManager peeps;
	public DockManager dock;
	
	public Fleet fleet;
	
	public static final int DEFAULT_SPACE = 200;
	
	public Ship(Fleet _fleet, String _type) {
		// for now, just one type
		mods = new ModuleManager(this, DEFAULT_SPACE);
		peeps = new PeopleManager(this);
		dock = new DockManager(this);
		fleet  = _fleet;
	}
	
	public void tick() {
		peeps.startNewRound();
		mods.startNewRound();
		dock.tick();
		mods.tick();
		peeps.tick();
	}
}
