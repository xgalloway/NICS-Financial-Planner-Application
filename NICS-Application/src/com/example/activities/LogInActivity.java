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
import com.example.presenter.LogInViewPresenter;
import com.example.support.ClickListener;
import com.example.view.LogInView;

/**
 * 
 * @author Team 16
 *
 * Handles user log in
 */
public class LogInActivity extends Activity implements LogInView, OnClickListener {

	Button enterButton;
	EditText usernameEditText;
	EditText passwordEditText;
	
	
	private ClickListener listener;
	LogInViewPresenter presenter;
	
	/**
	 * 
	 */
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_log_in);
		Application app = Application.INSTANCE;
		//presenter = new LogInViewPresenter(this, new UserModel());
		presenter = new LogInViewPresenter(this, app.getModel());
		initiateEditTextsAndButtons();
	}
	
	/**
	 * Depending on a user's account, different options
	 * will be displayed in the menu
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.log_in, menu);
		return true;
	}
	
	/**
	 * Allows a user to enter their username, password
	 * and then enter it for log in
	 */
	
	public void initiateEditTextsAndButtons() {
		enterButton = (Button)findViewById(R.id.goButton);
		enterButton.setOnClickListener(this);
		usernameEditText = (EditText) findViewById(R.id.amountEditText);
		passwordEditText = (EditText) findViewById(R.id.passwordEditText);
		
	}
	
	/**
	 * Turns the username the user entered into a string
	 */
	
	@Override
	public String getUsername() {
		return usernameEditText.getText().toString();
	}
	
	/**
     * Turns the password the user entered into a string
     */
	
	@Override
	public String getPassword() {
		return passwordEditText.getText().toString();
	}
	
	/**
	 * If the information is correct, it will allow the user to proceed
	 */
	
	@Override
	public void acceptLogin() {
		Intent i = new Intent(this, AccountListViewActivity.class);
		startActivity(i);
		finish();
		
	}

	/**
	 * Provides a search button
	 */
	
	@Override
	public void addSearchRequestNotifyCallback(final ClickListener lsnr) {
		listener = lsnr;
		
	}

	/**
	 * Allows the user to know whether or not the credentials are correct
	 */
	
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

	/**
     * When the button is pushed, the information will be viewed
     */
	
	@Override
	public void onClick(View v) {
		presenter.onClick(v);
	}
	
}
