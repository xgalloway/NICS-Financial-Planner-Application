package com.example.nics_application;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.*;
import android.view.View.*;

public class MainActivity extends Activity implements OnClickListener{

	Button button;
	EditText user;
	EditText pass;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		addListenerOnButton();
	}
	
	public void addListenerOnButton(){
		button = (Button) findViewById(R.id.button1);
		//String userString = user.getText().toString();
		//String passString = pass.getText().toString();
		
		button.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v){				
		Intent i = new Intent(this,LogInActivity.class);
		startActivity(i);
		
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
