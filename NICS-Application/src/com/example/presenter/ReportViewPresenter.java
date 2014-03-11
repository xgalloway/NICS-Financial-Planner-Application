package com.example.presenter;

import android.view.View;

import com.example.model.Model;
import com.example.nics_application.R;
import com.example.view.ClickListener;
import com.example.view.ReportView;

public class ReportViewPresenter implements ClickListener{
	
	private ReportView view;
	private final Model model;
	
	public ReportViewPresenter(ReportView view, Model model){
		this.view = view;
		this.model = model;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.showButton:
			String start = view.getStartDate();
			String end = view.getEndDate();
			view.acceptRange();
			break;
		}	
	}
	
}