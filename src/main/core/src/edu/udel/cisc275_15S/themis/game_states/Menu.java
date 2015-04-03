package edu.udel.cisc275_15S.themis.game_states;

import java.io.FileNotFoundException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.physics.box2d.World;

import edu.udel.cisc275_15S.themis.handlers.GameStateHandler;
import edu.udel.cisc275_15S.themis.interactables.Buttons;

public class Menu extends GameState {

		private Texture bg = new Texture(Gdx.files.internal("failure.jpg"));
		private Buttons play;

		public Menu(GameStateHandler gsh) {
			super(gsh);
			play = new Buttons("Play", 240, 160);
		}

		@Override
		public void handleInput() {
			
			if(play.isDown()) {
//				implement some sound here
				try {
					gsh.setState(GameStateHandler.PLAY);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		@Override
		public void update(float dt) {
			
			handleInput();
//			world.step(dt / 5, 8, 3);
//			
			play.update(dt);
			
		}

		@Override
		public void render() {
			
			sb.setProjectionMatrix(cam.combined);
//			draw the background using scale
//			sb.draw(bg, 0, 0, Themis.WIDTH, Themis.HEIGHT, Themis.SCALE, Themis.SCALE);
//			draw the play button
			sb.begin();
//			temporary bg
			sb.draw(bg, 0, 0);
			sb.end();
			play.render(sb);
		}

		@Override
		public void dispose() {
			// TODO Auto-generated method stub
			
		}
}
