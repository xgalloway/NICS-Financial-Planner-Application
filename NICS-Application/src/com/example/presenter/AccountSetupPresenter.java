package com.example.presenter;

import android.view.View;


import com.example.model.Model;
import com.example.model.UserAccount;
import com.example.nics_application.R;
import com.example.support.ClickListener;
import com.example.view.AccountSetupView;

/**
 * 
 * @author Team 16
 *
 */

public class AccountSetupPresenter implements ClickListener {

	private AccountSetupView view;
	private final Model model;
	
	/**
	 * Instantiates variables
	 * 
	 * @param v Page to be viewed
	 * @param m Model
	 */
	
	public AccountSetupPresenter(AccountSetupView v, Model m) {
		this.view = v;
		this.model = m;
		view.addSearchRequestNotifyCallback(this);
	}

	/**
	 * When the button is pushed, the information will be viewed
	 *
	 * @param v Page to be viewed
	 */
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.createButton:
				//String account_type = view.getDisplayName();
				String account_name = view.getName();
				String account_rate = view.getRate();
				String account_balance = view.getBalance();
				try {
					int rate = Integer.parseInt(account_rate);
					int balance = Integer.parseInt(account_balance);
					
					//model.getCurrent().addAccount(new UserAccount(account_type, account_name, balance, rate));
					String parent = model.getCurrent().getUsername();
					model.addUserAccount(account_name, balance, rate, parent);
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