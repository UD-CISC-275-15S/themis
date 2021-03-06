package edu.udel.cisc275_15S.themis.interactables;

import edu.udel.cisc275_15S.themis.interactables.Buttons;
import edu.udel.cisc275_15S.themis.interactables.Interactable;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.io.IOException;

import edu.udel.cisc275_15S.themis.Themis;
import edu.udel.cisc275_15S.themis.Data;

public class Objectives extends Buttons implements Interactable{
	
	private Texture incompleteImage;
	private Texture attemptedImage;
	private Texture completedImage;
	private boolean opened;
	private int numObjectives;
	private ArrayList<String> objectiveText;
	private ArrayList<Texture> objectiveCompleteness;
	private BitmapFont text;
	
	
	public Objectives(Texture image, float x, float y){
		super(image,x,y);
		incompleteImage = new Texture(Gdx.files.internal("gfx/obj-notComplete.png"));
		attemptedImage = new Texture(Gdx.files.internal("gfx/obj-attempted.png"));
		completedImage = new Texture(Gdx.files.internal("gfx/obj-completed.png"));
		objectiveText = new ArrayList<String>();
		objectiveCompleteness = new ArrayList<Texture>();
		text = Themis.getBitmapFont();
		try{
			Data.readObjectives(this);
		}
		catch(IOException e){
			e.printStackTrace();
		}
		numObjectives=objectiveText.size();
	}
	
	public void render(SpriteBatch sb) {
		super.render(sb);
		if (opened) {
			sb.begin();
			sb.setColor(1.0f, 1.0f, 1.0f, .5f);
			sb.draw(super.getBGImage(), 50f, 82f,400,230);
			sb.setColor(1.0f, 1.0f, 1.0f, 1.0f);
			for(int i=0;i<numObjectives;i++){
				sb.draw(objectiveCompleteness.get(i),80,95+(((Themis.HEIGHT-10)/numObjectives)*i)*2/3);
				text.draw(sb, objectiveText.get(i), 115,115+((Themis.HEIGHT-10)/numObjectives)*2/3*i);
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
	public void updateObjective(String str){
		for(int i=0;i<objectiveText.size();i++){
			if(objectiveText.get(i).toLowerCase().contains(str.toLowerCase())){
				if(objectiveCompleteness.get(i)==incompleteImage){
					objectiveCompleteness.set(i, attemptedImage);
				}
				else if(objectiveCompleteness.get(i)==attemptedImage){
					objectiveCompleteness.set(i, completedImage);
				}
			}
		}
	}
	
	public void update(float dt) {
        dt = Gdx.graphics.getDeltaTime();
		handleInput(); 
		opened = clicked;
	}

	
	public void setNumObjectives(int n){ numObjectives=n;}
	public void addText(String s){objectiveText.add(s);}
	public void addComplete(Texture t){objectiveCompleteness.add(t);}
	public ArrayList<String> getText(){return objectiveText;}
	public ArrayList<Texture> getTextures(){return objectiveCompleteness;}
	public Texture getComplete(){return completedImage;}
	public Texture getAttempted(){return attemptedImage;}
	public Texture getIncomplete(){return incompleteImage;}

}
