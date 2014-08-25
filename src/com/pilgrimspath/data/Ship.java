package com.pilgrimspath.data;

import com.pilgrimspath.data.module.ModuleManager;
import com.pilgrimspath.data.research.ResearchManager;

public class Ship {
	public ModuleManager mods;
	public PeopleManager peeps;
	public DockManager dock;
	public ResearchManager research;

	public Fleet fleet;

	public String name;
		
	public static final int DEFAULT_SPACE = 200;
	
	public Ship(Fleet _fleet, String _type, String _name) {
		// for now, just one type
		mods = new ModuleManager(this, DEFAULT_SPACE);
		peeps = new PeopleManager(this);
		dock = new DockManager(this);
		research = new ResearchManager();
		fleet  = _fleet;
		name = _name;
	}
	
	public void tick() {
		peeps.startNewRound();
		mods.startNewRound();
		dock.tick();
		mods.tick();
		peeps.tick();
	}
}
