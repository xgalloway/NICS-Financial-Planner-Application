 package com.example.activities;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.model.Application;
import com.example.model.User;
import com.example.model.UserAccount;
import com.example.model.UserModel;
import com.example.nics_application.R;
import com.example.presenter.AccountListViewPresenter;
import com.example.presenter.AccountViewPresenter;
import com.example.support.ClickListener;
import com.example.view.AccountListView;
import com.example.view.AccountView;

/**
 * 
 * @author Team 16
 *
 * Deals with the ability for a user to see their account activity in a list
 */
public class AccountListViewActivity extends Activity implements
		AccountListView, OnClickListener {

	private Button addButton, reportButton;
	private AccountListViewPresenter presenter;
	private ClickListener listener;
	private ListView list;

	/**
	 * Creates the account list that will be viewed
	 */
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_account_list_view);
		initiateViews();
		populateListView();
		Application app = Application.INSTANCE;
		presenter = new AccountListViewPresenter(this, app.getModel());

	}

	/**
	 * When the button is pressed on, the user can view it
	 */
	
	@Override
	public void onClick(View v) {
		presenter.onClick(v);
	}

	/**
	 * Adds a back button, a report button and a list option for
	 * the account list
	 */
	
	public void initiateViews() {
		addButton = (Button) findViewById(R.id.backButton);
		addButton.setOnClickListener(this);

		reportButton = (Button) findViewById(R.id.reportButton);
		reportButton.setOnClickListener(this);

		list = (ListView) findViewById(R.id.listViewMain);
		list.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> lv, View v, int position,
					long id) {
				presenter.onItemClick(v, position);

			}

		});
	}

	/**
	 * Allows the user to see the options previously created
	 */
	
	public boolean onCreateOptionsMenu(Menu menu) {
        
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.menu_options, menu);
	    return true;
	}

	
	/**
	 * 
	 */
	
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	        case R.id.logout:
	            Application app = Application.INSTANCE;
	            UserModel model = app.getModel();
	            model.setCurrentUser(null);
	            Intent i = new Intent(this, MainActivity.class);
	            startActivity(i);
	            finish();
	            return true;
	    }
        return super.onOptionsItemSelected(item);
	}
	
	/**
	 * 
	 */
	
	@Override
	public void addSearchRequestNotifyCallback(ClickListener lsr) {
		listener = lsr;
	}

	/**
	 * Sets up a user account
	 */
	
	@Override
	public void createAccount() {
		Intent i = new Intent(this, AccountSetupActivity.class);
		startActivity(i);
		finish();
	}
	
	/**
	 * 
	 */

	private void populateListView() {
		UserModel model = Application.INSTANCE.getModel();
		List<UserAccount> accounts = (List<UserAccount>) model
				.getUserAccounts(model.getCurrent().getUsername());
		String[] items = new String[accounts.size()];

		for (int i = 0; i < accounts.size(); i++) {
			items[i] = accounts.get(i).toString();
		}
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				R.layout.item_view, items);
		list.setAdapter(adapter);
	}
	
	/**
	 * Allows the user to look at their account
	 */

	@Override
	public void viewAccount() {
		Intent i = new Intent(this, AccountViewActivity.class);
		startActivity(i);
		finish();
	}
	
	/**
	 * Allows the user to look at their account report
	 */

	@Override
	public void viewReport() {
		Intent i = new Intent(this, ReportViewActivity.class);
		startActivity(i);
		finish();
	}

}