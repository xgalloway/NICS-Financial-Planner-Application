package com.example.activities;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.model.Application;
import com.example.model.Transaction;
import com.example.model.User;
import com.example.model.UserModel;
import com.example.nics_application.R;
import com.example.presenter.IncomeReportViewPresenter;
import com.example.presenter.SpendingReportViewPresenter;
import com.example.support.ClickListener;
import com.example.support.TransactionAdapter;
import com.example.view.IncomeReportView;

public class IncomeReportViewActivity extends Activity implements OnClickListener,
        IncomeReportView {

    private IncomeReportViewPresenter presenter;
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
        presenter = new IncomeReportViewPresenter(this, app.getModel());
        initiateEditTextsAndButtons();
        populateListView();
    }
    
    
    
    public void populateListView(){
        Application app = Application.INSTANCE;
        UserModel model = app.getModel();
        User u = model.getCurrent();
        String parent = u.getUsername();
        
        List<Transaction> deposits = null;
        try {
            deposits = model.getDeposits();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        HashSet<String> set = new HashSet<String>();
        
        double sum = 0;
        ArrayList<String> transactionList = new ArrayList<String>();
        
        for (int i = 0; i < deposits.size(); ++i ) {
            Transaction t = deposits.get(i);
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
    
    
    public void initiateEditTextsAndButtons() {
        back = (Button)findViewById(R.id.back);
        back.setOnClickListener(this);
        
        reportSumTextView = (TextView)findViewById(R.id.reportSumTextView);
        
        list = (ListView)findViewById(R.id.spendingListView);
    }
    
    @Override
    public void goBack() {
        Intent i = new Intent(this, AccountListViewActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public void addSearchRequestNotifyCallback(ClickListener lsnr) {
        listener = lsnr;

    }

    @Override
    public void onClick(View v) {
        presenter.onClick(v);
    }

}
