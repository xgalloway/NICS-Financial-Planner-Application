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

/**
 * 
 * @author Team 16
 *
 * Handles spending report viewing
 */
public class SpendingReportViewActivity extends Activity implements SpendingReportView, OnClickListener{
	
	private SpendingReportViewPresenter presenter;
	private ListView list;
	private ClickListener listener;
	private Button back;
	private TextView reportSumTextView;

	/**
     * Displays the spending report
     * 
     * @param savedInstanceState saves state of the application
     */
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_spending_report_view);
		Application app = Application.INSTANCE;
		presenter = new SpendingReportViewPresenter(this, app.getModel());
		initiateEditTextsAndButtons();
		populateListView();
	}
	
	/**
     * Allows user to exit this screen,
     * have a plain or viewing or see the info in a list.
     */
	
	public void initiateEditTextsAndButtons() {
		back = (Button)findViewById(R.id.back);
		back.setOnClickListener(this);
		
		reportSumTextView = (TextView)findViewById(R.id.reportSumTextView);
		
		list = (ListView)findViewById(R.id.spendingListView);
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
	 * Retrieves date
	 * 
	 * @return state
	 */
	
	@Override
	public Date getDate() {
		return null;
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
	 * Grabs all the withdrawals and puts them into a list
	 * for easier viewing.
	 */
	
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
		HashSet<String> set = new HashSet<String>();
		
		double sum = 0;
		ArrayList<String> transactionList = new ArrayList<String>();
		
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

	/**
	 * Allows user to go navigate back through application
	 */
	
	@Override
	public void goBack() {
		Intent i = new Intent(this, AccountListViewActivity.class);
		startActivity(i);
		finish();
	}
	
}