package com.example.presenter;

import android.view.View;



import com.example.model.Model;
import com.example.nics_application.R;
import com.example.support.ClickListener;
import com.example.view.AccountView;

/**
 * 
 * @author Team 16
 *
 */

public class AccountViewPresenter implements ClickListener {
	
	private AccountView view;
	private final Model model;

	/**
	 * Instantiates variables
	 * 
	 * @param v Page to be viewed
	 * @param m Model
	 */
	
	public AccountViewPresenter(AccountView v, Model m) {
		this.view = v;
		this.model = m;
		view.addSearchRequestNotifyCallback(this);
	}

	/**
	 * Takes user to transaction or leaves transaction screen
	 * depending on what button is pressed
	 * 
	 * @param v Page to be viewed
	 */
	
	@Override
	public void onClick(View v) {
		switch (v.getId()){ 
			case R.id.transactionButton:
				view.addTransaction();
				break;
			case R.id.backButton:
				model.setCurrentUser(model.getCurrent());
				view.leaveTransactionScreen();
				break;
			    
		}
	}

	public void onItemClick(View v, int position) {
		
	}


}
