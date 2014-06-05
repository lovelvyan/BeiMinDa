package com.newyouth.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.newyouth.bmdxyt.R;
import com.newyouth.schoolmap.SchoolMap;
import com.newyouth.studymaterial.StudyMaterialActivity;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;

@SuppressLint("NewApi")
public class MainFragment extends Fragment {

	private GridView gridView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		container = (ViewGroup) inflater.inflate(R.layout.fragment_main, null);
		List<Map<String, Object>> items = new ArrayList<Map<String, Object>>();

		for (int i = 0; i < 12; i++) {
			Map<String, Object> item = new HashMap<String, Object>();
			switch (i) {
			case 0:
				item.put("imageItem", R.drawable.grid_item_school_news);
				item.put("textItem", getString(R.string.grid_item_schoole_news));
				break;
			case 1:
				item.put("imageItem", R.drawable.grid_item_school_tongzhi);
				item.put("textItem", getString(R.string.grid_item_study_inform));
				break;
			case 2:
				item.put("imageItem", R.drawable.grid_item_school_ikonw);
				item.put("textItem", getString(R.string.grid_item_school_ikonw));
				break;
			case 3:
				item.put("imageItem", R.drawable.grid_item_school_server);
				item.put("textItem",
						getString(R.string.grid_item_school_server));
				break;
			case 4:
				item.put("imageItem", R.drawable.grid_item_school_findgoods);
				item.put("textItem", getString(R.string.grid_item_find_goods));
				break;
			case 5:
				item.put("imageItem", R.drawable.grid_item_study_file);
				item.put("textItem",
						getString(R.string.grid_item_study_material));
				break;
			case 6:
				item.put("imageItem", R.drawable.grid_item_school_person);
				item.put("textItem",
						getString(R.string.grid_item_school_person));
				break;
			case 7:
				item.put("imageItem", R.drawable.grid_item_students);
				item.put("textItem",
						getString(R.string.grid_item_school_student));
				break;
			case 8:
				item.put("imageItem", R.drawable.grid_item_xiaoyuanjingyan);
				item.put("textItem",
						getString(R.string.grid_item_school_experience));
				break;
			case 9:
				item.put("imageItem", R.drawable.grid_item_school_food);
				item.put("textItem", getString(R.string.grid_item_school_food));
				break;
			case 10:
				item.put("imageItem", R.drawable.grid_item_school_map);
				item.put("textItem", getString(R.string.grid_item_school_map));
				break;
			case 11:
				item.put("imageItem", R.drawable.grid_item_yijianfankui);
				item.put("textItem",
						getString(R.string.grid_item_idea_feedback));
				break;

			default:
				break;
			}
			items.add(item);
		}
		SimpleAdapter adapter = new SimpleAdapter(getActivity(), items,
				R.layout.grid_item_view,
				new String[] { "imageItem", "textItem" }, new int[] {
						R.id.main_item_img, R.id.main_item_text });

		gridView = (GridView) container.findViewById(R.id.main_grid);
		gridView.setAdapter(adapter);
		gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent intent = new Intent();
				switch (arg2) {

				case 0:
					intent.setClass(getActivity(), StudyMaterialActivity.class);
					break;
				case 1:
					intent.setClass(getActivity(), StudyMaterialActivity.class);
					break;

				case 2:
					intent.setClass(getActivity(), StudyMaterialActivity.class);
					break;

				case 3:
					intent.setClass(getActivity(), StudyMaterialActivity.class);
					break;
				case 4:
					intent.setClass(getActivity(), StudyMaterialActivity.class);
					break;
				case 5:
					intent.setClass(getActivity(), StudyMaterialActivity.class);

					break;
				case 6:
					intent.setClass(getActivity(), StudyMaterialActivity.class);
					break;
				case 7:
					intent.setClass(getActivity(), StudyMaterialActivity.class);
					break;
				case 8:
					intent.setClass(getActivity(), StudyMaterialActivity.class);
					break;

				case 9:
					intent.setClass(getActivity(), StudyMaterialActivity.class);
					break;
				case 10:
					intent.setClass(getActivity(), SchoolMap.class);
					break;
				case 11:
					intent.setClass(getActivity(), StudyMaterialActivity.class);

					break;
				default:
					intent.setClass(getActivity(), StudyMaterialActivity.class);
					break;
				}
				getActivity().startActivity(intent);
			}
		});

		return container;
	}
}
