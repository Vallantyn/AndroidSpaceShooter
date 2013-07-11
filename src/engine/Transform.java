package engine;

import android.graphics.Canvas;
import android.graphics.Matrix;

import java.util.ArrayList;
import java.util.Iterator;

public class Transform
		extends AbstractGameObject
{
	public GameObject gameObject = null;
	public Vector2    position   = new Vector2();
	public float      rotation   = 0.0f;

	private ArrayList<Transform> childs = new ArrayList<Transform>();
	private Transform            parent = null;

	private Matrix matrix = new Matrix();

	public void addChild (Transform child)
	{
		child.parent = this;

		childs.add(child);
	}

	public void start ()
	{
		if (childs.size() > 0)
		{
			Iterator<Transform> C = childs.iterator();

			while (C.hasNext())
			{
				C.next().gameObject.start();
			}
		}
	}

	public void update (float dt)
	{
		if (childs.size() > 0)
		{
			Iterator<Transform> C = childs.iterator();

			while (C.hasNext())
			{
				C.next().gameObject.update(dt);
			}
		}
	}

	public void lateUpdate (float dt)
	{
		if (childs.size() > 0)
		{
			Iterator<Transform> C = childs.iterator();

			while (C.hasNext())
			{
				C.next().gameObject.lateUpdate(dt);
			}
		}

	}

	public void draw (Canvas c)
	{
		if (childs.size() > 0)
		{
			Iterator<Transform> C = childs.iterator();

			while (C.hasNext())
			{
				((IRenderable)C.next().gameObject).render(c);
			}
		}

	}

	public void Translate (Vector2 v)
	{
		position.add(v);

		matrix.preTranslate(v.x, v.y);
	}

	public void Translate (float x, float y)
	{
		position.x += x;
		position.y += y;

		matrix.preTranslate(x, y);
	}

	public void Rotate (float a)
	{
		rotation += a;
		rotation %= 360;

		matrix.postRotate(a, position.x, position.y);
	}

	public void Rotate (double a)
	{
		rotation += a;
		rotation %= 360;

		matrix.postRotate((float) a, position.x, position.y);
	}

	public Matrix getMatrix ()
	{

//		Matrix pMatrix = parent != null ? parent.getMatrix() : new Matrix();

//		pMatrix.postConcat(matrix);

		return matrix;
	}

	public Vector2 forward ()
	{
		return new Vector2((float) Math.cos(rotation * Math.PI / 180),
				(float) Math.sin(rotation * Math.PI / 180));
	}

	public void lookAt (Vector2 to)
	{
		Vector2 dir = forward();
		dir.normalize();

		Vector2 aim = Vector2.sub(to, position);
		aim.normalize();

		double angle = Math.atan2(aim.y, aim.x) - Math.atan2(dir.y, dir.x);

		angle *= 180 / Math.PI;

		if (Math.abs(angle) > 180)
			angle -= 360 * Math.signum(angle);

		Rotate(angle);
	}
}
