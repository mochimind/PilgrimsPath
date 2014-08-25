package com.pilgrimspath.data.research;

import com.pilgrimspath.data.research.ResearchFile.ProjectType;

public class ResearchProject {
	public ResearchFile file;
	public int points;
	public int progress;
	
	public boolean complete() { return progress >= points; }
	
	public boolean isFile(int id) {
		return false;
	}
	
	public boolean isFile(ProjectType type) {
		return false;
	}
}
