package com.example.model.activities;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


import com.example.model.Transaction;
import com.example.model.User;
import com.example.model.UserAccount;
import com.example.model.UserModel;
import com.example.nics_application.R;
import com.example.presenter.AccountViewPresenter;

import com.example.view.AccountView;
import com.example.view.ClickListener;

public class AccountViewActivity extends Activity implements AccountView, OnClickListener {
	
	
	private Button backButton;
	private Button transactionButton;
	private TextView balanceTextView;
	private ListView list;
	
	private AccountViewPresenter presenter;
	private ClickListener listener;
	
	//public List<User> list = new ArrayList<User>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_account_view);
		
		populateListView();
		
		presenter = new AccountViewPresenter(this, new UserModel());
		
		initiateButtonsAndViews();
		
	}
	
	public void initiateButtonsAndViews() {
		backButton = (Button)findViewById(R.id.backButton);
		backButton.setOnClickListener(this);
		
		transactionButton = (Button)findViewById(R.id.transactionButton);
		transactionButton.setOnClickListener(this);
		
		balanceTextView = (TextView)findViewById(R.id.balanceTextView);
		balanceTextView.setText("text");
		
		list = (ListView) findViewById(R.id.listViewMain);
		
	}
	@Override
	public void onClick(View v) {
		presenter.onClick(v);
	}
	
	@Override
	public void addSearchRequestNotifyCallback(ClickListener lsr) {
		listener = lsr;
	}
	
	private void populateListView() {
		
		UserModel model = new UserModel();
		List<Transaction> transactions = model.getCurrent().getCurrent().getTransactions();
		String[] items = {""};
		if (transactions.size() < 0) {
			//items = {""};
		} else {
			items = new String[transactions.size()];
		
			for (int i = 0; i < transactions.size(); i++) {
				items[i] = transactions.get(i).toString();
			}
		}
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.item_view, items);
		list.setAdapter(adapter);
	}

	@Override
	public void addTransaction() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getAmount() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date getDate() {
		// TODO Auto-generated method stub
		return null;
	}
}	