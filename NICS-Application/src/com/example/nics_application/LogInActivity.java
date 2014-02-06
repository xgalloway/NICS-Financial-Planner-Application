package com.example.nics_application;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;
import android.view.*;
import android.view.View.*;

public class LogInActivity extends Activity implements OnClickListener {

	Button enterButton;
	EditText usernameEditText;
	EditText passwordEditText;
	private LogInController loginController;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_log_in);
		if (loginController == null) {
			loginController = new LogInController("", "");
		}
		addListenerOnButton();
		initiateEditTexts();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.log_in, menu);
		return true;
	}
	
	public void addListenerOnButton() {
		enterButton = (Button)findViewById(R.id.enterButton);
		enterButton.setOnClickListener(this);
	}

	public void initiateEditTexts() {
		usernameEditText = (EditText) findViewById(R.id.usernameEditText);
		passwordEditText = (EditText) findViewById(R.id.passwordEditText);
		
	}
	@Override
	public void onClick(View v) {
		switch(v.getId()) {
			case R.id.enterButton:
				String username = usernameEditText.getText().toString();
				String password = passwordEditText.getText().toString();
				loginController.setUsername(username);
				loginController.setPassword(password);
				if (loginController.isValid()) {
					Intent i = new Intent(this, AccountViewActivity.class);
					startActivity(i);
				} else {
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
		}
	}
	
}
