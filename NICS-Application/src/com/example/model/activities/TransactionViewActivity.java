package com.example.model.activities;

import java.util.Date;

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
import com.example.presenter.RegistrationViewPresenter;
import com.example.presenter.TransactionViewPresenter;
import com.example.view.AccountView;
import com.example.view.TransactionView;

public class TransactionViewActivity extends Activity implements TransactionView, OnClickListener {

	EditText amountEditText;
	EditText commentEditText;
	Button transactionButton;
	Button withdrawalButton;
	Button depositButton;
	Button cancelButton;
	TransactionViewPresenter presenter;
	
	private ClickListener listener;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_transaction_view);
		
		presenter = new TransactionViewPresenter(this, new UserModel());
		initiateButtonsAndViews();
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
	
	public void initiateButtonsAndViews() {
		amountEditText = (EditText) findViewById(R.id.amountEditText);
		commentEditText = (EditText) findViewById(R.id.commentEditText);
		
		withdrawalButton = (Button) findViewById(R.id.goButton);
		withdrawalButton.setOnClickListener(this);
		
		depositButton = (Button) findViewById(R.id.depositButton);
		depositButton.setOnClickListener(this);
		
		cancelButton = (Button) findViewById(R.id.cancelTransactionButton);
		cancelButton.setOnClickListener(this);
	}

	@Override
	public void makeTransaction() {
		Intent i = new Intent(this, AccountViewActivity.class);
		startActivity(i);
	}

	@Override
	public String getAmount() {
		return amountEditText.getText().toString();
	}

	@Override
	public Date getDate() {
		Date date = new Date(System.currentTimeMillis());
		Date newDate = new Date(date.getYear(), date.getMonth(), date.getDay());
		return newDate;
	}
	
	@Override
	public String getComments() {
		return commentEditText.getText().toString();
	}

	@Override
	public void addSearchRequestNotifyCallback(ClickListener lsnr) {
		listener = lsnr;
	}
	
	@Override
	public void displayAlertDialog() {
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
		alertDialog.setTitle("Transaction Failure");
		alertDialog.setMessage("Make sure to specify an amount before making a transaction.");
		alertDialog.setNeutralButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
			}
		});
		alertDialog.show();
		
	}

}
