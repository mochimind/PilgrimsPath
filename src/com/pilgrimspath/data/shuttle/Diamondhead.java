package com.pilgrimspath.data.shuttle;

/*
 * Light miner
 */
public class Diamondhead extends Shuttle {

	@Override public int size() { return 1; }
	@Override public int gasPerTick() { return 1; }
	@Override public int orePerTick() { return 1; }
	@Override public int getCrewRequirement() { return 2; }

	public static String NAME = "DiamondHead";
	
	@Override public String getDescription() {
		return "The Diamond Head XT100 all purpose excavation vessel was one of the first vessels " +
				"of the spacefaring age. Built mainly with space station and satellite technology, " +
				"this vessel is capable of performing a plethora of activities with its onboard " +
				"multitools, including excavation, salvaging, and gas mining. Although " +
				"considered by most captains as vintage junk, a surprising number of these are" +
				"still in operation, probably due to their extreme ruggedness and easy to learn" +
				"controls.";
	}
	
	@Override public String getName() { return NAME; }
	
	public Diamondhead(int _count) { super (_count); }
}
