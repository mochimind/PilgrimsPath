package com.pilgrimspath.data;

public class Resource {

	public static final String GAS_NAME = "gasses";
	public static final String FOOD_NAME = "food";
	public static final String ORE_NAME = "ore";
	public static final String DARKMATTER_NAME = "darkmatter";

	public static final String FUEL_NAME = "fuel";
	public static final String POLYMER_NAME = "polymer";
	public static final String METAL_NAME = "metal";
	public static final String MINERALS_NAME = "minerals";
	
	public static final String ALLOYS_NAME = "alloys";
	public static final String ELECTRONICS_NAME = "electronics";
	public static final String MACHINERY_NAME = "machinery";

	public static final String WARP_CORE_NAME = "warp core";
	public static final String TURRET_NAME = "turret assembly";
	public static final String SHIELDING_NAME = "shielding";
	
	public String name;
	public double amount;
	public double volume;
	
	// TODO: change this to implement volume
	public Resource(String _name, double _amount) {
		name = _name;
		amount = _amount;
		volume = 1f;
	}
	
	public Resource(Resource res) {
		name = res.name;
		amount = res.amount;
		volume = res.volume;
	}
	
	public boolean add(Resource res) {
		if (res.name == name) {
			amount += res.amount;
			volume += res.volume;
			return true;
		}
		return false;
	}
	
	public boolean remove(Resource res) {
		if (res.name.equals(name)) {
			if (amount < res.amount) {
				return false;
			} else {
				amount -= res.amount;
				volume -= res.volume;
				return true;
			}
		}
		return false;
	}
	
	// removes units number of res from this resource stockpile
	public int remove(int units, Resource res) {
		if (res.name.equals(name)) {
			int removing = (int) Math.min(amount / res.amount, units);
			amount -= res.amount * removing;
			return removing;
		} else {
			return 0;
		}
	}
	
	public void add(int units, Resource res) {
		if (res.name.equals(name)) {
			amount += res.amount * units;
		}
	}
}
