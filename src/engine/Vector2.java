package engine;

import android.graphics.Point;
import android.graphics.PointF;

public class Vector2
		extends PointF
{

	public Vector2 ()
	{
		super();
	}

	public Vector2 (float a)
	{
		this.x = a;
		this.y = a;
	}

	public Vector2 (Point p)
	{
		super(p);
	}

	public Vector2 (float x, float y)
	{
		super(x, y);
	}

	public void normalize ()
	{
		double factor = Math.sqrt(x * x + y * y);
		x /= factor;
		y /= factor;
	}

	public float dot (Vector2 v)
	{
		return v.x * x + v.y * y;
	}

	public Vector2 add (Vector2 v)
	{
		x += v.x;
		y += v.y;

		return this;
	}

	public Vector2 mul (float m)
	{
		x *= m;
		y *= m;

		return this;
	}

	public double dist (Vector2 v)
	{
		double X = x-v.x;
		double Y = y-v.y;
		return Math.sqrt(X*X+Y*Y);
	}

	static Vector2 add (Vector2 v1, Vector2 v2)
	{
		return new Vector2(v1.x + v2.x, v1.y + v2.y);
	}

	static Vector2 sub (Vector2 v1, Vector2 v2)
	{
		return new Vector2(v1.x - v2.x, v1.y - v2.y);
	}

	static Vector2 mul (Vector2 v, float m)
	{
		return new Vector2(v.x * m, v.y * m);
	}

}
