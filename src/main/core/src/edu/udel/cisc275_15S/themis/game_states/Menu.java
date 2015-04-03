package edu.udel.cisc275_15S.themis.game_states;

import java.io.FileNotFoundException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;

import edu.udel.cisc275_15S.themis.Themis;
import edu.udel.cisc275_15S.themis.handlers.GameStateHandler;
import edu.udel.cisc275_15S.themis.handlers.TouchInputHandler;
import edu.udel.cisc275_15S.themis.interactables.Buttons;

public class Menu extends GameState {

		private Texture bg = new Texture(Gdx.files.internal("gfx/themismenubg.jpg"));
		private Texture ud = new Texture(Gdx.files.internal("gfx/themis.png"));
		private int srcX = 0;
		private Buttons play;

		Texture right = new Texture("Sprites/right.png");

		float statetime = 0f;
		TextureRegion currentFrame;

		public Menu(GameStateHandler gsh) {
			super(gsh);
			play = new Buttons("Play", 240, 100);
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
			play.update(dt);
			moveBg();
			
		}
		public void moveBg() {
			if (srcX == bg.getWidth()-666) {
				srcX = 0;
			} else srcX++;
		}
		@Override
		public void render() {
			Gdx.gl.glClearColor(1, 1, 1, 1); 
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); 
			sb.setProjectionMatrix(cam.combined);
//			draw the background using scale
//			draw the play button
			sb.begin();
//			temporary bg
			sb.draw(bg, 0, 0, srcX, 0, 480, 320);
			sb.draw(ud, 0, 0);
			sb.end();
			
			TextureRegion[][] tmp2 = TextureRegion.split(right, right.getWidth()/10,right.getHeight());
			TextureRegion[] walkright = new TextureRegion[10];
			for (int i = 0; i < 10; i++) {
				walkright[i] = tmp2[0][i];
			}

			
			Animation wright = new Animation(1/12f, walkright);
	        currentFrame = wright.getKeyFrame(statetime, true);
	        statetime += Gdx.graphics.getDeltaTime();
	        sb.begin();
	        sb.draw(currentFrame, Themis.WIDTH/2, 15);             
	        sb.end();
			
			play.render(sb);
		}

		@Override
		public void dispose() {
			// TODO Auto-generated method stub
			
		}
}
