package edu.udel.cisc275_15S.themis.game_events;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import edu.udel.cisc275_15S.themis.game_entities.Player;

//	A ScriptedEvent is an Event which is hardcoded into the game. 
//	A ScripedEvent has conditions which must be fulfilled in ordered to be listed has completed 
// These conditions can vary from talking to a specific NPC, or to going to a certain location

public class ScriptedEvent extends Event{

	public ScriptedEvent(Player player, boolean valid, int animator, String name, Texture Avatar) {
		super(player, valid, animator, name, Avatar);
	}

	@Override
	public void update() {
		// AUTO Auto-generated method stub
		
	}

	@Override
	public void render(SpriteBatch sb) {
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

//	public void loadEvent()
//	Modify an NPC(s) to have their condition turned to true
//	
}
