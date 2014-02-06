package com.yowatari.machitomo;

import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost.TabSpec;

public class UserFragment extends Fragment implements TabListener {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.user, container, false);
		return view;
	}

	@Override
	public void onStart() {
		super.onStart();

		FragmentTransaction transaction = getFragmentManager()
				.beginTransaction();

		ProfileFragment pfFragment = new ProfileFragment();
		transaction.replace(R.id.profile, pfFragment, "UserProfile");
		transaction.commit();

		FragmentTabHost mTabHost = (FragmentTabHost) getView().findViewById(
				R.id.tabhost);
		mTabHost.setup(getActivity(), getChildFragmentManager(),
				R.id.tabhost_fragment);

		TabSpec actionsTab = mTabHost.newTabSpec("Actions");
		actionsTab.setIndicator("actions");
		mTabHost.addTab(actionsTab, TimelineFragment.class, null);

		TabSpec friendsTab = mTabHost.newTabSpec("Friends");
		friendsTab.setIndicator("friends");
		mTabHost.addTab(friendsTab, FriendListFragment.class, null);

	}

	@Override
	public void onTabReselected(Tab tab,
			android.app.FragmentTransaction transaction) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTabSelected(Tab tab,
			FragmentTransaction transaction) {
		int position = tab.getPosition();
		transaction = getChildFragmentManager().beginTransaction();
		switch (position) {
		case 0:
			TimelineFragment tlFragment = new TimelineFragment();
			transaction.replace(R.id.tabincontent, tlFragment);
			break;
		case 1:
			FriendListFragment fFragment = new FriendListFragment();
			transaction.replace(R.id.tabincontent, fFragment);
			break;

		default:
			break;
		}

	}

	@Override
	public void onTabUnselected(Tab tab,
			android.app.FragmentTransaction transaction) {
		// TODO Auto-generated method stub

	}

}
