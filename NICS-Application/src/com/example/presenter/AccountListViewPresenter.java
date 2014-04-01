package com.example.presenter;

import java.util.List;


import android.view.View;



import com.example.model.Model;
import com.example.model.UserAccount;
import com.example.nics_application.R;
import com.example.support.ClickListener;
import com.example.view.AccountListView;
import com.example.view.AccountView;

/**
 * 
 * @author Team 16
 *
 */

public class AccountListViewPresenter implements ClickListener {
	
	private AccountListView view;
	private final Model model;

	/**
	 * 
	 * @param v
	 * @param m
	 */
	
	public AccountListViewPresenter(AccountListView v, Model m) {
		this.view = v;
		this.model = m;
		view.addSearchRequestNotifyCallback(this);
	}

	/**
	 * If back button is pressed, user is taken to create account page
	 * If report button is pressed, user is taken to report page
	 * 
	 * @param v
	 */
	
	@Override
	public void onClick(View v) {
		switch (v.getId()){ 
			case R.id.backButton:
				view.createAccount();
				break;
			case R.id.reportButton:
				model.setCurrentUser(model.getCurrent());
				view.viewReport();
				break;
		}
	}
	
	/**
	 * When putton is pressed, user is taken to view account
	 * @param v
	 * @param position
	 */
	
	public void onItemClick(View v, int position) {
		String parent = model.getCurrent().getUsername();
		UserAccount current = model.getUserAccounts(parent).get(position);
		model.setCurrentAccount(current);
		view.viewAccount();
	}

}
