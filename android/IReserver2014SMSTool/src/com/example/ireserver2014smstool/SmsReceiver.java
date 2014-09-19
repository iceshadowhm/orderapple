package com.example.ireserver2014smstool;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

public class SmsReceiver extends BroadcastReceiver {
	
	public final static String TAG =  SmsReceiver.class.getSimpleName();
			
	@Override
	public void onReceive(Context context, Intent intent) {
		// Method 1
		Bundle bundle = intent.getExtras();
		SmsMessage[] msgs = null;
		String phone;
		String message;

		if (bundle != null) {
			Object[] pdus = (Object[]) bundle.get("pdus");
			msgs = new SmsMessage[pdus.length];
			for (int i = 0; i < msgs.length; i++) {
				msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
				phone = msgs[i].getOriginatingAddress();
				message = msgs[i].getMessageBody();
				
				Log.d(TAG, "phone:"+phone);
				Log.d(TAG, "message:"+message);
				
				// TODO: Check if this sms is from apple number
				
				//Example
//				String reserveString = "(Your reservation code is) UPN42Z1C";
				String reserveString = message;
				String reserveCode = message.substring(reserveString.indexOf(')')+1).trim();

				//TODO: send reserve code and reserve string back to server
				
			}
		}

	}

}
