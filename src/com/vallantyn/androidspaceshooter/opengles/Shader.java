package com.vallantyn.androidspaceshooter.opengles;

import android.content.Context;
import android.opengl.GLES20;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;

public class Shader
{
	public final String vertex
			          , fragment;
	public Hashtable<String, Integer> uniforms = new Hashtable<String, Integer>();
	public Hashtable<String, Integer> attribs  = new Hashtable<String, Integer>();
	private int vertexHandle, fragmentHandle, programHandle;

	public Shader (Context cx, String name)
	{
		vertexHandle = GLES20.glCreateShader(GLES20.GL_VERTEX_SHADER);
		fragmentHandle = GLES20.glCreateShader(GLES20.GL_FRAGMENT_SHADER);

		vertex = get(cx, name + ".vert");
		fragment = get(cx, name + ".frag");

		compile(vertexHandle, vertex);
		compile(fragmentHandle, fragment);

		createProgram();
	}

	private String get (Context cx, String name)
	{
		String out = null;
		try
		{
			InputStream is = cx.getAssets().open("shaders/" + name);

			int size = is.available();
			byte[] buffer = new byte[size];
			is.read(buffer);
			is.close();

			out = new String(buffer);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		return out;
	}

	private void compile (int handle, String shader)
	{
		if (handle != 0)
		{
			GLES20.glShaderSource(handle, shader);
			GLES20.glCompileShader(handle);

			final int[] compileStatus = new int[1];
			GLES20.glGetShaderiv(handle, GLES20.GL_COMPILE_STATUS, compileStatus, 0);

			if (compileStatus[0] == 0)
			{
				Log.e("SHADER", "Error compiling shader: " + GLES20.glGetShaderInfoLog(handle));
				GLES20.glDeleteShader(handle);
				handle = 0;
			}
		}
		if (handle == 0) throw new RuntimeException("Error creating Shader");
	}

	private void createProgram ()
	{
		programHandle = GLES20.glCreateProgram();

		if (programHandle != 0)
		{
			GLES20.glAttachShader(programHandle, vertexHandle);
			GLES20.glAttachShader(programHandle, fragmentHandle);

			GLES20.glLinkProgram(programHandle);

			final int[] linkStatus = new int[1];
			GLES20.glGetProgramiv(programHandle, GLES20.GL_LINK_STATUS, linkStatus, 0);

			if (linkStatus[0] == 0)
			{
				Log.e("SHADER", "Error compiling program: " + GLES20.glGetProgramInfoLog(programHandle));
				GLES20.glDeleteProgram(programHandle);
				programHandle = 0;
			}
		}

		if (programHandle == 0) throw new RuntimeException("Error creating program.");
	}

	public void setUniforms (String[] uniforms)
	{
		for (String uniform : uniforms)
		{
			this.uniforms.put(uniform, GLES20.glGetUniformLocation(programHandle, uniform));
		}
	}

	public void setAttributes (String[] attributes)
	{
		int i = 0;
		for (String attrib : attributes)
		{
			GLES20.glBindAttribLocation(programHandle, i++, attrib);

			this.attribs.put(attrib, GLES20.glGetAttribLocation(programHandle, attrib));
		}
	}

	public int getProgramHandle ()
	{
		return programHandle;
	}
}