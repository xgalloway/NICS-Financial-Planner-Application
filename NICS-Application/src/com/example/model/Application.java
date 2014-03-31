package com.example.model;

import android.content.Context;

/**
 * 
 * @author Team 16
 *
 */

public class Application {
	public static Application INSTANCE = new Application();
	private UserModel model;
	
	public Application() {
		
	}
	
	public void setModel(Context context) {
		this.model = new UserModel(context);
		model.initialize();
	}
	public UserModel getModel() {
		return model;
	}
}
