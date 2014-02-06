package com.yowatari.machitomo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

public class UserFragment extends Fragment {

	FragmentTabHost mTabHost;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.user, container, false);
		
		FragmentTransaction transaction = getFragmentManager()
				.beginTransaction();

		ProfileFragment pfFragment = new ProfileFragment();
		transaction.replace(R.id.profile, pfFragment, "UserProfile");
		transaction.commit();

		mTabHost = (FragmentTabHost) view.findViewById(R.id.tabhost);
		mTabHost.setup(getActivity(), getChildFragmentManager(),
				R.id.tab_container);
		
		mTabHost.addTab(mTabHost.newTabSpec("Actions").setIndicator("actions"),
				TabRoot.class, null);
		mTabHost.addTab(mTabHost.newTabSpec("Friends").setIndicator("friends"),
				TabRoot.class, null);
		
		return view;
	}

	/**
	 * Tabに入れる親Fragment
	 * 
	 * @author noxi
	 */
	public static class TabRoot extends Fragment implements OnClickListener {

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			if (container == null) {
				return null;
			}
			return inflater.inflate(R.layout.tab_root, container, false);
		}

		@Override
		public void onActivityCreated(Bundle savedInstanceState) {
			super.onActivityCreated(savedInstanceState);
			// 初回のみ自動で子を入れる
			if (savedInstanceState == null) {
				getChildFragmentManager().beginTransaction()
						.addToBackStack(null)
						.add(R.id.fragment_container, createNewChild())
						.commit();
			}
		}

		@Override
		public void onClick(View v) {
			getChildFragmentManager().beginTransaction().addToBackStack(null)
					.replace(R.id.fragment_container, createNewChild())
					.commit();
		}

		Fragment createNewChild() {
			TimelineFragment tlFragment = new TimelineFragment();
			return tlFragment;
		}
	}
}
