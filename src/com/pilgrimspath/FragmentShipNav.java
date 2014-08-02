package com.pilgrimspath;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;

public class FragmentShipNav extends Fragment {
	
	public static final int SHIP_NAV_MODULES = 1001000;
	public static final int SHIP_NAV_PEOPLE = 1001010;
	public static final int SHIP_NAV_DOCK = 1001020;
	public static final int SHIP_NAV_STATS = 1001030;
	
	private ShipNavListener activityCallback;
	
	private Button modules, people, shuttle, stat;
	
	public interface ShipNavListener {
		public void onNav(int navID);
		public int getSubMenuPage();
	}
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			activityCallback = (ShipNavListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException (activity.toString() + " must implement NavBarListener");
		}
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedINstanceState) {
		View view = inflater.inflate(R.layout.fragment_ship_nav, container, false);

		modules = (Button) view.findViewById(R.id.ship_modules);
		modules.setOnClickListener(new OnClickListener() {
			@Override 
			public void onClick(View view) { 
				activityCallback.onNav(SHIP_NAV_MODULES);
				updateButtons(SHIP_NAV_MODULES);
			}
		});
		
		people = (Button) view.findViewById(R.id.ship_people);
		people.setOnClickListener(new OnClickListener() {
			@Override 
			public void onClick(View view) { 
				activityCallback.onNav(SHIP_NAV_PEOPLE);
				updateButtons(SHIP_NAV_PEOPLE);
			}
		});
		
		shuttle = (Button) view.findViewById(R.id.ship_shuttles);
		shuttle.setOnClickListener(new OnClickListener() {
			@Override 
			public void onClick(View view) {
				activityCallback.onNav(SHIP_NAV_DOCK);
				updateButtons(SHIP_NAV_DOCK);
			}
		});

		stat = (Button) view.findViewById(R.id.ship_stats); 
		stat.setOnClickListener(new OnClickListener() {
			@Override 
			public void onClick(View view) {
				activityCallback.onNav(SHIP_NAV_STATS);
				updateButtons(SHIP_NAV_STATS);
			}
		});
		updateButtons(activityCallback.getSubMenuPage());

		return view;
	}
	
	private void updateButtons(int pageId) {
		if (pageId == SHIP_NAV_MODULES) {
			modules.setEnabled(false);
		} else {
			modules.setEnabled(true);
		}
		if (pageId == SHIP_NAV_PEOPLE) {
			people.setEnabled(false);
		} else {
			people.setEnabled(true);
		}
		if (pageId == SHIP_NAV_DOCK) {
			shuttle.setEnabled(false);
		} else {
			shuttle.setEnabled(true);
		}
		if (pageId == SHIP_NAV_STATS) {
			stat.setEnabled(false);
		} else {
			stat.setEnabled(true);
		}
	}
}