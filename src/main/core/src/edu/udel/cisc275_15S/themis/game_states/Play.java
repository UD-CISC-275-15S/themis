package edu.udel.cisc275_15S.themis.game_states;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.Array;

import edu.udel.cisc275_15S.themis.Data;
import edu.udel.cisc275_15S.themis.Themis;
import edu.udel.cisc275_15S.themis.game_entities.Character;
import edu.udel.cisc275_15S.themis.game_entities.HUD;
import edu.udel.cisc275_15S.themis.game_entities.NPC;
import edu.udel.cisc275_15S.themis.game_entities.Player;
import edu.udel.cisc275_15S.themis.handlers.CharacterInteractionHandler;
import edu.udel.cisc275_15S.themis.handlers.GameStateHandler;
import edu.udel.cisc275_15S.themis.handlers.MainCamera;

public class Play extends GameState {
//	Wasn't able to get it working with a relative file path, so I used an absolute path for testing. 
//	Run the main method to find your path and add Gamedata/PlayerData.txt to it 
//	public static String filepath = "/Users/brandon/Documents/Academics/CISC275/git/themis/src/main/core/Gamedata/PlayerData.txt";
//	public static String filepath = "C:/Users/Chris/Desktop/Poderance_and_Circumstance/CISC275/themis/src/main/core/Gamedata/PlayerData.txt";
	public static Data data = new Data();
	public static String filepath = data.getFilePath();
	public static File PlayerData = new File(filepath);
	private static Player player;
	private int tileMapWidth;
	private int tileMapHeight;
	private int tileSize;
	private TiledMap tileMap;
//	temporary bg until tileMap has been created
	private Texture bg = (new Texture(Gdx.files.internal("temp.jpg")));
	private OrthogonalTiledMapRenderer renderer; 
	private Array<NPC> npcs;
//	Tiles that the player cant pass through
	private HUD hud;
	private MainCamera cam2;
	private CharacterInteractionHandler CIH;
	private static Data d;
	MapObjects objects;
	
	public Play(GameStateHandler gsh) throws FileNotFoundException {
		super(gsh);
	    Data d = new Data();
//		create the player
		CreatePlayer();
//		set the camera bounds to the Tile map size.
		createSurface();
		cam2 = new MainCamera();
		cam2.setToOrtho(false, Themis.WIDTH, Themis.HEIGHT);
		cam2.setBounds(0, tileMapWidth * tileSize, 0, tileMapHeight * tileSize);
		
//		create the NPCs
		CreateNPCs();
//		create the hud, and set it to whatever the player owns. (backpack, resources, information, etc)
		hud = new HUD(player);
		CIH = new CharacterInteractionHandler(this);
		MapLayer collisionObjectLayer = tileMap.getLayers().get("nonpassable");	
		objects = collisionObjectLayer.getObjects();
	}
	
//	Only required if the Player has just started  a new game
	private static void CreatePlayer() throws FileNotFoundException {
//		if implementing choosable sprites, save player sprite selection in textfile and load into player class.
//		Otherwise, load default sprite here:
		
		Texture sprite = new Texture("Sprites/link.png");

		TextureRegion[] PlayerSprite = new TextureRegion[4];
		for (int i = 0; i < 4; i++){
			PlayerSprite[i] = new TextureRegion(sprite, i * 50, 0, 32, 32);
		}
		String name = d.readPlayerName(PlayerData);
		Float x = (d.readPlayerX(PlayerData));
		Float y = (d.readPlayerY(PlayerData));
		String dir = (d.readPlayerDir(PlayerData));
		
		player = new Player(PlayerSprite, x, y, Character.DOWN, "Mark");
		player.setUserBag();
		player.setObjButton();
		player.setUDSIS();
	}
	
//	This is required if NPC's are put in the map on Tile
	private void CreateNPCs() {
//		
	}
//	Load the tile map, surface layers
//	link to API info: https://github.com/libgdx/libgdx/wiki/Tile-maps
	private void createSurface() {
//		When making tiled maps make sure the filepath to the tilesets are relative to the map file. Open the tmx file in a text editor 
//		and check to be sure
//		Not sure why my try/catch creates a nullpointer
//		try {
			tileMap = new TmxMapLoader().load("maps/UDmap.tmx");	
//		}
//		catch(Exception e) {
//			System.out.println("Can't find map file.");
//			Gdx.app.exit();
//		}
		MapProperties prop = tileMap.getProperties();

		 tileMapWidth = prop.get("width", Integer.class);
		 tileMapHeight = prop.get("height", Integer.class);
//		tile height and width are the same
		 tileSize = prop.get("tilewidth", Integer.class);
		
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
		player.update();
//		for (int i = 0; i < NPCs.size(); i++) {
//			NPCs.get(i).update(dt);
//		}
		try {
			d.savePlayerData(filepath, player.getX(), player.getY(),player.getDirString(player.getDir()));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void render() {
	      Gdx.gl.glClearColor(0, 0, 0.2f, 1);
	      Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//	      cam follows the player

		  cam2.setPosition(player.getXpos(), player.getYpos());
		  cam2.update();
	      renderer.setView(cam2);
	      renderer.render();

	      sb.setProjectionMatrix(cam2.combined);
	      CIH.render(sb);
	      player.render(sb);
	      sb.setProjectionMatrix(hudCam.combined);
	      hud.render(sb);
//	      sb.setProjectionMatrix(cam2.combined);
//	      npcs.render();

	}

	@Override
	public void handleInput() {
		// TODO Auto-generated method stub
		CIH.touchHandler();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	public Player getPlayer() { return player;}
	public Array<NPC> getNPCS() {return npcs;}
	public int getTMwidth() { return tileMapWidth; }
	public int getTMheight() { return tileMapHeight; }
	public MapObjects getObjects() { return objects;}
	public static void main(String[] args) throws IOException {
	    String filePath = new File("").getAbsolutePath();
	    System.out.println(filePath);
	    Data d = new Data();
		System.out.println(d.readPlayerName(PlayerData));
		System.out.println(d.readPlayerX(PlayerData));
		System.out.println(d.readPlayerY(PlayerData));
		System.out.println(d.readPlayerDir(PlayerData));
//		
	}
}
