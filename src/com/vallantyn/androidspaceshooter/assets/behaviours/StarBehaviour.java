package com.vallantyn.androidspaceshooter.assets.behaviours;

import engine.AbstractBehaviour;
import engine.Screen;

/**
 * Created by Julien on 08/07/13.
 */
public class StarBehaviour
		extends AbstractBehaviour
{
	@Override
	public void start ()
	{

	}

	@Override
	public void update (float dt)
	{
		transform.Translate(-1, 0);

		if (transform.position.x < 0)
			transform.Translate(Screen.getWidth(), (int) (Math.random()*Screen.getHeight()) - transform.position.y);
	}

	@Override
	public void lateUpdate (float dt)
	{

	}
}
