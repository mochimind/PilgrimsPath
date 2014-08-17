package com.pilgrimspath.data;

import java.util.ArrayList;
import java.util.List;

import com.pilgrimspath.data.shuttle.Shuttle;
import com.pilgrimspath.data.shuttle.ShuttleStatManager;

public class DockManager {
	private Ship container;
	private int maxSpace;
	private int usedSpace;
	public List<Shuttle> shuttles;
	
	public static int START_SIZE = 2;

	public static final int ROLE_PARKED = 0;
	public static final int ROLE_GASSING = 100;

	// TODO: change so that crew on shuttle aren't counted to crew on ship for life support
	
	public DockManager(Ship _container) {
		shuttles = new ArrayList<Shuttle>();
		container = _container;
		maxSpace = START_SIZE;
		usedSpace = 0;
	}
	
	// returns false if the space used is more than after the update
	public boolean updateSpace(int newSpace) {
		if (newSpace > maxSpace) {
			// adding space is always good
			maxSpace = newSpace;
			return true;
		} else if (newSpace > usedSpace) {
			// removing space, but we have capacity
			maxSpace = newSpace;
			return true;
		} else {
			// we can't take away space unless we get rid of some ships first
			return false;
		}
	}
	
	public boolean awardShuttle(int type, int count) {
		int requiredSpace = ShuttleStatManager.GetSpaceRequirement(type, count);
		if (maxSpace - usedSpace < requiredSpace) { return false; }
		usedSpace += requiredSpace;
		
		for (int i=0 ; i<shuttles.size() ; i++) {
			if (shuttles.get(i).type == type) {
				shuttles.get(i).award(count);
				return true;
			}
		}
		shuttles.add(new Shuttle(type, count));
		return true;
	}
	
	public boolean assignShuttle(int shuttleType, int amount, int fromRole, int toRole) {
		if (fromRole == toRole) { return false; }
		for (int i=0 ; i<shuttles.size() ; i++) {
			if (shuttles.get(i).type == shuttleType) {
				return shuttles.get(i).reassign(amount, fromRole, toRole);
			}
		}
		return false;
	}
	
	public void tick() {
		for (int i=0 ; i<shuttles.size() ; i++) {
			// try to maintain crew on gassing shuttles
			Shuttle token = shuttles.get(i);
			int staffed = container.peeps.requestLabor(token.getGassing(), 
					ShuttleStatManager.GetStat(token.type).crewRequired());
			if (staffed == 0) {
				continue;
			} else if (staffed < token.getGassing()) {
				// didn't staff enough -> bring them back to base
				int recall = token.getGassing() - staffed;
				token.reassign(recall, ROLE_GASSING, ROLE_PARKED);
			}
			
			// now perform the gassing action
			container.fleet.resources.add(new Resource(Resource.GAS_NAME, 
					staffed * ShuttleStatManager.GetStat(token.type).gasPerTick()));
		}		
	}
	
	// gets the number of Shuttles of the type entry from all lists
	public int getCount(int type) {
		for (int i=0 ; i<shuttles.size() ; i++) {
			if (shuttles.get(i).type == type) { return shuttles.get(i).getTotal(); }
		}
		return 0;
	}
	
	public int getCount(int type, int role) {
		for (int i=0 ; i<shuttles.size() ; i++) {
			if (shuttles.get(i).type == type) { return shuttles.get(i).getRoleCount(role); }
		}
		return 0;
	}	
}
