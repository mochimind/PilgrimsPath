package com.pilgrimspath.data.stat;

import com.pilgrimspath.data.Ship;

public class PopulationStat extends Stat {

	private Ship host;
	private int population;
	private int maxPopulation;
	private int lastPopulation;
	private boolean laborShortage;
	
	public static final String NAME = "Population";
	
	public PopulationStat(Ship _host) {
		name = NAME;
		host = _host;
	}
	
	private void update() {
		population = host.peeps.getPopulation();
		maxPopulation = host.peeps.getMaxPopulation();
		lastPopulation = host.peeps.getLastPopulation();
		laborShortage = host.peeps.hasLaborShortage();
	}
	
	// yellow if population is going down, but there's spare population
	// red if population is going down and there's no spare population
	@Override public int getStatus() {
		update();
		if (laborShortage) {
			if (lastPopulation > population) {
				// population is shrinking
				return Stat.STATUS_RED;
			} else {
				return Stat.STATUS_YELLOW;
			}
		} else {
			if (lastPopulation > population) {
				return Stat.STATUS_YELLOW;
			} else {
				return Stat.STATUS_GREEN;
			}
		}
	}
	
	@Override public String getValue() {
		update();
		return population + "/" + maxPopulation;
	}
}
