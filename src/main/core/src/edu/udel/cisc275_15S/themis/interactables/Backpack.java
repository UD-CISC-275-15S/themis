package edu.udel.cisc275_15S.themis.interactables;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//Technically a button on our HUD with a different update and render method
//Doesn't actually need to implement Interactable but am leaving it alone atm
import com.badlogic.gdx.utils.Array;

import edu.udel.cisc275_15S.themis.handlers.TouchInputHandler;

public class Backpack extends Buttons implements Interactable {
	
	private Array<item> items;
	private boolean opened = false;
	public boolean isOpen() {
		return opened;
	}

	public void setOpened(boolean open) {
		this.opened = open;
	}

	public Backpack(Texture image, float x, float y) {
		super(image, x, y);
	}

//	I've commented this out because the backpack input logic should be the same as every other button
//	public void handleInput() {
//		if (isDown() && TouchInputHandler.isWithinBounds(getX()-32, getX()+32, getY()-32, getY()+32)){
//			opened = true;
//		}
//	}

	public void update(float dt) {
        dt = Gdx.graphics.getDeltaTime();
        if (dt < .016) {
		handleInput(); }
		opened = clicked;
		
	}

	@Override
	public void render(SpriteBatch sb) {
		super.render(sb);
		if (opened) {
			sb.begin();
			sb.setColor(1.0f, 1.0f, 1.0f, .5f);
			sb.draw(super.getBGImage(), getX(), getY() + 32f);
			sb.setColor(1.0f, 1.0f, 1.0f, 1.0f);
			sb.end();
		}
	}
	public void add(item item) {
//		add an item to the bag
//		items.add(item);
	}
	public void remove(item item){
//		remove an item from the bag
//		items.remove(item);
	}
}
