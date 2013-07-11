package com.vallantyn.androidspaceshooter.assets.behaviours;

import com.vallantyn.androidspaceshooter.assets.gameobjects.Missile;

import engine.AbstractBehaviour;
import engine.GameObject;
import engine.Input;
import engine.SoundFXManager;
import engine.pool.IFactorable;
import engine.pool.IPoolable;
import engine.pool.Pool;

public class SecondTouch
		extends AbstractBehaviour
{
	Pool<Missile> missilePool = new Pool<Missile>(new IFactorable()
	{
		@Override
		public IPoolable createNew ()
		{
			return new Missile();
		}
	}, 90);
	private boolean stPress = false;

	@Override
	public void start () {}

	@Override
	public void update (float dt)
	{
		if (Input.secondTouch)
		{
			if (!stPress)
			{
				stPress = true;

				final Missile m = missilePool.newObject();

				m.setOnDestroyListener(new OnDestroyListener()
				{
					@Override
					public void OnDestroy ()
					{
						missilePool.freeObject(m);
					}
				});

				GameObject.Instanciate((GameObject) m, gameObject.transform.position, 0.0f);

				SoundFXManager.SFX.MISSILE.play();

				m.sendMessage("setDirection", Input.touch2);
			}
		}
		else stPress = false;
	}

	@Override
	public void lateUpdate (float dt) {}

}
