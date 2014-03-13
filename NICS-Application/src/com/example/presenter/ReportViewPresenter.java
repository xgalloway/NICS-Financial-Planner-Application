package com.example.presenter;

import java.util.Date;

import support.ClickListener;

import android.view.View;

import com.example.model.Model;
import com.example.model.Report;
import com.example.nics_application.R;
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
				Date start = view.getStartDate();
				Date startNew = new Date(System.currentTimeMillis());
				startNew.setDate(start.getDay());
				startNew.setMonth(start.getMonth());
				
				Date end = view.getEndDate();
				Date endNew = new Date(System.currentTimeMillis());
				endNew.setDate(end.getDay());
				endNew.setMonth(end.getMonth());
				if (end.before(start)) {
					view.displayAlertDialog();
				} else {
					Report report = new Report(start, end);
					model.getCurrent().setCurrentReport(report);
					view.acceptRange();
				}
				break;
		}	
	}
	
}