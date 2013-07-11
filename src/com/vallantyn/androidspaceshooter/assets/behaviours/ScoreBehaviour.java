package com.vallantyn.androidspaceshooter.assets.behaviours;

import com.vallantyn.androidspaceshooter.assets.gameobjects.Score;

import engine.AbstractBehaviour;
import engine.IReceiver;

/**
 * Created by Julien on 09/07/13.
 */
public class ScoreBehaviour extends AbstractBehaviour implements IReceiver
{
	private long score = 0;

	@Override
	public void start ()
	{

	}

	@Override
	public void update (float dt)
	{
		((Score) gameObject).score.score = score;
	}

	@Override
	public void lateUpdate (float dt)
	{

	}

	@Override
	public void receiveMessage (String name, Object param)
	{
		if (name == "updateScore")
		{
			score += (Long) param;
		}
	}
}
