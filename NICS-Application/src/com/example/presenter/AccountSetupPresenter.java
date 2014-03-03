package com.example.presenter;

import android.view.View;


import com.example.model.Model;
import com.example.model.UserAccount;
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
				String account_rate = view.getRate();
				String account_balance = view.getBalance();
				try {
					int rate = Integer.parseInt(account_rate);
					int balance = Integer.parseInt(account_balance);
					
					model.getCurrent().addAccount(new UserAccount(account_type, account_name, balance, rate));
					view.acceptAccount();
				} catch (NumberFormatException e) {
					view.displayAlertDialog();
				}
				
				break;
			case R.id.cancelButton:
				view.cancelAccountCreation();
		}
	}
	

}