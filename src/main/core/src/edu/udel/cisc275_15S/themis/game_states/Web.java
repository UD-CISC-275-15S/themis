package edu.udel.cisc275_15S.themis.game_states;

import java.io.FileNotFoundException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import edu.udel.cisc275_15S.themis.Themis;
import edu.udel.cisc275_15S.themis.game_events.Quiz;
import edu.udel.cisc275_15S.themis.handlers.GameStateHandler;
import edu.udel.cisc275_15S.themis.interactables.Buttons;
public class Web extends GameState {

	private Buttons UDSIS;
	private Buttons UDMail;
	private Buttons back;
	private Texture email = new Texture(Gdx.files.internal("Button/email_default.png"));
	private Texture sis = new Texture(Gdx.files.internal("Button/UDSIS.png"));
	private Texture bg = new Texture(Gdx.files.internal("Button/Untitled.png"));
	
	
	
	public Web(GameStateHandler gsh) throws FileNotFoundException {
		super(gsh);
		UDSIS = new Buttons(sis, 150, 150);
		UDMail = new Buttons(email, 200, 150);
		back = new Buttons("back", 50, 50);
		
		
	}

	@Override
	public void handleInput() {
		if (UDSIS.isDown()){
			
		}
		else if (UDMail.isDown()){
			
		}
		
		else if (back.isDown()){
			try {
				gsh.setState(GameStateHandler.PLAY);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void update(float dt) {
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 1, 1, 1); 
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); 
		sb.setProjectionMatrix(cam.combined);
		sb.begin();
		sb.draw(bg, 0, 0);
		sb.end();
	}

	@Override
	public void dispose() {
		// AUTO Auto-generated method stub

	}

}
