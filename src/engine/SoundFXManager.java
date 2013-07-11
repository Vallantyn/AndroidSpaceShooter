package engine;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;

import java.io.IOException;

public class SoundFXManager
{

	private static SoundPool    pool;
	private        AssetManager am;

	private SoundFXManager ()
	{
		pool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
	}

	private static class SFXHolder
	{
		private final static SoundFXManager instance = new SoundFXManager();
	}

	public static SoundFXManager getInstance ()
	{
		return SFXHolder.instance;
	}

	public void setAssetManager (AssetManager a)
	{
		am = a;
	}

	public void preload ()
	{
		AssetFileDescriptor fd;

		try
		{
			fd = am.openFd("sfx/Select.wav");
			SFX.SELECT.setId(pool.load(fd, 1));
			fd.close();
			fd = am.openFd("sfx/Explosion.wav");
			SFX.EXPLOSION.setId(pool.load(fd, 1));
			fd.close();
			fd = am.openFd("sfx/Hurt.wav");
			SFX.HURT.setId(pool.load(fd, 1));
			fd.close();
			fd = am.openFd("sfx/Laser.wav");
			SFX.LASER.setId(pool.load(fd, 1));
			fd.close();
			fd = am.openFd("sfx/Missile.wav");
			SFX.MISSILE.setId(pool.load(fd, 1));
			fd.close();
			fd = am.openFd("sfx/Engine.wav");
			SFX.ENGINE.setId(pool.load(fd, 1));
			fd.close();
			fd = am.openFd("sfx/Pickup.wav");
			SFX.PICKUP.setId(pool.load(fd, 1));
			fd.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public enum SFX
	{
		SELECT(),
		HURT(),
		LASER(),
		PICKUP(),
		MISSILE(),
		ENGINE(),
		EXPLOSION();

		SFX ()
		{
		}

		public int id;

		public void setId (int i)
		{
			id = i;
		}

		public void play ()
		{
			pool.play(id, 1, 1, 0, 0, 1);
		}

		public void loop ()
		{
			pool.play(id, 1, 1, 0, -1, 1);
		}

		public void unLoop()
		{
			pool.setLoop(id, 0);
		}
	}
}
