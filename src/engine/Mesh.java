package engine;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;

import java.util.ArrayList;
import java.util.Iterator;

public class Mesh
		extends Path
{

	private ArrayList<Point> vertices = new ArrayList<Point>();

	public int color = 0;

	public void add (Point p)
	{
		vertices.add(p);
	}

	public void end ()
	{
		if (vertices.size() > 0)
		{
			Iterator<Point> vertData = vertices.iterator();

			Point p = vertData.next();

			moveTo(p.x, p.y);

			while (vertData.hasNext())
			{
				p = vertData.next();
				lineTo(p.x, p.y);
			}
		}

		close();
	}

	public void draw (Transform t, Canvas c)
	{
		Paint p = new Paint();
		p.setColor(color);

		c.translate(t.position.x,  t.position.y);
		c.rotate(t.rotation);

		c.drawPath(this, p);

		c.rotate(-t.rotation);
		c.translate(-t.position.x,  -t.position.y);

		t.draw(c);

		t.getMatrix().reset();
	}
}
