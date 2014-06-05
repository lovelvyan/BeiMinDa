package com.newyouth.bmdxyt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.newyouth.calendar.SchoolCalendar;
import com.newyouth.fragment.MainFragment;
import com.newyouth.fragment.UrlFragment;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.SearchManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;

	private CharSequence mDrawerTitle;
	private CharSequence mTitle;
	private String[] mMenuTitles;
	private String[] mMenuImgs;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		/* getActionBar().setDisplayHomeAsUpEnabled(true); */

		/**
		 * 下面代码定义actionBar的样式
		 */
		final ActionBar actionBar = getActionBar();
		// actionBar.setTitle(R.string.huang);
		// actionBar.setSubtitle(R.string.jixie);

		int titleId = Resources.getSystem().getIdentifier("action_bar_title",
				"id", "android");
		TextView title = (TextView) findViewById(titleId);

		title.setTextColor(this.getResources().getColor(
				R.color.actionbar_text_color));

		// title.setText("  " + Login.mUserName + "，您好！");
		int titleId2 = Resources.getSystem().getIdentifier(
				"action_bar_subtitle", "id", "android");
		TextView title2 = (TextView) findViewById(titleId2);
		/*
		 * title2.setTextColor(this.getResources().getColor(
		 * R.color.actionbar_subtext_color));
		 */
		// title2.setText("  宁夏机械研究院");
		// actionBar.setIcon(getResources().getDrawable(R.drawable.jixielogo));
		actionBar.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.ationbar_bg));

		/**
		 * 菜单列表
		 */

		mTitle = mDrawerTitle = getTitle();
		mMenuTitles = getResources().getStringArray(R.array.planets_name_array);
		mMenuImgs = getResources().getStringArray(R.array.planets_img_array);

		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.left_drawer);

		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
				GravityCompat.START);
		/*
		 * mDrawerList.setAdapter(new ArrayAdapter<String>(this,
		 * R.layout.drawer_list_item, mMenuTitles));
		 */
		List<Map<String, Object>> items = new ArrayList<Map<String, Object>>();

		for (int i = 0; i <= 5; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("textItem", mMenuTitles[i]);
			map.put("imageItem", mMenuImgs[i]);
			items.add(map);
		}

		/*
		 * SimpleAdapter adapter = new SimpleAdapter(this, items,
		 * R.layout.drawer_list_item, new String[] { "imageItem", "textItem" },
		 * new int[] { R.id.drawer_img, R.id.drawer_name });
		 */
		MenuAdapter adapter = new MenuAdapter(this, items, mDrawerList);
		mDrawerList.setAdapter(adapter);

		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);

		mDrawerToggle = new ActionBarDrawerToggle(this, /* host Activity */
		mDrawerLayout, /* DrawerLayout object */
		R.drawable.ic_drawer, /* nav drawer image to replace 'Up' caret */
		R.string.drawer_open, /* "open drawer" description for accessibility */
		R.string.drawer_close /* "close drawer" description for accessibility */
		) {
			public void onDrawerClosed(View view) {
				getActionBar().setTitle(mTitle);
				invalidateOptionsMenu(); // creates call to
											// onPrepareOptionsMenu()
			}

			@SuppressLint("NewApi")
			public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle(mDrawerTitle);
				invalidateOptionsMenu(); // creates call to
											// onPrepareOptionsMenu()
			}
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);

		if (savedInstanceState == null) {
			selectItem(0);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
		menu.findItem(R.id.action_calendar).setVisible(!drawerOpen);
		return super.onPrepareOptionsMenu(menu);
	}

	@SuppressLint("NewApi")
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		switch (item.getItemId()) {
		case R.id.action_calendar:
			/*
			 * Toast.makeText(this, "显示校历，是否是放假以及周末状态！",
			 * Toast.LENGTH_LONG).show();
			 */
			Intent intent = new Intent();
			intent.setClass(getApplicationContext(), SchoolCalendar.class);
			startActivity(intent);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private class DrawerItemClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			selectItem(position);
		}
	}

	@SuppressLint("NewApi")
	private void selectItem(int position) {
		Fragment fragment = null;
		switch (position) {
		case 0:
			fragment = new MainFragment();
			break;
		case 1:
			fragment = new MainFragment();
			break;
		case 2:
			fragment = new MainFragment();
			break;
		case 3:
			fragment = new MainFragment();
			break;
		case 4:
			fragment = new UrlFragment();
			break;
		case 5:
			fragment = new MainFragment();
			break;
		default:
			break;
		}
		FragmentManager fragmentManager = getFragmentManager();
		fragmentManager.beginTransaction()
				.replace(R.id.content_frame, fragment).commit();

		mDrawerList.setItemChecked(position, true);
		setTitle(mMenuTitles[position]);
		mDrawerLayout.closeDrawer(mDrawerList);
	}

	@SuppressLint("NewApi")
	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		getActionBar().setTitle(mTitle);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	private void exitDialog() {
		Dialog dialog = new AlertDialog.Builder(MainActivity.this)
				.setTitle("退出提示")
				.setMessage("你确定要校园通吗？")
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						MainActivity.this.finish();
					}
				})
				.setNegativeButton("取消", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
					}
				}).create();
		dialog.show();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			this.exitDialog();
		}
		return false;
	}

}