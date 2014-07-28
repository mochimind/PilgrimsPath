package com.pilgrimspath.data;

import java.util.ArrayList;
import java.util.List;


public class Fleet {
	public ResourceBundle resources;
	public List<Ship> ships;
	
	public Fleet() {
		resources = new ResourceBundle();
		ships = new ArrayList<Ship>();
	}
	
	public void addShip(Ship ship) {
		ships.add(ship);
	}
	
	public void tick() {
		for (int i=0 ; i<ships.size() ; i++) {
			ships.get(i).tick();
		}
	}
	
}
