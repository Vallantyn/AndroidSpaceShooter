package com.vallantyn.androidspaceshooter.assets.scenes;

import android.graphics.Color;

import com.vallantyn.androidspaceshooter.assets.behaviours.StarFieldBehaviour;
import com.vallantyn.androidspaceshooter.assets.gameobjects.Ship;

import engine.GameObject;
import engine.Scene;
import engine.Screen;
import engine.Vector2;

public class SampleScene
		extends Scene
{
	public SampleScene ()
	{
		color = Color.BLACK;

		GameObject starField = new GameObject();
		starField.addBehaviour(new StarFieldBehaviour());
		addChild(starField);

//		Ship ship = new Ship();
//		addChild(ship);
	}
}
