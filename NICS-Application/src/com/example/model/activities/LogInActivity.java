package com.example.model.activities;

import support.ClickListener;
import android.app.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.example.model.UserModel;
import com.example.nics_application.R;
import com.example.presenter.LogInViewPresenter;
import com.example.view.LogInView;

public class LogInActivity extends Activity implements LogInView, OnClickListener {

	Button enterButton;
	EditText usernameEditText;
	EditText passwordEditText;
	
	
	private ClickListener listener;
	LogInViewPresenter presenter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_log_in);
		
		presenter = new LogInViewPresenter(this, new UserModel());
		initiateEditTextsAndButtons();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.log_in, menu);
		return true;
	}
	
	public void initiateEditTextsAndButtons() {
		enterButton = (Button)findViewById(R.id.goButton);
		enterButton.setOnClickListener(this);
		usernameEditText = (EditText) findViewById(R.id.amountEditText);
		passwordEditText = (EditText) findViewById(R.id.passwordEditText);
		
	}
	
	@Override
	public String getUsername() {
		return usernameEditText.getText().toString();
	}
	
	@Override
	public String getPassword() {
		return passwordEditText.getText().toString();
	}
	
	@Override
	public void acceptLogin() {
		Intent i = new Intent(this, AccountListViewActivity.class);
		startActivity(i);
		
	}

	@Override
	public void addSearchRequestNotifyCallback(final ClickListener lsnr) {
		listener = lsnr;
		
	}

	@Override
	public void displayAlertDialog() {
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
		alertDialog.setTitle("Authentication Failure");
		alertDialog.setMessage("Authentication has failed. Please try again.");
		alertDialog.setNeutralButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
			}
		});
		alertDialog.show();
		
	}

	@Override
	public void onClick(View v) {
		presenter.onClick(v);
	}
	
}
