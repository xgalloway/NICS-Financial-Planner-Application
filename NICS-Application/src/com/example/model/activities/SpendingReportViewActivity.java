package com.example.model.activities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import support.ClickListener;
import support.TransactionAdapter;





import com.example.model.Report;
import com.example.model.Transaction;
import com.example.model.User;
import com.example.model.UserAccount;
import com.example.model.UserModel;
import com.example.nics_application.R;
import com.example.presenter.ReportViewPresenter;
import com.example.presenter.SpendingReportViewPresenter;
import com.example.view.SpendingReportView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class SpendingReportViewActivity extends Activity implements SpendingReportView, OnClickListener{
	
	private SpendingReportViewPresenter presenter;
	private ListView list;
	private ClickListener listener;
	private Button back;
	private TextView reportSumTextView;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_spending_report_view);
		
		presenter = new SpendingReportViewPresenter(this, new UserModel());
		initiateEditTextsAndButtons();
		populateListView();
	}
	
	public void initiateEditTextsAndButtons() {
		back = (Button)findViewById(R.id.back);
		back.setOnClickListener(this);
		
		reportSumTextView = (TextView)findViewById(R.id.reportSumTextView);
		
		list = (ListView)findViewById(R.id.spendingListView);
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
	public void addSearchRequestNotifyCallback(ClickListener lsnr) {
		listener = lsnr;
	}
	
	public void populateListView(){
		UserModel model = new UserModel();
		User u = model.getCurrent();
		Report r = u.getCurrentReport();
		double sum = 0;
		ArrayList transactionList = new ArrayList();
		for (int i = 0; i < u.getAccounts().size(); ++i) { 
			UserAccount account = u.getAccounts().get(i);
			List<Transaction> withdrawals = account.getWithdrawals();
			for (int j = 0; j < withdrawals.size(); ++j) {
				Transaction t = withdrawals.get(j);
				
				if (r.getEnd().getMonth() > t.getDate().getMonth()) {
					if (r.getStart().getMonth() < t.getDate().getMonth()) {
						transactionList.add(t.toString());
						sum = sum + t.getAmount();
					} else if (r.getStart().getMonth() == t.getDate().getMonth() && r.getStart().getDay() < t.getDate().getDay()) {
						transactionList.add(t.toString());
						sum = sum + t.getAmount();
					}
				} else if (r.getEnd().getMonth() == t.getDate().getMonth() && r.getEnd().getDay() > t.getDate().getDay()) {
					transactionList.add(t.toString());
					sum = sum + t.getAmount();
				}
				
				
			}
		}
		reportSumTextView.setText("Report Sum: " + sum);
		
		String[] transactions = Arrays.copyOf(transactionList.toArray(), transactionList.size(), String[].class);
		ArrayAdapter<String> adapter = new TransactionAdapter(this, R.layout.item_view, transactions);
		list.setAdapter(adapter);
	}

	@Override
	public void goBack() {
		Intent i = new Intent(this, AccountListViewActivity.class);
		startActivity(i);
		
	}
	
}