package edu.udel.cisc275_15S.themis.handlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
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
import edu.udel.cisc275_15S.themis.game_entities.Character;
import edu.udel.cisc275_15S.themis.game_events.Event;
import edu.udel.cisc275_15S.themis.game_states.Play;
import edu.udel.cisc275_15S.themis.interactables.Buttons;

//		Used to handle interactions between a Player and NPC
//		Since we are basically using the play state as our entire game, update the play state

public class CharacterInteractionHandler {
//	how many spaces the character moves
	public static final int MOVE = 2;
	public Player player;
	public Array<NPC> npcs;
	public Array<Buttons> npcButtons;
	public Array<Rectangle> npcRectangles;
//	Tiles that the player cant pass through
	public Play play;
	private BitmapFont font;
	public boolean event = true;
	public Event currentEvent;
	public SpriteBatch sb; 
	public int wallSwitch=0;
	
	private Sound wallHit = Gdx.audio.newSound(Gdx.files.internal("Audio/Bubble.mp3"));

	
	public CharacterInteractionHandler(Play gs) {

		player = gs.getPlayer();
		npcs = gs.getNPCS();
		play = gs;
		sb = play.getSB();

	}
	public void update() {
		eventHandler();
		if (!event) {
		currentEvent.update();
		currentEvent.render(sb);
		event = currentEvent.getcomplete();
		}
	}
//	For moving NPC's
	public void moveRight(Character ac) {
		{ ac.setX(ac.getXpos()+MOVE); ac.setDir("right");	}
	}
	public void moveLeft(Character ac) {
		{ ac.setX(ac.getXpos()-MOVE); ac.setDir("left");	}
	}
	public void moveUp(Character ac) {
		{ ac.setY(ac.getYpos()+MOVE); ac.setDir("up");	}
	}
	public void moveDown(Character ac) {
		{ ac.setY(ac.getYpos()-MOVE);; ac.setDir("down");	}
	}
	public void touchHandler() {
		
		if (TouchInputHandler.isClicked() && event) 
		{
//			for testing purposes
//			System.out.println("you are at screen cord: " + player.getXpos() + " , " + player.getYpos());
			if (checkright()) {moveRight(player);}
			if (checkleft()) {moveLeft(player);}
			if (checkup()) {moveUp(player);}
			if (checkdown()) {moveDown(player);}
		}
	}

	public boolean checkleft() {
		float UpperBound = Themis.HEIGHT/4;
		float LowerBound = Themis.HEIGHT - Themis.HEIGHT/4;
		boolean check = false;
		for (int i=0;i<play.getNPCS().size;i++){
			if(
				((player.getX()-MOVE>=play.getNPCS().get(i).getX()-20)&&(player.getX()-MOVE<=play.getNPCS().get(i).getX()+20)) &&
				((player.getY()>=play.getNPCS().get(i).getY()-20)&&(player.getY()<=play.getNPCS().get(i).getY()+20)))
			{
				check = true;
			}
		}
		Rectangle rect = new Rectangle(player.getXpos()-MOVE, player.getYpos(), 20, 20);
//		Check if there is a collision if the player were to move one step to the left
		return 
				!Collision(rect)  && !check &&
				player.getXpos() -10 >= 0.0 && TouchInputHandler.x < Themis.WIDTH/2 && TouchInputHandler.y > UpperBound && TouchInputHandler.y < LowerBound;
	}
	public boolean checkright() {
		float UpperBound = Themis.HEIGHT/4;
		float LowerBound = Themis.HEIGHT - Themis.HEIGHT/4;
		boolean check = false;
		for (int i=0;i<play.getNPCS().size;i++){
			if(
				((player.getX()+MOVE>=play.getNPCS().get(i).getX()-20)&&(player.getX()+MOVE<=play.getNPCS().get(i).getX()+20))&&
				((player.getY()>=play.getNPCS().get(i).getY()-20)&&(player.getY()<=play.getNPCS().get(i).getY()+20))){
				check = true;
			}
		}
		Rectangle rect = new Rectangle(player.getXpos()+MOVE, player.getYpos(), 20, 20);
//		Check if there is a collision if the player were to move one step to the right
		return 
				!Collision(rect) && !check &&
				player.getXpos()+10 < 1000 && TouchInputHandler.x > Themis.WIDTH/2 && TouchInputHandler.y > UpperBound && TouchInputHandler.y < LowerBound;
	}
	public boolean checkup() {
		float LeftBound = Themis.WIDTH/3;
		float RightBound = Themis.WIDTH - Themis.WIDTH/3;
		boolean check = false;
		for (int i=0;i<play.getNPCS().size;i++){
			if(((player.getX()>=play.getNPCS().get(i).getX()-20)&&(player.getX()<=play.getNPCS().get(i).getX()+20))
					&&
					((player.getY()+MOVE>=play.getNPCS().get(i).getY()-20)&&(player.getY()+MOVE<=play.getNPCS().get(i).getY()+20))){
				check = true;
			}
		}
		Rectangle rect = new Rectangle(player.getXpos(), player.getYpos()+MOVE, 20, 20);
//		Check if there is a collision if the player were to move one step up
		return 
				!Collision(rect)  && !check &&
				player.getYpos() < 1000 && TouchInputHandler.y < Themis.HEIGHT/2 && TouchInputHandler.x < RightBound && TouchInputHandler.x > LeftBound;
	}
	public boolean checkdown() {
		float LeftBound = Themis.WIDTH/3;
		float RightBound = Themis.WIDTH - Themis.WIDTH/3;
		boolean check = false;
		for (int i=0;i<play.getNPCS().size;i++){
			if(((player.getX()>=play.getNPCS().get(i).getX()-20)&&(player.getX()<=play.getNPCS().get(i).getX()+20))
					&&
					((player.getY()-MOVE>=play.getNPCS().get(i).getY()-20)&&(player.getY()-MOVE<=play.getNPCS().get(i).getY()+20))){
				check = true;
			}
		}
		Rectangle rect = new Rectangle(player.getXpos(), player.getYpos()-MOVE, 20, 20);
//		Check if there is a collision if the player were to move one step down
		return 
				!Collision(rect)  && !check &&
				player.getYpos() -10 >= 64.0 && TouchInputHandler.y > Themis.HEIGHT/2 && TouchInputHandler.x < RightBound && TouchInputHandler.x > LeftBound;
	}
//	For Collisions between map objects
	public boolean Collision(Rectangle rect) {
		
		MapObjects objects = play.getObjects();
		
		for (RectangleMapObject rectangleObject : objects.getByType(RectangleMapObject.class)) {

		    Rectangle rectangle = rectangleObject.getRectangle();
		    if (Intersector.overlaps(rectangle, rect)) {
		        return true;
		    } 
		} return false;
	}
	public void eventHandler() {
		boolean incompleteEvent;
		if (event) {
		for(int i=0;i<npcs.size;i++){
			if(
					((player.getX()>=npcs.get(i).getX()-20)&&(player.getX()<=npcs.get(i).getX()+20))&&((player.getY()-MOVE>=npcs.get(i).getY()-20)&&(player.getY()-MOVE<=npcs.get(i).getY()+20))
			||		((player.getX()>=npcs.get(i).getX()-20)&&(player.getX()<=npcs.get(i).getX()+20))&&((player.getY()+MOVE>=npcs.get(i).getY()-20)&&(player.getY()+MOVE<=npcs.get(i).getY()+20))
			||
				(
				((player.getX()-MOVE>=play.getNPCS().get(i).getX()-20)&&(player.getX()-MOVE<=play.getNPCS().get(i).getX()+20)) &&
				((player.getY()>=play.getNPCS().get(i).getY()-20)&&(player.getY()<=play.getNPCS().get(i).getY()+20)))
			||
				(
				((player.getX()-MOVE>=play.getNPCS().get(i).getX()-20)&&(player.getX()-MOVE<=play.getNPCS().get(i).getX()+20)) &&
				((player.getY()>=play.getNPCS().get(i).getY()-20)&&(player.getY()<=play.getNPCS().get(i).getY()+20))))
			{
				incompleteEvent = npcs.get(i).getEvent(0).getcomplete();
//				System.out.println(incompleteEvent);
				if (!incompleteEvent){
				currentEvent = npcs.get(i).getEvent(0);
				event = false;} 
				}
			} 
		} 
	}
	
	public void render(SpriteBatch sb){
//		Checking Collisions
		Buttons right = new Buttons("Ouch!!", player.getXpos(), player.getYpos()+50);
		Buttons left = new Buttons("Ugh!!", player.getXpos(), player.getYpos()-25);
		Buttons up = new Buttons("Gah!!", player.getXpos()+25, player.getYpos());
		Buttons down = new Buttons("Oof!!", player.getXpos()-25, player.getYpos());

		Rectangle rect = new Rectangle(player.getXpos()+MOVE, player.getYpos(), 20, 20);
		Rectangle rect1 = new Rectangle(player.getXpos()-MOVE, player.getYpos(), 20, 20);
		Rectangle rect2 = new Rectangle(player.getXpos(), player.getYpos()+MOVE, 20, 20);
		Rectangle rect3 = new Rectangle(player.getXpos()+MOVE, player.getYpos()-MOVE, 20, 20);
		
		if (!Collision(rect) && !Collision(rect1) && !Collision(rect2) && !Collision(rect3))
			wallSwitch=0;
		if (Collision(rect)) {
			right.render(sb);
			if (wallSwitch == 0)
				wallHit.play();
				wallSwitch =1;
			}
		if (Collision(rect1)) {
			left.render(sb);
			if (wallSwitch == 0)
				wallHit.play();
				wallSwitch =1;
			}
		if (Collision(rect2)) {
			up.render(sb);
			if (wallSwitch == 0)
				wallHit.play();
				wallSwitch =1;
			}
		if (Collision(rect3)) {
			down.render(sb);
			if (wallSwitch == 0)
				wallHit.play();
				wallSwitch =1;
			}
	}
	public boolean getEvent() { return event; }
}
