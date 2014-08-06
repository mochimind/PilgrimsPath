package com.pilgrimspath.data.shuttle;

public class Shuttle {
	
	private int total;
	private int parked;
	private int gassing;
	
	public int type;
	
	public static final int ROLE_PARKED = 11000;
	public static final int ROLE_GASSING = 11010;
	
	
	public Shuttle(int _type, int _count) {
		total = _count;
		parked = total;
		gassing = 0;
		type = _type;
	}
	
	public int getTotal() { return total; }
	public int getParked() { return parked; }
	public int getGassing() { return gassing; }
	public int getRole(int role) {
		switch (role) {
		case ROLE_PARKED:
			return parked;
		case ROLE_GASSING:
			return gassing;
		default:
			return 0;
		}
	}
	
	public boolean reassign(int count, int fromRole, int toRole) {
		switch(fromRole) {
		case ROLE_PARKED:
			if (parked < count) { return false; }
			switch(toRole) {
			case ROLE_GASSING:
				parked -= count;
				gassing += count;
				return true;
			default:
				return false;	
			}
		case ROLE_GASSING:
			if (gassing < count) { return false; }
			switch (toRole) {
			case ROLE_PARKED:
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
	
	public void add(int count) { add(count, ROLE_PARKED); }
	
	public void add(int count, int role) {
		total += count;
		switch(role) {
		case ROLE_PARKED:
			parked += role;
			break;
		case ROLE_GASSING:
			gassing += role;
			break;
		default:
			// TODO: add some sort of error reporting here
		}
	}
	
	public boolean remove(int count, int role) {
		switch (role) {
		case ROLE_PARKED:
			if (parked < count) { return false; }
			parked -= count;
			total -= count;
			return true;
		case ROLE_GASSING:
			if (gassing < count) { return false; }
			gassing -= count;
			total -= count;
			return true;
		default:
			return false;
		}
	}
	
	public void tick() {
		
	}
	
}
