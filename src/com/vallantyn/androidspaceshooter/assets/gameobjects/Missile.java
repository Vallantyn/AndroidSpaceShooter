package com.vallantyn.androidspaceshooter.assets.gameobjects;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.vallantyn.androidspaceshooter.assets.behaviours.MissileBehaviour;

import engine.BitmapManager;
import engine.CircleCollider;
import engine.GameObject;
import engine.IRenderable;
import engine.Sprite;
import engine.Vector2;
import engine.pool.IPoolable;

/**
 * Created by Julien on 05/07/13.
 */
public class Missile
		extends GameObject
	implements IPoolable
{
	Sprite sprite;

	public Missile()
	{
		super();

		TAG = "PLAYER_PROJECTILE";

		Bitmap bmp = BitmapManager.getInstance().loadBitmap("bitmap/spritesheet.png");
		sprite = new Sprite(bmp, new Rect(102, 1, 113, 26), 2);

		collider = new CircleCollider(this);
		collider.setRadius(22);

		setOnRenderListener(new OnRenderListener()
		{
			@Override
			public void OnRender (Canvas c)
			{
				sprite.draw(transform, c);
			}
		});

		addBehaviour(new MissileBehaviour());
	}

	@Override
	public void init ()
	{

	}

	@Override
	public void reset ()
	{
		transform.position = new Vector2();
		transform.rotation = 0;

		transform.getMatrix().reset();

		sendMessage("reset", null);
	}
}
