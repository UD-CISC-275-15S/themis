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

import edu.udel.cisc275_15S.themis.Themis;
import edu.udel.cisc275_15S.themis.game_states.Play;
import edu.udel.cisc275_15S.themis.handlers.GameStateHandler;
import edu.udel.cisc275_15S.themis.interactables.Backpack;
import edu.udel.cisc275_15S.themis.interactables.Objectives;
import edu.udel.cisc275_15S.themis.interactables.Online;

// This class draws the players backpack, Objectives, and UDSIS

public class HUD {
	
	private Backpack bag;
	private Objectives obj;
	private Online online;
	boolean OpenBag = false;
	boolean OpenObj = false;
	private Sound click = Gdx.audio.newSound(Gdx.files.internal("Audio/Click.mp3"));
	public GameStateHandler gsh;
	private Texture arrows = new Texture(Gdx.files.internal("gfx/arrow.png"));
	private Play mplay; // used to dispose play music

	Stage stage;
	Skin skin;
	Button Bag;
	Button Obj;
	Button Web;
	
	private Texture bagIcon = new Texture(Gdx.files.internal("gfx/themismenubg.jpg"));
	private Texture objIcon = new Texture(Gdx.files.internal("gfx/themis.png"));
	private Texture WebIcon = new Texture(Gdx.files.internal("gfx/themis.png"));
	
	private Texture up = new Texture(Gdx.files.internal("gfx/Ucircle.png"));
	private Texture down = new Texture(Gdx.files.internal("gfx/Dcircle.png"));
	private Texture left = new Texture(Gdx.files.internal("gfx/Lcircle.png"));
	private Texture right = new Texture(Gdx.files.internal("gfx/Rcircle.png"));


	
	public HUD(Player player, Play play) {
		bag = player.getBag();
		obj = player.getObjButton();
		online = player.getOnline();
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
				openInterfaceBag();
//				update(1/60);
			}
		});
		Obj.addListener(new ClickListener() {							
			@Override
			public void touchUp(InputEvent e, float x, float y, int pointer, int button){
				openInterfaceObj();
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
	
	private void openInterfaceBag(){
		if (OpenObj){
			OpenObj = false;
			obj.setOpened(false);
		}
		OpenBag = !OpenBag;
		bag.setOpened(OpenBag);
		Bag.addListener(new ClickListener() {							
			@Override
			public void touchUp(InputEvent e, float x, float y, int pointer, int button){
				openInterfaceBag();
//				update(1/60);
			}
		});
	}

	private void openInterfaceObj(){
		if (OpenBag){
			bag.setOpened(false);
			OpenBag = false;
		}
		OpenObj = !OpenObj;
		obj.setOpened(OpenObj);
		Obj.addListener(new ClickListener() {							
			@Override
			public void touchUp(InputEvent e, float x, float y, int pointer, int button){
				openInterfaceObj();
//				update(1/60);
			}
		});
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
		if (OpenBag) bag.render(sb);
		if (OpenObj) obj.render(sb);
		online.render(sb);
		
		stage.act();
		stage.draw();
		sb.begin();
		sb.draw(up, Themis.WIDTH / 2, Themis.HEIGHT - up.getHeight());
		sb.draw(down, Themis.WIDTH / 2, 0);
		sb.draw(left, 0, Themis.HEIGHT /2 );
		sb.draw(right, Themis.WIDTH - right.getWidth(), Themis.HEIGHT /2);
		sb.end();

		
	}
	public Backpack getbag() {
		return bag;
	}
	public Objectives getobj() {
		return obj;
	}
}
