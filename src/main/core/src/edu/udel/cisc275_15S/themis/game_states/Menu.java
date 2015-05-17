package edu.udel.cisc275_15S.themis.game_states;

import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import edu.udel.cisc275_15S.themis.Themis;
import edu.udel.cisc275_15S.themis.handlers.GameStateHandler;
public class Menu extends GameState {

	boolean newGame = true;
	Stage stage;
	Skin skin;
	private Texture bg = new Texture(Gdx.files.internal("gfx/themismenubg.jpg"));
	private Texture ud = new Texture(Gdx.files.internal("gfx/themis.png"));
	private int srcX = 0;
	private TextButton play;
	private TextButton newgame;

	Texture right = new Texture("Sprites/right.png");
	Texture texture;
	float statetime = 0f;
	TextureRegion currentFrame;

	private Sound click = Gdx.audio.newSound(Gdx.files.internal("Audio/Click.mp3"));

	public Menu(GameStateHandler gsh) {
		super(gsh);
		stage = new Stage();											// init the stage
		Gdx.input.setInputProcessor(stage);								// add an input processor to the stage to see touch
		skin = new Skin(Gdx.files.internal("Data/uiskin.json"));		// init the skin to load the UI settings
		play = new TextButton("Play", skin);							// create the start button
		play.setPosition(205, 80);										// sets the position to 320, 60, about midway at the bottom
		play.setSize(75,25);
		newgame = new TextButton("New Game", skin);							// create the start button
		newgame.setPosition(205, 50);										// sets the position to 320, 60, about midway at the bottom
		newgame.setSize(100,25);										// sets the size to fit the text
		
		play.addListener(new ClickListener() {							// adds a click listener
			@Override
			public void touchUp(InputEvent e, float x, float y, int pointer, int button){
				playClicked();											// notify that the button is clicked and change states
			}
		});

		stage.addActor(play);
		stage.addActor(newgame);

	}

	public void playClicked(){
//		if (newGame){
//			try {
//				click.play();
//				gsh.setState(GameStateHandler.USERINFO);
//				newGame = false;											// set the game state to the main play state
//				bg.dispose();												// dispose of the large image file
//				this.dispose();												// dispose the data loaded into this class
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		} else {
			try {
				click.play();
				gsh.setState(GameStateHandler.PLAY);						// set the game state to the main play state
				bg.dispose();												// dispose of the large image file
				this.dispose();												// dispose the data loaded into this class
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	
	public void newgameClicked(){
		if (newGame){
			try {
				click.play();
				gsh.setState(GameStateHandler.USERINFO);
				newGame = false;											// set the game state to the main play state
				bg.dispose();												// dispose of the large image file
				this.dispose();												// dispose the data loaded into this class
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				click.play();
				gsh.setState(GameStateHandler.PLAY);						// set the game state to the main play state
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
		moveBg();
	}
	//		
	public void moveBg() {
		if (srcX == bg.getWidth()-666) {
			srcX = 0;
		} else srcX++;
	}
	@Override
	public void render() {

		Gdx.gl.glClearColor(1, 1, 1, 1); 
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); 
		sb.setProjectionMatrix(cam.combined);
		sb.begin();
		sb.draw(bg, 0, 0, srcX, 0, 480, 320);
		sb.draw(ud, 0, 0);
		sb.end();

		TextureRegion[][] tmp2 = TextureRegion.split(right, right.getWidth()/10,right.getHeight());
		TextureRegion[] walkright = new TextureRegion[10];
		for (int i = 0; i < 10; i++) {
			walkright[i] = tmp2[0][i];
		}


		Animation wright = new Animation(1/12f, walkright);
		currentFrame = wright.getKeyFrame(statetime, true);
		statetime += Gdx.graphics.getDeltaTime();
		stage.act();
		sb.begin();
		sb.draw(currentFrame, Themis.WIDTH/2, 15);             
		sb.end();
		stage.draw();

	}

	@Override
	public void dispose() {
		// AUTO Auto-generated method stub

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}
}
