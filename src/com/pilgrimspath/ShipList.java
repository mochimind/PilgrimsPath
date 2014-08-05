package com.pilgrimspath;

import com.pilgrimspath.data.Game;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class ShipList extends Activity implements
		FragmentMainNav.MainNavListener {

	public static final String DATA_SHIP_ID = "DataShipID";
	
	private ShipAdapter adapter;

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

}
