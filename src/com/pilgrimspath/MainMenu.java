package com.pilgrimspath;

import com.pilgrimspath.data.Game;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainMenu extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_menu);
		
		Button newGame = (Button) findViewById(R.id.main_new_game);
		newGame.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				Game.StartNewGame();
				Intent intent = new Intent(MainMenu.this, FleetActivity.class);
				startActivity(intent);

			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_menu, menu);
		return true;
	}

}
