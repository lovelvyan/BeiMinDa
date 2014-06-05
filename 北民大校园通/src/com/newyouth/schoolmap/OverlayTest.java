package com.newyouth.schoolmap;

import android.graphics.drawable.Drawable;

import com.baidu.mapapi.map.ItemizedOverlay;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.OverlayItem;
import com.baidu.platform.comapi.basestruct.GeoPoint;

/*
 * 要处理overlay点击事件时需要继承ItemizedOverlay
 * 不处理点击事件时可直接生成ItemizedOverlay.
 */
class OverlayTest extends ItemizedOverlay<OverlayItem> {
	// 用MapView构造ItemizedOverlay
	public OverlayTest(Drawable mark, MapView mapView) {
		super(mark, mapView);
	}

	protected boolean onTap(int index) {
		// 在此处理item点击事件
		System.out.println("item onTap: " + index);
		return true;
	}

	public boolean onTap(GeoPoint pt, MapView mapView) {
		// 在此处理MapView的点击事件，当返回 true时
		super.onTap(pt, mapView);
		return false;
	}
	// 自2.1.1 开始，使用 add/remove 管理overlay , 无需重写以下接口
	/*
	 * @Override protected OverlayItem createItem(int i) { return
	 * mGeoList.get(i); }
	 * 
	 * @Override public int size() { return mGeoList.size(); }
	 */
}
