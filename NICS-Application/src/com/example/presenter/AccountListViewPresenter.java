package com.example.presenter;

import java.util.List;


import android.view.View;



import com.example.model.Model;
import com.example.model.UserAccount;
import com.example.nics_application.R;
import com.example.support.ClickListener;
import com.example.view.AccountListView;
import com.example.view.AccountView;

public class AccountListViewPresenter implements ClickListener {
	
	private AccountListView view;
	private final Model model;

	public AccountListViewPresenter(AccountListView v, Model m) {
		this.view = v;
		this.model = m;
		view.addSearchRequestNotifyCallback(this);
	}

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
	
	public void onItemClick(View v, int position) {
		model.getCurrent().setCurrentAccount(model.getCurrent().getAccounts().get(position));
		view.viewAccount();
	}

}
