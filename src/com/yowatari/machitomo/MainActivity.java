package com.yowatari.machitomo;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends FragmentActivity implements DrawerFragment.OnListItemSelectListener {

	private DrawerLayout mDrawerLayout;
	private ActionBarDrawerToggle mDrawerToggle;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		getActionBar().setDisplayHomeAsUpEnabled(true);	// UpNavigationアイコン横のアレを有効化
		getActionBar().setHomeButtonEnabled(true);		// UpNavigationを有効化
		
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerToggle = new ActionBarDrawerToggle(
				this,
				mDrawerLayout,
				R.drawable.ic_drawer,
				R.string.drawer_open,
				R.string.drawer_close
				) {
			
			@Override
			public void onDrawerClosed(View drawerView) {
				// ドロワー閉じた
			}
			
			@Override
			public void onDrawerOpened(View drawerView) {
				// ドロワー開いた
			}
			
			@Override
			public void onDrawerSlide(View drawerView, float slideOffset) {
				super.onDrawerSlide(drawerView, slideOffset);
				// ドロワーがスライド中
			}
			
			@Override
			public void onDrawerStateChanged(int newState) {
				// ドロワー状態が変化
				// newState: 表示／閉じ済み(0) ドラッグ中(1) ドラッグ後のアニメーション中(2)
				
			}
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);
		
		if (savedInstanceState == null) {
			onListItemSelected(0);
        }
	}
	
	@Override
	public void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		mDrawerToggle.syncState();	// ActivityとActionBarDrawerToggleを同期
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		mDrawerToggle.onConfigurationChanged(newConfig);	// イベントをActionBarDrawerToggleへ渡す
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		// ActionBarDrawerToggleへUpNavigationを渡す
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		
		// 通知とゲームプレイへの画面遷移はここが入り口
		Toast.makeText(this, item.getTitle() + "is selected.", Toast.LENGTH_SHORT).show();
		
		return super.onOptionsItemSelected(item);
	}
	
	// ドロワーメニュー選択時
	@Override
	public void onListItemSelected(int id) {
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		
		switch (id) {
		case 0:
			// Home
//			HomeFragment homeFragment = (HomeFragment)getSupportFragmentManager().findFragmentByTag("Home");
//			if (!homeFragment.isVisible()) {
//				homeFragment = new HomeFragment();
//			}
			HomeFragment homeFragment = new HomeFragment();
			transaction.replace(R.id.content, homeFragment, "Home");
			break;
		case 1:
			// Me
			break;
		case 2:
			// Friends
			FriendListFragment fFragment = new FriendListFragment();
			transaction.replace(R.id.content, fFragment, "Friends");
			transaction.addToBackStack(null);
			break;
		case 3:
			// NewFriends
			break;
		default:
			break;
		}
        
		// フラグメント切り替え
		transaction.commit();

		// メニューを選択状態とかタイトル表示を変えるとか
        //mDrawerList.setItemChecked(position, true);
        //setTitle(mPlanetTitles[position]);
		
		Toast.makeText(this, "select id: " + id, Toast.LENGTH_SHORT).show();
		
		// ドロワー閉じる
        mDrawerLayout.closeDrawers();
    }
	
	// ActionBarのカスタマイズ
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		
		final String allfriendsString = "@*android:drawable/ic_menu_allfriends";
		final int allfriendsId = getResources().getIdentifier(allfriendsString, null, null);
		menu.findItem(R.id.playing).setIcon(allfriendsId);
		
		final String notificationsString = "@*android:drawable/ic_menu_notifications";
		final int notificationsId = getResources().getIdentifier(notificationsString, null, null);
		menu.findItem(R.id.notifications).setIcon(notificationsId);
		
		return super.onCreateOptionsMenu(menu);
	}
}
