package com.example.presenter;

import android.view.View;


import com.example.model.Model;
import com.example.nics_application.R;
import com.example.view.ClickListener;
import com.example.view.AccountSetupView;

public class AccountSetupPresenter implements ClickListener {

	private AccountSetupView view;
	private final Model model;
	
	public AccountSetupPresenter(AccountSetupView v, Model m) {
		this.view = v;
		this.model = m;
		view.addSearchRequestNotifyCallback(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.createButton:
				String account_type = view.getDisplayName();
				String account_name = view.getName();
				if (model.isValidUser(account_type)) {
					//view.displayAlertDialog();
				} else {
					//model.addUser(account_type, account_name);
					//view.acceptAccount();
				}
		}
	}
	

}