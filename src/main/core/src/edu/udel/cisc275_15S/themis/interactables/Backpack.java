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
	
	private boolean opened;
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
			sb.draw(super.getBGImage(), 50f, 82f,400,230);
			sb.setColor(1.0f, 1.0f, 1.0f, 1.0f);
			if(Gdx.input.justTouched()){
				updateCurrText();
			}
			for(int i=0;i<currText.size;i++){
				bmf.draw(sb, currText.get(i), 60, (Themis.HEIGHT-15)-((13)*i));
			}
			sb.end();
		}
	}

	public void updateCurrText(){
		currText.clear();
		for(int i=0;i<16;i++){
			currText.add(text.peek());
			text.add(text.remove());
		}
	}
	public Queue<String> getText(){return text;}
}
