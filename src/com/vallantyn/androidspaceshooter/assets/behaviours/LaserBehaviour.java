package com.vallantyn.androidspaceshooter.assets.behaviours;

import android.graphics.PointF;
import android.text.format.Time;

import engine.AbstractBehaviour;
import engine.Vector2;

/**
 * Created by Julien on 05/07/13.
 */
public class LaserBehaviour
		extends AbstractBehaviour
{
	public  float speed    = 500.0f;
	private long  lifeTime = 2000;
	private long  bornDate = 0;

	public LaserBehaviour ()
	{
		super();
		Time now = new Time();
		now.setToNow();
		bornDate = now.toMillis(true);
	}

	@Override
	public void start ()
	{
	}

	@Override
	public void update (float dt)
	{
		Time now = new Time();
		now.setToNow();

		if (now.toMillis(true) - bornDate > lifeTime)
		{
			Destroy(gameObject);
		}

		transform.Translate(transform.forward().mul(speed * dt));
	}

	@Override
	public void lateUpdate (float dt)
	{

	}
}
