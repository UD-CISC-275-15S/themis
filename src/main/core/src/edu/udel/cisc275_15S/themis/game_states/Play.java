package edu.udel.cisc275_15S.themis.game_states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import edu.udel.cisc275_15S.themis.Themis;
import edu.udel.cisc275_15S.themis.game_entities.HUD;
import edu.udel.cisc275_15S.themis.game_entities.NPC;
import edu.udel.cisc275_15S.themis.game_entities.Player;
import edu.udel.cisc275_15S.themis.handlers.GameStateHandler;

public class Play extends GameState {

	private Player player;
	private int tileMapWidth;
	private int tileMapHeight;
	private int tileSize;
//	temporary bg until tileMap has been created
	private Texture bg = (new Texture(Gdx.files.internal("temp.jpg")));
	private OrthogonalTiledMapRenderer tmRenderer; 
	private Array<NPC> npcs;
	private HUD hud;
	
	public Play(GameStateHandler gsh) {
		super(gsh);
//		create the player
		CreatePlayer();
//		set the camera bounds to the Tile map size.
//		cam.setBounds(0, tileMapWidth * tileSize, 0, tileMapHeight * tileSize);
//		create the NPCs
		CreateNPCs();
//		create the hud, and set it to whatever the player owns. (backpack, resources, information, etc)
//		hud = new HUD(player);
	}
//	Only required if the Player has just started  a new game
	private void CreatePlayer() {
//		if implementing choosable sprites, save player sprite selection in textfile and load into player class.
//		Otherwise, load default sprite here:
	}
//	This is required if NPC's are put in the map on Tile
	private void CreateNPCs() {
//		
	}
	public void update(float dt) {
		handleInput();
//		player.update(dt);
//		for (int i = 0; i < NPCs.size(); i++) {
//			NPCs.get(i).update(dt);
//		}
	}
	
	public void render() {
	      Gdx.gl.glClearColor(0, 0, 0.2f, 1);
	      Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	      sb.begin();
//	      draw temp bg
	      sb.draw(bg, 0, 0);
	      sb.end();
//	      player.render();
//	      npcs.render();
//	      cam follows the player
//		  cam.setPosition(player.getPosition().x + Themis.WIDTH / 2, Themis.HEIGHT / 2);
	}

	@Override
	public void handleInput() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	public Player getPlayer() { return player;}
	public Array<NPC> getNPCS() {return npcs;}
}
