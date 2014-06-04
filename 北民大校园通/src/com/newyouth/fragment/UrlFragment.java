package com.newyouth.fragment;

import com.newyouth.bmdxyt.R;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class UrlFragment extends Fragment {

	private String url;
	private RadioGroup radioGroup_Url;
	RadioButton radioButtonPublicUrl, radioButtonLocalUrl;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		container = (ViewGroup) inflater.inflate(R.layout.fragment_url, null);

		radioGroup_Url = (RadioGroup) container
				.findViewById(R.id.radioGroup_Url);
		radioButtonPublicUrl = (RadioButton) container
				.findViewById(R.id.radioButtonPublicUrl);
		radioButtonLocalUrl = (RadioButton) container
				.findViewById(R.id.radioButtonLocalUrl);
		radioGroup_Url
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(RadioGroup group, int checkedId) {
						// TODO Auto-generated method stub
						if (checkedId == R.id.radioButtonPublicUrl) {
							url = "http://222.75.163.2:8080/nxjixie";

							SharedPreferences setsharedPreferences = getActivity()
									.getSharedPreferences("url",
											Context.MODE_PRIVATE);
							Editor editor = setsharedPreferences.edit();
							editor.putString("urlip", url);
							editor.commit();// 提交修改
							passDialog();
						} else if (checkedId == R.id.radioButtonLocalUrl) {
							url = "http://192.168.1.111:8080/nxjixie";
							SharedPreferences setsharedPreferences = getActivity()
									.getSharedPreferences("url",
											Context.MODE_PRIVATE);
							Editor editor = setsharedPreferences.edit();
							editor.putString("urlip", url);
							editor.commit();// 提交修改
							passDialog();
						}
					}

				});

		return container;
	}

	private void passDialog() {
		SharedPreferences getsharedPreferences = getActivity()
				.getSharedPreferences("url", Context.MODE_PRIVATE);
		String url = getsharedPreferences.getString("urlip", "");
		Dialog dialog = new AlertDialog.Builder(getActivity())
				.setTitle("设置后台访问地址").setMessage("您的设置完成!\n地址信息为：\n" + url)
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int whichButton) {
						FragmentManager fragmentManager = getFragmentManager();
						fragmentManager
								.beginTransaction()
								.replace(R.id.content_frame, new MainFragment())
								.commit();
						getActivity().getActionBar().setTitle("首页");
					}
				}).create();
		dialog.show();
	}
}
