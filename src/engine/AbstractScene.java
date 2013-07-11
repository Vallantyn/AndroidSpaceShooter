package engine;

import android.util.Log;

import java.util.ArrayList;

public abstract class AbstractScene
		extends AbstractGameObject
		implements
		IParentable
{

	protected ArrayList<GameObject> objects     = new ArrayList<GameObject>();
	protected ArrayList<GameObject> objToAdd    = new ArrayList<GameObject>();
	protected ArrayList<GameObject> objToRemove = new ArrayList<GameObject>();

	@Override
	public void addChild (GameObject child)
	{
		objToAdd.add(child);
	}

	public void beforeLoop ()
	{
		if (objToAdd.size() > 0)
		{
			objects.addAll(objToAdd);
			objToAdd.clear();
		}

		if (objToRemove.size() > 0 && objects.size() > 0)
		{
			objects.removeAll(objToRemove);
			objToRemove.clear();
		}
	}

	public void collideTest ()
	{
		for (GameObject goA : objects)
			if (goA.collider != null)
			{
				for (GameObject goB : objects)
					if (goB.collider != null)
					{
						if (!goA.equals(goB) && goA.collider.isColliding(goB.collider)) goA.OnTrigger(goB.collider);
					}
					else continue;
			}
			else continue;
	}
}
