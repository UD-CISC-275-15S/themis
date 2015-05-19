package edu.udel.cisc275_15S.themis.game_events;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

import edu.udel.cisc275_15S.themis.Data;
import edu.udel.cisc275_15S.themis.game_entities.Player;
//  Explains the General workings of the game, and only loads at the beginning of a new game.
// 	Has a different render method because of drawing the arrows pointing to the HUD 
// 	Comment and uncomment the render method to see the difference between drawing a regular event and a Tutorial
//	

public class Tutorial extends Event {
	Texture arrows;

	
	public Tutorial(Player player, boolean valid, int animator, String name) throws FileNotFoundException {
		super(player, valid, animator, name);
		arrows = new Texture(Gdx.files.internal("gfx/arrow.png"));
//		Avatar = new Texture(Gdx.files.internal("Avatars/advisorav.png"));
		Avatar = new Texture(Gdx.files.internal("Avatars/bluehenguide.png"));
		tex = new Texture(Gdx.files.internal("gfx/dialog.png"));
		diaY = 200;
		Dias = new Array<String>();
		try{
			Dias.add(Data.readTutIntro());
			Dias.add(Data.readTutObj());
			Dias.add(Data.readUDSIS());
			Dias.add(Data.readEnd());
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	public void update() {
		RemainingDia();
		NPCAvatarAnimation();
		EventComplete();
		diaAnimator();
		DiaCounter();
		if (complete) {
		try {
			Data.saveNewGame(complete);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		}
	}
	public void render(SpriteBatch sb, OrthographicCamera cam) {
//		drawTransparentRect(cam);
		sb.begin();
		tempDbg(sb);
		sb.draw(Avatar, avatarX, animator, Avatar.getWidth(), Avatar.getHeight());
		drawDia(sb);
		if (dia && currentDia == 0) {
			sb.draw(arrows, 415, 64);
		}
		if (dia && currentDia == 1) {
			sb.draw(arrows, 350, 64);
		}
		if (dia && currentDia == 2) {
			sb.draw(arrows, 285, 64);
		}
		sb.end();
	}
}

