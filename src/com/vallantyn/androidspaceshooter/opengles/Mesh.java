package com.vallantyn.androidspaceshooter.opengles;

/**
 * Created by Julien on 10/07/13.
 */
public abstract class Mesh
{
	protected FloatBuffer vertexBuffer = new FloatBuffer()
			, colorBuffer = new FloatBuffer()
			, normalBuffer = new FloatBuffer();
	protected float[] mMatrix  = new float[16];
	protected Shader  material = null;

	public Mesh ()
	{
	}

	public abstract void render (float[] pViewMatrix, float[] pProjectionMatrix, float[] pLightPosInEyeSpace);
}
