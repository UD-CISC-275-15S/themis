package edu.udel.cisc275_15S.themis.game_states;

import java.io.File;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

import edu.udel.cisc275_15S.themis.Data;
import edu.udel.cisc275_15S.themis.Themis;
import edu.udel.cisc275_15S.themis.game_entities.Character;
import edu.udel.cisc275_15S.themis.game_entities.HUD;
import edu.udel.cisc275_15S.themis.game_entities.NPC;
import edu.udel.cisc275_15S.themis.game_entities.Player;
import edu.udel.cisc275_15S.themis.game_events.Event;
import edu.udel.cisc275_15S.themis.game_events.Quiz;
import edu.udel.cisc275_15S.themis.game_events.RandomEvent;
import edu.udel.cisc275_15S.themis.game_events.Tutorial;
import edu.udel.cisc275_15S.themis.handlers.CharacterInteractionHandler;
import edu.udel.cisc275_15S.themis.handlers.TouchInputHandler;
import edu.udel.cisc275_15S.themis.handlers.GameStateHandler;
import edu.udel.cisc275_15S.themis.handlers.MainCamera;
import edu.udel.cisc275_15S.themis.handlers.TextInputHandler;
import edu.udel.cisc275_15S.themis.interactables.Buttons;

public class Play extends GameState {

	private Quiz q;	
	private boolean quiz = false;
	private boolean newGame = false;
	int i = 0;
	public static String filepath = Data.getFilePath();
	public static File PlayerData = new File(filepath);
	private static Player player;
	
	private int tileMapWidth;
	private int tileMapHeight;
	private int tileSize;
	private TiledMap tileMap;
	private OrthogonalTiledMapRenderer renderer; 
	private static int mapIndex;
	
	private Array<Rectangle> exits;
	private Array<NPC> npcs;
	private HUD hud;
	private CharacterInteractionHandler CIH;
	public MapObjects objects;
	RandomEvent randomEvent;
	private Tutorial Tutorial;
	private boolean opened = false;
	private GameStateHandler gsh;
	private Buttons back;

	public Play(GameStateHandler gsh) throws FileNotFoundException {
		super(gsh);
		this.gsh=gsh;
		
		CreatePlayer();
		createSurface();
		CreateNPCs();
		createExits();
		
		cam = new MainCamera();
		cam.setToOrtho(false, Themis.WIDTH, Themis.HEIGHT);
		cam.setBounds(0, tileMapWidth * tileSize, 0, tileMapHeight * tileSize);
		
		CIH = new CharacterInteractionHandler(this);
		Tutorial = new Tutorial(player, newGame, 0, "tutorial");
		hud = new HUD(player);
		q = new Quiz();
		back = new Buttons("<-- BACK",45,300);
		
		MapLayer collisionObjectLayer = tileMap.getLayers().get("nonpassable");	
		objects = collisionObjectLayer.getObjects();
	}

	private static void CreatePlayer() throws FileNotFoundException {

		Texture sprite = new Texture("Sprites/link.png");

		TextureRegion[] PlayerSprite = new TextureRegion[4];
		for (int i = 0; i < 4; i++){
			PlayerSprite[i] = new TextureRegion(sprite, i * 50, 0, 32, 32);
		}
		String name = Data.readPlayerName(PlayerData, "name");
		Float x = (Data.readPlayer(PlayerData, "x"));
		Float y = (Data.readPlayer(PlayerData, "y"));
		
		float map = (Data.readPlayer(PlayerData, "map"));
		mapIndex = (int) map;
//		
//		Float x = 50f;
//		Float y = 450f;
		String dir = (Data.readPlayerDir(PlayerData));

		player = new Player(PlayerSprite, x, y, Character.DOWN, name);
		player.setUserBag();
		player.setObjButton();
		player.setUDSIS();
		player.setUDMail();
		player.setOnline();
	}

	private int randDir() {
		Random rn=new Random();
		int n = rn.nextInt(4) + 1;
		switch (n) {
		case 1: return Character.DOWN;
		case 2: return Character.UP;
		case 3: return Character.LEFT;
		case 4: return Character.RIGHT;
		default: return Character.DOWN;
		}
	}
	private void CreateNPCs() {
		
		npcs = new Array<NPC>();
		randomEvent=new RandomEvent(null, false, 0, "name", null);
		Texture sprite=new Texture("Sprites/link.png");
		TextureRegion[] NPCSprite = new TextureRegion[4];
		for(int j=0;j<4;j++){
			NPCSprite[j]=new TextureRegion(sprite,j*50,0,32,32);
		}
		ArrayList<Event> al = new ArrayList<Event>();
		al.add(randomEvent);
		
		MapLayer NPC = tileMap.getLayers().get("npc");	
		MapObjects npcobjects;
		npcobjects = NPC.getObjects();
		
		for (RectangleMapObject npc : npcobjects.getByType(RectangleMapObject.class)) {
			float x =  (Float) npc.getProperties().get("x");
			float y =  (Float) npc.getProperties().get("y");
			NPC anpc = new NPC(NPCSprite, x, y, randDir(), "NPC",al);
			npcs.add(anpc);
		}
	}

	//Used to handle populating NPC's at random.
	private void populateNPCs(){
		for(int i=0;i<npcs.size;i++){
			npcs.get(i).render(sb);
		}
	}

	//	Load the tile map, surface layers
	//	link to API info: https://github.com/libgdx/libgdx/wiki/Tile-maps
	private void createSurface() {
		//		When making tiled maps make sure the filepath to the tilesets are relative to the map file. Open the tmx file in a text editor 
		
		String map = "trabant.tmx";
		switch (mapIndex) {
		case 0: map = "trabant.tmx"; 
		System.out.println("Loaded" + map);
		break;
		case 1: map = "worldmap.tmx"; 
		System.out.println("Loaded" + map);
		break; 
		default: map = "trabant.tmx";
		System.out.println("Loaded" + map);
		}
		tileMap = new TmxMapLoader().load("maps/" + map);	

		MapProperties prop = tileMap.getProperties();

		tileMapWidth = prop.get("width", Integer.class);
		tileMapHeight = prop.get("height", Integer.class);
		tileSize = prop.get("tilewidth", Integer.class);
		renderer = new OrthogonalTiledMapRenderer(tileMap);
	}
	
	private void createExits() {
		
		exits = new Array<Rectangle>();
		MapLayer EXITS = tileMap.getLayers().get("exit");	
		MapObjects ex;
		ex = EXITS.getObjects();
		
		for (RectangleMapObject ae : ex.getByType(RectangleMapObject.class)) {
			Rectangle as = ae.getRectangle();
			exits.add(as);
		}
	}
	
	private boolean exited() {

		Rectangle rect = new Rectangle(player.getXpos(), player.getYpos(), 20, 20);

		for (Rectangle exit : exits) {

		    if (Intersector.overlaps(exit, rect)) {
		        return true;
		    } 
		} return false;
	}
	
	public void testQuiz() {
		if (!newGame && i == 0) {
			quiz = true;
			i++;
		}
		if (!newGame && i > 0) {
			quiz = !q.getComplete(); 
		}
	}
	public void update(float dt) {
		handleInput();
		player.update();
		opened = player.getBag().isOpen() || player.getObjButton().isOpen();
		if (quiz) { q.update(); }
//		Comment out this line to hide quiz
//		testQuiz();
		if (!newGame) {
			hud.update(dt);
		}
		try {
			Data.savePlayerData(filepath, TextInputHandler.getPlayerName(),player.getX(), player.getY(),player.getDirString(player.getDir()),mapIndex);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		if (newGame) {
			Tutorial.update();
			newGame = Tutorial.getValid();
		}
		if (exited()) {
			mapIndex = 1;
			try {
				Data.savePlayerData(filepath, TextInputHandler.getPlayerName(),48f, 476f,player.getDirString(player.getDir()),mapIndex);
				gsh.setState(GameStateHandler.PLAY);
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
	}

	public void render() {
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		cam.setPosition(player.getXpos(), player.getYpos());
		cam.update();
		
		renderer.setView(cam);
		renderer.render();

		sb.setProjectionMatrix(cam.combined);
		CIH.render(sb);
		player.render(sb);
		populateNPCs();
		
		sb.setProjectionMatrix(hudCam.combined);
		if (newGame) {
			Tutorial.render(sb, hudCam);
		}
		if (quiz) {
			q.render(sb);
		}
		if(opened){
			player.getBag().render(sb);
			back.render(sb);
			
		}
//		for(int i=0;i<npcs.size;i++){
//			if(((player.getX()>=npcs.get(i).getX()-20)&&(player.getX()<=npcs.get(i).getX()+20))
//					&&
//					((player.getY()-CharacterInteractionHandler.MOVE>=npcs.get(i).getY()-20)&&(player.getY()-CharacterInteractionHandler.MOVE<=npcs.get(i).getY()+20))){
//				q.render(sb);
//			}
//		}
		hud.render(sb);
		sb.setProjectionMatrix(cam.combined);
		//System.out.println(sb.totalRenderCalls);
	}

	@Override
	public void handleInput() {
		if(player.getBag().isDown()){
			player.getBag().setOpened(true);
		}
		if (newGame) {return;} 
		if (opened) {
			if(back.isDown()){
				System.out.println("You pressed back!");
				opened=false;
			}
			return;} 
		if (quiz) {return;}
		else {CIH.touchHandler();}
	}

	@Override
	public void dispose() {
		npcs = null;
		hud = null;
		CIH = null;
		player = null;
		objects = null;
		exits = null;
		tileMap = null;
	
	}
	public Player getPlayer() { return player;}
	public Array<NPC> getNPCS() {return npcs;}
	public int getTMwidth() { return tileMapWidth; }
	public int getTMheight() { return tileMapHeight; }
	public MapObjects getObjects() { return objects;}
	public void setNewGame(boolean n) { newGame = n;}
	public Quiz getQuiz(){return q;};
	public SpriteBatch getSB(){return sb;}
	public Array<Rectangle> getExits() { return exits;}

	public static void main(String[] args) throws IOException {
		String filePath = new File("").getAbsolutePath();
		System.out.println(filePath);
	}
}
