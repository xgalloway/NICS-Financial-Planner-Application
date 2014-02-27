package com.example.presenter;

import android.view.View;



import com.example.model.Model;
import com.example.nics_application.R;
import com.example.view.ClickListener;
import com.example.view.RegistrationView;

public class RegistrationViewPresenter implements ClickListener {

	private RegistrationView view;
	private final Model model;
	
	public RegistrationViewPresenter(RegistrationView v, Model m) {
		this.view = v;
		this.model = m;
		view.addSearchRequestNotifyCallback(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.enterButton:
				String username = view.getUsername();
				String password = view.getPassword();
				if (model.isValidUser(username)) {
					view.displayAlertDialog();
				} else {
					model.addUser(username, password);
					view.acceptRegistration();
				}
				
		}

	}

}
