package com.pilgrimspath;

import java.util.List;

import com.pilgrimspath.data.DockManager;
import com.pilgrimspath.data.shuttle.Shuttle;
import com.pilgrimspath.data.shuttle.ShuttleStatManager;

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
	private int layout;
	private int role;
	
	public ShuttleAdapter(Context context, int resource, List<Shuttle> objects, int _role) {
		super(context, resource, objects);
		shuttles = objects;
		role = _role;
		layout = resource;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) getContext()
		        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = convertView == null ? inflater.inflate(layout, parent, false) : convertView;
		
		Shuttle rowItem = shuttles.get(position);

		TextView name = (TextView) rowView.findViewById(R.id.lv_shuttle_name);
		Button addShuttle = (Button) rowView.findViewById(R.id.lv_shuttle_more);
		Button subShuttle = (Button) rowView.findViewById(R.id.lv_shuttle_less);

		name.setText(ShuttleStatManager.GetStat(rowItem.type).name());
		
		int roleCount = rowItem.getRoleCount(role);
		int parkedCount = rowItem.getParked();
		int totalCount = rowItem.getTotal();

		updateGUI(rowView, roleCount, parkedCount, totalCount);
		
		addShuttle.setOnClickListener(new OnClickListener() {
			private int id;
			private View parent;
			@Override public void onClick(View arg0) {
				Shuttle s = shuttles.get(id);
				if (role == DockManager.ROLE_PARKED) {
					s.build(1);
				} else {
					s.reassign(1, DockManager.ROLE_PARKED, role);
				}
				int roleCount = s.getRoleCount(role);
				int parkedCount = s.getParked();
				int totalCount = s.getTotal();
				updateGUI(parent, roleCount, parkedCount, totalCount);
			}
			private OnClickListener _initialize(int _id, View _parent) {
				id = _id;
				parent = _parent;
				return this;
			}
		}._initialize(position, rowView));
		
		subShuttle.setOnClickListener(new OnClickListener() {
			private int id;
			private View parent;
			@Override public void onClick(View arg0) {
				Shuttle s = shuttles.get(id);
				
				if (role == DockManager.ROLE_PARKED) {
					s.destroy(1);
				} else {
					s.reassign(1, role, DockManager.ROLE_PARKED);
				}
				int roleCount = s.getRoleCount(role);
				int parkedCount = s.getParked();
				int totalCount = s.getTotal();
				updateGUI(parent, roleCount, parkedCount, totalCount);
			}
			private OnClickListener _initialize(int _id, View _parent) {
				id = _id;
				parent = _parent;
				return this;
			}
		}._initialize(position, rowView));
		
		return rowView;
	}
	
	private void updateGUI(View parent, int roleCount, int parkedCount, int totalCount) {
		TextView count = (TextView) parent.findViewById(R.id.lv_shuttle_count);
		Button addShuttle = (Button) parent.findViewById(R.id.lv_shuttle_more);
		Button subShuttle = (Button) parent.findViewById(R.id.lv_shuttle_less);
		
		if (role == DockManager.ROLE_PARKED) {
			count.setText(parkedCount + "/" + totalCount);
			if (parkedCount == 0) {
				subShuttle.setEnabled(false);
			} else {
				subShuttle.setEnabled(true);
			}
			addShuttle.setEnabled(true);
		} else {
			count.setText( roleCount + "(" + parkedCount + ")");
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
	
	public void updateRole(int _role) { role = _role; }
}
