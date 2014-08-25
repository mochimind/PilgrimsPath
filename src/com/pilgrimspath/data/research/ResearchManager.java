package com.pilgrimspath.data.research;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;

import com.pilgrimspath.data.Ticker;
import com.pilgrimspath.data.research.ResearchFile.ProjectType;

public class ResearchManager {
	public List<ResearchTree> trees;

	public static final String RESEARCH_COMPLETE_ACTION = "ResearchCompleteAction";
	
	public ResearchManager() {
		trees = new ArrayList<ResearchTree>();
		trees.add(new ResearchTree(ProjectType.Fundamental, this));
		trees.add(new ResearchTree(ProjectType.Civic, this));
		trees.add(new ResearchTree(ProjectType.Prototype, this));
		trees.add(new ResearchTree(ProjectType.Science, this));
	}
	
	public synchronized boolean beginResearch(int id, ProjectType type) {
		ResearchTree tree = getTree(type);
		if (tree == null) { return false; }
		return tree.beginResearch(id);
	}
	
	public synchronized void tick() {
		for (int i=0 ; i<trees.size() ; i++) {
			trees.get(i).tick();
		}
	}
	
	public synchronized void adjustResearchPerTick(int points, ProjectType tree) {
		ResearchTree token = getTree(tree);
		if (token == null) { return; }
		token.rpPerTick += points;
		if (token.rpPerTick < 0) { token.rpPerTick = 0; }
	}
	
	public synchronized ResearchTree getTree(ProjectType type) {
		for (int i=0 ; i<trees.size() ; i++) {
			if (trees.get(i).branch == type) {
				return trees.get(i);
			}
		}
		return null;
	}
	
	public synchronized void completeProject(int id, ProjectType branch) {
		Activity a = Ticker.GetActiveActivity();
		if (a != null) {
			Intent intent = new Intent(RESEARCH_COMPLETE_ACTION);
			a.sendBroadcast(intent);
			
			List<ResearchProject> unlocked = Archive.Unlock(id, branch);
			for (int i=0 ; i<unlocked.size() ; i++) {
				// TODO: make this more efficient, very dumb code right now
				for (int j=0 ; j<trees.size() ; j++) {
					if (trees.get(j).branch == unlocked.get(i).file.type) {
						trees.get(j).unlock(unlocked.get(i));
					}
				}
			}
		}
	}
}
