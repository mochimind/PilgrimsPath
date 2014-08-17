package com.pilgrimspath.data.shuttle;

import com.pilgrimspath.data.Resource;
import com.pilgrimspath.data.ResourceBundle;

/*
 * Light miner
 */
public class Diamondhead {
	private static ShuttleStat stats = null;
	private static boolean initialized = false;
	
	private static void init() {
		if (!initialized) {
			initialized = true;
			ResourceBundle buildCost = new ResourceBundle();
			buildCost.add(new Resource(Resource.ALLOYS_NAME, 5));
			buildCost.add(new Resource(Resource.POLYMER_NAME, 5));
			
			ResourceBundle destroyReward = new ResourceBundle();
			destroyReward.add(new Resource(Resource.ALLOYS_NAME, 2));
			destroyReward.add(new Resource(Resource.POLYMER_NAME, 2));
			
			stats = new ShuttleStat("DiamondHead",
					"The Diamond Head XT100 all purpose excavation vessel was one of the first vessels " +
					"of the spacefaring age. Built mainly with space station and satellite technology, " +
					"this vessel is capable of performing a plethora of activities with its onboard " +
					"multitools, including excavation, salvaging, and gas mining. Although " +
					"considered by most captains as vintage junk, a surprising number of these are" +
					"still in operation, probably due to their extreme ruggedness and intuitive" +
					"controls.",
					1, // size
					1, // ore
					1, // gas
					2, // crew
					buildCost,
					destroyReward);
		}
	}
	
	public static ShuttleStat GetStat() {
		init();
		return stats;
	}
}
