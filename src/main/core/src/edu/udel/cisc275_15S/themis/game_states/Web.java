package edu.udel.cisc275_15S.themis.game_states;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

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
	private Vector2 dragOld, dragNew;
	private boolean udsis = false;
	
	public Web(GameStateHandler gsh) throws FileNotFoundException {
		super(gsh);
		UDSIS = new Buttons(sis, 40, 280);
		UDMail = new Buttons(email, 120, 280);
		back = new Buttons(backicon, 400, 50);
		cam = new MainCamera();
		cam.setToOrtho(false, Themis.WIDTH, Themis.HEIGHT);
		cam.setBounds(0, bg.getWidth(), 0, bg.getHeight());
        dragNew = new Vector2(Gdx.input.getX(), Gdx.input.getY());
        dragOld = new Vector2(Gdx.input.getX(), Gdx.input.getY());
        
		
	}

	@Override
	public void handleInput() {
//		temporary code for functionality
		if (UDSIS.isDown() && !udsis){
			bg = new Texture(Gdx.files.internal("gfx/udsis1.png"));
			udsis = true;
		}

		if (UDMail.isDown()){
		}
		
		if (back.isDown()){
			try {
				gsh.setState(GameStateHandler.PLAY);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

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
		handleInput();
		UDSIS.update(dt);
		UDMail.update(dt);
		back.update(dt);
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
		UDSIS.render(sb);
		UDMail.render(sb);
		back.render(sb);
	}

	@Override
	public void dispose() {
		// AUTO Auto-generated method stub

	}

	@Override
	public void resize(int width, int height) {

	}

}
