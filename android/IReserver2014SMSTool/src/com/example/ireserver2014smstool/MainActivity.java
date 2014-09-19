package com.example.ireserver2014smstool;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	EditText phoneNumberEditText;
	Button retryButton;
	
	PreferenceHelper mHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mHelper = PreferenceHelper.getInstance(this);
		
		// Get edit text
		phoneNumberEditText = (EditText) findViewById(R.id.phoneNumberEditText);
		// Get retry button
		retryButton = (Button) findViewById(R.id.retryButton);

		retryButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				sendSMS();
			}
		});
	}

	private void sendSMS() {
		// Get request number
		String message = mHelper.getAppleRequestNumber();
		if(message == null || message.isEmpty())
		{
			Toast.makeText(this, "Request Number is Empty", Toast.LENGTH_SHORT).show();
			return;
		}
		
		// TODO:¡¡Check and work only for valid phone number
		// Get input phone number
		String phoneNumber = phoneNumberEditText.getText().toString();
		// Skip invalid phone number
		if (phoneNumber == null || phoneNumber.isEmpty()) {
			Toast.makeText(this, "Your Phone Number is Empty", Toast.LENGTH_SHORT).show();
			return;
		}

		// TODO:64500366
		String applePhoneNumber = mHelper.getApplePhoneNumber();
		if(applePhoneNumber == null || applePhoneNumber.isEmpty())
		{
			Toast.makeText(this, "Apple Phone Number is Empty", Toast.LENGTH_SHORT).show();
			return;
		}
		
		SmsManager manager = SmsManager.getDefault();
		manager.sendTextMessage(applePhoneNumber, null, message, null, null);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
