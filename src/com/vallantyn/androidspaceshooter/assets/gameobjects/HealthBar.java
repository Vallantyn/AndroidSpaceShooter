package com.vallantyn.androidspaceshooter.assets.gameobjects;

import com.vallantyn.androidspaceshooter.assets.behaviours.HealthBehaviour;

import java.util.Stack;

import engine.GameObject;

/**
 * Created by Julien on 09/07/13.
 */
public class HealthBar extends GameObject
{
	public HealthBar(Ship ship)
	{
		addBehaviour(new HealthBehaviour(ship));
	}
}
