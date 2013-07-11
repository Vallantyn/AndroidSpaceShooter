/**
 * La SurfaceView permet d'effectuer tout le traitement propre au rendu (dessin)
 * sur un autre thread que l'UIThread. Cela permet de ne pas surcharger ce dernier
 * afin de ne pas ralentir l'affichage � l'�cran.
 */

package com.vallantyn.androidspaceshooter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceView;

import com.vallantyn.androidspaceshooter.assets.scenes.SampleScene;

import java.util.Calendar;

import engine.GameEngine;
import engine.Input;
import engine.MusicPlayer;
import engine.Screen;
import engine.SoundFXManager;
import engine.Vector2;

public class CustomSurfaceView
		extends SurfaceView
		implements Runnable
{

	private Thread  loopThread;
	private boolean running;
	private float   dt;

	private boolean stPress = false;

	private MediaPlayer mp = null;

	private Vector2[] touched = new Vector2[10];

	public CustomSurfaceView (Context context)
	{
		super(context);
	}

	public CustomSurfaceView (Context context, AttributeSet attrs)
	{
		super(context, attrs);
	}

	public CustomSurfaceView (Context context, AttributeSet attrs, int defStyle)
	{
		super(context, attrs, defStyle);
	}

	public void resume ()
	{
		// instantier votre Thread ici
		running = true;

		if (loopThread == null)
		{
			loopThread = new Thread(this);
			loopThread.start();
		}

		if (!loopThread.isAlive())
		{
			loopThread = new Thread(this);
			loopThread.start();
		}

		if (mp != null) mp.start();
	}

	public void pause ()
	{
		if (mp != null) mp.pause();

		running = false;
	}

	@Override
	public void run ()
	{
		long t1 = Calendar.getInstance().getTimeInMillis();
		long t2;

		GameEngine game = GameEngine.getInstance();
		Canvas canvas = null;

		while (running)
		{
			t2 = Calendar.getInstance().getTimeInMillis();
			dt = t2 - t1;

			if (!getHolder().getSurface().isValid()) continue;

			canvas = getHolder().lockCanvas();

			Screen.setCanvas(canvas);

			if (!game.started)
			{
				game.setScene(new SampleScene());
				game.start();

				mp = MediaPlayer.create(getContext(), R.raw.background);
				mp.setLooping(true);
				mp.start();

//				MusicPlayer.getInstance().loadMusic("music/background.mp3");
			}

			game.update(dt / 1000);
			game.render(canvas);

			if (Input.secondTouch)
			{
				if (!stPress)
				{
					stPress = true;
				}
			}
			else
			{
				stPress = false;
			}

			getHolder().unlockCanvasAndPost(canvas);

			t1 = t2;
		}
	}

	@Override
	public boolean onTouchEvent (MotionEvent event)
	{

		// R�cup�rer le type d'action
		int action = event.getActionMasked();

		// R�cup�rer l'index
		int actionIndex = event.getActionIndex();

		// R�cup�rer l'id du pointeur
		int pointerId = event.getPointerId(actionIndex);

		switch (action)
		{
			case MotionEvent.ACTION_UP:
				// premier touch uniquement
			case MotionEvent.ACTION_POINTER_UP:
				touched[pointerId] = null;

				if (pointerId == 0)
					Input.firstTouch = false;
				if (pointerId == 1)
					Input.secondTouch = false;

				break;
			// autres touch
			case MotionEvent.ACTION_DOWN:
				// premier touch uniquement
			case MotionEvent.ACTION_POINTER_DOWN:
				for (int i = 0; i < event.getPointerCount(); i++)
				{
					touched[event.getPointerId(i)] = new Vector2(event.getX(i),
							event.getY(i));

					if (event.getPointerId(i) == 0)
					{
						Input.firstTouch = true;
						Input.touch = new PointF(event.getX(i), event.getY(i));
					}
					if (event.getPointerId(i) == 1)
					{
						Input.secondTouch = true;
						Input.touch2 = new PointF(event.getX(i), event.getY(i));
					}
				}

				break;
			// autres touch
			case MotionEvent.ACTION_MOVE:
				for (int i = 0; i < event.getPointerCount(); i++)
				{
					touched[event.getPointerId(i)] = new Vector2(event.getX(i),
							event.getY(i));

					if (event.getPointerId(i) == 0)
					{
						Input.touch = new PointF(event.getX(i), event.getY(i));
					}
					if (event.getPointerId(i) == 1)
					{
						Input.touch2 = new PointF(event.getX(i), event.getY(i));
					}
				}
				break;
			// tous les touch
		}

		return true;
	}
}