package com.example.activities;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.model.Application;
import com.example.model.Transaction;
import com.example.model.UserModel;
import com.example.nics_application.R;
import com.example.presenter.GraphViewPresenter;
import com.example.support.ClickListener;
import com.example.view.ChartView;
import com.jjoe64.graphview.BarGraphView;
import com.jjoe64.graphview.GraphView.GraphViewData;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.LineGraphView;

public class GraphViewActivity extends Activity implements
        OnClickListener, ChartView {

    private GraphViewPresenter presenter;
    private Button backButton;
    private ClickListener listener;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_view);
        Application app = Application.INSTANCE;
        presenter = new GraphViewPresenter(this, app.getModel());
        initiateButtons();
        try {
            createGraph();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    
    public void initiateButtons() {
        backButton = (Button)findViewById(R.id.backButton);
        backButton.setOnClickListener(this);
        
    }
    
    @Override
    public void addSearchRequestNotifyCallback(ClickListener lsnr) {
        listener = lsnr;
    }

    @Override
    public void onClick(View v) {
        presenter.onClick(v);
    }
    
    public void goBack() {
        Intent i = new Intent(this, AccountListViewActivity.class);
        startActivity(i);
        finish();
    }
    
    public void createGraph() throws ParseException {
        Application app = Application.INSTANCE;
        UserModel model = app.getModel();
        
        List<Transaction> withdrawals = model.getWithdrawals();
        List<Transaction> deposits = model.getDeposits();
        
        Date date = new Date(System.currentTimeMillis());
        int currentMonth = date.getMonth();
        
        String[] months = new String[3];
        months[0] = new DateFormatSymbols().getMonths()[date.getMonth() - 2].substring(0, 3);
        months[1] = new DateFormatSymbols().getMonths()[date.getMonth() - 1].substring(0, 3);
        months[2] = new DateFormatSymbols().getMonths()[date.getMonth()].substring(0, 3);
        
        Double[] values = new Double[3];
        for (int k = 0; k < values.length; ++k) {
            values[k] = (double) 0;
        }
        
        for (int i = 0; i < withdrawals.size(); ++i) {
            Transaction t = withdrawals.get(i);
            if (t.getDate().getMonth() == currentMonth) {
                values[2] = values[2] - t.getAmount();
            } else if (t.getDate().getMonth() == currentMonth - 1) {
                values[1] = values[1] - t.getAmount();
            } else if (t.getDate().getMonth() == currentMonth - 2) {
                values[0] = values[0] - t.getAmount();
            }
        }
        
        for (int i = 0; i < deposits.size(); ++i) {
            Transaction t = deposits.get(i);
            if (t.getDate().getMonth() == currentMonth) {
                values[2] = values[2] + t.getAmount();
            } else if (t.getDate().getMonth() == currentMonth - 1) {
                values[1] = values[1] + t.getAmount();
            } else if (t.getDate().getMonth() == currentMonth - 2) {
                values[0] = values[0] + t.getAmount();
            }
        }
        
        GraphViewSeries monthlySpending = new GraphViewSeries(new GraphViewData[] {
                new GraphViewData(currentMonth - 2, values[0])
                , new GraphViewData(currentMonth - 1, values[1])
                , new GraphViewData(currentMonth, values[2])
            });
             
            BarGraphView graphView = new BarGraphView(
                this
                , "Monthly Balance" // heading
            );
            graphView.addSeries(monthlySpending); 
            graphView.setHorizontalLabels(months);
             
            LinearLayout layout = (LinearLayout) findViewById(R.id.graph_layout);
            layout.addView(graphView);
    }

}
