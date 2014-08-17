package com.pilgrimspath.data.shuttle;

import com.pilgrimspath.data.DockManager;
import com.pilgrimspath.data.Game;
import com.pilgrimspath.data.ResourceBundle;

public class Shuttle {
	
	private int total;
	private int parked;
	private int gassing;
	
	public int type;
	
	public Shuttle(int _type, int _count) {
		total = _count;
		parked = total;
		gassing = 0;
		type = _type;
	}
	
	public synchronized int getTotal() { return total; }
	public synchronized int getParked() { return parked; }
	public synchronized int getGassing() { return gassing; }
	public synchronized int getRoleCount(int role) {
		switch (role) {
		case DockManager.ROLE_PARKED:
			return parked;
		case DockManager.ROLE_GASSING:
			return gassing;
		default:
			return 0;
		}
	}
	
	public synchronized boolean reassign(int count, int fromRole, int toRole) {
		switch(fromRole) {
		case DockManager.ROLE_PARKED:
			if (parked < count) { return false; }
			switch(toRole) {
			case DockManager.ROLE_GASSING:
				parked -= count;
				gassing += count;
				return true;
			default:
				return false;	
			}
		case DockManager.ROLE_GASSING:
			if (gassing < count) { return false; }
			switch (toRole) {
			case DockManager.ROLE_PARKED:
				gassing -= count;
				parked += count;
				return true;
			default:
				return false;
			}
		default:
			return false;
		}
	}
	
	public synchronized void build(int count) { 
		ResourceBundle buildCost = ShuttleStatManager.GetStat(type).buildCost;
		int buildable = Game.playerFleet.resources.checkAvailability(count, buildCost);
		Game.playerFleet.resources.remove(buildable, buildCost);
		parked += buildable;
		total += buildable;
	}
	
	public synchronized void award(int count) { award(count, DockManager.ROLE_PARKED); }
	
	public synchronized void award(int count, int role) {
		total += count;
		switch(role) {
		case DockManager.ROLE_PARKED:
			parked += role;
			break;
		case DockManager.ROLE_GASSING:
			gassing += role;
			break;
		default:
			// TODO: add some sort of error reporting here
		}
	}
	
	public synchronized boolean remove(int count, int role) {
		switch (role) {
		case DockManager.ROLE_PARKED:
			if (parked < count) { return false; }
			parked -= count;
			total -= count;
			return true;
		case DockManager.ROLE_GASSING:
			if (gassing < count) { return false; }
			gassing -= count;
			total -= count;
			return true;
		default:
			return false;
		}
	}
	
	public synchronized boolean destroy(int count) {
		boolean returnVal = remove(count, DockManager.ROLE_PARKED);
		if (returnVal) {
			ResourceBundle destroyReward = ShuttleStatManager.GetStat(type).destroyReward;
			Game.playerFleet.resources.add(count, destroyReward);
		}
		return returnVal;
	}
}
