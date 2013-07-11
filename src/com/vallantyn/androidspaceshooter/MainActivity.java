package com.vallantyn.androidspaceshooter;

import java.io.File;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;

import engine.BitmapManager;
import engine.MusicPlayer;
import engine.SoundFXManager;

public class MainActivity
		extends Activity
{

	CustomSurfaceView csv;
	// GameTime t;

	AudioManager am;

	@Override
	protected void onCreate (Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		setVolumeControlStream(AudioManager.STREAM_MUSIC);

		AssetManager assetManager = getAssets();
//
//		MusicPlayer.setContext(this);
//		MusicPlayer.setMusic(R.raw.background);

		SoundFXManager sfx = SoundFXManager.getInstance();
//		MusicPlayer mp = MusicPlayer.getInstance();
		BitmapManager bm = BitmapManager.getInstance();

		sfx.setAssetManager(assetManager);
//		mp.setAssetManager(assetManager);
		bm.setAssetManager(assetManager);

		sfx.preload();

		csv = (CustomSurfaceView) findViewById(R.id.Canvas);
	}

	@Override
	protected void onResume ()
	{
		super.onResume();
		csv.resume();

//		MusicPlayer.getInstance().play();
	}

	@Override
	protected void onPause ()
	{
		super.onPause();
		csv.pause();

//		MusicPlayer.getInstance().pause();
	}

	@Override
	public void onBackPressed ()
	{
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Are you sure you want to exit?")
				.setCancelable(false)
				.setPositiveButton("Yes", new DialogInterface.OnClickListener()
				{
					public void onClick (DialogInterface dialog, int id)
					{
						finish();

						System.runFinalizersOnExit(true);
						System.exit(0);
					}
				})
				.setNegativeButton("No", new DialogInterface.OnClickListener()
				{
					public void onClick (DialogInterface dialog, int id)
					{
						dialog.cancel();
					}
				});
		AlertDialog alert = builder.create();
		alert.show();
	}

	@Override
	public boolean onCreateOptionsMenu (Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
