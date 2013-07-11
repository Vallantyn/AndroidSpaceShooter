package com.vallantyn.androidspaceshooter.assets.mesh;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import engine.Transform;

/**
 * Created by Julien on 09/07/13.
 */
public class ScoreText
{
	public long score = 0;

	public ScoreText()
	{

	}

	public void draw(Transform t, Canvas c)
	{
		Paint p = new Paint();
		p.setColor(Color.WHITE);
		p.setTextSize(24);

		c.translate(t.position.x,  t.position.y);
		c.rotate(t.rotation);

		c.drawText("Score: "+score, 0, 0, p);

		c.rotate(-t.rotation);
		c.translate(-t.position.x,  -t.position.y);

		t.draw(c);

		t.getMatrix().reset();
	}
}
