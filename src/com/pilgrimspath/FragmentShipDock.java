package com.pilgrimspath;

import com.pilgrimspath.data.DockManager;
import com.pilgrimspath.data.Ship;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class FragmentShipDock extends Fragment {

	private Ship ship;
	private ShuttleAdapter adapter;
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		// TODO: may want to do some error checking here
		ship = ((ShipActivity) activity).ship;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedINstanceState) {
		View view = inflater.inflate(R.layout.fragment_ship_dock, container, false);
		
		ListView dock = (ListView) view.findViewById(R.id.ship_dock_list);
		adapter = new ShuttleAdapter(getActivity(), R.layout.listview_shuttle, 
				ship.dock.shuttles, DockManager.ROLE_PARKED);
		dock.setAdapter(adapter);
		
		return view;
	}

}
