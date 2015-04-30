package edu.udel.cisc275_15S.themis.game_entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import edu.udel.cisc275_15S.themis.interactables.Backpack;
import edu.udel.cisc275_15S.themis.interactables.Objectives;
import edu.udel.cisc275_15S.themis.interactables.Online;
import edu.udel.cisc275_15S.themis.interactables.UDMail;
import edu.udel.cisc275_15S.themis.interactables.UDSIS;

// This class draws the players backpack, Objectives, and UDSIS

public class HUD {
	
	private Player player;
	private Backpack bag;
	private Objectives obj;
	private UDSIS udsis;
	private UDMail email;
	private Online online;
	
	public HUD(Player player) {
		this.player = player;
		bag = player.getBag();
		obj = player.getObjButton();
		online = player.getOnline();
		udsis = player.getUDSIS();
		email = player.getEmail();
	}

	public void update(float dt) {
//		So that the interfaces don't open over each other
		if (obj.isOpen() && !bag.isOpen() && !online.isOpen()) {
			obj.update(dt);
		}
		else if (!obj.isOpen() && bag.isOpen() && !online.isOpen()) {
			bag.update(dt);
		}
		else if (!obj.isOpen() && !bag.isOpen() && online.isOpen()){
			online.update(dt);
			udsis.update(dt);
			email.update(dt);
			
		}
		else {
			obj.update(dt);
			bag.update(dt);
			online.update(dt);
			udsis.update(dt);
			email.update(dt);
			
		}
	}
	
	public void render(SpriteBatch sb){
		bag.render(sb);
		obj.render(sb);
		online.render(sb);		
	}
	
}
