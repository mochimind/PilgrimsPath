package com.pilgrimspath;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.pilgrimspath.data.module.Module;

public class ModuleAdapter extends ArrayAdapter<Module> {

	private List<Module> mods;
	private int layout;
	
	public ModuleAdapter(Context context, int resource, List<Module> objects) {
		super(context, resource, objects);
		
		mods = objects;
		layout = resource;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) getContext()
		        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = convertView == null ? inflater.inflate(layout, parent, false) : convertView;
		
		Module rowItem = mods.get(position);
		
		TextView name = (TextView) rowView.findViewById(R.id.lv_mod_name);
		TextView count = (TextView) rowView.findViewById(R.id.lv_mod_count);
		name.setText(rowItem.getName());
		count.setText(rowItem.lastOperated + "/" + rowItem.built);

		Button add = (Button) rowView.findViewById(R.id.lv_mod_more);
		add.setOnClickListener(new OnClickListener() {
			Module mod;
			
			@Override public void onClick(View arg0) { mod.build(1); }
			private OnClickListener initialize(Module _mod) {
				mod = _mod;
				return this;
			}
		}.initialize(rowItem));
		
		Button sub = (Button) rowView.findViewById(R.id.lv_mod_less);
		sub.setOnClickListener(new OnClickListener() {
			Module mod;
			
			@Override public void onClick(View arg0) { mod.destroy(1); }
			private OnClickListener initialize(Module _mod) {
				mod = _mod;
				return this;
			}
		}.initialize(rowItem));
		
		return rowView;
	}
}
