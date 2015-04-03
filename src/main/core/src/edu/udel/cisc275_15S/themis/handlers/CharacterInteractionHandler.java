package edu.udel.cisc275_15S.themis.handlers;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

import edu.udel.cisc275_15S.themis.Themis;
import edu.udel.cisc275_15S.themis.game_entities.NPC;
import edu.udel.cisc275_15S.themis.game_entities.Player;
import edu.udel.cisc275_15S.themis.game_states.Play;
import edu.udel.cisc275_15S.themis.interactables.Buttons;

//		Used to handle interactions between a Player and NPC
//		Since we are basically using the play state as our entire game, update the play state

public class CharacterInteractionHandler {
//	how many spaces the character moves
	public static final int MOVE = 2;
	public Player player;
	public Array<NPC> npcs;
//	Tiles that the player cant pass through
	public Play play;
	private BitmapFont font;

	
	public CharacterInteractionHandler(Play gs) {

		player = gs.getPlayer();
		npcs = gs.getNPCS();
		play = gs;
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
		
		if (TouchInputHandler.isClicked()) 
		{
//			for testing purposes
			System.out.println("you are at screen cord: " + player.getXpos() + " , " + player.getYpos());
			
			if (checkright()) { player.setX(player.getXpos()+MOVE); player.setDir("right");}
			if (checkleft()) { player.setX(player.getXpos()-MOVE); player.setDir("left");}
			if (checkup()) { player.setY(player.getYpos()+MOVE); player.setDir("up");}
			if (checkdown()){ player.setY(player.getYpos()-MOVE);; player.setDir("down");}
			
			}
		
	}
	public boolean checkleft() {
		float UpperBound = Themis.HEIGHT/4;
		float LowerBound = Themis.HEIGHT - Themis.HEIGHT/4;
		
		Rectangle rect = new Rectangle(player.getXpos()-MOVE, player.getYpos(), 20, 20);
//		Check if there is a collision if the player were to move one step to the left
		return 
				!Collision(rect) && 
				player.getXpos() -10 >= 0.0 && TouchInputHandler.x < Themis.WIDTH/2 && TouchInputHandler.y > UpperBound && TouchInputHandler.y < LowerBound;
	}
	public boolean checkright() {
		float UpperBound = Themis.HEIGHT/4;
		float LowerBound = Themis.HEIGHT - Themis.HEIGHT/4;
		
		Rectangle rect = new Rectangle(player.getXpos()+MOVE, player.getYpos(), 20, 20);
//		Check if there is a collision if the player were to move one step to the right
		return 
				!Collision(rect) && 
				player.getXpos()+10 < 1000 && TouchInputHandler.x > Themis.WIDTH/2 && TouchInputHandler.y > UpperBound && TouchInputHandler.y < LowerBound;
	}
	public boolean checkup() {
		float LeftBound = Themis.WIDTH/3;
		float RightBound = Themis.WIDTH - Themis.WIDTH/3;
		
		Rectangle rect = new Rectangle(player.getXpos(), player.getYpos()+MOVE, 20, 20);
//		Check if there is a collision if the player were to move one step to the up
		return 
				!Collision(rect) && 
				player.getYpos() < 1000 && TouchInputHandler.y < Themis.HEIGHT/2 && TouchInputHandler.x < RightBound && TouchInputHandler.x > LeftBound;
	}
	public boolean checkdown() {
		float LeftBound = Themis.WIDTH/3;
		float RightBound = Themis.WIDTH - Themis.WIDTH/3;
		
		Rectangle rect = new Rectangle(player.getXpos(), player.getYpos()-MOVE, 20, 20);
//		Check if there is a collision if the player were to move one step to the down
		return 
				!Collision(rect) && 
				player.getYpos() -10 >= 64.0 && TouchInputHandler.y > Themis.HEIGHT/2 && TouchInputHandler.x < RightBound && TouchInputHandler.x > LeftBound;
	}
	
	public boolean Collision(Rectangle rect) {
		
		MapObjects objects = play.getObjects();
		
		for (RectangleMapObject rectangleObject : objects.getByType(RectangleMapObject.class)) {

		    Rectangle rectangle = rectangleObject.getRectangle();
		    if (Intersector.overlaps(rectangle, rect)) {
		        return true;
		    } 
		} return false;
	}
	public void render(SpriteBatch sb){
//		Checking Collisions
		Buttons right = new Buttons("Ouch!!", player.getXpos(), player.getYpos()+50);
		Buttons left = new Buttons("wtf!!", player.getXpos(), player.getYpos()-25);
		Buttons up = new Buttons("dammit!!", player.getXpos()+25, player.getYpos());
		Buttons down = new Buttons("oof!!", player.getXpos()-25, player.getYpos());

		Rectangle rect = new Rectangle(player.getXpos()+MOVE, player.getYpos(), 20, 20);
		Rectangle rect1 = new Rectangle(player.getXpos()-MOVE, player.getYpos(), 20, 20);
		Rectangle rect2 = new Rectangle(player.getXpos(), player.getYpos()+MOVE, 20, 20);
		Rectangle rect3 = new Rectangle(player.getXpos()+MOVE, player.getYpos()-MOVE, 20, 20);

		if (Collision(rect)) {right.render(sb);}
		if (Collision(rect1)) {left.render(sb);}
		if (Collision(rect2)) {up.render(sb);}
		if (Collision(rect3)) {down.render(sb);}
	}
}
