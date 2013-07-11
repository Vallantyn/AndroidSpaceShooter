package com.vallantyn.androidspaceshooter.opengles;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.os.SystemClock;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class CustomRenderer implements GLSurfaceView.Renderer
{
	private Cube cube;

	private final int mBytesPerFloat = 4, mStrideBytes = 7 * mBytesPerFloat, mPositionOffset = 0, mColorOffset = 3, mPositionDataSize = 3, mColorDataSize = 4;

	private final Context cx;

	private float[] mViewMatrix = new float[16], mProjectionMatrix = new float[16], mModelMatrix = new float[16], mMVPMatrix = new float[16];

	private Shader basic;

	public CustomRenderer (Context cx)
	{
		this.cx = cx;
	}

	@Override
	public void onSurfaceCreated (GL10 glUnused, EGLConfig config)
	{
		cube = new Cube(cx);

		GLES20.glClearColor(.5f, .5f, .5f, 1.f);

		GLES20.glEnable(GLES20.GL_CULL_FACE);
		GLES20.glEnable(GLES20.GL_DEPTH_TEST);

		final float eyeX = 0.f, eyeY = 0.f, eyeZ = 1.5f

				, lookX = 0.f
				  , lookY = 0.f
				  , lookZ = -5.f

			      , upX = 0.f
				  , upY = 1.f
				  , upZ = 0.f;

		Matrix.setLookAtM(mViewMatrix, 0
				, eyeX, eyeY, eyeZ
				, lookX, lookY, lookZ
				, upX, upY, upZ);
	}

	@Override
	public void onSurfaceChanged (GL10 glUnused, int width, int height)
	{
		GLES20.glViewport(0, 0, width, height);

		final float ratio  =  (float) width / height
				  , left   =  -ratio
				  , right  =   ratio
				  , bottom =  -1.0f
				  , top    =   1.0f
				  , near   =    1.0f
				  , far    = 10.f;

		Matrix.frustumM(mProjectionMatrix, 0
				, left, right
				, bottom, top
				, near, far);
	}

	@Override
	public void onDrawFrame (GL10 glUnused)
	{
		GLES20.glClear(GLES20.GL_DEPTH_BUFFER_BIT | GLES20.GL_COLOR_BUFFER_BIT);

		long time = SystemClock.uptimeMillis() % 10000L;
		float angleInDegress = (360f/10000f) * ((int) time);

		Matrix.setIdentityM(cube.mMatrix, 0);
		Matrix.translateM(cube.mMatrix, 0, 0f, 0f, -5.0f);
		Matrix.rotateM(cube.mMatrix, 0, angleInDegress, 0.0f, 1.0f, 0.0f);

		cube.render(mViewMatrix, mProjectionMatrix, new float[]{2.0f, 2.0f, -2.0f});
	}
}
