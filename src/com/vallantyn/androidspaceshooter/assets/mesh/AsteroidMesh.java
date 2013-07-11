package com.vallantyn.androidspaceshooter.assets.mesh;

import android.graphics.Color;
import android.graphics.Point;

import engine.Mesh;

/**
 * Created by Julien on 09/07/13.
 */
public class AsteroidMesh extends Mesh
{
	public AsteroidMesh()
	{
		super();

		color = Color.DKGRAY;

		add(new Point(-5, -5));
		add(new Point(-5, 5));
		add(new Point(5, 5));
		add(new Point(5, -5));

		end();
	}
}
