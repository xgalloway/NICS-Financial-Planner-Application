package com.example.presenter;

import java.util.Date;


import android.view.View;

import com.example.model.Model;
import com.example.model.Transaction;
import com.example.model.UserAccount;
import com.example.nics_application.R;
import com.example.support.ClickListener;
import com.example.view.RegistrationView;
import com.example.view.TransactionView;

/**
 * 
 * @author Team 16
 *
 */

public class TransactionViewPresenter implements ClickListener {

	private TransactionView view;
	private final Model model;
	
	/**
	 * Instantiates variables
	 * @param v Page to be viewed
	 * @param m Model
	 */
	
	public TransactionViewPresenter(TransactionView v, Model m) {
		this.view = v;
		this.model = m;
		view.addSearchRequestNotifyCallback(this);
	}

	/**
	 * If the user made a withdrawal, the corresponding alert dialog box shows
	 * and the account balance is updated correctly.
	 * If the user made a deposit, the corresponding alert dialog box show
	 * and the account balance is updated correctly.
	 * Both times the transaction is added the the list of transactions.
	 * 
	 * @param v Page to be viewed
	 */
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.goButton:
				String amount = view.getAmount();
				
				if (amount.toString().length() < 1) {
					view.displayAlertDialog();
				} else {
					double a = Integer.parseInt(amount);
					Date date = view.getDate();
					String comments = view.getComments();
					Transaction t = new Transaction(a, date, "withdrawal", comments, model.getCurrentAccount().getName());
					model.makeTransaction(t);
					
					UserAccount old = model.getCurrentAccount();
					old.setBalance(old.getBalance() - a);
					model.updateUserAccount(old);
					
					
					view.makeTransaction();
				}
				break;
			case R.id.depositButton:
				String amount2 = view.getAmount();
				if (amount2.toString().length() < 1) {
					view.displayAlertDialog();
				} else {
					double a2 = Integer.parseInt(amount2);
					Date date2 = view.getDate();
					String comments2 = view.getComments();
					Transaction t = new Transaction(a2, date2, "deposit", comments2, model.getCurrentAccount().getName());
					model.makeTransaction(t);
					
					UserAccount old = model.getCurrentAccount();
					old.setBalance(old.getBalance() + a2);
					model.updateUserAccount(old);
					
					view.makeTransaction();
				}
			case R.id.cancelTransactionButton:
				view.makeTransaction();
		}

	}
	

}
