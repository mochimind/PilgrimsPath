package com.pilgrimspath;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class FleetActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fleet);
		
		Button fleetNav = (Button) findViewById(R.id.fleet_nav_fleet);
		fleetNav.setEnabled(false);
		
		Button shipNav = (Button) findViewById(R.id.fleet_nav_ship);
		shipNav.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				// warp to the patient consent builder
				Intent intent = new Intent(FleetActivity.this, ShipActivity.class);
				startActivity(intent);

			}
		});

		Button newsNav = (Button) findViewById(R.id.fleet_nav_news);
		newsNav.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				// warp to the patient consent builder
				Intent intent = new Intent(FleetActivity.this, NewsActivity.class);
				startActivity(intent);

			}
		});

		Button combatNav = (Button) findViewById(R.id.fleet_nav_combat);
		combatNav.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				// warp to the patient consent builder
				Intent intent = new Intent(FleetActivity.this, CombatActivity.class);
				startActivity(intent);

			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.fleet, menu);
		return true;
	}

}
