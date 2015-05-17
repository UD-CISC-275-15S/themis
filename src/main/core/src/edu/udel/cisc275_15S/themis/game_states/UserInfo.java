package edu.udel.cisc275_15S.themis.game_states;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
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

import edu.udel.cisc275_15S.themis.Data;
import edu.udel.cisc275_15S.themis.handlers.GameStateHandler;

public class UserInfo extends GameState{

	
	// All Private variables so that no one can modify the UI state
	Stage stage;
	
	Label name;
	Label id;
	
	TextButton start;
	TextField txfuser;
	TextField txfid;
	
	Skin skin;
	
	private Sound click = Gdx.audio.newSound(Gdx.files.internal("Audio/Click.mp3"));
	
	Texture bg = new Texture(Gdx.files.internal("gfx/infoscreen.png")); // Holds the background image

	public UserInfo(GameStateHandler gsh) {
		super(gsh);
		stage = new Stage();											// init the stage
		Gdx.input.setInputProcessor(stage);								// add an input processor to the stage to see touch
		skin = new Skin(Gdx.files.internal("Data/uiskin.json"));		// init the skin to load the UI settings
		start = new TextButton("Start", skin);							// create the start button
		start.setPosition(320, 60);										// sets the position to 320, 60, about midway at the bottom
		start.setSize(80,30);											// sets the size to fit the text
		start.addListener(new ClickListener() {							// adds a click listener
			@Override
			public void touchUp(InputEvent e, float x, float y, int pointer, int button){
				start.setText("Play!");								// while you have it clicked, set the text to Have fun
				startClicked();											// notify that the button is clicked and change states
			}
		});
		
		name = new Label("First Name:", skin);							// init the first name label with appropriate
		name.setPosition(110, 190);										// position
		name.setSize(20, 20);											// size
		name.setColor(Color.WHITE);										// color
		
		id = new Label("UDID:", skin);								// init the college label
		id.setPosition(150, 120);
		id.setSize(20, 20);
		id.setColor(Color.WHITE);
		
		txfuser = new TextField("", skin);								// init the textfield for the first name
		txfuser.setPosition(200, 180);
		txfuser.setSize(240, 40);
		
		txfid = new TextField("", skin);								// init the college textfield
		txfid.setPosition(200, 110);
		txfid.setSize(240, 40);
		
		stage.addActor(name);											// add all these interactables to the stage to be drawn
		stage.addActor(id);
		stage.addActor(txfuser);
		stage.addActor(txfid);
		stage.addActor(start);
		
		
	}

	public void startClicked(){
		click.play();
		System.out.println("clicked");
		if (txfuser.getText().equals("") || txfuser.getText().equals("Please type your name.")){
			txfuser.setText("Please type your name.");
			start.addListener(new ClickListener() {							// adds a click listener
				@Override
				public void touchUp(InputEvent e, float x, float y, int pointer, int button){
					start.setText("Play!");								// while you have it clicked, set the text to Have fun
					startClicked();											// notify that the button is clicked and change states
				}
			});
		} 
		if (txfid.getText().equals("") || txfid.getText().equals("Please type your Student ID.")) {
			txfid.setText("Please type your Student ID.");
			start.addListener(new ClickListener() {							// adds a click listener
				@Override
				public void touchUp(InputEvent e, float x, float y, int pointer, int button){
					start.setText("Play!");								// while you have it clicked, set the text to Have fun
					startClicked();											// notify that the button is clicked and change states
				}
			});
		} 
		if (!txfuser.getText().equals("") && !txfuser.getText().equals("Please type your name.") &&
				!txfid.getText().equals("") && !txfid.getText().equals("Please type your Student ID.")) {
			try {
				Data.savePlayerName(txfuser.getText(), txfid.getText());
				gsh.setState(GameStateHandler.PLAY);						// set the game state to the main play state
				gsh.game().background.dispose();
				bg.dispose();												// dispose of the large image file
				this.dispose();												// dispose the data loaded into this class
			} catch (IOException e) {
				e.printStackTrace();
			}
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
		Gdx.gl.glClearColor(1, 1, 1, 1); 								// set up the screen clear
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); 						// clear it
		sb.setProjectionMatrix(cam.combined);							// set the coordinate system to the screen
		stage.act();													// allow the interactables to act (interact)
		stage.getBatch().begin();										// set up batch
		stage.getBatch().draw(bg,0,0);									// draw bg
		stage.getBatch().end();											// tear down batch
		stage.draw(); 													// draw the interactables
	}
	
	@Override
	public void dispose() {
		stage = null;													// get rid of the stage data
		skin = null;													// get rid of the UI settings data
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}
}
