package edu.udel.cisc275_15S.themis.handlers;

import com.badlogic.gdx.utils.Array;

import edu.udel.cisc275_15S.themis.Themis;
import edu.udel.cisc275_15S.themis.game_entities.NPC;
import edu.udel.cisc275_15S.themis.game_entities.Player;
import edu.udel.cisc275_15S.themis.game_states.GameState;
import edu.udel.cisc275_15S.themis.game_states.Play;

//		Used to handle interactions between a Player and NPC
//		Since we are basically using the play state as our entire game, update the play state

public class CharacterInteractionHandler {
	public Player player;
	public Array<NPC> npcs;
	
	public CharacterInteractionHandler(Player player, Array<NPC> npcs) {
		player = player;
		npcs = npcs;
	}
	public void update(float dt) {
//		if player posX && Y within talking distance of NPC
		for (NPC npc: npcs) {
//			if (
//			player posX - NPC posX <= absValue of defined constant &&
//			player posY - NPC posY <= absValue of defined constant && 
//			TouchInputHandler.isClicked() &&
//				Player clicks on NPC 
//			TouchInputHandler.x == NPC posX &&
//			TouchInputHandler.y == NPC posY &&
//			NPC.getEvent() != null)
//			perform some action/NPC.getEvent().loadEvent()
		}
	}
	public void render(){
//		use this method to draw dialogue
	}
}
