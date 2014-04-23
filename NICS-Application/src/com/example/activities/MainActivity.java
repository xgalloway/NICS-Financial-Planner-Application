package com.example.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import com.example.model.Application;
import com.example.nics_application.R;
import com.example.presenter.WelcomeViewPresenter;
import com.example.support.ClickListener;
import com.example.support.SavingTips;
import com.example.view.WelcomeView;

/**
 * 
 * @author Team 16
 *
 * Handles main screen
 */
public class MainActivity extends Activity implements WelcomeView, OnClickListener {

	Button logInButton;
	Button registerButton;
	ImageView logo;
	private WelcomeViewPresenter presenter;
	private ClickListener listener;
	
	/**
	 * Displays the log in and register buttons
	 * along with the logo on the main screen
	 * 
	 * @param savedInstanceState saves state of the application
	 */
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Application app = Application.INSTANCE;
		presenter = new WelcomeViewPresenter(this);
		
		logInButton = (Button) findViewById(R.id.loginButton);
		logInButton.setOnClickListener(this);
		registerButton = (Button) findViewById(R.id.goButton);
		registerButton.setOnClickListener(this);
		logo = (ImageView) findViewById(R.id.imageView1);
		displayAlertDialog();
		MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.song);
		mediaPlayer.start();
	}
	
	/**
	 * Creates menu with appropriate options
	 * 
	 * @param menu Container for menu items
	 * @return state
	 */
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/**
	 * If log in button is pushed, the user will be forwarded
	 * to the log in screen
	 */
	
	@Override
	public void startLogin() {
		Intent i = new Intent(this, LogInActivity.class);
		startActivity(i);
		finish();

	}

    /**
     * If registration button is pushed, the user will be forwarded
     * to the registration screen
     */	
	
	@Override
	public void startRegistration() {
		Intent i = new Intent(this, RegistrationActivity.class);
		startActivity(i);
		finish();

	}

	/**
     * Sets listener for the application
     * 
     * @param lsnr Corresponds to the listener
     */
	
	@Override
	public void addSearchRequestNotifyCallback(final ClickListener lsnr) {
		listener = lsnr;
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
	
	public void displayAlertDialog() {
	    SavingTips st = new SavingTips();
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Savings Tip Of The Day!");
        alertDialog.setMessage(st.getTip());
        alertDialog.setNeutralButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alertDialog.show();
        
    }
}
