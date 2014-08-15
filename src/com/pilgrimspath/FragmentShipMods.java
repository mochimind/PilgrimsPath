package com.pilgrimspath;

import com.pilgrimspath.data.Ship;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class FragmentShipMods extends UpdatableFragment {
	private Ship ship;
	private ModuleAdapter adapter;
	
	private UpdateActivity updatableParent;
	
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		// TODO: may want to do some error checking here
		ship = ((ShipContainerActivity) activity).getShip();
		updatableParent = ((UpdateActivity) activity);
	}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedINstanceState) {
		View view = inflater.inflate(R.layout.fragment_ship_mods, container, false);
		
		ListView lv = (ListView) view.findViewById(R.id.ship_mods_list);
		adapter = new ModuleAdapter(getActivity(), R.layout.listview_mods, ship.mods.modules, updatableParent);
		lv.setAdapter(adapter);
		
		return view;
	}
	
	@Override public void update() {
		adapter.notifyDataSetChanged();
	}
}
