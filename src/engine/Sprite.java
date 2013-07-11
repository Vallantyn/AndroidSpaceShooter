package engine;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

import java.util.Iterator;

public class Sprite
{
	protected Bitmap sprite;
	protected Rect   bounds;

	public Sprite (Bitmap base, Rect place)
	{
		sprite = Bitmap.createBitmap(base, place.left, place.top, place.width(), place.height());
		bounds = new Rect(0, 0, place.width(), place.height());
	}

	public Sprite (Bitmap base, Rect place, int scale)
	{
		Bitmap _bmp = Bitmap.createBitmap(base, place.left, place.top, place.width(), place.height());
		sprite = Bitmap.createScaledBitmap(_bmp
				, place.width() * scale
				, place.height() * scale
				, true);

		bounds = new Rect(0, 0, place.width() * scale, place.height() * scale);
	}

	public void draw (Transform t, Canvas c)
	{
		c.translate(t.position.x, t.position.y);
		c.rotate(t.rotation+90);

		Paint p = new Paint();
		p.setColor(Color.GREEN);
		p.setStyle(Paint.Style.STROKE);
		p.setStrokeWidth(2);

		c.drawBitmap(sprite, -bounds.width() / 2, -bounds.height() / 2, null);
//		if (t.gameObject.collider != null) c.drawCircle(0, 0, t.gameObject.collider.getRadius(), p);

		c.rotate(-t.rotation-90);
		c.translate(-t.position.x, -t.position.y);

		t.draw(c);

		t.getMatrix().reset();
	}

	public void draw (Transform t, Canvas c, Paint p)
	{
		c.translate(t.position.x, t.position.y);
		c.rotate(t.rotation+90);

		c.drawBitmap(sprite, -bounds.width() / 2, -bounds.height() / 2, p);

		c.rotate(-t.rotation-90);
		c.translate(-t.position.x, -t.position.y);

		t.draw(c);

		t.getMatrix().reset();
	}
}
