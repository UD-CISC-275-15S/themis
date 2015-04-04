package edu.udel.cisc275_15S.themis.handlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;

// Basically does what the Gdx Touch Input Handler does

public class TouchInputHandler { 
	
	public static Boolean down = false;
//	x, y pos of where the screen has been touched
	public static float x;
	public static float y;
	public static boolean isClicked() {
		return down;
	}
   
	public static void update() {
	    if(Gdx.input.isTouched()){ // checks if the mouse button is currently held down, use justTouched for one click
	    	down = true;
//	    	Testing touch
	        x = Gdx.input.getX();
	        y = Gdx.input.getY();
			System.out.println("down at " + x + " " + y);
	    	} else down = false; 
	   	}
	
}
