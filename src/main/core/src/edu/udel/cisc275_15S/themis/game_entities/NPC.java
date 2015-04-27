package edu.udel.cisc275_15S.themis.game_entities;
//	NPC's are nonplayable characters which can be interacted with 
//	may have multiple dialogues, a quiz, or a quest. 

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import edu.udel.cisc275_15S.themis.game_events.Event;

public class NPC extends Character {
	private boolean onMap; 
	private boolean condition;//Used to determine whether an NPC can be interacted with and start/complete their Event
	private ArrayList<Event> event;

	public NPC() {
		
	}
	public NPC(TextureRegion[] sprite, float x, float y, int dir, String name, ArrayList<Event> event) {
		super(sprite, x, y, dir, name);
	}
	public ArrayList<Event> getEvent() { return event;}
	
//	Method to have an NPC move randomly
	private void randMovement() {
		float accum = 0;
		accum += Gdx.graphics.getDeltaTime();
		if (accum >= 3) {
		accum = 0;
		}
	};
}
