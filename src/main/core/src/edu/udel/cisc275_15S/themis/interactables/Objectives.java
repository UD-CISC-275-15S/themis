package edu.udel.cisc275_15S.themis.interactables;

import edu.udel.cisc275_15S.themis.interactables.Buttons;
import edu.udel.cisc275_15S.themis.interactables.Interactable;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;

import edu.udel.cisc275_15S.themis.Themis;
import edu.udel.cisc275_15S.themis.handlers.TouchInputHandler;

public class Objectives extends Buttons implements Interactable{
	
	private Texture incompleteImage;
	private Texture attemptedImage;
	private Texture completedImage;
	private ArrayList<String> objectiveText;
	private ArrayList<Integer> objectiveCompleteness;
	private boolean opened=false;
	private Scanner in;
	private int numObjectives;
	
	
	
	public Objectives(Texture image, float x, float y){
		super(image,x,y);
		incompleteImage = new Texture(Gdx.files.internal("gfx/incomplete.gif"));
		attemptedImage = new Texture(Gdx.files.internal("gfx/attempted.gif"));
		completedImage = new Texture(Gdx.files.internal("gfx/completed.gif"));
		objectiveText = new ArrayList<String>();
		objectiveCompleteness = new ArrayList<Integer>();
		readObjectives();
	}
	
	public void render(SpriteBatch sb) {
		super.render(sb);
		if (opened) {
			sb.begin();
			sb.setColor(1.0f, 1.0f, 1.0f, .5f);
			sb.draw(super.getBGImage(), getX(), getY() + 32f);
			sb.setColor(1.0f, 1.0f, 1.0f, 1.0f);
			BitmapFont text = new BitmapFont();
			for(int i=0;i<numObjectives;i++){
				if(objectiveCompleteness.get(i)==0){
					sb.draw(incompleteImage,40,50+((Themis.HEIGHT-50)/numObjectives)*i);
				}
				if(objectiveCompleteness.get(i)==1){
					sb.draw(attemptedImage,40,50+((Themis.HEIGHT-50)/numObjectives)*i);
				}
				if(objectiveCompleteness.get(i)==2){
					sb.draw(attemptedImage,40,50+((Themis.HEIGHT-50)/numObjectives)*i);
				}
				text.draw(sb, objectiveText.get(i), 65,50+((Themis.HEIGHT-50)/numObjectives)*i);
			}
			sb.end();
		}
	}
	
	public boolean isOpen() {
		return opened;
	}
	
	public void setOpened(boolean open) {
		this.opened = open;
	}
	
	public void update(float dt) {
        dt = Gdx.graphics.getDeltaTime();
        if (dt < .016) {
		handleInput(); }
		opened = clicked;
		
	}
	
	public void readObjectives(){
		try{
			in = new Scanner(Gdx.files.internal("Gamedata/Objectives.txt").file());
			numObjectives =Integer.parseInt(in.nextLine());
			while(in.hasNext()){
				objectiveCompleteness.add(Integer.parseInt(in.nextLine()));
				objectiveText.add(in.nextLine());
			}
			in.close();
		}
		catch(FileNotFoundException e){
			System.out.println("File not found");
			e.printStackTrace();
		}
	}
	public void updateObjectives(){
		try{
			objectiveCompleteness = new ArrayList<Integer>();
			in = new Scanner(Gdx.files.internal("Gamedata/Objectives.txt").file());
			in.nextLine();
			while(in.hasNext()){
				objectiveCompleteness.add(Integer.parseInt(in.nextLine()));
				in.nextLine();
			}
		}
		catch(FileNotFoundException e){
			System.out.println("File not found");
			e.printStackTrace();
		}
	}

}