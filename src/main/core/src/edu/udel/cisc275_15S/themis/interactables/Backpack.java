package edu.udel.cisc275_15S.themis.interactables;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//Technically a button on our HUD with a different update and render method
//Doesn't actually need to implement Interactable but am leaving it alone atm
import com.badlogic.gdx.utils.Array;

public class Backpack extends Buttons implements Interactable {
	
	private Array<item> items;
	public Backpack(Texture image, float x, float y) {
		super(image, x, y);
	}

	@Override
	public void handleInput() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(SpriteBatch sb) {
		super.render(sb);		
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
