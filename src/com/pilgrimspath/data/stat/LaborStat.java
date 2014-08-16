package com.pilgrimspath.data.stat;

import com.pilgrimspath.data.Ship;

public class LaborStat extends Stat {
	private Ship host;
	private int requestedLabor;
	private int availableLabor;
	private boolean laborShortage;
	private boolean populationDeclining;
	
	public static final String NAME = "Labor";
	
	public LaborStat(Ship _host) {
		host = _host;
		name = NAME;
	}
	
	private void update() {
		availableLabor = host.peeps.getAvailableLabor();
		requestedLabor = host.peeps.getRequestedLabor();
		laborShortage = host.peeps.hasLaborShortage();
		populationDeclining = host.peeps.populationDeclining();
	}
	
	@Override public int getStatus() {
		update();
		if (laborShortage) {
			return Stat.STATUS_RED;
		} else {
			if (populationDeclining) {
				// population is shrinking => labor is shrinking
				return Stat.STATUS_YELLOW;
			} else {
				return Stat.STATUS_GREEN;
			}
		}
	}

	@Override public String getValue() {
		update();
		return requestedLabor + "/" + availableLabor;
	}
	
}
