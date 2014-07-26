package com.pilgrimspath.data.resource;

import java.util.ArrayList;
import java.util.List;

public class ResourceBundle {
	public List<Resource> resources;
	
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
	
	public void add(Resource res) {
		for (int i=0 ; i<resources.size() ; i++) {
			// TODO: implement equals function
			if (resources.get(i).name == res.name) {
				resources.get(i).add(res);
				return;
			}
		}
		// not in the bundle
		resources.add(new Resource(res));
	}
	
	public boolean remove(Resource res) {
		for (int i=0 ; i<resources.size() ; i++) {
			if (resources.get(i).name == res.name) {
				return resources.get(i).remove(res);
			}
		}
		return false;
	}
}
