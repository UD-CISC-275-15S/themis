package edu.udel.cisc275_15S.themis.game_events;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

import edu.udel.cisc275_15S.themis.Answer;
import edu.udel.cisc275_15S.themis.Data;
import edu.udel.cisc275_15S.themis.Question;
import edu.udel.cisc275_15S.themis.Themis;
import edu.udel.cisc275_15S.themis.handlers.TouchInputHandler;
import edu.udel.cisc275_15S.themis.interactables.Buttons;

//	A Quiz can consists of multiple questions, each having 4 answers
//	TODO: Discuss what triggers quizzes, what events include quizzes, etc...

public class Quiz {
	public static ArrayList<Question> questions;
	public Texture temp = new Texture("gfx/themismenubg.jpg");
	public Texture Qbg = new Texture("gfx/textbox.gif");
	public Texture Abg = new Texture("gfx/textbox.gif");
	public static Data d;
	public int currentQ;
//	Checks if the Quiz has been completed 
	public boolean complete = false;
//	Checks if the current question was answered correctly
	public boolean qVal = false;
	public ArrayList<Answer> answers;
	public Array<Buttons> ans;
	
	public Quiz() throws FileNotFoundException {
		d = new Data();
		questions = d.getQ();
		shuffleAnswers();
		currentQ = 0;
		answers = questions.get(currentQ).getAnswers();
		ans = convertToButtons();
		complete = false;
	}
	public void update() {
		//nextQ();
		updateAns();
	}
	public void render(SpriteBatch sb) {
		
		
		if (!complete) {
			drawBoxes(sb);
			drawQ(sb);
			drawAns(sb);
		}
	}
	public Array<Buttons> convertToButtons() {
		ans = new Array<Buttons>();
		int heightMod = 0;
		for (Answer an : answers) {
			Buttons but = new Buttons(an.toString(), Themis.WIDTH-140, 140 + heightMod);
			ans.add(but);
			heightMod+=30;
		} return ans;
	}
	public void drawAns(SpriteBatch sb) {
		int heightMod = 0;
		for (Buttons but : ans) {
			sb.setColor(1.0f,1.0f,1.0f,0.5f);
			sb.begin();
			sb.draw(Abg, Themis.WIDTH-145, 120 + heightMod, 130, 25);
			sb.setColor(1.0f,1.0f,1.0f,1.0f);
			sb.end();
			but.render(sb);
			heightMod+=30;
		}
	}
	public void drawBoxes(SpriteBatch sb) {
//		sb.begin();
//		sb.draw(temp, 0, 0);
//		sb.end();
		sb.setColor(1.0f,1.0f,1.0f,0.5f);
		sb.begin();
		sb.draw(Qbg, 0, Themis.HEIGHT-Themis.HEIGHT/4+10, Themis.WIDTH, 50);
		sb.setColor(1.0f,1.0f,1.0f,1.0f);
		sb.end();
	}
	public void drawQ(SpriteBatch sb) {
		BitmapFont q = new BitmapFont();
		drawBoxes(sb);
		sb.begin();	
		q.drawWrapped(sb, questions.get(currentQ).getQ(), Themis.WIDTH / 18 , Themis.HEIGHT-Themis.HEIGHT/8, Themis.WIDTH);
		sb.end();
	
	}
	public void dispose() {
		
	}
	public void shuffleAnswers() {
		long seed = System.nanoTime();
		for (Question aq : questions) {
			Collections.shuffle(aq.getAnswers(), new Random(seed));
			System.out.println(aq.getQ());
			for (Answer an : aq.getAnswers()) {
				System.out.println(an.toString());
			}
		}
	}
	public void updateAns() {
		float x = 0;
		float y = 0; 
		for (Buttons but : ans) {
			x = but.getX();
			y = but.getY();
			if (TouchInputHandler.isClicked() && TouchInputHandler.isWithinBounds(x, y, 130 , 25)) {
				System.out.println("You just clicked " + but.toString());
				for (Answer an : answers) {
					if (but.toString() == an.toString()) {
						if (an.getBool()) {
							qVal = true;
							System.out.println("You just clicked the right Answer!!");
						} else System.out.println("Thats...Wrong.");
					}
				}
			}
		}
	}
	public void nextQ() {
		if (qVal && currentQ != questions.size()-1) {
			qVal = false;
			currentQ++;
			shuffleAnswers();
			answers = questions.get(currentQ).getAnswers();
			ans = convertToButtons();
		}
		if (qVal && currentQ == questions.size()-1) {
			complete = true;
		}
	}

	public static void main(String[] args) throws IOException {
		d = new Data();
		questions = d.getQ();
		long seed = System.nanoTime();
		for (Question aq : questions) {
			Collections.shuffle(aq.getAnswers(), new Random(seed));
			System.out.println(aq.getQ());
			for (Answer an : aq.getAnswers()) {
				System.out.println(an.toString());
			}
		}
		
	}
	public boolean getComplete() {
		return complete;
	}
}