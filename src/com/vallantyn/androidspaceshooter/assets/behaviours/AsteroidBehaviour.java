package com.vallantyn.androidspaceshooter.assets.behaviours;

import com.vallantyn.androidspaceshooter.assets.gameobjects.Asteroid;
import com.vallantyn.androidspaceshooter.assets.gameobjects.Score;

import engine.AbstractBehaviour;
import engine.AbstractCollider;
import engine.Dice;
import engine.ICollidable;
import engine.Screen;
import engine.SoundFXManager;
import engine.Vector2;

/**
 * Created by Julien on 09/07/13.
 */
public class AsteroidBehaviour
		extends AbstractBehaviour
		implements ICollidable
{
	private final float   speed    = (float) (Math.random() * 100.0f);
	private final double  deltaRot = (Math.random() - 1.0) * 90.0;
	private final Vector2 deltaPos = new Vector2((float) (Math.random() - .5f) * 2.0f, (float) (Math.random() - .5f) * 2.0f);
	private       boolean choked   = false;

	private Score score = null;

	public AsteroidBehaviour(Score score)
	{
		this.score = score;
	}

	@Override
	public void start ()
	{
//		transform.lookAt(new Vector2(Screen.getWidth() / 2, Screen.getHeight() / 2));
	}

	@Override
	public void update (float dt)
	{
		transform.Rotate(deltaRot * dt);

		if (!choked) transform.Translate(deltaPos.x * speed * dt, deltaPos.y * speed * dt);

		if (transform.position.x < -gameObject.collider.getRadius())
			transform.Translate(Screen.getWidth() + gameObject.collider.getRadius(), 0);
		if (transform.position.x > Screen.getWidth() + gameObject.collider.getRadius())
			transform.Translate(-Screen.getWidth() - gameObject.collider.getRadius(), 0);
		if (transform.position.y < -gameObject.collider.getRadius())
			transform.Translate(0, Screen.getHeight() + gameObject.collider.getRadius());
		if (transform.position.y > Screen.getHeight() + gameObject.collider.getRadius())
			transform.Translate(0, -Screen.getHeight() - gameObject.collider.getRadius());
	}

	@Override
	public void lateUpdate (float dt)
	{

	}

	@Override
	public void onCollide (AbstractCollider ac)
	{
		if (ac.gameObject.TAG == "PLAYER_PROJECTILE")
		{
			SoundFXManager.SFX.EXPLOSION.play();
			Destroy(gameObject);
			Destroy(ac.gameObject);

			if (Dice.get100(60) && Asteroid.number < Asteroid.LIMIT) Instanciate(new Asteroid(score)
					, new Vector2((float) Math.random() * Screen.getWidth(), (float) Math.random() * Screen.getHeight())
					, 0.0f);

			score.sendMessage("updateScore", (long)(gameObject.collider.getRadius()*10));
		}
		else if (ac.gameObject.TAG == "PLAYER")
		{
			ac.gameObject.sendMessage("takeDamage", 1);
			SoundFXManager.SFX.HURT.play();

			Destroy(gameObject);
		}

//		if (!choked) SoundFXManager.SFX.EXPLOSION.play();

//		choked = true;
	}
}
