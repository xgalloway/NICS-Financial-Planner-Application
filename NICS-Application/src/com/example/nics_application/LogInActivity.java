package com.example.nics_application;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class LogInActivity extends Activity {

	private LogInController loginController;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_log_in);
		loginController = new LogInController("user", "pass");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.log_in, menu);
		return true;
	}

}
