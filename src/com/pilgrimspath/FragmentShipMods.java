package com.pilgrimspath;

import com.pilgrimspath.data.Ship;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentShipMods extends Fragment {
	private Ship ship;
	
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		// TODO: may want to do some error checking here
		ship = ((ShipActivity) activity).ship;
	}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedINstanceState) {
		View view = inflater.inflate(R.layout.fragment_ship_people, container, false);
		
		return view;
	}
}
