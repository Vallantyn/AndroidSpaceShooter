package com.vallantyn.androidspaceshooter.assets.gameobjects;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import com.vallantyn.androidspaceshooter.assets.behaviours.AsteroidBehaviour;

import engine.BitmapManager;
import engine.CircleCollider;
import engine.GameObject;
import engine.Sprite;
import engine.Vector2;
import engine.pool.IPoolable;

/**
 * Created by Julien on 09/07/13.
 */
public class Asteroid
		extends GameObject
		implements IPoolable
{
	public static final int LIMIT  = 30;
	public static       int number = 0;
	Sprite sprite;
	Rect[] spriteData = new Rect[]
			{
					new Rect(2, 4, 44, 50)
					,
					new Rect(50, 1, 72, 21)
					,
					new Rect(48, 29, 69, 52)
					,
					new Rect(79, 4, 119, 54)
					,
					new Rect(122, 4, 122 + 69, 79)
			};

	public Asteroid (Score score)
	{
		super();

		number++;

		TAG = "ENNEMI";

		/*
		* 2 4 42 46
		* 50 1 22 20
		* 48 29 21 23
		* 79 4 40 50
		* */

		int picked = (int) (Math.random() * spriteData.length);
		int halfW = spriteData[picked].width() / 2;
		int halfH = spriteData[picked].height() / 2;

		sprite = new Sprite(BitmapManager.getInstance().loadBitmap("bitmap/asteroids.png"), spriteData[picked]);
		collider = new CircleCollider(this);

		collider.setRadius((halfH + halfW) / 2);
		collider.setBounds(new Rect(-halfW, -halfH, halfW, halfH));

		addBehaviour(new AsteroidBehaviour(score));

		setOnRenderListener(new OnRenderListener()
		{
			int alpha = 0;

			@Override
			public void OnRender (Canvas c)
			{
				Paint p = new Paint();
				p.setAlpha(alpha);

				if (alpha < 0xFF) alpha += 0x3;

				sprite.draw(transform, c, p);
			}
		});

		setOnDestroyListener(new OnDestroyListener()
		{
			@Override
			public void OnDestroy ()
			{
				number--;
			}
		});
	}

	@Override
	public void init ()
	{
		int picked = (int) (Math.random() * spriteData.length);
		int halfW = spriteData[picked].width() / 2;
		int halfH = spriteData[picked].height() / 2;

		sprite = new Sprite(BitmapManager.getInstance().loadBitmap("bitmap/asteroids.png"), spriteData[picked]);
		collider = new CircleCollider(this);

		collider.setRadius((halfH + halfW) / 2);
		collider.setBounds(new Rect(-halfW, -halfH, halfW, halfH));
	}

	@Override
	public void reset ()
	{
		transform.position = new Vector2();
		transform.rotation = 0;

		transform.getMatrix().reset();
	}
}
