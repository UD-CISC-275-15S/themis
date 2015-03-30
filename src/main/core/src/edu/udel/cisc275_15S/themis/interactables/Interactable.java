package edu.udel.cisc275_15S.themis.interactables;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface Interactable {
	public void update(float dt);
	public void handleInput();
	public void render(SpriteBatch sb);
}
