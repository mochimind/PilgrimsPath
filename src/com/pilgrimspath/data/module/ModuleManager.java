package com.pilgrimspath.data.module;

import java.util.ArrayList;
import java.util.List;

import com.pilgrimspath.data.Ship;


public class ModuleManager {
	public static String HYDROPONICS = "Hydroponics";
	public static String POWER_PLANT = "Power Plant";
	public static String POLYMER_PLANT = "Polymer Plant";
	public static String BASIC_HOUSING = "Basic Housing";
	
	private List<Module> modules;
	private Ship container;
	private int powerOutput;
	private int subscribedPower;
	
	private int maxSpace;
	private int usedSpace;
	
	public ModuleManager(Ship _container, int _space) {
		modules = new ArrayList<Module>();
		container = _container;
		powerOutput = 0;
		subscribedPower = 0;
		maxSpace = _space;
		usedSpace = 0;
	}
	
	public int addModules(String id, int count) {
		Module buildModule = null;
		for (int i=0 ; i<modules.size() ; i++) {
			if (modules.get(i).getName().equals(id)) {
				buildModule = modules.get(i);
			}
		}
		if (buildModule == null) { buildModule = ModuleFactory.CreateModule(id, container); }
		return buildModule.build(count);
	}
	
	public int removeModules(String id, int count) {
		for (int i=0 ; i<modules.size() ; i++) {
			if (modules.get(i).getName().equals(id)) {
				return modules.get(i).destroy(count);
			}
		}
		return 0;
	}
	
	// checks whether there's enough space to build unit number of modules
	public int checkSpace(int units, int spacePerUnit) {
		int supportable = Math.min((maxSpace - usedSpace) / spacePerUnit, units);
		return supportable;
	}
	
	// allocates space to build the modules
	public int useSpace(int units, int spacePerUnit) {
		int supportable = Math.min((maxSpace - usedSpace) / spacePerUnit, units);
		usedSpace += spacePerUnit * supportable;
		return supportable;
	}
	
	public void tick() {
		// TODO: sort modules by priority
		for (int i=0 ; i<modules.size() ; i++) {
			modules.get(i).tick();
		}
	}
	
	public int repair(int repairPoints) {
		return repairPoints;
	}
	
	public void startNewRound() {
		subscribedPower = 0;
	}
	
	public int checkPower(int units, int powerPerUnit) {
		int available = powerOutput - subscribedPower;
		return (int) Math.min(units, Math.floor(available / powerPerUnit));
	}
	
	public int requestPower(int units, int powerPerUnit) {
		int available = powerOutput - subscribedPower;
		int supplied = (int) Math.min(units, Math.floor(available / powerPerUnit));
		
		subscribedPower += supplied * powerPerUnit;
		return supplied;
	}
}
