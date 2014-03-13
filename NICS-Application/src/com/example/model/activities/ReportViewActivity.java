package com.example.model.activities;

import java.util.Date;

import support.ClickListener;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.model.UserModel;
import com.example.nics_application.R;
import com.example.presenter.ReportViewPresenter;
import com.example.view.ReportView;

public class ReportViewActivity extends Activity implements ReportView, OnClickListener{
	
	Button showButton;
	TextView startDateTextView, endDateTextView;
	DatePicker startDatePicker, endDatePicker;
	
	private ClickListener listener;
	private ReportViewPresenter presenter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_report_view);
		
		presenter = new ReportViewPresenter(this, new UserModel());
		initiateEditTextsAndButtons();
	}
	
	public void initiateEditTextsAndButtons() {
		showButton = (Button)findViewById(R.id.showButton);
		showButton.setOnClickListener(this);
		
		startDateTextView = (TextView)findViewById(R.id.startDateTextView);
		endDateTextView = (TextView)findViewById(R.id.endDateTextView);
		
		startDatePicker = (DatePicker)findViewById(R.id.startDatePicker);
		startDatePicker.setOnClickListener(this);
		
		endDatePicker = (DatePicker)findViewById(R.id.endDatePicker);
		endDatePicker.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		presenter.onClick(v);
	}

	@Override
	public void acceptRange() {
		Intent i = new Intent(this, SpendingReportViewActivity.class);
		startActivity(i);
	}

	@Override
	public Date getStartDate() {
		int year = startDatePicker.getYear();
		int month = startDatePicker.getMonth();
		int day = startDatePicker.getDayOfMonth();
		Date date = new Date(year, month, day);
		return date;
	}

	@Override
	public Date getEndDate() {
		int year = endDatePicker.getYear();
		int month = endDatePicker.getMonth();
		int day = endDatePicker.getDayOfMonth();
		Date date = new Date(year, month, day);
		return date;
	}

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

	@Override
	public void addSearchRequestNotifyCallback(ClickListener lsnr) {
		listener = lsnr;
	}
	
}