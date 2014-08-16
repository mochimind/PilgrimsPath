package com.pilgrimspath.data.stat;

import com.pilgrimspath.data.PeopleManager;
import com.pilgrimspath.data.Ship;

public class HealthStat extends Stat {

	private float health;
	private boolean healthDeclining;
	
	public static final String NAME = "Health";
	private Ship host;
	
	public HealthStat(Ship _host) {
		host = _host;
		name = NAME;
	}
	
	private void update() {
		health = host.peeps.getHealth();
		healthDeclining = host.peeps.healthDeclining();
	}
	
	@Override public int getStatus() {
		update();
		if (health < PeopleManager.MORTALITY_THRESHOLD) { return Stat.STATUS_RED; }
		if (healthDeclining) { return Stat.STATUS_YELLOW; }
		return Stat.STATUS_GREEN;
	}

	@Override public String getValue() {
		update();
		return ((int) (health * 100)) + "%";
	}

}
