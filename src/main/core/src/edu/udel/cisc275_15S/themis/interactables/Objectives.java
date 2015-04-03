package edu.udel.cisc275_15S.themis.interactables;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//		Technically a button on our HUD with a different update and render method
//		Doesn't actually need to implement Interactable but am leaving it alone atm
public class Objectives extends Buttons implements Interactable {

	public Objectives(Texture image, float x, float y) {
		super(image, x, y);
		// TODO Auto-generated constructor stub
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

}
