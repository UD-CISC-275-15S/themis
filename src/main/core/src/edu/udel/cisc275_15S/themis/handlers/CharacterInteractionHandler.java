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
	
	public CharacterInteractionHandler(Play gs) {
		player = gs.getPlayer();
		npcs = gs.getNPCS();
	}
	public void update(float dt) {
		touchHandler();
//		if player posX && Y within talking distance of NPC
//		for (NPC npc: npcs) {
//			if (
//			player posX - NPC posX <= absValue of defined constant &&
//			player posY - NPC posY <= absValue of defined constant && 
//			TouchInputHandler.isClicked() &&
//				Player clicks on NPC 
//			TouchInputHandler.x == NPC posX &&
//			TouchInputHandler.y == NPC posY &&
//			NPC.getEvent() != null)
//			perform some action/NPC.getEvent().loadEvent()
//		}
	}
	public void touchHandler() {
		float UpperBound = Themis.HEIGHT/4;
		float LowerBound = Themis.HEIGHT - Themis.HEIGHT/4;
		float LeftBound = Themis.WIDTH/3;
		float RightBound = Themis.WIDTH - Themis.WIDTH/3;
		if (TouchInputHandler.isClicked()) {
//				&&
//				TouchInputHandler.x == x &&
//				TouchInputHandler.y == y) {
//			for testing purposes
			System.out.println("you are at screen cord: " + player.getXpos() + " , " + player.getYpos());
			
			if (TouchInputHandler.x > Themis.WIDTH/2 && TouchInputHandler.y > UpperBound && TouchInputHandler.y < LowerBound) 
			{ player.setX(player.getXpos()+5); player.setDir("right");}
			if (TouchInputHandler.x < Themis.WIDTH/2 && TouchInputHandler.y > UpperBound && TouchInputHandler.y < LowerBound) 
			{ player.setX(player.getXpos()-5); player.setDir("left");}
			if (TouchInputHandler.y < Themis.HEIGHT/2 && TouchInputHandler.x < RightBound && TouchInputHandler.x > LeftBound) 
			{ player.setY(player.getYpos()+5); player.setDir("up");}
			if (TouchInputHandler.y > Themis.HEIGHT/2 && TouchInputHandler.x < RightBound && TouchInputHandler.x > LeftBound)
			{ player.setY(player.getYpos()-5);; player.setDir("down");}
			
			}
		
	}
	public void render(){
//		use this method to draw dialogue
	}
}
