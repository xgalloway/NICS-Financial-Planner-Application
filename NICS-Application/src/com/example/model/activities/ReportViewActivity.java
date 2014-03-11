package com.example.model.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.example.model.UserModel;
import com.example.nics_application.R;
import com.example.presenter.ReportViewPresenter;
import com.example.view.ClickListener;
import com.example.view.ReportView;

public class ReportViewActivity extends Activity implements ReportView, OnClickListener{
	
	Button showButton;
	EditText startDateText, endDateText;
	
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
		startDateText = (EditText) findViewById(R.id.startDateText);
		endDateText = (EditText) findViewById(R.id.endDateText);
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
	public String getStartDate() {
		return startDateText.getText().toString();
	}

	@Override
	public String getEndDate() {
		return endDateText.getText().toString();
	}

	@Override
	public void displayAlertDialog() {
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
		alertDialog.setTitle("Authentication Failure");
		alertDialog.setMessage("Authentication has failed. Please try again.");
		alertDialog.setNeutralButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
			}
		});
		alertDialog.show();
	}

	@Override
	public void addSearchRequestNotifyCallback(ClickListener lsr) {
		listener = lsr;
	}
	
}