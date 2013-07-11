package com.vallantyn.androidspaceshooter.assets.behaviours;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.vallantyn.androidspaceshooter.assets.gameobjects.HealthUnit;
import com.vallantyn.androidspaceshooter.assets.gameobjects.Ship;
import com.vallantyn.androidspaceshooter.assets.scenes.SampleScene;

import java.util.Stack;

import engine.AbstractBehaviour;
import engine.GameEngine;
import engine.GameObject;
import engine.IReceiver;
import engine.Input;
import engine.Screen;
import engine.Vector2;

/**
 * Created by Julien on 09/07/13.
 */
public class HealthBehaviour extends AbstractBehaviour implements IReceiver
{
	Stack<HealthUnit> healthUnits;
	Ship              player;

	public HealthBehaviour (Ship ship)
	{
		player = ship;
	}

	@Override
	public void start ()
	{

		healthUnits = new Stack<HealthUnit>();

		for (int i = 0; i < 10; i++)
		{
			HealthUnit unit = new HealthUnit();
			Instanciate(unit, new Vector2(transform.position.x + i * 7, transform.position.y), 0);
			healthUnits.push(unit);
		}
	}

	@Override
	public void update (float dt)
	{
		if (healthUnits.isEmpty())
		{
			Destroy(player);

			GameObject gameOver = new GameObject();

			gameOver.setOnRenderListener(new GameObject.OnRenderListener()
			{
				@Override
				public void OnRender (Canvas c)
				{
					String txt = "GAME OVER";
					String subTxt = "- Double Touch to restart -";

					Paint p = new Paint();
					p.setColor(Color.WHITE);

					p.setTextSize(72);
					c.drawText(txt, Screen.getWidth()/2 - p.measureText(txt)/2, Screen.getHeight()/2, p);

					p.setTextSize(24);
					c.drawText(subTxt, Screen.getWidth()/2 - p.measureText(subTxt)/2, Screen.getHeight()/2+72, p);
				}
			});

			gameOver.addBehaviour(new GameOverBehaviour());

			Instanciate(gameOver, new Vector2(), 0);
		}
	}

	@Override
	public void lateUpdate (float dt)
	{

	}

	@Override
	public void receiveMessage (String name, Object param)
	{
		if (name == "takeDamage")
		{
			int number = (Integer) param;

			for (int i = 0; i<number && number <= healthUnits.size(); i++)
			{
				Destroy(healthUnits.lastElement());
				healthUnits.remove(healthUnits.size()-1);
			}
		}
	}

	private class GameOverBehaviour extends AbstractBehaviour
	{
		private boolean check = false;

		@Override
		public void start ()
		{
			check = (Input.firstTouch || Input.secondTouch);
		}

		@Override
		public void update (float dt)
		{
			if (Input.firstTouch && Input.secondTouch && !check)
				GameEngine.getInstance().setScene(new SampleScene());
		}

		@Override
		public void lateUpdate (float dt)
		{

		}
	}
}
