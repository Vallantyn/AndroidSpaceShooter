package com.vallantyn.androidspaceshooter.assets.gameobjects;

import android.graphics.Canvas;

import com.vallantyn.androidspaceshooter.assets.behaviours.ScoreBehaviour;
import com.vallantyn.androidspaceshooter.assets.mesh.ScoreText;

import engine.GameObject;

/**
 * Created by Julien on 09/07/13.
 */
public class Score extends GameObject
{
	public ScoreText score;

	public Score()
	{
		score = new ScoreText();

		setOnRenderListener(new OnRenderListener()
		{
			@Override
			public void OnRender (Canvas c)
			{
				score.draw(transform, c);
			}
		});

		addBehaviour(new ScoreBehaviour());
	}
}
