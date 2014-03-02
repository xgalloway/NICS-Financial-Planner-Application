package com.example.model.activities;
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
import com.example.view.ClickListener;

public class AccountSetupActivity extends Activity implements AccountSetupView, OnClickListener {
	
	private Button createButton;
	private EditText accountNameText, displayNameText, balanceText, rateText;
	private String name;
	
	private ClickListener listener;
	AccountSetupPresenter presenter;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_account_setup);
		
		presenter = new AccountSetupPresenter(this, new UserModel());
		
		createButton = (Button) findViewById(R.id.createButton);
		createButton.setOnClickListener(this);
		
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
	public void onClick(View v) {
		presenter.onClick(v);
		
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

	/*public void create(){
		user.addAccount();
	}*/
	
}