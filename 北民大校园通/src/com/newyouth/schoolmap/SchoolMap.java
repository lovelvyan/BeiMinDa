package com.newyouth.schoolmap;

import java.io.IOException;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.map.MKEvent;
import com.baidu.mapapi.map.MapController;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.OverlayItem;
import com.baidu.mapapi.map.PoiOverlay;
import com.baidu.mapapi.map.PopupClickListener;
import com.baidu.mapapi.map.PopupOverlay;
import com.baidu.mapapi.search.MKPoiInfo;
import com.baidu.mapapi.search.MKPoiResult;
import com.baidu.mapapi.search.MKSearch;
import com.baidu.mapapi.search.MKSearchListener;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import com.newyouth.bmdxyt.MainActivity;
import com.newyouth.bmdxyt.R;

public class SchoolMap extends Activity {

	BMapManager mBMapMan = null;
	MapView mMapView = null;

	MKSearch mMKSearch = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		mBMapMan = new BMapManager(getApplication());
		mBMapMan.init(null);
		// 注意：请在试用setContentView前初始化BMapManager对象，否则会报错
		setContentView(R.layout.activity_school_map);

		getActionBar().setDisplayHomeAsUpEnabled(true);

		final ActionBar actionBar = getActionBar();
		actionBar.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.ationbar_bg));
		int titleId = Resources.getSystem().getIdentifier("action_bar_title",
				"id", "android");
		TextView title = (TextView) findViewById(titleId);
		title.setTextColor(this.getResources().getColor(
				R.color.actionbar_text_color));

		mMapView = (MapView) findViewById(R.id.bmapsView);
		mMapView.setBuiltInZoomControls(true);
		// 设置启用内置的缩放控件

		// mMapView.setTraffic(true);//实时交通通信图
		// mMapView.setSatellite(true);//卫星图

		MapController mMapController = mMapView.getController();
		// 得到mMapView的控制权,可以用它控制和驱动平移和缩放
		GeoPoint point = new GeoPoint((int) (38.502741 * 1E6),
				(int) (106.113595 * 1E6));
		// 用给定的经纬度构造一个GeoPoint，单位是微度 (度 * 1E6)106.113595,38.502741
		mMapController.setCenter(point);// 设置地图中心点
		mMapController.setZoom(18);// 设置地图zoom级别
		/*		*/
		/**
		 * 在想要添加Overlay的地方使用以下代码， 比如Activity的onCreate()中
		 */
		/*
		 * // 准备要添加的Overlay double mLat1 = 38.502741; double mLon1 =
		 * 106.113595;// 北方民族大学主楼 double mLat2 = 39.9022; double mLon2 =
		 * 116.3922; double mLat3 = 39.917723; double mLon3 = 116.3722; //
		 * 用给定的经纬度构造GeoPoint，单位是微度 (度 * 1E6) GeoPoint p1 = new GeoPoint((int)
		 * (mLat1 * 1E6), (int) (mLon1 * 1E6)); GeoPoint p2 = new GeoPoint((int)
		 * (mLat2 * 1E6), (int) (mLon2 * 1E6)); GeoPoint p3 = new GeoPoint((int)
		 * (mLat3 * 1E6), (int) (mLon3 * 1E6)); // 准备overlay图像数据，根据实情情况修复
		 * Drawable mark = getResources().getDrawable(R.drawable.action_search);
		 * // 用OverlayItem准备Overlay数据 OverlayItem item1 = new OverlayItem(p1,
		 * "item1", "item1"); //
		 * 使用setMarker()方法设置overlay图片,如果不设置则使用构建ItemizedOverlay时的默认设置
		 * OverlayItem item2 = new OverlayItem(p2, "item2", "item2");
		 * item2.setMarker(mark); OverlayItem item3 = new OverlayItem(p3,
		 * "item3", "item3");
		 * 
		 * // 创建IteminizedOverlay OverlayTest itemOverlay = new
		 * OverlayTest(mark, mMapView); // 将IteminizedOverlay添加到MapView中
		 * 
		 * mMapView.getOverlays().clear();
		 * mMapView.getOverlays().add(itemOverlay);
		 * 
		 * // 现在所有准备工作已准备好，使用以下方法管理overlay. // 添加overlay,
		 * 当批量添加Overlay时使用addItem(List<OverlayItem>)效率更高
		 * itemOverlay.addItem(item1); itemOverlay.addItem(item2);
		 * itemOverlay.addItem(item3); mMapView.refresh(); // 删除overlay . //
		 * itemOverlay.removeItem(itemOverlay.getItem(0)); //
		 * mMapView.refresh(); // 清除overlay // itemOverlay.removeAll(); //
		 * mMapView.refresh();
		 */
		// pop demo
		// 创建pop对象，注册点击事件监听接口
		PopupOverlay pop_main = new PopupOverlay(mMapView,
				new PopupClickListener() {
					public void onClickedPopup(int index) {
						// 在此处理pop点击事件，index为点击区域索引,点击区域最多可有三个
						Toast.makeText(getApplicationContext(), "北方民族大学",
								Toast.LENGTH_LONG).show();
					}
				});
		/**
		 * 准备pop弹窗资源，根据实际情况更改 弹出包含三张图片的窗口，可以传入三张图片、两张图片、一张图片。
		 * 弹出的窗口，会根据图片的传入顺序，组合成一张图片显示. 点击到不同的图片上时，回调函数会返回当前点击到的图片索引index
		 */
		Bitmap[] bmps = new Bitmap[1];
		try {
			bmps[0] = BitmapFactory.decodeStream(getAssets().open(
					"baidu_map_local.png"));
			/*
			 * bmps[1] = BitmapFactory.decodeStream(getAssets().open(
			 * "action_search.png")); bmps[2] =
			 * BitmapFactory.decodeStream(getAssets().open(
			 * "action_search.png"));
			 */
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 弹窗弹出位置
		GeoPoint ptTAM = new GeoPoint((int) (38.502741 * 1E6),
				(int) (106.113595 * 1E6));
		// 弹出pop,隐藏pop
		pop_main.showPopup(bmps, ptTAM, 32);
		// 隐藏弹窗
		// pop.hidePop();

	/*	// pop demo
		// 创建pop对象，注册点击事件监听接口
		PopupOverlay pop_book = new PopupOverlay(mMapView,
				new PopupClickListener() {
					public void onClickedPopup(int index) {

						Toast.makeText(getApplicationContext(), "北方民族大学图书馆",
								Toast.LENGTH_LONG).show();
					}
				});

		Bitmap[] bmps_book = new Bitmap[1];
		try {
			bmps_book[0] = BitmapFactory.decodeStream(getAssets().open(
					"baidu_map_local02.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		GeoPoint ptTAM_book = new GeoPoint((int) (38.501851 * 1E6),
				(int) (106.113524 * 1E6));// 106.113524,38.501851

		pop_book.showPopup(bmps_book, ptTAM_book, 32);*/

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			NavUtils.navigateUpTo(this, new Intent(this, MainActivity.class));
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onDestroy() {
		mMapView.destroy();
		if (mBMapMan != null) {
			mBMapMan.destroy();
			mBMapMan = null;
		}
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		mMapView.onPause();
		if (mBMapMan != null) {
			mBMapMan.stop();
		}
		super.onPause();
	}

	@Override
	protected void onResume() {
		mMapView.onResume();
		if (mBMapMan != null) {
			mBMapMan.start();
		}
		super.onResume();
	}

}
