package com.example.activities;

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

import com.example.model.Application;
import com.example.model.UserModel;
import com.example.nics_application.R;
import com.example.presenter.RegistrationViewPresenter;
import com.example.support.ClickListener;
import com.example.view.RegistrationView;


public class RegistrationActivity extends Activity implements RegistrationView, OnClickListener {

	Button enterButton;
	EditText usernameEditText;
	EditText passwordEditText;
	
	private ClickListener listener;
	RegistrationViewPresenter presenter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registration);
		Application app = Application.INSTANCE;
		presenter = new RegistrationViewPresenter(this, app.getModel());
		initiateEditTextsAndButtons();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.log_in, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		presenter.onClick(v);

	}

	public void initiateEditTextsAndButtons() {
		enterButton = (Button)findViewById(R.id.goButton);
		enterButton.setOnClickListener(this);
		usernameEditText = (EditText) findViewById(R.id.amountEditText);
		passwordEditText = (EditText) findViewById(R.id.commentEditText);
		
	}
	
	@Override
	public void acceptRegistration() {
		Intent i = new Intent(this, LogInActivity.class);
		startActivity(i);
		finish();

	}

	@Override
	public void addSearchRequestNotifyCallback(ClickListener lsnr) {
		listener = lsnr;
	}

	public String getUsername() {
		return usernameEditText.getText().toString();
	}
	
	public String getPassword() {
		return passwordEditText.getText().toString();
	}
	
	@Override
	public void displayAlertDialog() {
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
		alertDialog.setTitle("Registration Failure");
		alertDialog.setMessage("User under this name already exists. Please choose a different name");
		alertDialog.setNeutralButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
			}
		});
		alertDialog.show();
		
	}
	
	@Override 
	public void displayAlertDialog2() {
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
		alertDialog.setTitle("Registration Failure");
		alertDialog.setMessage("Username or password too short. Must be at least 5 characters.");
		alertDialog.setNeutralButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
			}
		});
		alertDialog.show();
	}
	
}
