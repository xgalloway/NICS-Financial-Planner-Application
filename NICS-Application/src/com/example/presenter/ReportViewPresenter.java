package com.example.presenter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


import android.view.View;

import com.example.model.Model;
import com.example.model.Report;
import com.example.nics_application.R;
import com.example.support.ClickListener;
import com.example.view.ReportView;

/**
 * 
 * @author Team 16
 *
 */

public class ReportViewPresenter implements ClickListener{
	
	private ReportView view;
	private final Model model;
	
	/**
	 * Instantiates variables
	 * 
	 * @param view Page to be viewed
	 * @param model Model
	 */
	
	public ReportViewPresenter(ReportView view, Model model){
		this.view = view;
		this.model = model;
	}

	/**
	 * Shows option for user to choose dates to show spending reports.
	 * If the user chooses invalid dates, an alert dialog is shown.
	 * If not, the dates are accepted and the report is generated.
	 * 
	 * @param v Page to be viewed
	 */
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.startDateButton:
				view.displayDateDialog("start");
				break;
			case R.id.endDateButton:
				view.displayDateDialog("end");
				break;
			case R.id.showButton:
				
				String startDate = view.getStartDate();
				String endDate = view.getEndDate();
				//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				
			Date start = null;
			Date end = null;
			try {
				start = sdf.parse(startDate);
				end = sdf.parse(endDate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//System.out.println("Date start compare To Date end" + start.compareTo(end));
				if (end.before(start)) {
					view.displayAlertDialog();
				} else {
					//Report report = new Report(start, end);
					//model.getCurrent().setCurrentReport(report);
					model.setStartAndEndDates(startDate, endDate);
					view.acceptRange();
				}
				break;
		}	
	}
	
}