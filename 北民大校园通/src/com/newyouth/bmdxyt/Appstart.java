package com.newyouth.bmdxyt;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.view.Window;

public class Appstart extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// »•µÙ±ÍÃ‚¿∏
		setContentView(R.layout.activity_appstart);

		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Intent intent = new Intent(Appstart.this, MainActivity.class);
				startActivity(intent);
				Appstart.this.finish();
			}
		}, 1000);
	}
}