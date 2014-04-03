package com.example.activities;

import java.util.Calendar;
import java.util.Date;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.model.Application;
import com.example.model.UserModel;
import com.example.nics_application.R;
import com.example.presenter.ReportViewPresenter;
import com.example.support.ClickListener;
import com.example.view.ReportView;

/**
 * 
 * @author Team 16
 * 
 * Handles the report viewing
 */
public class ReportViewActivity extends Activity implements ReportView, OnClickListener{
	
	Button showButton, startButton, endButton;
	TextView startDateTextView, endDateTextView;
	//DatePicker startDatePicker, endDatePicker;
	
	private ClickListener listener;
	private ReportViewPresenter presenter;
	
	/**
     * Displays the report view
     * 
     * @param savedInstanceState saves state of the application
     */
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_report_view);
		Application app = Application.INSTANCE;
		presenter = new ReportViewPresenter(this, app.getModel());
		initiateEditTextsAndButtons();
	}
	
	/**
	 * Allows user to choose dates for reports
	 * and then view them.
	 */
	
	public void initiateEditTextsAndButtons() {
		showButton = (Button)findViewById(R.id.showButton);
		showButton.setOnClickListener(this);
		
		startButton = (Button)findViewById(R.id.startDateButton);
		startButton.setOnClickListener(this);
		
		endButton = (Button)findViewById(R.id.endDateButton);
		endButton.setOnClickListener(this);
		
		startDateTextView = (TextView)findViewById(R.id.startDateTextView);
		endDateTextView = (TextView)findViewById(R.id.endDateTextView);
		/*
		startDatePicker = (DatePicker)findViewById(R.id.startDatePicker);
		startDatePicker.setOnClickListener(this);
		
		endDatePicker = (DatePicker)findViewById(R.id.endDatePicker);
		endDatePicker.setOnClickListener(this);
		*/
	}
    
    /**
     * When the button is pushed, the information will be viewed
     * 
     * @param v The view that was clicked
     */
	
	@Override
	public void onClick(View v) {
		presenter.onClick(v);
	}

	/**
	 * Accepts report dates and moves on to the next screen
	 */
	
	@Override
	public void acceptRange() {
		Intent i = new Intent(this, SpendingReportViewActivity.class);
		startActivity(i);
		finish();
	}

	/**
	 * Changes start date chooser to a string
	 * 
	 * @return string
	 */
	
	@Override
	public String getStartDate() {
		return startButton.getText().toString();
	}

    /**
     * Changes end date chooser to a string
     * 
     * @return string
     */
	
	@Override
	public String getEndDate() {
		return endButton.getText().toString();
	}

	/**
	 * Tells user if they entered the date correctly
	 * or if they need to correct their mistake.
	 */
	
	@Override
	public void displayAlertDialog() {
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
		alertDialog.setTitle("Error");
		alertDialog.setMessage("End date must be after Start date.");
		alertDialog.setNeutralButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
			}
		});
		alertDialog.show();
	}

	/**
     * Sets listener for the application
     * 
     * @param lsnr Corresponds to the listener
     */
	
	@Override
	public void addSearchRequestNotifyCallback(ClickListener lsnr) {
		listener = lsnr;
	}

	/**
	 * Creates a way for the user to choose a start and an end
	 * date; it shows in a date picker instead of a drop down.
	 * 
	 * @param startOrEnd
	 */
	
	@Override
	public void displayDateDialog(String startOrEnd) {
		final Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int day = c.get(Calendar.DAY_OF_MONTH);
		 
		if (startOrEnd.equals("start")) {
			DatePickerDialog dpd = new DatePickerDialog(this,
			        new DatePickerDialog.OnDateSetListener() {
			 
			            @Override
			            public void onDateSet(DatePicker view, int year,
			                    int month, int day) {
			            	startButton.setText((month + 1) + "/" + day + "/" + year);
			                
			 
			            }
			        }, year, month, day);
			dpd.show();
		} else if (startOrEnd.equals("end")) {
			DatePickerDialog dpd = new DatePickerDialog(this,
			        new DatePickerDialog.OnDateSetListener() {
			 
			            @Override
			            public void onDateSet(DatePicker view, int year,
			                    int month, int day) {
			            	endButton.setText((month + 1) + "/" + day + "/" + year);
			                
			            }
			        }, year, month, day);
			dpd.show();
		}
		
		
	}
	
}