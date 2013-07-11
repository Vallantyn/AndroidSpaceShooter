package com.vallantyn.androidspaceshooter.assets.mesh;

import android.graphics.Color;
import android.graphics.Point;

import engine.Mesh;

/**
 * Created by Julien on 05/07/13.
 */
public class LaserMesh extends Mesh
{
	public LaserMesh ()
	{
		super();

		color = Color.RED;

		add(new Point(-10, -1));
		add(new Point(-10, 1));
		add(new Point(10, 1));
		add(new Point(10, -1));

		end();
	}
}
