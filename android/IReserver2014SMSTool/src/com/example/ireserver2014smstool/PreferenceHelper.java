package com.example.ireserver2014smstool;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class PreferenceHelper {
	public static String KEY_APPLE_PHONE_NUMBER = "apple_phone_nubmer";
	public static String KEY_APPLE_REQUEST_NUMBER = "apple_request_number";
	public static String KEY_APPLE_RESERVE_CODE = "apple_reserve_code";
	private Context mContext;
	private SharedPreferences mPreferences;
	private static PreferenceHelper mInstance;
	
	public static PreferenceHelper getInstance(Context context)
	{
		if(mInstance == null)
		{
			mInstance = new PreferenceHelper();
			mInstance.mContext = context;
			mInstance.mPreferences =   
		            context.getSharedPreferences("iReserverPreference",  
		                    Context.MODE_WORLD_READABLE);
		}
		return mInstance;		
	}
	
	public String getApplePhoneNumber()
	{
		return mPreferences.getString(KEY_APPLE_PHONE_NUMBER, "");
	}
	
	public void setApplePhoneNumber(String applePhoneNumber)
	{
		Editor editor = mPreferences.edit();
		editor.putString(KEY_APPLE_PHONE_NUMBER, applePhoneNumber);
	}
	
	public String getAppleRequestNumber()
	{
		return mPreferences.getString(KEY_APPLE_REQUEST_NUMBER, "");
	}
	
	public void setRequestPhoneNumber(String appleRequestNumber)
	{
		Editor editor = mPreferences.edit();
		editor.putString(KEY_APPLE_REQUEST_NUMBER, appleRequestNumber);
	}
	
	public String getAppleReserverCode()
	{
		return mPreferences.getString(KEY_APPLE_RESERVE_CODE, "");
	}
	
	public void setAppleReserveCode(String appleReserveCode)
	{
		Editor editor = mPreferences.edit();
		editor.putString(KEY_APPLE_RESERVE_CODE, appleReserveCode);
	}
	
	
	
}
