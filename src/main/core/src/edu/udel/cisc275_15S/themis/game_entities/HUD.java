package edu.udel.cisc275_15S.themis.game_entities;

import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import edu.udel.cisc275_15S.themis.game_states.Play;
import edu.udel.cisc275_15S.themis.handlers.GameStateHandler;
import edu.udel.cisc275_15S.themis.interactables.Backpack;
import edu.udel.cisc275_15S.themis.interactables.Objectives;
import edu.udel.cisc275_15S.themis.interactables.Online;
import edu.udel.cisc275_15S.themis.interactables.UDMail;
import edu.udel.cisc275_15S.themis.interactables.UDSIS;

// This class draws the players backpack, Objectives, and UDSIS

public class HUD {
	
	private Backpack bag;
	private Objectives obj;
	private UDSIS udsis;
	private UDMail email;
	private Online online;
	boolean OpenBag = false;
	boolean OpenObj = false;
	private Sound click = Gdx.audio.newSound(Gdx.files.internal("Audio/Click.mp3"));
	public GameStateHandler gsh;
	private Play mplay; // used to dispose play music

	Stage stage;
	Skin skin;
	Button Bag;
	Button Obj;
	Button Web;
	
	private Texture bagIcon = new Texture(Gdx.files.internal("gfx/themismenubg.jpg"));
	private Texture objIcon = new Texture(Gdx.files.internal("gfx/themis.png"));
	private Texture WebIcon = new Texture(Gdx.files.internal("gfx/themis.png"));

	
	public HUD(Player player, Play play) {
		bag = player.getBag();
		obj = player.getObjButton();
		online = player.getOnline();
		udsis = player.getUDSIS();
		email = player.getEmail();
		gsh = play.getGsh();
		mplay = play;
		
		stage = new Stage();											
		Gdx.input.setInputProcessor(stage);								
		skin = new Skin(Gdx.files.internal("Data/uiskin.json"));		
		
		bagIcon = new Texture(Gdx.files.internal("Button/bag.png"));
		objIcon = new Texture(Gdx.files.internal("Button/info.png"));
		WebIcon = new Texture(Gdx.files.internal("Button/browser.png"));

		TextureRegion bagtr = new TextureRegion(bagIcon);
		TextureRegion objtr = new TextureRegion(objIcon);
		TextureRegion webtr = new TextureRegion(WebIcon);
		
		Bag = new Button(new Image(bagtr), skin);
		Obj = new Button(new Image(objtr), skin);
		Web = new Button(new Image(webtr), skin);

		Bag.setPosition(413, 0);
		Bag.setSize(bagIcon.getWidth(), bagIcon.getHeight());
		
		Obj.setPosition(349, 0);
		Obj.setSize(objIcon.getWidth(), objIcon.getHeight());

		Web.setPosition(285, 0);
		Web.setSize(WebIcon.getWidth(), WebIcon.getHeight());
		
		Bag.addListener(new ClickListener() {							
			@Override
			public void touchUp(InputEvent e, float x, float y, int pointer, int button){
			if (!OpenBag) {	
				OpenBag = true;
				click.play();
				bag.setOpened(true);
				System.out.println(bag.isOpen());
			}
			else if (OpenBag) {	
				OpenBag = false;
				click.play();
				bag.setOpened(false);
			}
//				update(1/60);
			}
		});
		Obj.addListener(new ClickListener() {							
			@Override
			public void touchUp(InputEvent e, float x, float y, int pointer, int button){
			if (!OpenObj) {
				OpenObj = true;
				click.play();
				obj.setOpened(true);
			}
			else if (OpenObj) {
				OpenObj = false;
				click.play();
				obj.setOpened(false);
			} 
//				update(1/60);
			}
		});
		Web.addListener(new ClickListener() {							
			@Override
			public void touchUp(InputEvent e, float x, float y, int pointer, int button){
				try {
					mplay.getMusic().dispose();
					click.play();
					gsh.setState(GameStateHandler.WEB);						
				} catch (IOException e1) {
					e1.printStackTrace();
					}			
				}
		});
		stage.addActor(Bag);
		stage.addActor(Obj);
		stage.addActor(Web);
	}

	public void update(float dt) {
//		So that the interfaces don't open over each other
		if (OpenBag) {
			bag.update(dt);
		}
		if (OpenObj) {
			obj.update(dt);
		}
	}
	
	public void render(SpriteBatch sb){
		bag.render(sb);
		obj.render(sb);
		online.render(sb);	
		stage.act();
		stage.draw();
	}
	public Backpack getbag() {
		return bag;
	}
	public Objectives getobj() {
		return obj;
	}
}
