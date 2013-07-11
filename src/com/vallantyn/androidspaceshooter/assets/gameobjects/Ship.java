package com.vallantyn.androidspaceshooter.assets.gameobjects;

import android.graphics.Canvas;
import android.graphics.Rect;

import com.vallantyn.androidspaceshooter.assets.behaviours.SecondTouch;
import com.vallantyn.androidspaceshooter.assets.behaviours.ShipBehaviour;
import com.vallantyn.androidspaceshooter.assets.behaviours.TouchControl;

import engine.BitmapManager;
import engine.CircleCollider;
import engine.GameObject;
import engine.IRenderable;
import engine.Sprite;

public class Ship
		extends GameObject
{
	Sprite   sprite;

	public Ship ()
	{
		super();

		TAG = "PLAYER";

		sprite = new Sprite(
				BitmapManager.getInstance().loadBitmap("bitmap/ship.png")
				, new Rect(49, 140, 49+21, 140+27), 3);

		collider = new CircleCollider(this);

		collider.setRadius((21+27)/2);

		setOnRenderListener(new OnRenderListener()
		{
			@Override
			public void OnRender (Canvas c)
			{
				sprite.draw(transform, c);
			}
		});

		addBehaviour(new TouchControl());
		addBehaviour(new SecondTouch());
		addBehaviour(new ShipBehaviour());
	}
}
