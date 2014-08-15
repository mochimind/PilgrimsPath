package com.pilgrimspath;

import com.pilgrimspath.data.Game;
import com.pilgrimspath.data.Ship;
import com.pilgrimspath.data.Ticker;

import android.os.Bundle;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.Menu;

public class ShipActivity extends Activity implements FragmentShipNav.ShipNavListener, FragmentMainNav.MainNavListener, 
			UpdatableReceiver, ShipContainerActivity {

	private int curFrameType;
	private FragmentResources resourceFrag;
	private UpdatableFragment curFrame;
	private Ship ship;

	private final BroadcastReceiver updateReceiver = new BroadcastReceiver() {
		@Override public void onReceive(Context context, Intent intent) {
			if (intent.getAction().equals(Ticker.UPDATE_ACTIVITY_ACTION)) {
				update();
			}
		}
	};
	private final IntentFilter updateFilter = new IntentFilter(Ticker.UPDATE_ACTIVITY_ACTION);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO make this load the last page that was being shows
		// TODO: figure out if curFrame is needed, seems redundant
		curFrameType = 0;
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ship);

		Bundle args = getIntent().getExtras();
		ship = Game.playerFleet.ships.get(args.getInt(ShipList.DATA_SHIP_ID));
		//enableFrame(FragmentShipNav.SHIP_NAV_MODULES);
		
		resourceFrag = (FragmentResources) getFragmentManager().findFragmentById(R.id.ship_resources);
		Ticker.SetActiveActivity(this);
		registerReceiver(updateReceiver, updateFilter);
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		unregisterReceiver(updateReceiver);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ship, menu);
		return true;
	}
	
	private void enableFrame(int navElement) {
		if (navElement == curFrameType) { return; }
		if (navElement == FragmentShipNav.SHIP_NAV_DOCK) {
			FragmentShipDock f = new FragmentShipDock();
			getFragmentManager().beginTransaction().replace(R.id.ship_body, f).commit();
			curFrameType = FragmentShipNav.SHIP_NAV_DOCK;
			curFrame = f;
		} else if (navElement == FragmentShipNav.SHIP_NAV_MODULES) {
			FragmentShipMods f = new FragmentShipMods();
			getFragmentManager().beginTransaction().replace(R.id.ship_body, f).commit();
			curFrameType = FragmentShipNav.SHIP_NAV_MODULES;
			curFrame = f;
		} else if (navElement == FragmentShipNav.SHIP_NAV_PEOPLE) {
			FragmentShipPeople f = new FragmentShipPeople();
			getFragmentManager().beginTransaction().replace(R.id.ship_body, f).commit();
			curFrameType = FragmentShipNav.SHIP_NAV_PEOPLE;
			curFrame = f;
		} else if (navElement == FragmentShipNav.SHIP_NAV_STATS) {
			FragmentShipStats f = new FragmentShipStats();
			getFragmentManager().beginTransaction().replace(R.id.ship_body, f).commit();
			curFrameType = FragmentShipNav.SHIP_NAV_STATS;
			curFrame = f;
		}
	}

	@Override
	public void onNav(int navID) { enableFrame(navID); }

	@Override
	public int getSubMenuPage() { return curFrameType; }

	@Override
	public int getCurrentPanel() { return FragmentMainNav.MAIN_NAV_SHIP; }
	
	public void update() {
		resourceFrag.update();
		if (curFrame != null) { curFrame.update(); }
	}
	
	public Ship getShip() { return ship; }
}
