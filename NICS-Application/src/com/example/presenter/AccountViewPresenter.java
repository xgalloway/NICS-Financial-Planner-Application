package com.example.presenter;

import android.view.View;



import com.example.model.Model;
import com.example.nics_application.R;
import com.example.view.ClickListener;
import com.example.view.AccountView;

public class AccountViewPresenter implements ClickListener {
	
	private AccountView view;
	//private final Model model;

	public AccountViewPresenter(AccountView v/*, Model m*/) {
		this.view = v;
		//this.model = m;
		view.addSearchRequestNotifyCallback(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()){ 
			case R.id.addButton:
				view.startAdd();
				break;
		}
	}

}
