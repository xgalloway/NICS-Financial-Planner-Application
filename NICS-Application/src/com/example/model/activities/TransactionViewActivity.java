package com.example.model.activities;

import java.util.Date;

import android.app.Activity;
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
import com.example.view.ClickListener;
import com.example.view.TransactionView;

public class TransactionViewActivity extends Activity implements TransactionView, OnClickListener {

	EditText amountEditText;
	EditText commentEditText;
	Button transactionButton;
	
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
		transactionButton = (Button) findViewById(R.id.transactionButton);
		transactionButton.setOnClickListener(this);
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
		return date;
	}
	
	@Override
	public String getComments() {
		return commentEditText.getText().toString();
	}

	@Override
	public void addSearchRequestNotifyCallback(ClickListener lsnr) {
		listener = lsnr;
	}

}
