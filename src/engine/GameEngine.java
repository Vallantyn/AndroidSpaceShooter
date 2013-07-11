package engine;

import android.graphics.Canvas;

public class GameEngine
		extends AbstractGameEngine
{
	private static class EngineHolder
	{
		private final static GameEngine instance = new GameEngine();
	}

	public static GameEngine getInstance ()
	{
		return EngineHolder.instance;
	}

	@Override
	public void update (float dt)
	{
		if (tempScene != null) {
			scene = tempScene;
			tempScene = null;
		}

		scene.beforeLoop();
		scene.update(dt);
		lateUpdate(dt);
	}

	@Override
	public void lateUpdate (float dt)
	{
		scene.lateUpdate(dt);
		scene.collideTest();
	}

	public void render (Canvas c)
	{
		scene.render(c);
	}
}
