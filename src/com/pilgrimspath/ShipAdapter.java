package com.pilgrimspath;

import java.util.List;

import com.pilgrimspath.data.Ship;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ShipAdapter extends ArrayAdapter<Ship> {
	
	private List<Ship> ships;

	public ShipAdapter(Context context, int resource, List<Ship> objects) {
		super(context, resource, objects);
		ships = objects;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) getContext()
		        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = convertView == null ? inflater.inflate(R.layout.listview_ship, parent, false) : convertView;
		
		Ship rowItem = ships.get(position);
		
		TextView name = (TextView) rowView.findViewById(R.id.lv_ship_name);
		name.setText(rowItem.name);
		TextView crew = (TextView) rowView.findViewById(R.id.lv_ship_crew);
		crew.setText("c:" + rowItem.peeps.getPopulation() + "/" + rowItem.peeps.getMaxPopulation());
		
		return rowView;
	}

}
