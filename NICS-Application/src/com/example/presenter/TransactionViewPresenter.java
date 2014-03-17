package com.example.presenter;

import java.util.Date;


import android.view.View;

import com.example.model.Model;
import com.example.nics_application.R;
import com.example.support.ClickListener;
import com.example.view.RegistrationView;
import com.example.view.TransactionView;

public class TransactionViewPresenter implements ClickListener {

	private TransactionView view;
	private final Model model;
	
	public TransactionViewPresenter(TransactionView v, Model m) {
		this.view = v;
		this.model = m;
		view.addSearchRequestNotifyCallback(this);
	}

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
					model.getCurrent().getCurrent().makeTransaction(a, date, "withdrawal", comments);
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
					model.getCurrent().getCurrent().makeTransaction(a2, date2, "deposit", comments2);
					view.makeTransaction();
				}
			case R.id.cancelTransactionButton:
				view.makeTransaction();
		}

	}

}
