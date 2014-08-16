package com.pilgrimspath.data.module;

import java.util.ArrayList;
import java.util.List;

import com.pilgrimspath.data.Ship;


public class ModuleManager {
	public static String HYDROPONICS = "Hydroponics";
	public static String POWER_PLANT = "Power Plant";
	public static String POLYMER_PLANT = "Polymer Plant";
	public static String BASIC_HOUSING = "Basic Housing";
	
	public List<Module> modules;
	private Ship container;
	private int powerOutput = 0;
	private int lastUsedPower = 0;
	private int subscribedPower;
	
	private int maxSpace;
	private int usedSpace;
	
	public ModuleManager(Ship _container, int _space) {
		modules = new ArrayList<Module>();
		container = _container;
		maxSpace = _space;
		usedSpace = 0;
	}
	
	// gives the modules for free to the user provided there is enough space
	public int rewardModules(int id, int count) {
		Module buildModule = null;
		for (int i=0 ; i<modules.size() ; i++) {
			if (modules.get(i).id == id) {
				buildModule = modules.get(i);
			}
		}
		if (buildModule == null) {
			buildModule = ModuleFactory.CreateModule(id, container);
			modules.add(buildModule);
		}
		return buildModule.reward(count);
	}
	
	public int addModules(int id, int count) {
		Module buildModule = null;
		for (int i=0 ; i<modules.size() ; i++) {
			if (modules.get(i).id == id) {
				buildModule = modules.get(i);
			}
		}
		if (buildModule == null) { 
			buildModule = ModuleFactory.CreateModule(id, container);
			modules.add(buildModule);
		}
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
		lastUsedPower = subscribedPower;
		subscribedPower = 0;
	}
	
	public void adjustPower(int units) { powerOutput += units; }
	
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
	
	public synchronized int getPowerUsePercent() {
		return 100 * lastUsedPower / powerOutput;
	}
}
