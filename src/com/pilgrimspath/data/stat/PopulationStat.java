package com.pilgrimspath.data.stat;

import com.pilgrimspath.data.Ship;

public class PopulationStat extends Stat {

	private Ship host;
	private int population;
	private int maxPopulation;
	private boolean populationDeclining;
	private boolean laborShortage;
	
	public static final String NAME = "Population";
	
	public PopulationStat(Ship _host) {
		name = NAME;
		host = _host;
	}
	
	private void update() {
		population = host.peeps.getPopulation();
		maxPopulation = host.peeps.getMaxPopulation();
		populationDeclining = host.peeps.populationDeclining();
		laborShortage = host.peeps.hasLaborShortage();
	}
	
	// yellow if population is going down, but there's spare population
	// red if population is going down and there's no spare population
	@Override public int getStatus() {
		update();
		if (laborShortage) {
			if (populationDeclining) {
				return Stat.STATUS_RED;
			} else {
				return Stat.STATUS_YELLOW;
			}
		} else {
			if (populationDeclining) {
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
