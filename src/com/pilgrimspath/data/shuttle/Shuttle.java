package com.pilgrimspath.data.shuttle;

public class Shuttle {
	
	public int count = 0;
	
	public int size() { return 0; }
	public int gasPerTick() { return 0; }
	public int orePerTick() { return 0; }
	public String getDescription() { return ""; }
	public String getName() { return ""; }
	public int getCrewRequirement() { return 0; }
	
	public Shuttle(int _count) {
		count = _count;
	}
	
	public int getSizeRequirement() { return size() * count; }
	
	public boolean activate(int num) {
		return false;
	}
	
	public boolean deactivate(int num) {
		return false;
	}
	
	public void tick() {
		
	}
	
	public void merge(Shuttle other) {
		if (other.getClass() == this.getClass()) {
			count += other.count;
		}
	}
	
	public void add(int _count) { count += _count; }
	
	public boolean remove(int _count) { 
		if (count < _count) {
			return false;
		} else {
			count -= _count;
			return true;
		}
	}
}
