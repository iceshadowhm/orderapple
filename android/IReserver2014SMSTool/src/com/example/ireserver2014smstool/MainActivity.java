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

public class MainActivity extends Activity {

	EditText phoneNumberEditText;
	Button retryButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// Get edit text
		phoneNumberEditText = (EditText) findViewById(R.id.phoneNumberEditText);
		// Get retry button
		retryButton = (Button) findViewById(R.id.retryButton);

		retryButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO: if random reserver number exists, retry
				sendSMS("TEST-XXXXXX01");
			}
		});
	}

	private void sendSMS(String message) {
		// Get input phone number
		String phoneNumber = phoneNumberEditText.getText().toString();
		// Skip invalid phone number
		if (phoneNumber == null || phoneNumber.isEmpty()) {
			return;
		}

		// TODO:64500366
		String applePhoneNumber = "61780317";
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
