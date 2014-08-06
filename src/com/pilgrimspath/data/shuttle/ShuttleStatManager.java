package com.pilgrimspath.data.shuttle;

public class ShuttleStatManager {
	public static final int DIAMONDHEAD = 1000;
	
	public static ShuttleStat GetStat(int type) {
		switch(type) {
		case DIAMONDHEAD:
			return Diamondhead.GetStat();
		default:
			return null;
		}
	}
	
	public static int GetSpaceRequirement(int type, int count) {
		return GetStat(type).size() * count;
	}
}
