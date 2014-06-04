package com.newyouth.bmdxyt;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MenuAdapter extends BaseAdapter {
	private Context context;
	private List<Map<String, Object>> listItems;
	private LayoutInflater listContainer;
	private ListView listView;

	static class ListItemView {
		public TextView title_view;
		public TextView img_view;
	}

	public MenuAdapter(Context context, List<Map<String, Object>> MenuList,
			ListView Listview) {
		this.context = context;
		this.listContainer = LayoutInflater.from(context);
		this.listView = Listview;
		this.listItems = MenuList;
	}

	@Override
	public int getCount() {
		return listItems.size();
	}

	@Override
	public Map<String, Object> getItem(int position) {
		return listItems.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ListItemView listItemView = null;
		if (convertView == null) {
			convertView = listContainer
					.inflate(R.layout.drawer_list_item, null);
			listItemView = new ListItemView();
			listItemView.title_view = (TextView) convertView
					.findViewById(R.id.drawer_name);
			listItemView.img_view = (TextView) convertView
					.findViewById(R.id.drawer_img);
			convertView.setTag(listItemView);
		} else {
			listItemView = (ListItemView) convertView.getTag();
		}
		Map<String, Object> map = listItems.get(position);

		listItemView.title_view.setText(map.get("textItem").toString());
		String flag = map.get("imageItem").toString();
		if (flag.equals("1")) {
			listItemView.img_view
					.setBackgroundResource(R.drawable.ic_action_select_all);
		} else if (flag.equals("2")) {
			listItemView.img_view
					.setBackgroundResource(R.drawable.ic_action_person);
		} else if (flag.equals("3")) {
			listItemView.img_view
					.setBackgroundResource(R.drawable.ic_action_favorite);
		} else if (flag.equals("4")) {
			listItemView.img_view
					.setBackgroundResource(R.drawable.ic_action_new_label);
		} else if (flag.equals("5")) {
			listItemView.img_view
					.setBackgroundResource(R.drawable.ic_action_web_site);
		} else if (flag.equals("6")) {
			listItemView.img_view
					.setBackgroundResource(R.drawable.ic_action_settings);
		}

		return convertView;
	}

	public void refreshData(List<Map<String, Object>> listItems) {
		this.listItems = listItems;
		notifyDataSetChanged();
	}

}
