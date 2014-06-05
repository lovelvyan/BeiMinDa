package com.newyouth.schoolmap;

import android.graphics.drawable.Drawable;

import com.baidu.mapapi.map.ItemizedOverlay;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.OverlayItem;
import com.baidu.platform.comapi.basestruct.GeoPoint;

/*
 * Ҫ����overlay����¼�ʱ��Ҫ�̳�ItemizedOverlay
 * ���������¼�ʱ��ֱ������ItemizedOverlay.
 */
class OverlayTest extends ItemizedOverlay<OverlayItem> {
	// ��MapView����ItemizedOverlay
	public OverlayTest(Drawable mark, MapView mapView) {
		super(mark, mapView);
	}

	protected boolean onTap(int index) {
		// �ڴ˴���item����¼�
		System.out.println("item onTap: " + index);
		return true;
	}

	public boolean onTap(GeoPoint pt, MapView mapView) {
		// �ڴ˴���MapView�ĵ���¼��������� trueʱ
		super.onTap(pt, mapView);
		return false;
	}
	// ��2.1.1 ��ʼ��ʹ�� add/remove ����overlay , ������д���½ӿ�
	/*
	 * @Override protected OverlayItem createItem(int i) { return
	 * mGeoList.get(i); }
	 * 
	 * @Override public int size() { return mGeoList.size(); }
	 */
}
