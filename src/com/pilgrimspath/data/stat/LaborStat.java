package com.pilgrimspath.data.stat;

import com.pilgrimspath.data.Ship;

public class LaborStat extends Stat {
	private Ship host;
	private int allocatedLabor;
	private int requiredLabor;
	private int lastPop;
	private int curPop;
	
	public static final String NAME = "Labor";
	
	public LaborStat(Ship _host) {
		host = _host;
		name = NAME;
	}
	
	private void update() {
		allocatedLabor = host.peeps.getAvailableLabor();
		requiredLabor = host.peeps.getRequestedLabor();
		lastPop = host.peeps.getLastPopulation();
		curPop = host.peeps.getPopulation();
	}
	
	@Override public int getStatus() {
		update();
		if (allocatedLabor < requiredLabor) {
			return Stat.STATUS_RED;
		} else {
			if (lastPop > curPop) {
				// population is shrinking => labor is shrinking
				return Stat.STATUS_YELLOW;
			} else {
				return Stat.STATUS_GREEN;
			}
		}
	}

	@Override public String getValue() {
		update();
		return allocatedLabor + "/" + requiredLabor;
	}
	
}
