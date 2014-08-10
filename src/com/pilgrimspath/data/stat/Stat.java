package com.pilgrimspath.data.stat;

public abstract class Stat {
	public String name;
	
	public static final int STATUS_GREEN = 21000;
	public static final int STATUS_YELLOW = 21010;
	public static final int STATUS_RED = 21020;
	
	public Stat() {
		name = "";
	}
	
	public abstract int getStatus();
	
	public abstract String getValue();
}
