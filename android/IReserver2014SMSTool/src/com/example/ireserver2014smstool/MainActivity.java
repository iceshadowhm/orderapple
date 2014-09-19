package com.example.ireserver2014smstool;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
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
		
		startMiniHttpService();
		
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		stopMiniHttpService();
	}

	private void sendSMS() {
		// Get request number
		String message = mHelper.getAppleRequestNumber();
		if (message == null || message.isEmpty()) {
			Toast.makeText(this, "Request Number is Empty", Toast.LENGTH_SHORT)
					.show();
			return;
		}

		// TODO:¡¡Check and work only for valid phone number
		// Get input phone number
		String phoneNumber = phoneNumberEditText.getText().toString();
		// Skip invalid phone number
		if (phoneNumber == null || phoneNumber.isEmpty()) {
			Toast.makeText(this, "Your Phone Number is Empty",
					Toast.LENGTH_SHORT).show();
			return;
		}

		// TODO:64500366
		String applePhoneNumber = mHelper.getApplePhoneNumber();
		if (applePhoneNumber == null || applePhoneNumber.isEmpty()) {
			Toast.makeText(this, "Apple Phone Number is Empty",
					Toast.LENGTH_SHORT).show();
			return;
		}

		SmsManager manager = SmsManager.getDefault();
		manager.sendTextMessage(applePhoneNumber, null, message, null, null);
	}
	
	public void startMiniHttpService()
	{
		Intent startIntent = new Intent(this, MiniHttpService.class);  
        startService(startIntent);  
	}
	
	public void stopMiniHttpService()
	{
        Intent stopIntent = new Intent(this, MiniHttpService.class);  
        stopService(stopIntent);  
	}

}
