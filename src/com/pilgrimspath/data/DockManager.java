package com.pilgrimspath.data;

import java.util.ArrayList;
import java.util.List;

import com.pilgrimspath.data.shuttle.Shuttle;
import com.pilgrimspath.data.shuttle.ShuttleFactory;

public class DockManager {
	private Ship container;
	private int maxSpace;
	private int usedSpace;
	private List<Shuttle> parkedShuttles;
	private List<Shuttle> gassingShuttles;
	
	public static int START_SIZE = 2;

	public static final int ROLE_PARKED = 0;
	public static final int ROLE_GASSING = 100;

	// TODO: change so that crew on shuttle aren't counted to crew on ship for life support
	
	public DockManager(Ship _container) {
		parkedShuttles = new ArrayList<Shuttle>();
		gassingShuttles = new ArrayList<Shuttle>();
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
	
	public boolean addShuttle(Shuttle newShuttle) {
		int requiredSpace = newShuttle.getSizeRequirement();
		if (maxSpace - usedSpace < requiredSpace) { return false; }
		usedSpace += requiredSpace;
		
		for (int i=0 ; i<parkedShuttles.size() ; i++) {
			if (parkedShuttles.get(i).getClass() == newShuttle.getClass()) {
				parkedShuttles.get(i).merge(newShuttle);
				return true;
			}
		}
		parkedShuttles.add(newShuttle);
		return true;
	}
	
	public boolean assignShuttle(String shuttleType, int amount, int fromRole, int toRole) {
		if (fromRole == toRole) { return false; }
		List<Shuttle> fromList, toList;
		if (fromRole == ROLE_PARKED) {
			fromList = parkedShuttles;
		} else if (fromRole == ROLE_GASSING) {
			fromList = gassingShuttles;
		} else {
			return false;
		}
		
		if (toRole == ROLE_PARKED) {
			toList = parkedShuttles;
		} else if (toRole == ROLE_GASSING) {
			toList = gassingShuttles;
		} else {
			return false;
		}
		
		for (int i=0 ; i<fromList.size() ; i++) {
			if (fromList.get(i).getName().equals(shuttleType)) {
				if (fromList.get(i).count >= amount) {
					// we have enough, move them to the activeShuttles space
					if (!ModifyList(toList, fromList.get(i).getName(), amount)) { return false; }
					if (!ModifyList(fromList, fromList.get(i).getName(), -amount)) { return false; }
					return true;
				} else {
					// not enough
					return false;
				}
			}
		}
		return false;
	}
	
	public void tick() {
		// try to maintain crew on ships
		for (int i=0 ; i<gassingShuttles.size() ; i++) {
			Shuttle p = gassingShuttles.get(i);
			int staffed = container.peeps.requestLabor(p.count,	p.getCrewRequirement());
			if (staffed == 0) {
				continue;
			} else if (staffed < p.count) {
				// didn't staff enough -> bring them back to base
				int recall = p.count - staffed;
				ModifyList(gassingShuttles, p.getName(), -recall);
			}
			
			// perform actions for each ship
			container.fleet.resources.add(new Resource(Resource.GAS_NAME, staffed * p.gasPerTick()));
		}		
	}
	
	// gets the number of Shuttles of the type entry from all lists
	public int getCount(String entry) {
		int out = 0;
		out += getCount(parkedShuttles, entry);
		out += getCount(gassingShuttles, entry);
		
		return out;
	}
	
	// gets number of shuttles of type entry with the role
	public int getCount(String entry, int role) {
		switch (role) {
		case ROLE_PARKED:
			return getCount(parkedShuttles, entry);
		case ROLE_GASSING:
			return getCount(gassingShuttles, entry);
		default:
			return 0;
		}
	}
	
	private static int getCount(List<Shuttle> useList, String entry) {
		for (int i=0 ; i<useList.size() ; i++) {
			if (useList.get(i).getName().equals(entry)) {
				return useList.get(i).count;
			}
		}
		return 0;
	}
	
	// adds count number of entry Shuttles to the specified list
	public static boolean ModifyList(List<Shuttle> list, String entry, int count) {
		if (list == null || entry == null || count == 0) { return false; }
		boolean isAdd = count > 0;
		for (int i=0 ; i<list.size() ; i++) {
			if (list.get(i).getName().equals(entry)) {
				if (isAdd) { 
					list.get(i).add(count);
					return true;
				} else {
					if (!list.get(i).remove(count)) { return false; }
					if (list.get(i).count == 0) {
						list.remove(i);
					}
					return true;
				}
			}
		}
		
		// not in list
		if (isAdd) {
			list.add(ShuttleFactory.CreateShuttle(entry, count));
			return true;
		} else { 
			return false;
		}
	}
}
