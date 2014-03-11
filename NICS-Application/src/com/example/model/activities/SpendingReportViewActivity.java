package com.example.model.activities;

import java.util.Date;





import com.example.model.UserModel;
import com.example.nics_application.R;
import com.example.presenter.ReportViewPresenter;
import com.example.presenter.SpendingReportViewPresenter;
import com.example.view.ClickListener;
import com.example.view.SpendingReportView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class SpendingReportViewActivity extends Activity implements SpendingReportView, OnClickListener{
	
	private SpendingReportViewPresenter presenter;
	private ListView list;
	private ClickListener listener;
	private Button back;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_spending_report_view);
		
		presenter = new SpendingReportViewPresenter(this, new UserModel());
		initiateEditTextsAndButtons();
	}
	
	public void initiateEditTextsAndButtons() {
		back = (Button)findViewById(R.id.back);
		back.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		presenter.onClick(v);
	}

	@Override
	public Date getDate() {
		return null;
	}

	@Override
	public void addSearchRequestNotifyCallback(ClickListener lsr) {
		listener = lsr;
	}
	
	public void populateListView(){
		
	}

	@Override
	public void goBack() {
		Intent i = new Intent(this, AccountListViewActivity.class);
		startActivity(i);
		
	}
	
}