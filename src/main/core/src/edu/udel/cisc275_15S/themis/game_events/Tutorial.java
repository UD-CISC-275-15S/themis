package edu.udel.cisc275_15S.themis.game_events;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

import edu.udel.cisc275_15S.themis.Themis;
import edu.udel.cisc275_15S.themis.game_entities.Player;
import edu.udel.cisc275_15S.themis.handlers.TouchInputHandler;

//  Explains the General workings of the game, and only loads at the beginning of a new game.
// 	Has a different render method because of drawing the arrows pointing to the HUD 
// 	Comment and uncomment the render method to see the difference between drawing a regular event and a Tutorial
//	

public class Tutorial extends Event {
	BitmapFont Dialogue;
	Texture arrows = new Texture(Gdx.files.internal("gfx/arrow.png"));
	
	static String Introduction = 
			"Hello and welcome to the University of Delaware! "
			+ "Today you are here to learn about the resources you will need to become a successful student. "
			+ "This is your bookbag, and it contains multiple resources. " 
			+ "First and foremost, there is a reference book, where you can find information about the available resources around campus. ";
	String Obj = 
			" This is an objective list, that contains a list of tasks you need to complete.";
	String UDSIS = 
			"This is your computer, which you can use to access UDSIS as well as your email.";
	String End = 
			"Feel free to talk to other students on their tour today. For now, lets get you set up with your advisor.";
	
	
	public Tutorial(Player player, boolean valid, int animator, String name) {
		super(player, valid, animator, name);
//		Avatar = new Texture(Gdx.files.internal("Avatars/advisorav.png"));
		Avatar = new Texture(Gdx.files.internal("Avatars/bluehenguide.png"));
		Dias = new Array<String>();
		Dias.add(Introduction);
		Dias.add(Obj);
		Dias.add(UDSIS);
		Dias.add(End);
	}

	public void render(SpriteBatch sb, OrthographicCamera cam) {
//		drawTransparentRect(cam);
		sb.begin();
		tempDbg(sb);
		sb.draw(Avatar, avatarX, animator, Avatar.getWidth(), Avatar.getHeight());
		drawDia(sb);
		if (dia && currentDia == 0) {
			sb.draw(arrows, 3, 64);
		}
		if (dia && currentDia == 1) {
			sb.draw(arrows, 67, 64);
		}
		if (dia && currentDia == 2) {
			sb.draw(arrows, 130, 64);
		}
		sb.end();
	}
}

