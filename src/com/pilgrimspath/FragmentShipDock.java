package com.pilgrimspath;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class FragmentShipDock extends Fragment {

	private int shipID;
	private ShuttleAdapter adapter;
	
	@Override
	public void onAttach(Activity activity) {
		// TODO: may want to do some error checking here
		shipID = ((ShipActivity) activity).shipID;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedINstanceState) {
		View view = inflater.inflate(R.layout.fragment_ship_dock, container, false);
		
		ListView dock = (ListView) view.findViewById(R.id.ship_dock_list);
		
		return view;
	}

}
