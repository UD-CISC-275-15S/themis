package edu.udel.cisc275_15S.themis.game_states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import edu.udel.cisc275_15S.themis.handlers.GameStateHandler;

public class Pack extends GameState {

	Stage stage;

	TextButton back;

	Skin skin;

	//Texture bg = new Texture(Gdx.files.internal("")); // Holds the background image

	public Pack(GameStateHandler gsh) {
		super(gsh);
		stage = new Stage();											// init the stage
		Gdx.input.setInputProcessor(stage);								// add an input processor to the stage to see touch
		skin = new Skin(Gdx.files.internal("Data/uiskin.json"));		// init the skin to load the UI settings
		back = new TextButton("Back", skin);							// create the start button
		back.setPosition(0, 0);										// sets the position to 320, 60, about midway at the bottom
		back.setSize(80,30);											// sets the size to fit the text
		back.addListener(new ClickListener() {							// adds a click listener
			@Override
			public void touchUp(InputEvent e, float x, float y, int pointer, int button){
				back.setText("Have fun!");								// while you have it clicked, set the text to Have fun
				backClicked();											// notify that the button is clicked and change states
			}
		});	
	}

	public void backClicked(){
		gsh.popState();
		this.dispose();												// dispose the data loaded into this class

	}

	@Override
	public void handleInput() {
		// AUTO Auto-generated method stub

	}

	@Override
	public void update(float dt) {
		// AUTO Auto-generated method stub

	}

	@Override
	public void render() {
		// AUTO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// AUTO Auto-generated method stub

	}

	@Override
	public void resize(int width, int height) {
		// AUTO Auto-generated method stub

	}

}
