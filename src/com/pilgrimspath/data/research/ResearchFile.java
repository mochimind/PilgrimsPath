package com.pilgrimspath.data.research;

public class ResearchFile {
	public enum ProjectType {
		Fundamental,
		Prototype,
		Science,
		Civic
	}
	
	public int id;
	public ProjectType type;
}
