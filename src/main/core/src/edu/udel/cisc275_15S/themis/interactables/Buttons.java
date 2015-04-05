package edu.udel.cisc275_15S.themis.interactables;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import edu.udel.cisc275_15S.themis.Themis;
import edu.udel.cisc275_15S.themis.handlers.TouchInputHandler;

public class Buttons implements Interactable {
//	Button center at x,y
	protected float x;
	protected float y;
//	Width and height of the button
	protected float width;
	protected float height;
//	Button image
	protected Texture image;
//	Has the button been pushed
	protected boolean clicked = false;
//	Used if the button is just a string (may be used for dialogues or arbitrary events) and not an image from our res folder
	protected String string;
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
//		Trying to prevent the button from being updating several times in one click
        dt = Gdx.graphics.getDeltaTime();
        if (dt > .016) {
		handleInput(); }

	}

	@Override
	public void handleInput() {
		if (TouchInputHandler.isClicked() && TouchInputHandler.isWithinBounds(x, y, width-30, Themis.HEIGHT - height)){
			clicked = !clicked;
			System.out.println("Button center is located at: " + x + "," + y);
		}
	}
	public boolean isDown() { return clicked; }
	public float getX() {return x;}
	public float getY() {return y;}
	public Texture getImage() {return image;}

}
