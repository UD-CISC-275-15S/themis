package edu.udel.cisc275_15S.themis.game_states;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
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
import edu.udel.cisc275_15S.themis.interactables.Buttons;

public class Play extends GameState {

	private boolean newGame = false;
	int i = 0;
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
	private boolean backpackOpened = false;
	private boolean UDSISOpened = false;
	private boolean objectivesOpened = false;
	private GameStateHandler gsh;
	
	private Music music = Gdx.audio.newMusic(Gdx.files.internal("Audio/Annoying.mp3"));
	private int musicMap = 99;	// compares to mapIndex to see if music needs to change

	public Play(GameStateHandler gsh) throws IOException {
		super(gsh);
		this.gsh=gsh;
		
		CreatePlayer();
		LoadMap();
		CreateNPCs();
		
		cam = new MainCamera();
		cam.setToOrtho(false, Themis.WIDTH, Themis.HEIGHT);
		cam.setBounds(0, tileMapWidth * tileSize, 0, tileMapHeight * tileSize);
		
		CIH = new CharacterInteractionHandler(this);
		Tutorial = new Tutorial(player, newGame, 0, "tutorial");
		hud = new HUD(player);

		
		for (int i = 0; i < exits.size; i++) {
			System.out.println("Loaded: ");
		}
		
	}

	private static void CreatePlayer() throws IOException {

		Texture sprite = new Texture("Sprites/link.png");

		TextureRegion[] PlayerSprite = new TextureRegion[4];
		for (int i = 0; i < 4; i++){
			PlayerSprite[i] = new TextureRegion(sprite, i * 50, 0, 32, 32);
		}
		String name = Data.readPlayerName("name");
		Float x = (Data.readPlayer("x"));
		Float y = (Data.readPlayer("y"));
		
		float map = (Data.readPlayer("map"));
		mapIndex = (int) map;
//		
//		Float x2 = 50f;
//		Float y2 = 425f;
		String dir = (Data.readPlayerDir());

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
	private int randNPC() {
		Random rn=new Random();
		int n = rn.nextInt(6) + 1;
		return n;
	}
	private void CreateNPCs() {

		npcs = new Array<NPC>();

		MapLayer NPC = tileMap.getLayers().get("npc");	
		MapObjects npcobjects;
		npcobjects = NPC.getObjects();
		
		String questNPC;
		String questSprite;
		String sideQ;
		
		switch (mapIndex) {
		case 0: questNPC = "guide";
				questSprite = "1";
				break;
		case 1: questNPC = "guide";
				questSprite = "2";
				break;
		case 2: questNPC = "advisor";
				questSprite = "advisor";
				break;
		case 3: questNPC = "doctor";
				questSprite = "doctor";
				break;
		default: questNPC = "guide";
				questSprite = "1";
				break;
		}
		MapLayer Quest = tileMap.getLayers().get(questNPC);
		MapObjects mainNPCs;
		mainNPCs = Quest.getObjects();

		for (RectangleMapObject npc : npcobjects.getByType(RectangleMapObject.class)) {
			
			float x =  (Float) npc.getProperties().get("x");
			float y =  (Float) npc.getProperties().get("y");
			Texture sprite = new Texture("Sprites/npcs/" + randNPC() + ".png");
			TextureRegion[] NPCSprite = new TextureRegion[4];		
			for(int j=0;j<4;j++){
				NPCSprite[j]=new TextureRegion(sprite,j*52,0,32,32);
			}
			ArrayList<Event> al = new ArrayList<Event>();
			
			try {
				randomEvent = new RandomEvent(player, false, "name");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
//			try {
//				Data d = new Data();
//				Quiz Generic = new Quiz(d);
//				al.add(Generic);
//
//			} catch (IOException e) {
//				System.out.print("Quiz cannot be loaded.");
//				e.printStackTrace();
//			}
			al.add(randomEvent);
			NPC anpc = new NPC(NPCSprite, x, y, randDir(), "NPC",al);
			npcs.add(anpc);
//			for (int i = 0; i < anpc.getEvents().size();i++){
//			if (anpc.getEvents().get(i) != null) {System.out.println("Event at index:" + i);}
//			}
		}
		for (RectangleMapObject quest : mainNPCs.getByType(RectangleMapObject.class)) {
			
			float x =  (Float) quest.getProperties().get("x");
			float y =  (Float) quest.getProperties().get("y");
			Texture sprite = new Texture("Sprites/npcs/" + questSprite + ".png");
			TextureRegion[] NPCSprite = new TextureRegion[4];		
			for(int j=0;j<4;j++){
				NPCSprite[j]=new TextureRegion(sprite,j*50,0,32,32);
			}
			ArrayList<Event> al = new ArrayList<Event>();

			al.add(randomEvent);
			NPC anpc = new NPC(NPCSprite, x, y, randDir(), "NPC",al);
			npcs.add(anpc);
//			for (int i = 0; i < anpc.getEvents().size();i++){
//			if (anpc.getEvents().get(i) != null) {System.out.println("Event at index:" + i);}
//			}
		}
	}

	//Used to handle populating NPC's at random.
	private void populateNPCs(){
		for(int i=0;i<npcs.size;i++){
			npcs.get(i).render(sb);
		}
	}
	
	public void updateMusic(){
		if (musicMap != mapIndex){
			switch (mapIndex) {
			case 0:
				musicMap = 0;
				music = Gdx.audio.newMusic(Gdx.files.internal("Audio/Glass.mp3"));
				music.play();
				break;
			case 1:
				musicMap=1;
				music = Gdx.audio.newMusic(Gdx.files.internal("Audio/Annoying.mp3"));
				music.play();
				break;
			case 2:
				musicMap= 2;
				music = Gdx.audio.newMusic(Gdx.files.internal("Audio/Happy.mp3"));
				music.play();
				break;
			default:
				musicMap = mapIndex;
				music = Gdx.audio.newMusic(Gdx.files.internal("Audio/Annoying.mp3"));
				music.play();
				
			}
		}
	}

	//	Load the tile map, surface layers
	//	link to API info: https://github.com/libgdx/libgdx/wiki/Tile-maps
	private void LoadMap() {
	//	When making tiled maps make sure the filepath to the tilesets are relative to the map file. Open the tmx file in a text editor 
		
		String map = "trabant.tmx";
		switch (mapIndex) {
		case 0: map = "trabant.tmx"; 
		System.out.println("Loaded " + map);
		break;
		case 1: map = "World Map.tmx"; 
		System.out.println("Loaded " + map);
		break; 
		case 2: map = "smith.tmx"; 
		System.out.println("Loaded " + map);
		break; 
		case 3: map = "health.tmx"; 
		System.out.println("Loaded " + map);
		break; 
		default: map = "trabant.tmx";
		System.out.println("Loaded " + map);
		}
		tileMap = new TmxMapLoader().load("maps/" + map);	

		MapProperties prop = tileMap.getProperties();

		tileMapWidth = prop.get("width", Integer.class);
		tileMapHeight = prop.get("height", Integer.class);
		tileSize = prop.get("tilewidth", Integer.class);
		renderer = new OrthogonalTiledMapRenderer(tileMap);
		
		MapLayer collisionObjectLayer = tileMap.getLayers().get("impassable");	
		objects = collisionObjectLayer.getObjects();
		
		if (map == "World Map.tmx") {
			createEntrances();
		} else createExits();
	}
	private void createEntrances() {
		
		exits = new Array<Rectangle>();
		MapLayer entrance = tileMap.getLayers().get("trabant");
		MapObjects entr;
		entr = entrance.getObjects();
		
		for (RectangleMapObject ae : entr.getByType(RectangleMapObject.class)) {
			Rectangle as = ae.getRectangle();
			exits.add(as);
		}
		entrance = tileMap.getLayers().get("smith");
		entr = entrance.getObjects();
		for (RectangleMapObject ae : entr.getByType(RectangleMapObject.class)) {
			Rectangle as = ae.getRectangle();
			exits.add(as);
		}
		entrance = tileMap.getLayers().get("health");
		entr = entrance.getObjects();
		for (RectangleMapObject ae : entr.getByType(RectangleMapObject.class)) {
			Rectangle as = ae.getRectangle();
			exits.add(as);
		}
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
	
	private void exited() {
		
		boolean exited = false;
		Rectangle rect = new Rectangle(player.getXpos(), player.getYpos(), 20, 20);
		Rectangle exit = new Rectangle();

		for (Rectangle temp : exits) {
		    if (Intersector.overlaps(temp, rect)) {
		        exited = true;
		        exit = temp;
		    } 
		}
		int map = 0;
		float x = 0f;
		float y = 0f;
		
		if (exited) {
			music.dispose();
		if (mapIndex == 0) {
			map = 1;
			x = 48f;
			y = 520f;
		}
		if (mapIndex == 2) {
			map = 1;
			x = 48f;
			y = 318f;
		}
		if (mapIndex == 3) {
			map = 1;
			x = 342f;
			y = 124f;
			
		} else if (mapIndex == 1) {
			for (int i = 0; i < exits.size; i++) {
				if (exits.get(i) == exit) {
					map = i;
					if (i == 0) {
						x = 226f;
						y = 120f;
					}
					if (i == 2) {
						x = 300f;
						y = 150f;
					}
					if (i == 3) {
						x = 270f;
						y = 110f;
					}
					}
				}
			}
		
				try {
			Data.savePlayerData(player.getName() ,x , y,player.getDirString(player.getDir()),map); // TODO get player name from textfield
			gsh.setState(GameStateHandler.PLAY);
			
		} catch (IOException e) {
			e.printStackTrace();
		}}
	}

	public void update(float dt) {
		handleInput();
//		player.update();

		backpackOpened = player.getBag().isOpen();
		objectivesOpened = player.getObjButton().isOpen();
//		Comment out this line to hide quiz
//		testQuiz();
		if (!newGame) {
			hud.update(dt);
		}
//		try {
//			Data.savePlayerData( player.getName() ,player.getX(), player.getY(),player.getDirString(player.getDir()),mapIndex); // TODO get name from textfield
//		} catch (IOException e) {
//			e.printStackTrace();
//		}


		if (newGame) {
			Tutorial.update();
			newGame = Tutorial.getcomplete();
		}
		exited();
		loadWeb();

	}

	public void render() {
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		cam.setPosition(player.getXpos(), player.getYpos());
		cam.update();
		
		updateMusic();
		
		renderer.setView(cam);
		renderer.render();

		sb.setProjectionMatrix(cam.combined);
		CIH.render(sb);
		player.render(sb);
		populateNPCs();
		int n = 0;
		
		sb.setProjectionMatrix(hudCam.combined);
		if (newGame) {
			Tutorial.render(sb, hudCam);
		}

		if(backpackOpened){
			player.getBag().render(sb);
		}
		if(objectivesOpened&&n==0){
			try{
				Data.updateObjectives(player.getObjButton());
			}
			catch(FileNotFoundException e){
				e.printStackTrace();
			}
			n++;
			player.getObjButton().render(sb);
		}
		if(objectivesOpened&&n==1){
			if(player.getObjButton().isDown()){
				n=0;
			}
		}
		CIH.update();
		hud.render(sb);
		sb.setProjectionMatrix(cam.combined);
//		System.out.println(sb.totalRenderCalls);
	}

	public void loadWeb(){
		if (player.getOnline().isDown()){
			try {
				gsh.setState(GameStateHandler.WEB);
				System.out.println("Web loaded");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	@Override
	public void handleInput() {
		if(player.getBag().isDown()){
			player.getBag().setOpened(true);
		}
		if(player.getObjButton().isDown()){
			player.getObjButton().setOpened(true);
		}
		if (newGame) {return;}
		else if (backpackOpened) {return;}
		else if(objectivesOpened){return;}
		CIH.touchHandler();
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
		cam = null;
	
	}
	public Player getPlayer() { return player;}
	public Array<NPC> getNPCS() {return npcs;}
	public int getTMwidth() { return tileMapWidth; }
	public int getTMheight() { return tileMapHeight; }
	public MapObjects getObjects() { return objects;}
	public void setNewGame(boolean n) { newGame = n;}
	public SpriteBatch getSB(){return sb;}
	public Array<Rectangle> getExits() { return exits;}
	public boolean getNewGame(){ return newGame;}

	public static void main(String[] args) throws IOException {
		String filePath = new File("").getAbsolutePath();
		System.out.println(filePath);
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}
}
