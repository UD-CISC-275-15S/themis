package edu.udel.cisc275_15S.themis.interactables;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//Technically a button on our HUD with a different update and render method
//Doesn't actually need to implement Interactable but am leaving it alone atm

public class Online extends Buttons implements Interactable {

	private boolean opened = false;
	private Buttons back;
	
	
	 
	public boolean isOpen(){
		return opened;
	}
	
	
	public Online(Texture image, float x, float y) {
		super(image, x, y);
	}

	@Override
	public void handleInput() {
		// AUTO Auto-generated method stub
		
	}

	@Override
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
			System.out.println("opening browser");
			sb.begin();
			sb.end();
		}
	}

}
