package com.pilgrimspath.data;

import java.util.ArrayList;
import java.util.List;

public class ResourceBundle {
	public List<Resource> resources;
	//private double containerSize = 0f;
	
	public ResourceBundle() {
		resources = new ArrayList<Resource>();
	}
	
	// we want to clone the list, not use the original in case the original is changed
	public ResourceBundle(List<Resource> res) {
		resources = new ArrayList<Resource>();
		for (int i=0 ; i<res.size() ; i++) {
			resources.add(new Resource(res.get(i)));
		}
	}
	
	public double getResourceCount(String id) {
		for (int i=0 ; i<resources.size() ; i++) {
			if (resources.get(i).name.equals(id)) { return resources.get(i).amount; }
		}
		return 0f;
	}
	
	public void add(Resource res) {
		for (int i=0 ; i<resources.size() ; i++) {
			// TODO: implement equals function
			if (resources.get(i).name.equals(res.name)) {
				resources.get(i).add(res);
				return;
			}
		}
		// not in the bundle
		resources.add(new Resource(res));
	}
	
	public boolean remove(Resource res) {
		for (int i=0 ; i<resources.size() ; i++) {
			if (resources.get(i).name.equals(res.name)) {
				return resources.get(i).remove(res);
			}
		}
		return false;
	}
	
	public void multiply(int count) {
		
	}
	
	public void divide(int count) {
		
	}
	
	// checks whether we can remove units number of the specified ResourceBundle from this ResourceBundle
	public int checkAvailability(int units, ResourceBundle unitCost) {
		int supportable = units;
		for (int i=0 ; i<unitCost.resources.size() ; i++) {
			Resource need = unitCost.resources.get(i);

			boolean found = false;
			for (int j=0 ; j<resources.size() ; j++) {
				Resource stash = resources.get(j);
				if (stash.name.equals(need.name)) {
					found = true;
					supportable = (int) Math.min(supportable, stash.amount / need.amount);
					continue;
				}
			}
			if (!found) { return 0; }
		}
		
		return supportable;
	}
	
	public int remove(int units, ResourceBundle unitCost) {
		int removed = units;
		
		for (int i=0 ; i<unitCost.resources.size() ; i++) {
			Resource need = unitCost.resources.get(i);

			boolean found = false;
			for (int j=0 ; j<resources.size() ; j++) {
				Resource stash = resources.get(j);
				if (stash.name.equals(need.name)) {
					found = true;
					removed = Math.min(removed, stash.remove(units, need));
					break;
				}
			}
			if (!found) { return 0; }
		}
		
		return removed;
	}
	
	public void add(int units, ResourceBundle unitCost) {
		for (int i=0 ; i<unitCost.resources.size() ; i++) {
			Resource give = unitCost.resources.get(i);

			boolean found = false;
			for (int j=0 ; j<resources.size() ; j++) {
				Resource stash = resources.get(j);
				if (stash.name.equals(give.name)) {
					found = true;
					stash.add(units, give);
					break;
				}
			}
			if (!found) { resources.add(units, give); }
		}
	}
}
