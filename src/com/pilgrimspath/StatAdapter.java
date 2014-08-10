package com.pilgrimspath;

import java.util.List;

import com.pilgrimspath.data.stat.Stat;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class StatAdapter extends ArrayAdapter<Stat> {
	private List<Stat> stats;
	private int layout;
	
	public StatAdapter(Context context, int resource, List<Stat> objects) {
		super(context, resource, objects);
		stats = objects;
		layout = resource;
	}
	
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) getContext()
		        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = convertView == null ? inflater.inflate(layout, parent, false) : convertView;
		
		Stat rowItem = stats.get(position);
		TextView name = (TextView) rowView.findViewById(R.id.lv_stat_name);
		name.setText(rowItem.name);
		TextView value = (TextView) rowView.findViewById(R.id.lv_stat_val);
		value.setText(rowItem.getValue());
		
		rowView.setBackgroundColor(rowItem.getStatus() == Stat.STATUS_GREEN ? Color.GREEN : 
					(rowItem.getStatus() == Stat.STATUS_RED ? Color.RED : Color.YELLOW));
		
		return rowView;
	}
}
