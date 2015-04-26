package edu.udel.cisc275_15S.themis.game_states;

import java.io.FileNotFoundException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import edu.udel.cisc275_15S.themis.handlers.GameStateHandler;
import edu.udel.cisc275_15S.themis.handlers.TextInputHandler;

public class UserInfo extends GameState{

	Stage stage;
	
	Label name;
	Label coll;
	
	TextButton start;
	TextField txfuser;
	TextField txfcoll;
	
	Skin skin;
	
	Texture bg = new Texture(Gdx.files.internal("gfx/userinfobg.png"));
	TextInputHandler listener = new TextInputHandler();

	public UserInfo(GameStateHandler gsh) {
		super(gsh);
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		skin = new Skin(Gdx.files.internal("Data/uiskin.json"));
		start = new TextButton("Start", skin);
		start.setPosition(200, 60);
		start.setSize(80,60);
		start.addListener(new ClickListener() {
			@Override
			public void touchUp(InputEvent e, float x, float y, int pointer, int button){
				start.setText("Have fun!");
				startClicked();
			}
		});
		
		name = new Label("First Name:", skin);
		name.setPosition(110, 200);
		name.setSize(60, 60);
		name.setColor(Color.BLACK);
		
		coll = new Label("College:", skin);
		coll.setPosition(135, 130);
		coll.setSize(60, 60);
		coll.setColor(Color.BLACK);
		
		txfuser = new TextField("", skin);
		txfuser.setPosition(200, 200);
		txfuser.setSize(240, 60);
		
		txfcoll = new TextField("", skin);
		txfcoll.setPosition(200, 130);
		txfcoll.setSize(240, 60);
		
		stage.addActor(name);
		stage.addActor(coll);
		stage.addActor(txfuser);
		stage.addActor(txfcoll);
		stage.addActor(start);
		
		
	}

	public void startClicked(){
		try {
			gsh.setState(GameStateHandler.PLAY);
			bg.dispose();
			this.dispose();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void handleInput() {

	}

	@Override
	public void update(float dt) {
		handleInput();
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 1, 1, 1); 
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); 
		sb.setProjectionMatrix(cam.combined);
		stage.act();
		stage.draw(); 
		
	}
	
	@Override
	public void dispose() {
		stage = null;
		skin = null;
	}


	
}
