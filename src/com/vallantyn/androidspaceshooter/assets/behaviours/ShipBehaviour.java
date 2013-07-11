package com.vallantyn.androidspaceshooter.assets.behaviours;

import android.text.format.Time;

import com.vallantyn.androidspaceshooter.assets.gameobjects.HealthBar;
import com.vallantyn.androidspaceshooter.assets.gameobjects.HealthUnit;
import com.vallantyn.androidspaceshooter.assets.gameobjects.Laser;
import com.vallantyn.androidspaceshooter.assets.gameobjects.Ship;

import engine.AbstractBehaviour;
import engine.GameObject;
import engine.IReceiver;
import engine.Screen;
import engine.SoundFXManager;
import engine.Vector2;

/**
 * Created by Julien on 05/07/13.
 */
public class ShipBehaviour
		extends AbstractBehaviour implements IReceiver
{
	private final long cooldown = 1000;
	private       long lastTime = 0;
private HealthBar health;


	public ShipBehaviour ()
	{
		super();
	}

	@Override
	public void start ()
	{

		health = new HealthBar((Ship) gameObject);
		Instanciate(health, new Vector2(3*Screen.getWidth()/4, 50), 0.0f);

		Time now = new Time();
		now.setToNow();

		lastTime = now.toMillis(true);
	}

	@Override
	public void update (float dt)
	{
		Time now = new Time();
		now.setToNow();

		if (now.toMillis(true) - lastTime > cooldown)
		{
			lastTime = now.toMillis(true);
			Instanciate(new Laser(), transform.position, transform.rotation);
			SoundFXManager.SFX.LASER.play();
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
			health.sendMessage(name, param);
	}
}
