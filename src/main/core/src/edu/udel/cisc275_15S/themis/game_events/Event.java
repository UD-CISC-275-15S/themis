package edu.udel.cisc275_15S.themis.game_events;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
	public static final int diaX = 30;
	public static final int diaY = 115;
	public Texture tex = new Texture(Gdx.files.internal("gfx/Dialogue.png"));
	public String name;//name of the event/quest/objective
	public Player player;
	protected Texture Avatar;
	public boolean complete = false;
	protected boolean dia = false; //Used to check if the current dialogue box is no longer writing text
	protected boolean remainingdia = true; //used to check if theres any dialogue left
	public boolean npccomplete = false; //This is used for NPC animation
	protected int animator; //Used for animating the Avatar
	protected int dialogue = 0; //Used for creating a typing effect with the dialogue
	public static final int avatarX = 0; //Avatar draws at the left edge, mid height of the screen
	protected BitmapFont dialoguebox;
	protected Array<String> Dias; //An array list of all of the dialogues contained by the Event
	protected int currentDia; //Used if the event triggers some kind of change in the Players state, i.e the player loses or gains an item
	protected String action;
	protected String item;

	public void update() {
		RemainingDia();
		NPCAvatarAnimation();
		EventComplete();
		diaAnimator();
		DiaCounter();
	}
	public void render(SpriteBatch sb) {
		sb.begin();
		tempDbg(sb);
//		sb.draw(Avatar, avatarX, animator, Avatar.getWidth(), Avatar.getHeight());
		if (currentDia < Dias.size) {
		drawDia(sb);}
		sb.end();
	}
	public void handleinput() {
		
	}
	public void dispose() {
		
	}
	
	public Event(Player player, boolean complete, int animator, String name, Texture Avatar) {
		this.player = player;
		this.complete = complete;
		this.animator = -200;
		this.dialogue = 0;
		this.name = name;
	}
	public Event(Player player, boolean complete, int animator, String name) {
		this.player = player;
		this.complete = complete;
		this.animator = -200;
		this.dialogue = 0;
		this.name = name;
	}
	public Event(Player player, boolean complete, String name) {
		this.player = player;
		this.complete = complete;
		this.animator = -200;
		this.dialogue = 0;
		this.name = name;
	}
	public Event(Player player, boolean complete, int animator, String name, String action, Texture Avatar, String item) {
		this.player = player;
		this.complete = complete;
		this.animator = -200;
		this.dialogue = 0;
		this.name = name;
		this.action = action;
		this.item = item;
	}
//	Quiz constructor. 
	public Event() {
		
	}
//	Constructs the Dialogue box's background
	public void tempDbg(SpriteBatch sb) {
		if (npccomplete) {
		sb.setColor(1.0f, 1.0f, 1.0f, .5f);
		sb.draw(tex, diaX , Themis.HEIGHT/ 5, 400, diaY);
		sb.setColor(1.0f, 1.0f, 1.0f, 1.0f);
		}
	}
//	Stores all of the characters of the string into an array
	public static char[] CreateDialogueArray(String astr) {
		char[] Dialogue = astr.toCharArray();
		return Dialogue;
	}
//	Draw the dialogue up to the current Dialogue index
	public void drawDia(SpriteBatch sb) {
		char[] hi = CreateDialogueArray(Dias.get(currentDia));
		String astr = "";
		for (int i = 0; i < dialogue; i++) {
			astr = astr + hi[i];
		}
		dialoguebox = new BitmapFont();
		dialoguebox.drawWrapped(sb, astr, diaX + 20, diaY + 20, 380);
	}
//	Updates the dialogue index per second, together with drawDia this creates the typewriter effect
	public void diaAnimator() {
        float dt = 0;
        dt = Gdx.graphics.getDeltaTime();
        int size = CreateDialogueArray(Dias.get(currentDia)).length;
		if (dialogue >= size) {
			dia = true;
		} else if (TouchInputHandler.isClicked() && npccomplete && dialogue < size-10) {
			dialogue+=2;
		} else if (npccomplete && dt > .017)
			dialogue++;
	}
//	This updates the current dialogue box to the next one in the array.
	public void DiaCounter() {
        if (dia == true && currentDia < Dias.size-1 && TouchInputHandler.isClicked()) {
        	dialogue = 0;
        	dia = false;
        	currentDia++;
        }
	}
//	Computes the remaining dialogues in the array
	public void RemainingDia() {
		if (currentDia == Dias.size-1)
    	remainingdia = false;
	}
//	Draw the NPC at its default X, and change its Y every frame until it is in the middle of the screen
	public void NPCAvatarAnimation() {
		if (animator >= Themis.HEIGHT/4) {
			npccomplete = true;
		} else animator+=3;
		
	}
//	Checks whether the Event has been completed and if so, set it to false.
	public void EventComplete() {
		if (dia && !remainingdia && TouchInputHandler.isClicked()) {
			complete = true;
		}
	}
	public boolean getcomplete() {
		return complete;
	}
	public boolean getnpccomplete() {
		return npccomplete;
	}
	public void setcomplete(boolean b) {
		complete = b;
	}
}
