package com.pilgrimspath;

import java.util.List;

import com.pilgrimspath.data.DockManager;
import com.pilgrimspath.data.Ship;
import com.pilgrimspath.data.shuttle.Shuttle;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

public class ShuttleAdapter extends ArrayAdapter<Shuttle> {
	
	private List<Shuttle> shuttles;
	private Ship ship;
	private int role, roleCount, parkedCount, totalCount;
	private Button addShuttle, subShuttle;
	
	public ShuttleAdapter(Context context, int resource, List<Shuttle> objects, Ship _ship, int _role) {
		super(context, resource, objects);
		shuttles = objects;
		ship = _ship;
		role = _role;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) getContext()
		        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.listview_ship, parent, false);
		
		Shuttle rowItem = shuttles.get(position);

		TextView name = (TextView) rowView.findViewById(R.id.lv_shuttle_name);
		TextView count = (TextView) rowView.findViewById(R.id.lv_shuttle_count);
		addShuttle = (Button) rowView.findViewById(R.id.lv_shuttle_more);
		subShuttle = (Button) rowView.findViewById(R.id.lv_shuttle_less);

		name.setText(rowItem.getName());
		
		roleCount = ship.dock.getCount(rowItem.getName(), role);
		parkedCount = ship.dock.getCount(rowItem.getName(), DockManager.ROLE_PARKED);
		totalCount = ship.dock.getCount(rowItem.getName());
		
		if (role == DockManager.ROLE_PARKED) {
			count.setText(parkedCount + "/" + totalCount);
			addShuttle.setVisibility(View.INVISIBLE);
			subShuttle.setVisibility(View.INVISIBLE);	
		} else {
			count.setText( roleCount + "(" + parkedCount + ")");
			updateButtonStatus();
			addShuttle.setOnClickListener(new OnClickListener() {
				private String id;
				@Override public void onClick(View arg0) {
					ship.dock.assignShuttle(id, 1, DockManager.ROLE_PARKED, role);
					updateButtonStatus();
				}
				private OnClickListener _initialize(String _id) {
					id = _id;
					return this;
				}
			}._initialize(rowItem.getName()));
			
			subShuttle.setOnClickListener(new OnClickListener() {
				private String id;
				@Override public void onClick(View arg0) {
					ship.dock.assignShuttle(id, 1, role, DockManager.ROLE_PARKED);
					updateButtonStatus();
				}
				private OnClickListener _initialize(String _id) {
					id = _id;
					return this;
				}
			}._initialize(rowItem.getName()));
		}
		
		return rowView;
	}
	
	private void updateButtonStatus() {
		if (parkedCount == 0) {
			addShuttle.setEnabled(false);
		} else {
			addShuttle.setEnabled(true);
		}

		if (roleCount == 0) { 
			subShuttle.setEnabled(false); 
		} else {
			subShuttle.setEnabled(true);
		}

	}
}
