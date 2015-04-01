package edu.udel.cisc275_15S.themis.game_states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.Array;

import edu.udel.cisc275_15S.themis.game_entities.HUD;
import edu.udel.cisc275_15S.themis.game_entities.NPC;
import edu.udel.cisc275_15S.themis.game_entities.Player;
import edu.udel.cisc275_15S.themis.handlers.GameStateHandler;

public class Play extends GameState {

	private Player player;
	private int tileMapWidth;
	private int tileMapHeight;
	private int tileSize;
	private TiledMap tileMap;
//	temporary bg until tileMap has been created
	private Texture bg = (new Texture(Gdx.files.internal("temp.jpg")));
	private OrthogonalTiledMapRenderer renderer; 
	private Array<NPC> npcs;
	private HUD hud;
	
	public Play(GameStateHandler gsh) {
		super(gsh);
//		create the player
		CreatePlayer();
		
//		set the camera bounds to the Tile map size.
		createSurface();
		cam.setBounds(0, tileMapWidth * tileSize, 0, tileMapHeight * tileSize);
		
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
	
//	Load the tile map, surface layers
//	link to API info: https://github.com/libgdx/libgdx/wiki/Tile-maps
	private void createSurface() {
//		try {
			TiledMap tileMap = new TmxMapLoader().load("maps/UDmap.tmx");	
//		}
//		catch(Exception e) {
//			System.out.println("Can't find map file.");
//			Gdx.app.exit();
//		}
		MapProperties prop = tileMap.getProperties();

		int tileMapWidth = prop.get("width", Integer.class);
		int tileMapHeight = prop.get("height", Integer.class);
//		tile height and width are the same
		int tileSize = prop.get("tilewidth", Integer.class);
		
		float unitscale = 1/16f;
		renderer = new OrthogonalTiledMapRenderer(tileMap);

//		Need the following later when we give properties to these objects. i.e buildings and trees are impassable, going to a door starts GameEvent. etc
//		TiledMapTileLayer layer;
//		layer = (TiledMapTileLayer) map.getLayers().get("surface");
//		layer = (TiledMapTileLayer) map.getLayers().get("trees");
//		layer = (TiledMapTileLayer) map.getLayers().get("Buildings");
//		layer = (TiledMapTileLayer) map.getLayers().get("interactable");

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
	      renderer.setView(cam);
	      renderer.render();
//	      sb.begin();
////	  draw temp bg
//	      sb.draw(bg, 0, 0);
//	      sb.end();
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
