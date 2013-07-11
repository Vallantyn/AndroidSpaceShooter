package com.vallantyn.androidspaceshooter.assets.behaviours;

import android.graphics.PointF;
import android.util.Log;

import engine.AbstractBehaviour;
import engine.Input;
import engine.Screen;
import engine.SoundFXManager;
import engine.Transform;
import engine.Vector2;

public class TouchControl
		extends AbstractBehaviour
{

	private float velocity        = 0.0f;
	private float acceleration    = 2f;
	private float deceleration    = 4f;
	private float maxVelocity     = 400.0f;
	private float angularVelocity = 5.0f;

	private boolean ftPress = false;

	private Transform aim = new Transform();

	private PointF dest = new PointF(Screen.getWidth()/2, 0);

	@Override
	public void start ()
	{

	}

	@Override
	public void update (float dt)
	{
		if (Input.firstTouch)
		{
			dest = Input.touch;
			accelerate();
			if (!ftPress)
			{
				SoundFXManager.SFX.ENGINE.play();
				ftPress = true;
			}
		}
		else
		{
			deccelerate();
			ftPress = false;
		}

		MoveTo(dest, dt);
	}

	@Override
	public void lateUpdate (float dt)
	{

	}

	public void accelerate ()
	{
		velocity += acceleration;
		if (velocity >= maxVelocity)
			velocity = maxVelocity;
	}

	public void deccelerate ()
	{
		velocity -= deceleration;
		if (velocity <= 0) {
			velocity = 0;
		}
	}

	private void MoveForward (float dt)
	{
		transform.Translate(transform.forward().mul(dt * velocity));
	}

	private double Lerp (double from, double to, float amount)
	{
		return /* from + */ (to - from) * amount;
	}

	public void MoveTo (PointF to, float dt)
	{
		MoveForward(dt);

		aim.position = transform.position;
		aim.lookAt(new Vector2(to.x, to.y));

		transform.Rotate(Lerp(transform.rotation, aim.rotation, dt * angularVelocity));
	}
}
