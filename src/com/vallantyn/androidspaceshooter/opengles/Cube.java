package com.vallantyn.androidspaceshooter.opengles;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.Matrix;

/**
 * Created by Julien on 10/07/13.
 */
public class Cube extends Mesh
{
	public Cube(Context cx)
	{
		material = new Shader(cx, "basic");

		material.setUniforms(new String[]{
				"u_MVMatrix"
			  , "u_MVPMatrix"
			  , "u_LightPosition"
		});
		material.setAttributes(new String[]{
				"a_Position"
			  , "a_Color"
			  , "a_Normal"
		});

		final float[] vertex =
				{
						// Front face
						-1.0f, 1.0f, 1.0f,
						-1.0f, -1.0f, 1.0f,
						1.0f, 1.0f, 1.0f,
						-1.0f, -1.0f, 1.0f,
						1.0f, -1.0f, 1.0f,
						1.0f, 1.0f, 1.0f,

						// Right face
						1.0f, 1.0f, 1.0f,
						1.0f, -1.0f, 1.0f,
						1.0f, 1.0f, -1.0f,
						1.0f, -1.0f, 1.0f,
						1.0f, -1.0f, -1.0f,
						1.0f, 1.0f, -1.0f,

						// Back face
						1.0f, 1.0f, -1.0f,
						1.0f, -1.0f, -1.0f,
						-1.0f, 1.0f, -1.0f,
						1.0f, -1.0f, -1.0f,
						-1.0f, -1.0f, -1.0f,
						-1.0f, 1.0f, -1.0f,

						// Left face
						-1.0f, 1.0f, -1.0f,
						-1.0f, -1.0f, -1.0f,
						-1.0f, 1.0f, 1.0f,
						-1.0f, -1.0f, -1.0f,
						-1.0f, -1.0f, 1.0f,
						-1.0f, 1.0f, 1.0f,

						// Top face
						-1.0f, 1.0f, -1.0f,
						-1.0f, 1.0f, 1.0f,
						1.0f, 1.0f, -1.0f,
						-1.0f, 1.0f, 1.0f,
						1.0f, 1.0f, 1.0f,
						1.0f, 1.0f, -1.0f,

						// Bottom face
						1.0f, -1.0f, -1.0f,
						1.0f, -1.0f, 1.0f,
						-1.0f, -1.0f, -1.0f,
						1.0f, -1.0f, 1.0f,
						-1.0f, -1.0f, 1.0f,
						-1.0f, -1.0f, -1.0f,
				};

		// R, G, B, A
		final float[] color =
				{
						// Front face (red)
						1.0f, 0.0f, 0.0f, 1.0f,
						1.0f, 0.0f, 0.0f, 1.0f,
						1.0f, 0.0f, 0.0f, 1.0f,
						1.0f, 0.0f, 0.0f, 1.0f,
						1.0f, 0.0f, 0.0f, 1.0f,
						1.0f, 0.0f, 0.0f, 1.0f,

						// Right face (green)
						0.0f, 1.0f, 0.0f, 1.0f,
						0.0f, 1.0f, 0.0f, 1.0f,
						0.0f, 1.0f, 0.0f, 1.0f,
						0.0f, 1.0f, 0.0f, 1.0f,
						0.0f, 1.0f, 0.0f, 1.0f,
						0.0f, 1.0f, 0.0f, 1.0f,

						// Back face (blue)
						0.0f, 0.0f, 1.0f, 1.0f,
						0.0f, 0.0f, 1.0f, 1.0f,
						0.0f, 0.0f, 1.0f, 1.0f,
						0.0f, 0.0f, 1.0f, 1.0f,
						0.0f, 0.0f, 1.0f, 1.0f,
						0.0f, 0.0f, 1.0f, 1.0f,

						// Left face (yellow)
						1.0f, 1.0f, 0.0f, 1.0f,
						1.0f, 1.0f, 0.0f, 1.0f,
						1.0f, 1.0f, 0.0f, 1.0f,
						1.0f, 1.0f, 0.0f, 1.0f,
						1.0f, 1.0f, 0.0f, 1.0f,
						1.0f, 1.0f, 0.0f, 1.0f,

						// Top face (cyan)
						0.0f, 1.0f, 1.0f, 1.0f,
						0.0f, 1.0f, 1.0f, 1.0f,
						0.0f, 1.0f, 1.0f, 1.0f,
						0.0f, 1.0f, 1.0f, 1.0f,
						0.0f, 1.0f, 1.0f, 1.0f,
						0.0f, 1.0f, 1.0f, 1.0f,

						// Bottom face (magenta)
						1.0f, 0.0f, 1.0f, 1.0f,
						1.0f, 0.0f, 1.0f, 1.0f,
						1.0f, 0.0f, 1.0f, 1.0f,
						1.0f, 0.0f, 1.0f, 1.0f,
						1.0f, 0.0f, 1.0f, 1.0f,
						1.0f, 0.0f, 1.0f, 1.0f
				};

		final float[] normals =
				{
						// Front face
						0.0f, 0.0f, 1.0f,
						0.0f, 0.0f, 1.0f,
						0.0f, 0.0f, 1.0f,
						0.0f, 0.0f, 1.0f,
						0.0f, 0.0f, 1.0f,
						0.0f, 0.0f, 1.0f,

						// Right face
						1.0f, 0.0f, 0.0f,
						1.0f, 0.0f, 0.0f,
						1.0f, 0.0f, 0.0f,
						1.0f, 0.0f, 0.0f,
						1.0f, 0.0f, 0.0f,
						1.0f, 0.0f, 0.0f,

						// Back face
						0.0f, 0.0f, -1.0f,
						0.0f, 0.0f, -1.0f,
						0.0f, 0.0f, -1.0f,
						0.0f, 0.0f, -1.0f,
						0.0f, 0.0f, -1.0f,
						0.0f, 0.0f, -1.0f,

						// Left face
						-1.0f, 0.0f, 0.0f,
						-1.0f, 0.0f, 0.0f,
						-1.0f, 0.0f, 0.0f,
						-1.0f, 0.0f, 0.0f,
						-1.0f, 0.0f, 0.0f,
						-1.0f, 0.0f, 0.0f,

						// Top face
						0.0f, 1.0f, 0.0f,
						0.0f, 1.0f, 0.0f,
						0.0f, 1.0f, 0.0f,
						0.0f, 1.0f, 0.0f,
						0.0f, 1.0f, 0.0f,
						0.0f, 1.0f, 0.0f,

						// Bottom face
						0.0f, -1.0f, 0.0f,
						0.0f, -1.0f, 0.0f,
						0.0f, -1.0f, 0.0f,
						0.0f, -1.0f, 0.0f,
						0.0f, -1.0f, 0.0f,
						0.0f, -1.0f, 0.0f
				};

		vertexBuffer = new FloatBuffer(vertex);
		colorBuffer = new FloatBuffer(color);
		normalBuffer = new FloatBuffer(normals);
	}

	@Override
	public void render (float[] pViewMatrix, float[] pProjectionMatrix, float[] pLightPosInEyeSpace)
	{
		float[] lMVPMatrix = new float[16];

		GLES20.glUseProgram(material.getProgramHandle());

//		vertexBuffer.getBuffer().position(0);
		GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, vertexBuffer.index);
		GLES20.glEnableVertexAttribArray(material.attribs.get("a_Position"));
		GLES20.glVertexAttribPointer(material.attribs.get("a_Position"), 3, GLES20.GL_FLOAT, false,
				0, 0);


//		colorBuffer.getBuffer().position(0);
		GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, colorBuffer.index);
		GLES20.glEnableVertexAttribArray(material.attribs.get("a_Color"));
		GLES20.glVertexAttribPointer(material.attribs.get("a_Color"), 4, GLES20.GL_FLOAT, false,
				0, 0);


//		normalBuffer.getBuffer().position(0);
		GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, normalBuffer.index);
		GLES20.glEnableVertexAttribArray(material.attribs.get("a_Normal"));
		GLES20.glVertexAttribPointer(material.attribs.get("a_Normal"), 3, GLES20.GL_FLOAT, false,
				0, 0);


		GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, 0);

		Matrix.multiplyMM(lMVPMatrix, 0, pViewMatrix, 0, mMatrix, 0);
		GLES20.glUniformMatrix4fv(material.uniforms.get("u_MVMatrix"), 1, false, lMVPMatrix, 0);

		Matrix.multiplyMM(lMVPMatrix, 0, pProjectionMatrix, 0, lMVPMatrix, 0);
		GLES20.glUniformMatrix4fv(material.uniforms.get("u_MVPMatrix"), 1, false, lMVPMatrix, 0);

		GLES20.glUniform3f(material.uniforms.get("u_LightPosition"), pLightPosInEyeSpace[0], pLightPosInEyeSpace[1], pLightPosInEyeSpace[2]);

		GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, 36);
	}
}
