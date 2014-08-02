package com.pilgrimspath;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;

public class FragmentMainNav extends Fragment {
	
	public static final int MAIN_NAV_FLEET = 1011000;
	public static final int MAIN_NAV_SHIP = 1011010;
	public static final int MAIN_NAV_NEWS = 1011020;
	public static final int MAIN_NAV_COMBAT = 1011030;
	
	public interface MainNavListener {
		public int getCurrentPanel();
	}
	
	private MainNavListener activityCallback;
	
	private Button fleetNav, shipNav, newsNav, combatNav;
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			activityCallback = (MainNavListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException (activity.toString() + " must implement NavBarListener");
		}
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedINstanceState) {
		View view = inflater.inflate(R.layout.fragment_main_nav, container, false);
		
		fleetNav = (Button) view.findViewById(R.id.main_nav_fleet);
		//fleetNav.setEnabled(false);
		fleetNav.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				changePanel(MAIN_NAV_FLEET);
			}
		});
		
		shipNav = (Button) view.findViewById(R.id.main_nav_ship);
		shipNav.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				changePanel(MAIN_NAV_SHIP);
			}
		});

		newsNav = (Button) view.findViewById(R.id.main_nav_news);
		newsNav.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				changePanel(MAIN_NAV_NEWS);
			}
		});

		combatNav = (Button) view.findViewById(R.id.main_nav_combat);
		combatNav.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				changePanel(MAIN_NAV_COMBAT);
			}
		});

		adjustButtons(activityCallback.getCurrentPanel());
		return view;
	}
	
	private void adjustButtons(int id) {
		if (id == MAIN_NAV_FLEET) {
			fleetNav.setEnabled(false);
		} else if (id== MAIN_NAV_SHIP) {
			shipNav.setEnabled(false);
		} else if (id == MAIN_NAV_NEWS) {
			newsNav.setEnabled(false);
		} else if (id == MAIN_NAV_COMBAT) {
			combatNav.setEnabled(false);
		}
	}
	
	private void changePanel(int newPanel) {
		Intent intent;
		switch (newPanel) {
		case FragmentMainNav.MAIN_NAV_COMBAT:
			intent = new Intent(getActivity(), CombatActivity.class);
			startActivity(intent);
			break;
		case FragmentMainNav.MAIN_NAV_FLEET:
			intent = new Intent(getActivity(), FleetActivity.class);
			startActivity(intent);
			break;
		case FragmentMainNav.MAIN_NAV_NEWS:
			intent = new Intent(getActivity(), NewsActivity.class);
			startActivity(intent);
			break;
		case FragmentMainNav.MAIN_NAV_SHIP:
			intent = new Intent(getActivity(), ShipActivity.class);
			startActivity(intent);
			break;
		}
	}
}
