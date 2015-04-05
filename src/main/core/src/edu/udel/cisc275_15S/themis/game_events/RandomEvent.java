package edu.udel.cisc275_15S.themis.game_events;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import edu.udel.cisc275_15S.themis.game_entities.Player;

public class RandomEvent extends Event{
	
		public RandomEvent(Player player, boolean valid, int animator, String name, Texture Avatar) {
		super(player, valid, animator, name, Avatar);
	}

		@Override
		public void update() {
			// AUTO Auto-generated method stub
			
		}

		@Override
		public void render(SpriteBatch sb, OrthographicCamera cam) {
			// AUTO Auto-generated method stub
			
		}

		@Override
		public void handleinput() {
			// AUTO Auto-generated method stub
			
		}

		@Override
		public void dispose() {
			// AUTO Auto-generated method stub
			
		}
}