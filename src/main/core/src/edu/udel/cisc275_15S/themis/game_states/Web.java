package edu.udel.cisc275_15S.themis.game_states;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import edu.udel.cisc275_15S.themis.Themis;
import edu.udel.cisc275_15S.themis.handlers.GameStateHandler;
import edu.udel.cisc275_15S.themis.handlers.MainCamera;
import edu.udel.cisc275_15S.themis.interactables.Buttons;
public class Web extends GameState {

	private Buttons UDSIS;
	private Buttons UDMail;
	private Buttons back;
	private Texture backicon = new Texture(Gdx.files.internal("gfx/backicon.png"));
	private Texture email = new Texture(Gdx.files.internal("gfx/email2.png"));
	private Texture sis = new Texture(Gdx.files.internal("gfx/UDSISicon.png"));
	private Texture bg = new Texture(Gdx.files.internal("gfx/udelhome.png"));
	private Texture home = new Texture(Gdx.files.internal("gfx/udelhome.png"));
	private Texture sispage = new Texture(Gdx.files.internal("gfx/udsis1.png"));
	private Vector2 dragOld, dragNew;
	private boolean udsis = false;
	int i = 0;
	Stage stage;
	Skin skin;
	Button BACK;
	Button EMAIL;
	Button SIS;
	
	private Sound click = Gdx.audio.newSound(Gdx.files.internal("Audio/Click.mp3"));
	
	public Web(final GameStateHandler gsh) throws FileNotFoundException {
		super(gsh);
		
		stage = new Stage();											
		Gdx.input.setInputProcessor(stage);								
		skin = new Skin(Gdx.files.internal("Data/uiskin.json"));	
		
		TextureRegion backtr = new TextureRegion(backicon);
		TextureRegion emailtr = new TextureRegion(email);
		TextureRegion sistr = new TextureRegion(sis);
		
		BACK = new Button(new Image(backtr), skin);
		EMAIL = new Button(new Image(emailtr), skin);
		SIS = new Button(new Image(sistr), skin);
		
		BACK.setPosition(400, 10);
		BACK.setSize(backicon.getWidth(), backicon.getHeight());
		
		EMAIL.setPosition(100, 10);
		EMAIL.setSize(email.getWidth(), email.getHeight());

		SIS.setPosition(20, 10);
		SIS.setSize(sis.getWidth(), sis.getHeight());

		BACK.addListener(new ClickListener() {							
			@Override
			public void touchUp(InputEvent e, float x, float y, int pointer, int button){
				click.play();
					try {
						gsh.setState(GameStateHandler.PLAY);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
			}
		});
		
		EMAIL.addListener(new ClickListener() {							
			@Override
			public void touchUp(InputEvent e, float x, float y, int pointer, int button){
				click.play();
			}
		});
		
		SIS.addListener(new ClickListener() {							
			@Override
			public void touchUp(InputEvent e, float x, float y, int pointer, int button){
				click.play();
				if (i == 0) {
					i = 1;
					bg = sispage;
					} else if (i == 1) {
				i = 0;
				bg = home;
					}
			}
		});
		
		stage.addActor(BACK);
		stage.addActor(EMAIL);
		stage.addActor(SIS);
		
		cam = new MainCamera();
		cam.setToOrtho(false, Themis.WIDTH, Themis.HEIGHT);
		cam.setBounds(0, bg.getWidth(), 0, bg.getHeight());
		
        dragNew = new Vector2(Gdx.input.getX(), Gdx.input.getY());
        dragOld = new Vector2(Gdx.input.getX(), Gdx.input.getY());
		
	}
	
	public void dragInput() {
		if (Gdx.input.justTouched())
		{
		    dragNew = new Vector2(Gdx.input.getX(), Gdx.input.getY());
		    dragOld = dragNew;
		}
		if (Gdx.input.isTouched())
	    {
	        dragNew = new Vector2(Gdx.input.getX(), Gdx.input.getY());
	        if (!dragNew.equals(dragOld))
	        {
	            cam.translate(dragOld.x - dragNew.x, dragNew.y - dragOld.y); //Translate by subtracting the vectors
	            cam.update();
	            dragOld = dragNew; //Drag old becomes drag new.
	        }
	    }
	}
	@Override
	public void update(float dt) {
//		handleInput();
//		UDSIS.update(dt);
//		UDMail.update(dt);
//		back.update(dt);
		dragInput();
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 1, 1, 1); 
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); 
		sb.setProjectionMatrix(cam.combined);
		sb.begin();
		sb.draw(bg, 0, 0);
		sb.end();
		sb.setProjectionMatrix(hudCam.combined);
		stage.act();
		stage.draw();
	}

	@Override
	public void dispose() {
		// AUTO Auto-generated method stub

	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void handleInput() {
		// TODO Auto-generated method stub
		
	}

}
