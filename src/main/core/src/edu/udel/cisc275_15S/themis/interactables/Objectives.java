package edu.udel.cisc275_15S.themis.interactables;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import edu.udel.cisc275_15S.themis.handlers.TouchInputHandler;
//		Technically a button on our HUD with a different update and render method
//		Doesn't actually need to implement Interactable but am leaving it alone atm
public class Objectives extends Buttons implements Interactable {

	
	private Texture bgImage = new Texture(Gdx.files.internal("gfx/textbox.gif"));
	private boolean opened = false;
	public boolean isOpen() {
		return opened;
	}

	public void setOpened(boolean open) {
		this.opened = open;
	}

	public Objectives(Texture image, float x, float y) {
		super(image, x, y);
		// TODO Auto-generated constructor stub
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

}
