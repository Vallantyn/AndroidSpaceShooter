package com.vallantyn.androidspaceshooter.assets.behaviours;

import android.graphics.PointF;
import android.text.format.Time;
import android.util.Log;

import java.util.Calendar;

import engine.AbstractBehaviour;
import engine.IReceiver;
import engine.Transform;
import engine.Vector2;

/**
 * Created by Julien on 05/07/13.
 */
public class MissileBehaviour
		extends AbstractBehaviour
		implements IReceiver
{
	public  final float     speed           = 500.0f;
	public  Vector2   direction       = new Vector2();
	private Transform aim             = new Transform();
	private final float     angularVelocity = 5.0f;
	private boolean   fixed           = false;
	private final long     lifeTime        = 2000;
	private long bornDate = 0;

	public MissileBehaviour() {
		super();
	}



	@Override
	public void receiveMessage (String name, Object param)
	{
		if (name == "setDirection")
		{
			PointF dir = (PointF) param;
			direction.x = dir.x;
			direction.y = dir.y;
		}
	}

	@Override
	public void start ()
	{
//		direction = new Vector2();
		fixed = false;

		Time now = new Time();
		now.setToNow();
		bornDate = now.toMillis(true);
	}

	@Override
	public void update (float dt)
	{
		Time now = new Time();
		now.setToNow();

		if (now.toMillis(true)-bornDate > lifeTime) {
//			gameObject.fireDestroyListener();
			Destroy(gameObject);
		}

		transform.Translate(transform.forward().mul(speed * dt));

		if (!fixed && direction.dist(transform.position) > 48)
		{
			aim.position = transform.position;
			aim.lookAt(new Vector2(direction.x, direction.y));

			transform.Rotate(Lerp(transform.rotation, aim.rotation, dt * angularVelocity));
		}
		else fixed = true;


	}

	@Override
	public void lateUpdate (float dt)
	{

	}

	private double Lerp (double from, double to, float amount)
	{
		return /* from + */ (to - from) * amount;
	}
}
