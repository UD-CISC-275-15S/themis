package edu.udel.cisc275_15S.themis.interactables;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Button;

import edu.udel.cisc275_15S.themis.Themis;
import edu.udel.cisc275_15S.themis.handlers.TouchInputHandler;

public class Buttons extends Button implements Interactable {
	protected float x;
	protected float y;
	protected float width;
	protected float height;
	protected Texture image;
	protected boolean clicked = false;
	protected String string;
	protected Texture bgImage = new Texture(Gdx.files.internal("gfx/textbox.gif"));
	
	public Buttons(Texture image, float x, float y) {
		this.image = image;
		this.x = x;
		this.y = y;
		width = image.getWidth();
		height = image.getHeight();
		System.out.println("This button is at "+x+" "+width+", "+y+" "+height);
	}
	
	public Buttons(String string, float x, float y) {
		this.string = string;
		this.y = y;
		this.x = x;

		int len = string.length();
//		Temporary height and widths for TextButtons
		width = 30;
		height = 30;
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
		if (Gdx.input.justTouched() && TouchInputHandler.isWithinBounds(x, y, width, Themis.HEIGHT - height)){
			clicked = !clicked;
			System.out.println("Button center is located at: " + x + "," + y);
		}
	}
	public Texture getBGImage(){return bgImage;}
	public boolean isDown() { return clicked; }
	public float getX() {return x;}
	public float getY() {return y;}
	public Texture getImage() {return image;}
	public String toString() { return string;}
	public void dispose() { if (image != null) image.dispose();}

}
