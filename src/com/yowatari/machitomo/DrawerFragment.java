package com.yowatari.machitomo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class DrawerFragment extends ListFragment {
	
	public interface OnListItemSelectListener {
		public void onListItemSelected(int id);
	}
	
	private OnListItemSelectListener mListener;
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		
		if (activity instanceof OnListItemSelectListener == false)
			throw new ClassCastException("OnListItemSelectListenerが見当たりません.");
		
		mListener = (OnListItemSelectListener) activity;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		String[] data = {
				"Home",
				"Me",
				"Friends",
				"New Friends"
		};
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, data);
		
		setListAdapter(adapter);
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		
		mListener.onListItemSelected((int)id);
	}
	
}
