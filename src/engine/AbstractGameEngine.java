package engine;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public abstract class AbstractGameEngine
		extends AbstractGameObject
{
	protected Scene scene;
	public boolean started = false;

	protected Scene tempScene;

	@Override
	public void start ()
	{
		scene.start();

		started = true;
	}

	public Scene getScene ()
	{
		return scene;
	}

	public void setScene (Scene scene)
	{
		if (this.scene != null)
		{
			this.tempScene = scene;
		}
		else this.scene = scene;

	}
}