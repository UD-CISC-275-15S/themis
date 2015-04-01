package edu.udel.cisc275_15S.themis.game_entities;
//	NPC's are nonplayable characters which can be interacted with 
//	may have Dialogue or trigger and Event
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import edu.udel.cisc275_15S.themis.game_events.Event;

public class NPC extends Character {
//	Is the current NPC on the map?
//	Useful for iterating across an array of NPCs, and having them move around randomly if we want to put some on the map.
	
	private boolean onMap; 
//	Used to determine whether an NPC can be interacted with and start/complete their Event
	private boolean condition;
	private Event event;

	public NPC(TextureRegion[] sprite, float x, float y, int dir, String name, Event event) {
		super(sprite, x, y, dir, name);
		// TODO Auto-generated constructor stub
	}
	public Event getEvent() { return event;}
	
//	Method to have an NPC move randomly
	private void randMovement() {
//		if (rand generator == gdx.getDeltaTime() % someINT
//		update X or Y
	};
}
