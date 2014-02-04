package com.yowatari.machitomo;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.widget.ArrayAdapter;

public class TimelineFragment extends ListFragment {

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		String[] data = {
				"timeline01",
				"timeline02",
				"timeline03",
				"timeline04",
				"timeline05",
				"timeline06",
				"timeline07",
				"timeline08",
				"timeline09",
				"timeline10"
		};
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, data);
		
		setListAdapter(adapter);
	}
}
