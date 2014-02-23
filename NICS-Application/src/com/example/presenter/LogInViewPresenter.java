package com.example.presenter;

import android.view.View;

import com.example.model.Model;
import com.example.nics_application.R;
import com.example.view.ClickListener;
import com.example.view.LogInView;

public class LogInViewPresenter implements ClickListener {
	
	private LogInView view;
	private final Model model;

	public LogInViewPresenter(LogInView v, Model m) {
		this.view = v;
		this.model = m;
		view.addSearchRequestNotifyCallback(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.enterButton:
				String name = view.getUsername();
				String password = view.getPassword();
				if (model.acceptCredentials(name, password)) {
					view.acceptLogin();
				} else {
					view.displayAlertDialog();
				}
				break;
		}

	}

}
