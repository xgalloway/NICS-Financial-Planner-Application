package com.example.model.activities;
import support.ClickListener;
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

import com.example.model.User;
import com.example.model.UserModel;
import com.example.nics_application.R;
import com.example.presenter.AccountSetupPresenter;
import com.example.view.AccountSetupView;

public class AccountSetupActivity extends Activity implements AccountSetupView, OnClickListener {
	
	private Button createButton;
	private Button cancelButton;
	private EditText accountNameText, displayNameText, balanceText, rateText;
	private String name;
	
	private ClickListener listener;
	AccountSetupPresenter presenter;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_account_setup);
		
		presenter = new AccountSetupPresenter(this, new UserModel());
		
		initiateButtonsAndEditTexts();
	}

	@Override
	public void onClick(View v) {
		presenter.onClick(v);
		
	}
	
	@Override
	public void initiateButtonsAndEditTexts() {
		createButton = (Button) findViewById(R.id.createButton);
		createButton.setOnClickListener(this);
		
		cancelButton = (Button) findViewById(R.id.cancelButton);
		cancelButton.setOnClickListener(this);
		
		accountNameText = (EditText) findViewById(R.id.accountNameText);
		//accountNameText.setText(name);
		
		displayNameText = (EditText) findViewById(R.id.displayNameText);
		//displayNameText.setText(name);
		
		balanceText = (EditText) findViewById(R.id.balanceText);
		//balanceText.setText(name);
		
		rateText = (EditText) findViewById(R.id.rateText);
		//rateText.setText(name);
	}

	@Override
	public void acceptAccount() {
		Intent i = new Intent(this, AccountListViewActivity.class);
		startActivity(i);
	}

	
	@Override
	public String getName() {
		return accountNameText.getText().toString();
	}
	
	@Override
	public String getDisplayName() {
		return displayNameText.getText().toString();
	}
	
	@Override
	public void addSearchRequestNotifyCallback(ClickListener lsr) {
		listener = lsr;
	}

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

	@Override
	public void cancelAccountCreation() {
		Intent i = new Intent(this, AccountListViewActivity.class);
		startActivity(i);
	}

	@Override
	public String getRate() {
		return rateText.getText().toString();
	}

	@Override
	public String getBalance() {
		return balanceText.getText().toString();
	}

	/*public void create(){
		user.addAccount();
	}*/
	
}