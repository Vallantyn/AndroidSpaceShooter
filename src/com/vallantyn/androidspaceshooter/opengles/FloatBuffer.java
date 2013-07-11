package com.vallantyn.androidspaceshooter.opengles;

import android.opengl.GLES20;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * Created by Julien on 10/07/13.
 */
public class FloatBuffer
{
	private float[]              data;

	public int index;

	private java.nio.FloatBuffer buffer;

	private final int mBytesPerFloat = 4;

	public FloatBuffer (float[] data)
	{
		this.data = data;

		genBuffer();
	}

	private void genBuffer ()
	{
		buffer = ByteBuffer
				.allocateDirect(data.length * mBytesPerFloat)
				.order(ByteOrder.nativeOrder())
				.asFloatBuffer();

		buffer.put(data).position(0);

		final int[] buffers = new int[1];
		GLES20.glGenBuffers(1, buffers, 0);

		GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, buffers[0]);
		GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, buffer.capacity() * mBytesPerFloat, buffer, GLES20.GL_STATIC_DRAW);
		GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, 0);

		index = buffers[0];

		buffer.limit(0);
		buffer = null;
	}

	public FloatBuffer ()
	{
		data = new float[0];
		buffer = null;
	}

	public float[] getData ()
	{
		return data;
	}

	public void setData (float[] data)
	{
		this.data = data;
		genBuffer();
	}

	public java.nio.FloatBuffer getBuffer ()
	{
		return buffer;
	}
}
