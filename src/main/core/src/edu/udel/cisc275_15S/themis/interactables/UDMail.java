package edu.udel.cisc275_15S.themis.interactables;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//Technically a button on our HUD with a different update and render method
//Doesn't actually need to implement Interactable but am leaving it alone atm

public class UDMail extends Buttons implements Interactable {

	private boolean opened = false;
	private Buttons back;
	public boolean isOpen(){
		return opened;
	}
	
	public UDMail(Texture image, float x, float y) {
		super(image, x, y);
	}

	@Override
	public void handleInput() {
		// AUTO Auto-generated method stub
		
	}

	@Override
	public void update(float dt) {
		// AUTO Auto-generated method stub
		
	}

	@Override
	public void render(SpriteBatch sb) {
		sb.begin();
		sb.draw(image, x-width/2, y-height/2);
		sb.end();
		if (opened){
			
			
		}
	}
}
