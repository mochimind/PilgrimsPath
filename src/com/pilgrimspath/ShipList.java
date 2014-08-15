package com.pilgrimspath;

import com.pilgrimspath.data.Game;
import com.pilgrimspath.data.Ticker;

import android.os.Bundle;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class ShipList extends Activity implements
		FragmentMainNav.MainNavListener, UpdatableReceiver {

	public static final String DATA_SHIP_ID = "DataShipID";
	
	private ShipAdapter adapter;

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
		setContentView(R.layout.activity_ship_list);

		ListView shipList = (ListView) findViewById(R.id.ship_list);
		adapter = new ShipAdapter(this, R.layout.listview_ship,
				Game.playerFleet.ships);
		shipList.setAdapter(adapter);

		shipList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int pos,
					long id) {
				Intent teleport = new Intent(ShipList.this, ShipActivity.class);
				teleport.putExtra(DATA_SHIP_ID, pos);
				startActivity(teleport);
			}
		});
		
		resourceFrag = (FragmentResources) getFragmentManager().findFragmentById(R.id.ship_list_resources);
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
		getMenuInflater().inflate(R.menu.ship_list, menu);
		return true;
	}

	@Override
	public int getCurrentPanel() {
		return FragmentMainNav.MAIN_NAV_SHIP;
	}
	
	public void update() {
		adapter.notifyDataSetChanged();
		resourceFrag.update();
	}
}
