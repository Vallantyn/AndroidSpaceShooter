package engine;

import android.graphics.Rect;

/**
 * Created by Julien on 08/07/13.
 */
public abstract class AbstractCollider
{
	protected Rect bounds;

	protected int  radius;
	public GameObject gameObject;

	public AbstractCollider (GameObject ago)
	{
		bounds = new Rect();
		radius = 0;
		gameObject = ago;
	}

	public abstract boolean isColliding (AbstractCollider other);

	public Rect getBounds ()
	{
		return bounds;
	}

	public void setBounds (Rect bounds)
	{
		this.bounds = bounds;
	}

	public int getRadius ()
	{
		return radius;
	}

	public void setRadius (int radius)
	{
		this.radius = radius;
	}
}
