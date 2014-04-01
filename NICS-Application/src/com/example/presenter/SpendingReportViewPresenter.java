package com.example.presenter;

import android.view.View;
import com.example.model.Model;
import com.example.nics_application.R;
import com.example.support.ClickListener;
import com.example.view.SpendingReportView;

/**
 * 
 * @author Team 16
 *
 */

public class SpendingReportViewPresenter implements ClickListener{
	
	SpendingReportView view;
	private final Model model;
	
	/**
	 * Instantiates variables
	 * @param view
	 * @param model
	 */
	
	public SpendingReportViewPresenter(SpendingReportView view, Model model){
		this.view = view;
		this.model = model;
	}
	
	/**
	 * Allows user to navigate backwards through application
	 * @param v
	 */
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.back:
			view.goBack();
			break;
		}	
		
	}
	
}