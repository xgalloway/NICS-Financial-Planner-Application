package com.example.presenter;

import java.util.Date;

import android.view.View;

import com.example.model.Model;
import com.example.nics_application.R;
import com.example.view.ClickListener;
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
				double a = Integer.parseInt(amount);
				Date date = view.getDate();
				String comments = view.getComments();
				model.getCurrent().getCurrent().makeTransaction(a, date, comments);
				view.makeTransaction();
				break;
		}

	}

}
