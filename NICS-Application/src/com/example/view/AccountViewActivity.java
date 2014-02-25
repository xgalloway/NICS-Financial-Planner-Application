package com.example.view;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.model.activities.AccountSetupActivity;
import com.example.nics_application.R;
import com.example.presenter.AccountViewPresenter;

public class AccountViewActivity extends Activity implements AccountView, OnClickListener {
	Button addButton;
	AccountViewPresenter presenter;
	private ClickListener listener;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_account_view);
		
		presenter = new AccountViewPresenter(this/*, new UserModel()*/);
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
		startActivity(i);
		
	}
	
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}

