package engine;

import android.util.Log;

/**
 * Created by Julien on 08/07/13.
 */
public class CircleCollider extends AbstractCollider
{
	public CircleCollider (GameObject ago)
	{
		super(ago);
	}

	@Override
	public boolean isColliding (AbstractCollider other)
	{

		Vector2 posA = gameObject.transform.position;
		Vector2 posB = other.gameObject.transform.position;
		int R = radius + other.radius;

		return posA.dist(posB) < R;
	}
}
