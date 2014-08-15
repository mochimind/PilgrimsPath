package com.pilgrimspath;

import com.pilgrimspath.data.Ticker;

import android.os.Bundle;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;

public class FleetActivity extends Activity implements FragmentMainNav.MainNavListener, UpdateActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fleet);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.fleet, menu);
		return true;
	}

	@Override
	public int getCurrentPanel() { return FragmentMainNav.MAIN_NAV_FLEET; }

	public void update() {}
}
