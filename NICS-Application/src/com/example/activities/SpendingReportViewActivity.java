package com.example.activities;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;






import com.example.model.Application;
import com.example.model.Report;
import com.example.model.Transaction;
import com.example.model.User;
import com.example.model.UserAccount;
import com.example.model.UserModel;
import com.example.nics_application.R;
import com.example.presenter.ReportViewPresenter;
import com.example.presenter.SpendingReportViewPresenter;
import com.example.support.ClickListener;
import com.example.support.TransactionAdapter;
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
		Application app = Application.INSTANCE;
		presenter = new SpendingReportViewPresenter(this, app.getModel());
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
		Application app = Application.INSTANCE;
		UserModel model = app.getModel();
		User u = model.getCurrent();
		String parent = u.getUsername();
		
		List<Transaction> withdrawals = null;
		try {
			withdrawals = model.getWithdrawals();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HashSet set = new HashSet();
		
		double sum = 0;
		ArrayList transactionList = new ArrayList();
		
		for (int i = 0; i < withdrawals.size(); ++i ) {
			Transaction t = withdrawals.get(i);
			if (model.getStartDate().before(t.getDate())) {
				if (model.getEndDate().after(t.getDate())) {
					 if (!set.contains(t.getParent())) {
						 set.add(t.getParent());
						 transactionList.add(t.getParent() + ":");
					 } 
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