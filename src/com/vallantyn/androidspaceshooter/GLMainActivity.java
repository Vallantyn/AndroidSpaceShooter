package com.vallantyn.androidspaceshooter;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

import com.vallantyn.androidspaceshooter.opengles.CustomRenderer;

/**
 * Created by Julien on 10/07/13.
 */
public class GLMainActivity extends Activity
{
	private GLSurfaceView mGLSurfaceView;

	@Override
	protected void onCreate (Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		mGLSurfaceView = new GLSurfaceView(this);

		final ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
		final ConfigurationInfo configurationInfo = activityManager.getDeviceConfigurationInfo();
		final boolean supportEs2 = configurationInfo.reqGlEsVersion >= 0x20000;

		if (supportEs2)
		{
			mGLSurfaceView.setEGLContextClientVersion(2);
			mGLSurfaceView.setRenderer(new CustomRenderer(this));
		}
		else return;

		setContentView(mGLSurfaceView);
	}

	@Override
	protected void onResume ()
	{
		super.onResume();
		mGLSurfaceView.onResume();
	}

	@Override
	protected void onPause ()
	{
		super.onPause();
		mGLSurfaceView.onPause();
	}
}
