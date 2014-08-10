package com.pilgrimspath.data.module;

import com.pilgrimspath.data.Ship;

public class ModuleFactory {
	public static Module CreateModule(int id, Ship container) {
		switch (id) {
		case Module.BASIC_HOUSING:
			return new BasicHousing(container);
		case Module.HYDROPONICS:
			return new Hydroponics(container);
		case Module.POLYMER_FACTORY:
			return new PolymerFactory(container);
		case Module.POWER_STATION:
			return new PowerStation(container);
		case Module.REFINERY:
			return new Refinery(container);
		default:
			return null;
		}
	}
}
