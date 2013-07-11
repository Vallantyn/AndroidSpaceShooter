package engine;

import android.graphics.Canvas;

public class Screen
{
	private static Canvas canvas = null;

	public static void setCanvas (Canvas c)
	{
		if (canvas != null) return;

		canvas = c;
	}

	public static int getWidth ()
	{
		if (canvas == null) return 0;

		return canvas.getWidth();
	}

	public static int getHeight ()
	{
		if (canvas == null) return 0;

		return canvas.getHeight();
	}
}
