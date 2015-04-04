package edu.udel.cisc275_15S.themis.interactables;


import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

import edu.udel.cisc275_15S.themis.handlers.TouchInputHandler;

public class Buttons implements Interactable {
//	Button center at x,y
	private float x;
	private float y;
//	Width and height of the button
	private float width;
	private float height;
//	Button image
	private Texture image;
//	Has the button been pushed
	private boolean clicked = false;
//	Used if the button is just a string (may be used for dialogues or arbitrary events) and not an image from our res folder
	private String string;
	Vector3 vec;
	private OrthographicCamera cam;

	public Buttons(Texture image, float x, float y) {
		this.image = image;
		this.x = x;
		this.y = y;
		width = image.getWidth();
		height = image.getHeight();
		vec = new Vector3();
	}
	public Buttons(String string, float x, float y) {
		this.string = string;
		this.x = x;
		this.y = y;
		int len = string.length();
//		Temporary height and widths for TextButtons
		width = len * 10;
		height = len * 5;
		vec = new Vector3();
	}
	public void render(SpriteBatch sb) {
		sb.begin();
//		If TextButton
		if (string!=null) {
			BitmapFont TextButton = new BitmapFont();
			TextButton.draw(sb, string, x, y);
//			If the button is an image
		} else 
			sb.draw(image, x-width/2, y-height/2);
		sb.end();
	}
	
	@Override
	public void update(float dt) {
		handleInput();
	}

	@Override
	public void handleInput() {
		if (TouchInputHandler.isClicked()) { 
//				&&
//			Check if the area clicked is where equivalent to the buttons 
//			Not quite working yet...
//				TouchInputHandler.x  > x - width / 2 && TouchInputHandler.x  < x + width / 2 &&
//				TouchInputHandler.y > y - height / 2 && TouchInputHandler.y < y + height / 2) {
			clicked = true;
//			testing TextButton 
			System.out.println("Button center is located at: " + x + "," + y);
		} else {
			clicked = false;
		}
	}
	
	public boolean isDown() { return clicked; }
	public float getX() {return x;}
	public float getY() {return y;}
	public Texture getImage() {return image;}

}
