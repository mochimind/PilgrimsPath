package com.pilgrimspath.data.module;

import com.pilgrimspath.data.resource.ResourceBundle;

public class Module {
	
	public static String HYDROPONICS = "Hydroponics";
	public static String POWER_PLANT = "Power Plant";
	public static String POLYMER_PLANT = "Polymer Plant";
	public static String BASIC_HOUSING = "Basic Housing";
	
	protected int operational = 0;
	protected int built = 0;

	public String name() { return ""; }
	public ResourceBundle buildCost() { return null; }
	public ResourceBundle demolishReward() { return null; }
	public int laborRequired() { return 0; }
	public int powerRequired() { return 0; }
}
