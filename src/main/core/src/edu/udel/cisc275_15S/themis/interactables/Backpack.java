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
	private Texture bgImage = new Texture(Gdx.files.internal("gfx/textbox.gif"));
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

	@Override
	public void handleInput() {
		if (isDown() && TouchInputHandler.isWithinBounds(getX()-32, getX()+32, getY()-32, getY()+32)){
			opened = true;
		}
	}

	@Override
	public void update(float dt) {
		handleInput();
		
	}

	@Override
	public void render(SpriteBatch sb) {
		super.render(sb);
		if (opened) {
			sb.begin();
			sb.draw(bgImage, getX(), getY() + 32f);
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
