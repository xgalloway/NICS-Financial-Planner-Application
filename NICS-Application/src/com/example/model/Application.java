package com.example.model;

import android.content.Context;

/**
 * 
 * @author Team 16
 * 
 * Creates application
 *
 */

public class Application {
	public static Application INSTANCE = new Application();
	private UserModel model;
	
	public Application() {
		
	}
	
	/**
	 * Takes database information and makes it the model
	 * 
	 * @param context Database information
	 */
	
	public void setModel(Context context) {
		this.model = new UserModel(context);
		model.initialize();
	}
	
	/**
	 * Gets user model
	 * 
	 * @return Model
	 */
	public UserModel getModel() {
		return model;
	}
}
