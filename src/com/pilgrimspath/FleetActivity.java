package com.pilgrimspath;

import com.pilgrimspath.data.Ticker;

import android.os.Bundle;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.Menu;

public class FleetActivity extends Activity implements FragmentMainNav.MainNavListener, UpdatableReceiver {

	private FragmentResources resourceFrag;
	
	
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
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fleet);

		resourceFrag = (FragmentResources) getFragmentManager().findFragmentById(R.id.fleet_resources);
		Ticker.SetActiveActivity(this);
		registerReceiver(updateReceiver, updateFilter);
	}
	
	@Override
	public void onDestroy() {
		unregisterReceiver(updateReceiver);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.fleet, menu);
		return true;
	}

	@Override
	public int getCurrentPanel() { return FragmentMainNav.MAIN_NAV_FLEET; }

	public void update() {
		resourceFrag.update();
	}
}
