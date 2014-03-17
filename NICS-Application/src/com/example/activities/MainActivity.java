package com.example.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import com.example.nics_application.R;
import com.example.presenter.WelcomeViewPresenter;
import com.example.support.ClickListener;
import com.example.view.WelcomeView;

public class MainActivity extends Activity implements WelcomeView, OnClickListener {

	Button logInButton;
	Button registerButton;
	ImageView logo;
	private WelcomeViewPresenter presenter;
	private ClickListener listener;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		presenter = new WelcomeViewPresenter(this);
		
		logInButton = (Button) findViewById(R.id.loginButton);
		logInButton.setOnClickListener(this);
		registerButton = (Button) findViewById(R.id.goButton);
		registerButton.setOnClickListener(this);
		logo = (ImageView) findViewById(R.id.imageView1);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void startLogin() {
		Intent i = new Intent(this, LogInActivity.class);
		startActivity(i);

	}

	@Override
	public void startRegistration() {
		Intent i = new Intent(this, RegistrationActivity.class);
		startActivity(i);

	}

	@Override
	public void addSearchRequestNotifyCallback(final ClickListener lsnr) {
		listener = lsnr;
	}

	@Override
	public void onClick(View v) {
		presenter.onClick(v);
	}
}
