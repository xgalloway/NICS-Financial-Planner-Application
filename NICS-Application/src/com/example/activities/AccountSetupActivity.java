package com.example.activities;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.model.Application;
import com.example.model.User;
import com.example.model.UserModel;
import com.example.nics_application.R;
import com.example.presenter.AccountSetupPresenter;
import com.example.support.ClickListener;
import com.example.view.AccountSetupView;

/**
 * 
 * @author Team 16
 *
 * Deals with a user setting up their account
 */

public class AccountSetupActivity extends Activity implements AccountSetupView, OnClickListener {
	
	private Button createButton;
	private Button cancelButton;
	private EditText accountNameText, balanceText, rateText;
	private String name;
	
	private ClickListener listener;
	AccountSetupPresenter presenter;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_account_setup);
		Application app = Application.INSTANCE;
		presenter = new AccountSetupPresenter(this, app.getModel());
		
		initiateButtonsAndEditTexts();
	}

	/**
	 * When the button is pushed, the account setup up option will display
	 * @param v
	 */
	
	@Override
	public void onClick(View v) {
		presenter.onClick(v);
		
	}
	
	/**
	 * Allows the user to be able to use buttons and text fields
	 */
	
	@Override
	public void initiateButtonsAndEditTexts() {
		createButton = (Button) findViewById(R.id.createButton);
		createButton.setOnClickListener(this);
		
		cancelButton = (Button) findViewById(R.id.cancelButton);
		cancelButton.setOnClickListener(this);
		
		accountNameText = (EditText) findViewById(R.id.accountNameText);
		//accountNameText.setText(name);
		
		balanceText = (EditText) findViewById(R.id.balanceText);
		//balanceText.setText(name);
		
		rateText = (EditText) findViewById(R.id.rateText);
		//rateText.setText(name);
	}

	/**
	 * Goes to the next screen
	 */
	
	@Override
	public void acceptAccount() {
		Intent i = new Intent(this, AccountListViewActivity.class);
		startActivity(i);
		finish();
	}

	/**
	 * Changes name text field into a string	
	 */
	
	@Override
	public String getName() {
		return accountNameText.getText().toString();
	}
	
	/**
	 * Changes display name text field into a string  
	 */
	
	@Override
	public String getDisplayName() {
		return null;
	}
	
	/**
     * Sets listener for the application
     * 
     * @param lsr
     */
	
	@Override
	public void addSearchRequestNotifyCallback(ClickListener lsr) {
		listener = lsr;
	}

	/**
	 * Allows user to know if the information they inserted
	 * is correct or incorrect
	 */
	
	@Override
	public void displayAlertDialog() {
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
		alertDialog.setTitle("Error");
		alertDialog.setMessage("Balance and Interest Rate must be numbers");
		alertDialog.setNeutralButton("OK", new DialogInterface.OnClickListener() {
			
			@Override
			
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
			}
		});
		alertDialog.show();
		
	}

	/**
	 * Allows the user to not go through with registration
	 */
	
	@Override
	public void cancelAccountCreation() {
		Intent i = new Intent(this, AccountListViewActivity.class);
		startActivity(i);
		finish();
	}

	/**
	 * Gets interest rate
	 */
	
	@Override
	public String getRate() {
		return rateText.getText().toString();
	}

	/**
	 * 
	 */
	@Override
	public String getBalance() {
		return balanceText.getText().toString();
	}

	/*public void create(){
		user.addAccount();
	}*/
	
}