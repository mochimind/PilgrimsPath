package com.pilgrimspath.data;

import android.app.Activity;
import android.content.Intent;

public class Ticker implements Runnable {

	public static final int TICK_INTERVAL = 2000;
	public static final String UPDATE_ACTIVITY_ACTION = "com.pilgrimspath.UpdateActivity";
	
	private static Activity active;
	
	public static synchronized void SetActiveActivity(Activity a) { active = a; }
	private static synchronized Activity GetActiveActivity() { return active; }
	@Override
	public void run() {
		while (true) {
			// TODO: we should be updating all ships here
			Game.playerFleet.tick();
			
			// update the active activity's display
			Activity a = GetActiveActivity();
			if (a != null) {
				Intent i = new Intent(UPDATE_ACTIVITY_ACTION);
				a.sendBroadcast(i);
			}
			
			try {
				Thread.sleep(TICK_INTERVAL);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	

}
