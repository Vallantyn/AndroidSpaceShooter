package com.vallantyn.androidspaceshooter.assets.behaviours;

import android.text.format.Time;

import com.vallantyn.androidspaceshooter.assets.gameobjects.Asteroid;
import com.vallantyn.androidspaceshooter.assets.gameobjects.Score;
import com.vallantyn.androidspaceshooter.assets.gameobjects.Ship;
import com.vallantyn.androidspaceshooter.assets.gameobjects.Star;

import engine.AbstractBehaviour;
import engine.Screen;
import engine.Vector2;

/**
 * Created by Julien on 08/07/13.
 */
public class StarFieldBehaviour
		extends AbstractBehaviour
{
	long lastTime = 0;
	long cooldown = 5000;
	Score score;

	@Override
	public void start ()
	{
		int w = Screen.getWidth();
		int h = Screen.getHeight();

		score = new Score();

		for (int i = 0; i < 100; i++)
		{
			Instanciate(new Star()
					, new Vector2((float) Math.random() * w, (float) Math.random() * h)
					, 0.0f);
		}

		Instanciate(new Asteroid(score)
				, new Vector2((float) Math.random() * w, (float) Math.random() * h)
				, 0.0f);

		Instanciate(new Ship(), new Vector2(Screen.getWidth() / 2, Screen.getHeight() / 2), 0);

		Instanciate(score, new Vector2(50, 50), 0.0f);

		Time now = new Time();
		now.setToNow();

		lastTime = now.toMillis(true);
	}

	@Override
	public void update (float dt)
	{
		Time now = new Time();
		now.setToNow();

		if (now.toMillis(true) - lastTime > cooldown && Asteroid.number < Asteroid.LIMIT)
		{
			lastTime = now.toMillis(true);
			Instanciate(new Asteroid(score)
					, new Vector2((float) Math.random() * Screen.getWidth(), (float) Math.random() * Screen.getHeight())
					, 0.0f);
		}
	}

	@Override
	public void lateUpdate (float dt)
	{

	}
}
