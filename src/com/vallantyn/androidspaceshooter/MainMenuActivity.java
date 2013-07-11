package com.vallantyn.androidspaceshooter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Julien on 09/07/13.
 */
public class MainMenuActivity
		extends Activity
{

	@Override
	protected void onCreate (Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_menu_layout);

		Button button = (Button) findViewById(R.id.button);

		button.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick (View v)
			{
				Intent i = new Intent(MainMenuActivity.this, MainActivity.class);
				startActivity(i);
				finish();
			}
		});
	}
}
