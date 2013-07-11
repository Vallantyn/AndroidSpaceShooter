package com.vallantyn.androidspaceshooter.assets.gameobjects;

import android.graphics.Canvas;

import com.vallantyn.androidspaceshooter.assets.behaviours.StarBehaviour;
import com.vallantyn.androidspaceshooter.assets.mesh.StarMesh;

import engine.GameObject;
import engine.IRenderable;
import engine.pool.IPoolable;

/**
 * Created by Julien on 08/07/13.
 */
public class Star
		extends GameObject
{
	StarMesh mesh = new StarMesh();

	public Star()
	{
		setOnRenderListener(new OnRenderListener()
		{
			@Override
			public void OnRender (Canvas c)
			{
				mesh.draw(transform, c);
			}
		});

		addBehaviour(new StarBehaviour());
	}
}
