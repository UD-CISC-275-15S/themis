package edu.udel.cisc275_15S.themis.handlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import edu.udel.cisc275_15S.themis.Themis;

// Basically does what the Gdx Touch Input Handler does

public class TouchInputHandler { 

	public static Boolean down = false;
	//	x, y pos of where the screen has been touched
	public static float x;
	public static float y;
	public static boolean isClicked() { return down;}

	public static void update() {
		if(Gdx.input.justTouched()){ // checks if the mouse button is currently held down, use justTouched for one click
			down = true;
			//Testing touch
			x = Gdx.input.getX();
			y = Gdx.input.getY();
			System.out.println("down at " + x + " " + (Themis.HEIGHT - y));
		} else down = false; 
		if(Gdx.input.isTouched()){ // checks if the mouse button is currently held down, use justTouched for one click
			down = true;
			x = Gdx.input.getX();
			y = Gdx.input.getY();
		} else down = false; 
	}
	public static boolean isWithinBounds(float x1, float y1, float width, float height){
		Rectangle cursor = new Rectangle(TouchInputHandler.x - 15, TouchInputHandler.y - 15, 15, 15);
		Rectangle button = new Rectangle(x1 - 10, Themis.HEIGHT - y1 - 10, width - 20, height);
	    if (Intersector.overlaps(cursor, button)) {
	        return true;
	    } else return false;
	}
}
