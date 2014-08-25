package com.pilgrimspath.data.research;

import java.util.ArrayList;
import java.util.List;

import com.pilgrimspath.data.research.ResearchFile.ProjectType;


public class ResearchTree {
	public List<ResearchProject> available;
	public List<Integer> complete;
	public ResearchProject researching;
	public ProjectType branch;
	
	public int rpPerTick;
	
	private ResearchManager parent;
	
	public ResearchTree(ProjectType _branch, ResearchManager _parent) {
		branch = _branch;
		parent = _parent;
		available = new ArrayList<ResearchProject>();
		complete = new ArrayList<Integer>();
	}
	
	public synchronized boolean beginResearch(int id) {
		ResearchProject file = null;
		// check if this is a project we've started already
		for (int i=0 ; i<available.size() ; i++) {
			if (available.get(i).isFile(id)) { file = available.get(i); }
		}
		
		if (file == null) { return false; }
		
		researching = file;
		
		// TODO: consider checking the completed projects as well
		return true;
	}
	
	public synchronized void tick() {
		if (researching != null) {
			researching.progress += rpPerTick;
			if (researching.complete()) {
				complete.add(researching.file.id);
				parent.completeProject(researching.file.id, branch);
				researching = null;
			}
		}
	}
	
	public synchronized void unlock(ResearchProject file) {
		available.add(file);
	}
}
