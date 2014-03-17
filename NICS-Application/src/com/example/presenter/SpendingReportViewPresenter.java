package com.example.presenter;

import android.view.View;

import com.example.model.Model;
import com.example.nics_application.R;
import com.example.support.ClickListener;
import com.example.view.SpendingReportView;

public class SpendingReportViewPresenter implements ClickListener{
	
	SpendingReportView view;
	private final Model model;
	
	public SpendingReportViewPresenter(SpendingReportView view, Model model){
		this.view = view;
		this.model = model;
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.back:
			view.goBack();
			break;
		}	
		
	}
	
}