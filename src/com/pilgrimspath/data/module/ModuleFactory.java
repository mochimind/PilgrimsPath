package com.pilgrimspath.data.module;

import com.pilgrimspath.data.Ship;

public class ModuleFactory {
	public static Module CreateModule(String id, Ship container) {
		if (id == BasicHousing.NAME) {
			return new BasicHousing(container);
		} else if (id == Hydroponics.NAME) {
			return new Hydroponics(container);
		} else if (id == PolymerFactory.NAME) {
			return new PolymerFactory(container);
		} else if (id == PowerStation.NAME) {
			return new PowerStation(container);
		}
		return new Module(container);
	}
}
