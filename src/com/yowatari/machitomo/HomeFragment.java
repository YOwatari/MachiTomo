package com.yowatari.machitomo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class HomeFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.home, container, false);
		
		return view;
	}
	
	@Override
	public void onStart() {
		super.onStart();
		
		FragmentTransaction transaction = getFragmentManager().beginTransaction();
		
		ActionMenuFragment amFragment = new ActionMenuFragment();
		TimelineFragment tlFragment = new TimelineFragment();
		
		transaction.replace(R.id.Action, amFragment, "ActionMenu");
		transaction.replace(R.id.TimeLine, tlFragment, "Timeline");
		
		transaction.commit();
	}

}
