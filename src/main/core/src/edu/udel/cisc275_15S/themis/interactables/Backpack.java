package edu.udel.cisc275_15S.themis.interactables;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.Array;

import edu.udel.cisc275_15S.themis.Data;
import edu.udel.cisc275_15S.themis.Themis;
//Technically a button on our HUD with a different update and render method
//Doesn't actually need to implement Interactable but am leaving it alone atm



public class Backpack extends Buttons implements Interactable {
	
	private boolean opened = false;
	private Queue<String> text;
	private Array<String> currText;
	private BitmapFont bmf;
	public boolean isOpen() {
		return opened;
	}

	public void setOpened(boolean open) {
		this.opened = open;
	}

	public Backpack(Texture image, float x, float y) {
		super(image, x, y);
		currText = new Array<String>();
		text = new LinkedList<String>();
		try{
		Data.ReadHandbook(this);
		}
		catch(IOException e){
			e.printStackTrace();
		}
		bmf = Themis.getBitmapFont();
		updateCurrText();
	}

//	I've commented this out because the backpack input logic should be the same as every other button
//	public void handleInput() {
//		if (isDown() && TouchInputHandler.isWithinBounds(getX()-32, getX()+32, getY()-32, getY()+32)){
//			opened = true;
//		}
//	}

	public void update(float dt) {
        dt = Gdx.graphics.getDeltaTime();
		handleInput(); 
		opened = clicked;	
	}

	@Override
	public void render(SpriteBatch sb) {
		super.render(sb);
		if (opened) {
			sb.begin();
			sb.setColor(1.0f, 1.0f, 1.0f, .5f);
			sb.draw(super.getBGImage(), 480f - getX(), getY() + 32f);
			sb.setColor(1.0f, 1.0f, 1.0f, 1.0f);
			for(int i=0;i<10;i++){
				bmf.draw(sb, currText.get(i), 50, (Themis.HEIGHT-50)-((15)*i));
			}
			if(Gdx.input.justTouched()){
				updateCurrText();
			}
			sb.end();
		}
	}

	public void updateCurrText(){
		for(int i=0;i<10;i++){
			currText.insert(i, text.peek());
			text.add(text.remove());
		}
	}
	public Queue<String> getText(){return text;}
}
