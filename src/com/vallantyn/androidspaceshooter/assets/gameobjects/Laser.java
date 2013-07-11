package com.vallantyn.androidspaceshooter.assets.gameobjects;

import android.graphics.Canvas;
import android.graphics.Rect;

import com.vallantyn.androidspaceshooter.assets.behaviours.LaserBehaviour;
import com.vallantyn.androidspaceshooter.assets.mesh.LaserMesh;

import engine.BitmapManager;
import engine.CircleCollider;
import engine.GameObject;
import engine.IRenderable;
import engine.Sprite;

/**
 * Created by Julien on 05/07/13.
 */
public class Laser
		extends GameObject
{
	Sprite sprite;

	public Laser ()
	{
		super();

		TAG = "PLAYER_PROJECTILE";

		sprite = new Sprite(BitmapManager.getInstance().loadBitmap("bitmap/projectiles.png"), new Rect(4, 42, 8, 53), 2);

		collider = new CircleCollider(this);
		collider.setRadius(6);

		setOnRenderListener(new OnRenderListener()
		{
			@Override
			public void OnRender (Canvas c)
			{
				sprite.draw(transform, c);
			}
		});

		addBehaviour(new LaserBehaviour());
	}
}
