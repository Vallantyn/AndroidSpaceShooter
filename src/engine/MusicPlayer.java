package engine;

import java.io.IOException;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.util.Log;

public class MusicPlayer
{

	private final String TAG = "MusicPlayer";

	private static MediaPlayer  mediaPlayer;
	private        AssetManager am;

	private MusicPlayer (Context cx, int musicId)
	{
		mediaPlayer = MediaPlayer.create(cx, musicId);
		mediaPlayer.start();

//		mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
//
//		mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener()
//		{
//			@Override
//			public boolean onError (MediaPlayer mp, int what, int extra)
//			{
//				return false;
//			}
//		});
//
//		mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener()
//		{
//			@Override
//			public void onPrepared (MediaPlayer mp)
//			{
//				try {
//					mp.start();
//				} catch (IllegalStateException e) {
//					e.printStackTrace();
//				}
//			}
//		});
	}

	private static class MPHolder
	{
		public static Context cx;
		public static int mId;
		private final static MusicPlayer instance = new MusicPlayer(cx, mId);
	}

	public void setAssetManager (AssetManager a)
	{
		am = a;
	}

	public static void setContext (Context c)
	{
		MPHolder.cx = c;
	}
	public static void setMusic (int mId)
	{
		MPHolder.mId = mId;
	}

	public static MusicPlayer getInstance ()
	{
		return MPHolder.instance;
	}

	public void loadMusic (String path)
	{
		try
		{
			AssetFileDescriptor fd = am.openFd(path);

			mediaPlayer.reset();
			// on set le fichier audio
			mediaPlayer.setDataSource(fd.getFileDescriptor());

			// on pr�pare l'audio
			mediaPlayer.prepareAsync();

//			mediaPlayer.prepare();
//			mediaPlayer.start();
		}
		catch (IOException e)
		{
			Log.e(TAG, "Problem during audio loading", e);
		}
	}

	public void play ()
	{
		mediaPlayer.start();
	}

	public void pause ()
	{
		mediaPlayer.pause();
	}

	public void stop ()
	{
		mediaPlayer.stop();
	}
}
