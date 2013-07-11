package hex.physics;

/**
 * Represents a Vec2f.
 */
public class Vector2
{
	/**
	 * A {0, 0} Vec2f.
	 * */
	public static final Vector2 zero = new Vector2();
	/**
	 * A {1, 1} Vec2f.
	 * */
	public static final Vector2 one = new Vector2(1);
	/**
	 * A {0, 1} Vec2f.
	 * */
	public static final Vector2 up = new Vector2(0, 1);
	/**
	 * A {1, 0} Vec2f.
	 * */
	public static final Vector2 forward = new Vector2(1, 0);

	/**
	 * X component.
	 * */
	public float x;
	/**
	 * Y component.
	 * */
	public float y;

	public Vector2 ()
	{
		x = y = 0.0f;
	}
	public Vector2 (final float a)
	{
		x = a;
		y = a;
	}
	public Vector2 (final Vector2 v)
	{
		x = v.x;
		y = v.y;
	}
	public Vector2 (final float x, final float y)
	{
		this.x = x;
		this.y = y;
	}

	/**
	 * Adds the value to both X and Y.
	 *
	 * @param a
	 * 		The value to add.
	 *
	 * @return The modified Vec2f.
	 */
	public Vector2 add (final float a)
	{
		x += a;
		y += a;

		return this;
	}
	/**
	 * Add the values to X and Y.
	 *
	 * @param x
	 * 		Value to add to X.
	 * @param y
	 * 		Value to add to Y.
	 *
	 * @return The modified Vec2f.
	 */
	public Vector2 add (final float x, final float y)
	{
		this.x += x;
		this.y += y;

		return this;
	}
	/**
	 * Add the Vec2f.
	 *
	 * @param v
	 * 		Vector to add.
	 *
	 * @return The modified Vec2f.
	 */
	public Vector2 add (final Vector2 v)
	{
		x += v.x;
		y += v.y;

		return this;
	}

	public Vector2 sub (final float a)
	{
		return add(-a);
	}
	public Vector2 sub (final float x, final float y)
	{
		return add(-x, -y);
	}
	public Vector2 sub (final Vector2 v)
	{
		return add(-v.x, -v.y);
	}
}
