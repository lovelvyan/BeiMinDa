package com.newyouth.studymaterial;

import com.newyouth.bmdxyt.MainActivity;
import com.newyouth.bmdxyt.R;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.widget.TextView;

public class StudyMaterialActivity extends Activity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_studymaterial);

		getActionBar().setDisplayHomeAsUpEnabled(true);

		final ActionBar actionBar = getActionBar();
		actionBar.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.ationbar_bg));
		int titleId = Resources.getSystem().getIdentifier("action_bar_title",
				"id", "android");
		TextView title = (TextView) findViewById(titleId);
		title.setTextColor(this.getResources().getColor(
				R.color.actionbar_text_color));
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpTo(this, new Intent(this, MainActivity.class));
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
