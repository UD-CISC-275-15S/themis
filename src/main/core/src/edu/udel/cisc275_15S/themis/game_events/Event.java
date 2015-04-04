package edu.udel.cisc275_15S.themis.game_events;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.utils.Array;

import edu.udel.cisc275_15S.themis.Themis;
import edu.udel.cisc275_15S.themis.game_entities.Player;
//	Event/Quest
//	An event occurs when the Player fulfills a specific condition.
//	By successfully completing the Event, the player will attain
//	information, backpack items, etc.
//	Also updates objectives and information text files 
//  Two types Events: Controllable, and nonControllable
import edu.udel.cisc275_15S.themis.handlers.TouchInputHandler;

public class Event {
//	modifiers for the dialogue and dialogue box's x andy pos
	public static final int diaX = 40;
	public static final int diaY = 100;
//	name of the event/quest/objective
	public String name;
//	is the event currently valid, or has it been previously completed
	public Player player;
//	The NPC avatar used, if one is used
	protected Texture Avatar;
	public boolean valid = true;
	protected ShapeRenderer shapeRenderer = new ShapeRenderer();
//	Used to check if the current dialogue box is no longer writing text
	protected boolean dia = false;
//	used to check if theres any dialogue left
	protected boolean remainingdia = true;
//	This is used for NPC animation
	public boolean npccomplete = false;
//	Used for animating the Avatar
	protected int animator;
//	Used for creating a typing effect with the dialogue
	protected int dialogue = 0;
//	Avatar draws at the left edge, mid height of the screen
	public static final int avatarX = 0;
	protected BitmapFont dialoguebox;
//	An array list of all of the dialogues contained by the Event
	protected Array<String> Dias;
	protected int currentDia;
//	Used if the event triggers some kind of change in the Players state, i.e the player loses or gains an item
	protected String action;
	protected String item;
	
	public void update() {
		RemainingDia();
		NPCAvatarAnimation();
		EventComplete();
		diaAnimator();
		DiaCounter();
	}
	public void render(SpriteBatch sb, OrthographicCamera cam) {
		sb.begin();
		tempDbg(sb);
		sb.draw(Avatar, avatarX, animator, Avatar.getWidth(), Avatar.getHeight());
		if (currentDia < Dias.size) {
		drawDia(sb);}
		sb.end();
	}
	public void handleinput() {
		
	}
	public void dispose() {
		
	}
	
	public Event(Player player, boolean valid, int animator, String name, Texture Avatar) {
		this.player = player;
		this.valid = valid;
		this.animator = -200;
		this.dialogue = 0;
		this.name = name;
	}
	public Event(Player player, boolean valid, int animator, String name) {
		this.player = player;
		this.valid = valid;
		this.animator = -200;
		this.dialogue = 0;
		this.name = name;
	}
	public Event(Player player, boolean valid, int animator, String name, String action, String item) {
		this.player = player;
		this.valid = valid;
		this.animator = -200;
		this.dialogue = 0;
		this.name = name;
		this.action = action;
		this.item = item;
	}
//    trying to draw a transparent rectangle using libGdx's shaperenderer to be used for dialogue bgs, but its not working yet
	public void drawTransparentRect(OrthographicCamera camera){
	    Gdx.gl.glClearColor(0, 0, 0, 0); 
	    shapeRenderer.setProjectionMatrix(camera.combined);
	    shapeRenderer.begin(ShapeType.Filled);
	    shapeRenderer.setColor(new Color(0, 0, 0, 0.5f)); 
	    shapeRenderer.rect(Themis.WIDTH/2, Themis.HEIGHT/2, Themis.WIDTH, Themis.HEIGHT);
	    shapeRenderer.end();
	}
	public void tempDbg(SpriteBatch sb) {
		if (npccomplete) {
 		Texture tex = new Texture(Gdx.files.internal("gfx/textbox.gif"));
		sb.setColor(1.0f, 1.0f, 1.0f, .5f);
		sb.draw(tex, Themis.WIDTH/5  + diaX , Themis.HEIGHT/ 5, 300, 120 + diaY);
		sb.setColor(1.0f, 1.0f, 1.0f, 1.0f);
		}
	}
	public static char[] CreateDialogueArray(String astr) {
		char[] Dialogue = astr.toCharArray();
		int size = Dialogue.length;
		return Dialogue;
	}

	public void drawDia(SpriteBatch sb) {
		char[] hi = CreateDialogueArray(Dias.get(currentDia));
		String astr = "";
		for (int i = 0; i < dialogue; i++) {
			astr = astr + hi[i];
		}
		dialoguebox = new BitmapFont();
		dialoguebox.drawWrapped(sb, astr, Themis.WIDTH / 3, Themis.HEIGHT / 2 + diaY, Themis.WIDTH / 2 + 10);
	}
	
	public void diaAnimator() {
        float dt = 0;
        dt = Gdx.graphics.getDeltaTime();
        int size = CreateDialogueArray(Dias.get(currentDia)).length;
		if (dialogue >= size) {
			dia = true;
		} else if (TouchInputHandler.isClicked() && npccomplete && dialogue < size-10) {
			dialogue++;
		} else if (npccomplete && dt > .017)
			dialogue++;
	}
	public void DiaCounter() {
        int size = CreateDialogueArray(Dias.get(currentDia)).length;
        if (dia == true && currentDia < Dias.size-1 && TouchInputHandler.isClicked()) {
        	dialogue = 0;
        	dia = false;
        	currentDia++;
        }
	}
	public void RemainingDia() {
		if (currentDia == Dias.size-1)
    	remainingdia = false;
	}
//		Draw the NPC at its default X, and change its Y every frame until it is in the middle of the screen
	public void NPCAvatarAnimation() {
		if (animator >= Themis.HEIGHT / 8) {
			npccomplete = true;
		} else animator+=3;
		
	}
	public void EventComplete() {
		if (dia && !remainingdia && TouchInputHandler.isClicked()) {
			valid = false;
		}
	}
	public boolean getValid() {
		return valid;
	}
	public boolean getnpccomplete() {
		return npccomplete;
	}
}
