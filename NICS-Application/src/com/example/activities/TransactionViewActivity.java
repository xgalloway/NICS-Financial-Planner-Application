package com.example.activities;

import java.util.Date;


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
import com.example.presenter.TransactionViewPresenter;
import com.example.support.ClickListener;
import com.example.view.AccountView;
import com.example.view.TransactionView;

/**
 * 
 * @author Team 16
 * 
 * Allows user to view all transactions at once
 */
public class TransactionViewActivity extends Activity implements TransactionView, OnClickListener {

	EditText amountEditText;
	EditText commentEditText;
	Button transactionButton;
	Button withdrawalButton;
	Button depositButton;
	Button cancelButton;
	TransactionViewPresenter presenter;
	
	private ClickListener listener;
	
	/**
     * Displays the log in and register buttons
     * along with the logo on the main screen
     * 
     * @param savedInstanceState
     */
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_transaction_view);
		Application app = Application.INSTANCE;
		presenter = new TransactionViewPresenter(this, app.getModel());
		initiateButtonsAndViews();
	}
	
	/**
     * Creates menu with appropriate options
     * 
     * @param menu
     */
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.log_in, menu);
		return true;
	}
	
	/**
     * When the button is pushed, the information will be viewed
     * 
     * @param v
     */
	
	@Override
	public void onClick(View v) {
		presenter.onClick(v);

	}
	
	/**
     * Allows user to deposit, withdrawal, cancel, enter amount and
     * leave a comment for a transaction.
     */
	
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

	/**
	 * Allows transaction button to be used
	 */
	
	@Override
	public void makeTransaction() {
		Intent i = new Intent(this, AccountViewActivity.class);
		startActivity(i);
		finish();
	}

	/**
	 * Turns user amount text into a string
	 */
	
	@Override
	public String getAmount() {
		return amountEditText.getText().toString();
	}

	/**
     * Turns user date text into a string
     */
	
	@Override
	public Date getDate() {
		Date date = new Date(System.currentTimeMillis());
		return date;
	}
	
	/**
     * Turns user comment text into a string
     */
	
	@Override
	public String getComments() {
		return commentEditText.getText().toString();
	}

	/**
     * Provides a search button
     * 
     * @param lsnr
     */
	
	@Override
	public void addSearchRequestNotifyCallback(ClickListener lsnr) {
		listener = lsnr;
	}
	
	/**
	 * Tells user if the transaction was a success or if
	 * they forgot to enter in an amount.
	 */
	
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
