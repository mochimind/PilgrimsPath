package com.pilgrimspath;

import com.pilgrimspath.data.Game;
import com.pilgrimspath.data.Resource;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentResources extends UpdatableFragment {

	private TextView alloy, gas, fuel, food, ore, polymer, power;
	private ShipContainerActivity parent;
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		// TODO: may want to do some error checking here
		try {
			parent = ((ShipContainerActivity) activity);
		} catch (ClassCastException e) {
			parent = null;
		}
	}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedINstanceState) {
		View view = inflater.inflate(R.layout.fragment_resources, container, false);

		alloy = (TextView) view.findViewById(R.id.resources_alloy);
		gas = (TextView) view.findViewById(R.id.resources_gas);
		fuel = (TextView) view.findViewById(R.id.resources_fuel);
		food = (TextView) view.findViewById(R.id.resources_food);
		ore = (TextView) view.findViewById(R.id.resources_ore);
		polymer = (TextView) view.findViewById(R.id.resources_polymer);
		power = (TextView) view.findViewById(R.id.resources_power);
		
		update();
		return view;
	}
	
	@Override public void update() {
		int setVal = (int)Game.playerFleet.resources.getResourceCount(Resource.ALLOYS_NAME);
		alloy.setText("AL" + (setVal > 999 ? "999+" : setVal));
		setVal = (int)Game.playerFleet.resources.getResourceCount(Resource.GAS_NAME);
		gas.setText("GA" + (setVal > 999 ? "999+" : setVal));
		setVal = (int) Game.playerFleet.resources.getResourceCount(Resource.FUEL_NAME);
		fuel.setText("FU" + (setVal > 999 ? "999+" : setVal));
		setVal = (int) Game.playerFleet.resources.getResourceCount(Resource.FOOD_NAME);
		food.setText("FO" + (setVal > 999 ? "999+" : setVal));
		setVal = (int) Game.playerFleet.resources.getResourceCount(Resource.ORE_NAME);
		ore.setText("OR" + (setVal > 999 ? "999+" : setVal));
		setVal = (int) Game.playerFleet.resources.getResourceCount(Resource.POLYMER_NAME);
		polymer.setText("PO" + (setVal > 999 ? "999+" : setVal));
		if (parent != null && parent.getShip() != null) {
			setVal = (int) (parent.getShip().mods.getPowerAvailable());
			if (setVal > 0) {
				power.setText("PW" + setVal);
			} else {
				power.setText("PW" + "!!");
			}
		} else {
			power.setText("PW NA");
		}
	}
}
