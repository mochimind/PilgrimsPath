package com.pilgrimspath;

import java.util.ArrayList;
import java.util.List;

import com.pilgrimspath.data.Ship;
import com.pilgrimspath.data.stat.HealthStat;
import com.pilgrimspath.data.stat.LaborStat;
import com.pilgrimspath.data.stat.PopulationStat;
import com.pilgrimspath.data.stat.Stat;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

public class FragmentShipPeople extends UpdatableFragment {
	
	private Ship ship;
	
	private Button transferCrew;
	private ListView list;
	private StatAdapter adapter;
	private List<Stat> stats;
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		// TODO: may want to do some error checking here
		ship = ((ShipContainerActivity) activity).getShip();
	}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedINstanceState) {
		View view = inflater.inflate(R.layout.fragment_ship_people, container, false);
		
		stats = new ArrayList<Stat>();
		stats.add(new PopulationStat(ship));
		stats.add(new LaborStat(ship));
		stats.add(new HealthStat(ship));
		list = (ListView) view.findViewById(R.id.ship_people_list);
		adapter = new StatAdapter(getActivity(), R.layout.listview_stat, stats);
		list.setAdapter(adapter);
		
		transferCrew = (Button) view.findViewById(R.id.ship_people_transfer);
		transferCrew.setOnClickListener(new OnClickListener() {
			@Override public void onClick(View arg0) {
				
			}
			
		});

		return view;
	}
	
	@Override public void update() {
		adapter.notifyDataSetChanged();
	}
}
