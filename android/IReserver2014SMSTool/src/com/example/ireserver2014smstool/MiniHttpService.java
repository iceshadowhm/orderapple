package com.example.ireserver2014smstool;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import org.apache.http.conn.util.InetAddressUtils;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.example.nanohttppd.NanoHTTPD;

public class MiniHttpService extends Service {
	public static final String TAG = MiniHttpService.class.getSimpleName();
	
	NanoHTTPD nanoHTTPD;
	int port = 8080;
	File wwwroot;
	String hostaddres;

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		wwwroot = new File("http://lsc20051426.pagekite.me");
		startServer();
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		
		return super.onStartCommand(intent, flags, startId);
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		stopServer();
	}
	
	public void startServer() {

		try {
			nanoHTTPD = new NanoHTTPD(port, wwwroot);
		} catch (IOException ioe) {

		}

	}

	public void stopServer() {
		if (nanoHTTPD != null)
		{
			Log.d(TAG,"ip_address:"+getLocalIpAddress());
			nanoHTTPD.stop();
		}
	}

	public String getLocalIpAddress() {
		try {
			// Retrieve network ports
			Enumeration<NetworkInterface> infos = NetworkInterface
					.getNetworkInterfaces();
			while (infos.hasMoreElements()) {
				// Get network port
				NetworkInterface niFace = infos.nextElement();
				Enumeration<InetAddress> enumIpAddr = niFace.getInetAddresses();
				while (enumIpAddr.hasMoreElements()) {
					InetAddress mInetAddress = enumIpAddr.nextElement();
					// If IP is not 127.0.0.1 return IP address
					if (!mInetAddress.isLoopbackAddress()
							&& InetAddressUtils.isIPv4Address(mInetAddress
									.getHostAddress())) {
						return mInetAddress.getHostAddress().toString();
					}
				}
			}
		} catch (SocketException e) {

		}
		return null;
	}


}
