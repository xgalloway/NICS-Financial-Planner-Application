package com.example.model.activities;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.model.User;
import com.example.model.UserAccount;
import com.example.model.UserModel;
import com.example.nics_application.R;
import com.example.presenter.AccountListViewPresenter;
import com.example.presenter.AccountViewPresenter;
import com.example.view.AccountListView;
import com.example.view.AccountView;
import com.example.view.ClickListener;

public class AccountListViewActivity extends Activity implements AccountListView, OnClickListener {
	
	private Button addButton;
	private AccountListViewPresenter presenter;
	private ClickListener listener;
	
	public List<User> list = new ArrayList<User>();
	public User actor;
	//public static int i = 0;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_account_view);
		
		populateListView();
		
		presenter = new AccountListViewPresenter(this, new UserModel());
		
		addButton = (Button) findViewById(R.id.addButton);
		addButton.setOnClickListener(this);
		
	}
	
	@Override
	
	public void onClick(View v) {
		presenter.onClick(v);
	}
	
	@Override
	
	public void addSearchRequestNotifyCallback(ClickListener lsr) {
		listener = lsr;
	}
	
	@Override
	
	public void createAccount() {
		Intent i = new Intent(this, AccountSetupActivity.class);
		startActivity(i);
	}
	
	private void populateListView() {
		ListView list = (ListView) findViewById(R.id.listViewMain);
		
		String[] items = {""};
		List<UserAccount> aList = new ArrayList<UserAccount>();
		if (aList.size() > 0) {
			for (int i = 0; i < aList.size(); i++) {
				items[i] = aList.get(i).getAccountName();
			}
		}
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.item_view, items);
		list.setAdapter(adapter);
	}
}	