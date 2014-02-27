package com.example.view;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.model.activities.AccountSetupActivity;
import com.example.nics_application.R;
import com.example.presenter.AccountViewPresenter;

public class AccountViewActivity extends Activity implements AccountView, OnClickListener {
	private Button addButton;
	private AccountViewPresenter presenter;
	private ClickListener listener;
	
	
	
	@Override
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_account_view);
		
		populateListView();
		
		presenter = new AccountViewPresenter(this);
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
	
	public void startAdd() {
		Intent i = new Intent(this, AccountSetupActivity.class);
		//i.putE
		startActivity(i);
	}
	
	private void populateListView() {
		String[] items =  {""};
		//for
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				R.layout.item_view,
				items);
		ListView list = (ListView) findViewById(R.id.listViewMain);
		list.setAdapter(adapter);
	}
}	