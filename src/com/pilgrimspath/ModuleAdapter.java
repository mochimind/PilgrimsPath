package com.pilgrimspath;

import java.util.List;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.pilgrimspath.data.module.Module;

public class ModuleAdapter extends ArrayAdapter<Module> {

	private List<Module> mods;
	
	public ModuleAdapter(Context context, int resource, List<Module> objects) {
		super(context, resource, objects);
		
		mods = objects;
	}
	
}
