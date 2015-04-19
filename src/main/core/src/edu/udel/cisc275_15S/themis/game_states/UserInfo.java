package edu.udel.cisc275_15S.themis.game_states;

import java.io.FileNotFoundException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import edu.udel.cisc275_15S.themis.handlers.GameStateHandler;
import edu.udel.cisc275_15S.themis.handlers.TextInputHandler;
import edu.udel.cisc275_15S.themis.interactables.Buttons;

public class UserInfo extends GameState {
		
		String name;
		String ID;
		String college;
		
		private Texture bg = new Texture(Gdx.files.internal("gfx/userinfobg.png"));
		TextInputHandler listener = new TextInputHandler();

		private Buttons start;

		public UserInfo(GameStateHandler gsh) {
			super(gsh);
			start = new Buttons("Start", 200, 70);
			Gdx.input.getTextInput(listener, "Enter First Name", "", "Your first name here.");
			
		}

		
		@Override
		public void handleInput() {
			
			if(start.isDown()) {
//				implement some sound here
				try {
					gsh.setState(GameStateHandler.PLAY);
					bg.dispose();
					start.dispose();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		}

		@Override
		public void update(float dt) {
			handleInput();
			start.update(dt);
		}

		@Override
		public void render() {
			Gdx.gl.glClearColor(1, 1, 1, 1); 
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); 
			sb.setProjectionMatrix(cam.combined);
			sb.begin();
			sb.draw(bg, 0, 0, 480, 320);
			sb.end();
			
			start.render(sb);
		}

		@Override
		public void dispose() {
			// AUTO Auto-generated method stub
			
		}
}
