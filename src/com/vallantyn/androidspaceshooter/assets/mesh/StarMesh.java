package com.vallantyn.androidspaceshooter.assets.mesh;

import android.graphics.Color;
import android.graphics.Point;

import engine.Mesh;

/**
 * Created by Julien on 08/07/13.
 */
public class StarMesh
		extends Mesh
{
	public StarMesh ()
	{
		super();

		color = Color.argb(0x66, 0xFF, 0xFF, 0xFF);

		add(new Point(-1, -1));
		add(new Point(-1, 1));
		add(new Point(1, 1));
		add(new Point(1, -1));

		end();
	}
}
