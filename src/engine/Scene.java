package engine;

import android.graphics.Canvas;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

public class Scene
		extends AbstractScene
{

	protected int color = 0;

	@Override
	public void start ()
	{
		if (objects.size() > 0)
		{
			Iterator<GameObject> GO = objects.iterator();

			while (GO.hasNext())
			{
				GameObject go = GO.next();

				go.start();
			}
		}
	}

	@Override
	public void update (float dt)
	{
		if (objects.size() > 0)
		{
			Iterator<GameObject> GO = objects.iterator();

			while (GO.hasNext())
			{
				GameObject go = GO.next();
				go.update(dt);
			}
		}
	}

	@Override
	public void lateUpdate (float dt)
	{
		if (objects.size() > 0)
		{
			Iterator<GameObject> GO = objects.iterator();

			while (GO.hasNext())
			{
				GameObject go = GO.next();

				go.lateUpdate(dt);
			}
		}
	}

	public void render (Canvas c)
	{
		c.drawColor(color);

		if (objects.size() > 0)
		{
			Iterator<GameObject> GO = objects.iterator();

			while (GO.hasNext())
			{
				GameObject go = GO.next();

				if (go.renderListener != null) go.renderListener.OnRender(c);
			}
		}
	}
}
