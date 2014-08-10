package com.pilgrimspath;

import com.pilgrimspath.data.DockManager;
import com.pilgrimspath.data.Ship;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

public class FragmentShipDock extends Fragment {

	private Ship ship;
	private ShuttleAdapter adapter;
	private int displayRole;
	
	private Button displayAll, displayGas;
	private ListView dock;
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		// TODO: may want to do some error checking here
		ship = ((ShipActivity) activity).ship;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedINstanceState) {
		View view = inflater.inflate(R.layout.fragment_ship_dock, container, false);
		
		// TODO: show the last view we were looking at
		displayRole = DockManager.ROLE_PARKED;
		
		dock = (ListView) view.findViewById(R.id.ship_dock_list);
		adapter = new ShuttleAdapter(getActivity(), R.layout.listview_shuttle, 
				ship.dock.shuttles, displayRole);
		dock.setAdapter(adapter);
		
		displayAll = (Button) view.findViewById(R.id.ship_dock_all);
		displayAll.setOnClickListener(new OnClickListener() {
			@Override public void onClick(View arg0) {
				displayRole = DockManager.ROLE_PARKED;
				adapter.updateRole(displayRole);
				adapter.notifyDataSetChanged();
				updateButtons();
			}			
		});
		displayGas = (Button) view.findViewById(R.id.ship_dock_gas);
		displayGas.setOnClickListener(new OnClickListener() {
			@Override public void onClick(View arg0) {
				displayRole = DockManager.ROLE_GASSING;
				adapter.updateRole(displayRole);
				adapter.notifyDataSetChanged();
				updateButtons();
			}			
		});
		
		updateButtons();
		return view;
	}
	
	private void updateButtons() {
		if (displayRole == DockManager.ROLE_GASSING) {
			displayGas.setEnabled(false);
		} else {
			displayGas.setEnabled(true);
		}
		if (displayRole == DockManager.ROLE_PARKED) {
			displayAll.setEnabled(false);
		} else {
			displayAll.setEnabled(true);
		}
	}

}
