package edu.udel.cisc275_15S.themis.game_states;

import com.badlogic.gdx.graphics.OrthographicCamera;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import edu.udel.cisc275_15S.themis.Themis;
import edu.udel.cisc275_15S.themis.handlers.GameStateHandler;
import edu.udel.cisc275_15S.themis.handlers.MainCamera;

public abstract class GameState {
	
	protected GameStateHandler gsh;
	protected Themis game;
	
	protected SpriteBatch sb;
	protected MainCamera cam;
	protected OrthographicCamera hudCam;
	
	protected GameState(GameStateHandler gsh) {
		this.gsh = gsh;
		game = gsh.game();
		sb = game.getSpriteBatch();
		cam = game.getCamera();
		hudCam = game.getHUDCamera();
	}
	
	public abstract void handleInput();
	public abstract void update(float dt);
	public abstract void render();
	public abstract void dispose();
	
}
	

