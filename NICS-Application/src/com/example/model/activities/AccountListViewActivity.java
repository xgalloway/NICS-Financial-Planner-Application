package com.example.model.activities;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
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
	private ListView list;
	
	@Override 
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_account_list_view);
		initiateViews();
		populateListView();
		
		presenter = new AccountListViewPresenter(this, new UserModel());
		
	}
	
	@Override
	
	public void onClick(View v) {
		presenter.onClick(v);
	}
	
	public void initiateViews() {
		addButton = (Button) findViewById(R.id.backButton);
		addButton.setOnClickListener(this);
		
		list = (ListView)findViewById(R.id.listViewMain);
		list.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> lv, View v, int position,
					long id) {
				presenter.onItemClick(v, position);
				
			}
			
		});
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
		//ListView list = (ListView) findViewById(R.id.listViewMain);
		
		UserModel model = new UserModel();
		List<UserAccount> accounts = model.getCurrent().getAccounts();
		String[] items = new String[accounts.size()];
		//List<UserAccount> aList = new ArrayList<UserAccount>();
		for (int i = 0; i < accounts.size(); i++) {
			items[i] = accounts.get(i).toString();
		}
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.item_view, items);
		list.setAdapter(adapter);
	}

	@Override
	public void viewAccount() {
		Intent i = new Intent(this, AccountViewActivity.class);
		startActivity(i);
	}
	
	
}	