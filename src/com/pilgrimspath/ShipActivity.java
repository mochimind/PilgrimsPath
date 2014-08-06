package com.pilgrimspath;

import com.pilgrimspath.data.Game;
import com.pilgrimspath.data.Ship;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class ShipActivity extends Activity implements FragmentShipNav.ShipNavListener, FragmentMainNav.MainNavListener {

	private int curFrame;
	public Ship ship;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO make this load the last page that was being shows
		curFrame = FragmentShipNav.SHIP_NAV_MODULES;
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ship);

		Bundle args = getIntent().getExtras();
		ship = Game.playerFleet.ships.get(args.getInt(ShipList.DATA_SHIP_ID));
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ship, menu);
		return true;
	}
	
	private void enableFrame(int navElement) {
		if (navElement == curFrame) { return; }
		if (navElement == FragmentShipNav.SHIP_NAV_DOCK) {
			FragmentShipDock f = new FragmentShipDock();
			getFragmentManager().beginTransaction().replace(R.id.ship_body, f).commit();
		} else if (navElement == FragmentShipNav.SHIP_NAV_MODULES) {
			FragmentShipMods f = new FragmentShipMods();
			getFragmentManager().beginTransaction().replace(R.id.ship_body, f).commit();
		} else if (navElement == FragmentShipNav.SHIP_NAV_PEOPLE) {
			FragmentShipPeople f = new FragmentShipPeople();
			getFragmentManager().beginTransaction().replace(R.id.ship_body, f).commit();
		} else if (navElement == FragmentShipNav.SHIP_NAV_STATS) {
			FragmentShipStats f = new FragmentShipStats();
			getFragmentManager().beginTransaction().replace(R.id.ship_body, f).commit();
		}
	}

	@Override
	public void onNav(int navID) { enableFrame(navID); }

	@Override
	public int getSubMenuPage() { return curFrame; }

	@Override
	public int getCurrentPanel() { return FragmentMainNav.MAIN_NAV_SHIP; }
}
