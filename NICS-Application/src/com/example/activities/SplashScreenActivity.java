package com.example.activities;

import com.example.model.Application;
import com.example.nics_application.R;
import com.example.presenter.WelcomeViewPresenter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.widget.Button;
import android.widget.ImageView;

public class SplashScreenActivity extends Activity {
	
	private static int SPLASH_TIME_LIMIT = 2000;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash_screen);
		Application app = Application.INSTANCE;
		app.setModel(getApplicationContext());
		
		new Handler().postDelayed(new Runnable() {
            @Override
			public void run() {
            	endSplash();				
			}
        }, SPLASH_TIME_LIMIT);
    }
		
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void endSplash() {
		Intent i = new Intent(this, MainActivity.class);
		startActivity(i);
		finish();
	}

}
