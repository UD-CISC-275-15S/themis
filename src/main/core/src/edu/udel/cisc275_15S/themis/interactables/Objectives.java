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
			sb.draw(bgImage, 32, getY() + 32f);
			sb.setColor(1.0f, 1.0f, 1.0f, 1.0f);

			sb.end();
		}
		
	}

}
