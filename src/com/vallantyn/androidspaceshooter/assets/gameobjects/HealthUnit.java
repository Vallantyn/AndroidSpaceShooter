package com.vallantyn.androidspaceshooter.assets.gameobjects;

import android.graphics.Canvas;
import android.graphics.Rect;

import engine.BitmapManager;
import engine.GameObject;
import engine.Sprite;

/**
 * Created by Julien on 09/07/13.
 */
public class HealthUnit
		extends GameObject
{
	public Sprite hb;

	public HealthUnit ()
	{
		hb = new Sprite(BitmapManager.getInstance().loadBitmap("bitmap/projectiles.png"), new Rect(146, 182, 153, 196));

		setOnRenderListener(new OnRenderListener()
		{
			@Override
			public void OnRender (Canvas c)
			{
				transform.Rotate(90);
				hb.draw(transform, c);
				transform.Rotate(-90);
			}
		});
	}
}
