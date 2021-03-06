package edu.udel.cisc275_15S.themis.game_events;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
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

public class Quiz extends Event {
//	Button Width & Height
	public final int WIDTH = 330;
	public final int HEIGHT = 25;
	public Array<Question> questions;
	public Array<Answer> answers;
	public Array<Buttons> ans;
	public Texture temp = new Texture("gfx/themismenubg.jpg");
	public Texture Qbg = new Texture("gfx/dialog.png");
	public Texture Abg = new Texture("gfx/dialog.png");
	public int currentQ;
	public boolean complete = false;//Checks if the Quiz has been completed 
	public boolean qVal = false;//Checks if the current question was answered correctly
	static Data d;
	BitmapFont q;
	public Sound right = Gdx.audio.newSound(Gdx.files.internal("Audio/Bright.mp3"));
	public Sound wrong = Gdx.audio.newSound(Gdx.files.internal("Audio/WallHit.mp3"));
	public static Map<Integer, QuestionData> qdata = new HashMap<Integer, QuestionData>();
	
	public Quiz(Data d) throws IOException {
		Quiz.d = d;
		d.readQ();
		d.readA();
		questions = d.randQ();
		shuffleAnswers();
		currentQ = 0;
		answers = questions.get(currentQ).getAnswers();
		ans = convertToButtons();
		complete = false;
		System.out.println("This quiz has: " + questions.size + " questions total.");
		for(int i = 0; i < questions.size; i++) {
			qdata.put(i, new QuestionData(questions.get(i).getQ()));
		}
		q=Themis.getBitmapFont();
	}
	public void update() {
		nextQ();
		try {
			updateAns();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void render(SpriteBatch sb) {
		
		if (!complete) {
			drawBoxes(sb);
			drawQ(sb);
			drawAns(sb);
		}
		if (complete) {
			temp.dispose();
			Qbg.dispose();
			Abg.dispose();
		}
	}
	public Array<Buttons> convertToButtons() {
		ans = new Array<Buttons>();
		int heightMod = 0;
		for (Answer an : answers) {
			Buttons but = new Buttons(an.toString(), 110, 140 + heightMod);
			ans.add(but);
			heightMod+=30;
		} return ans;
	}
	public void drawAns(SpriteBatch sb) {
		int heightMod = 0;
		for (Buttons but : ans) {
			sb.setColor(1.0f,1.0f,1.0f,0.5f);
			sb.begin();
			sb.draw(Abg, 100, 120 + heightMod, WIDTH, HEIGHT);
			sb.setColor(1.0f,1.0f,1.0f,1.0f);
			sb.end();
			but.render(sb);
			heightMod+=30;
		}
	}
	public void drawBoxes(SpriteBatch sb) {

		sb.setColor(1.0f,1.0f,1.0f,0.5f);
		sb.begin();
		sb.draw(Qbg, 0, Themis.HEIGHT-Themis.HEIGHT/4+10, Themis.WIDTH, 50);
		sb.setColor(1.0f,1.0f,1.0f,1.0f);
		sb.end();
	}
	public void drawQ(SpriteBatch sb) {
		drawBoxes(sb);
		sb.begin();	
		q.drawWrapped(sb, questions.get(currentQ).getQ(), 25, 290, Themis.WIDTH-50);
		sb.end();
	
	}
	public void shuffleAnswers() {
		for (Question aq : questions) {
			aq.getAnswers().shuffle();
//			System.out.println(aq.getQ());
//			for (Answer an : aq.getAnswers()) {
//				System.out.println(an.toString());
//			}
		}
	}
	public void updateAns() throws IOException {
		float x = 0;
		float y = 0; 
		for (Buttons but : ans) {
			x = but.getX();
			y = but.getY();
			if (Gdx.input.justTouched() && TouchInputHandler.isWithinBounds(x, y, WIDTH , HEIGHT)) {
				System.out.println("You just clicked " + but.toString());
				for (Answer an : answers) {
					if (but.toString() == an.toString()) {
						if (an.getBool()) {
							qVal = true;
							System.out.println("You just clicked the right Answer!!");
							right.play();
							qdata.get(currentQ).updateQuestion(true,0);
							processAnswer(true, 0);
						} else{
							System.out.println("Thats...Wrong.");
							wrong.play();
							qdata.get(currentQ).updateQuestion(false, 0);
							processAnswer(false, 0);
						}
					}
				}
			}
		}
	}
	public void nextQ() {
		if (qVal && currentQ != questions.size-1) {
			qVal = false;
			currentQ++;
			shuffleAnswers(); 
			int remainingQ = questions.size - currentQ;
			System.out.println("Remaining Questions: " + remainingQ);
			answers = questions.get(currentQ).getAnswers();
			ans = convertToButtons();
		}
		if (qVal && currentQ == questions.size-1) {
			complete = true;
			temp.dispose();
			Qbg.dispose();
			Abg.dispose();
		}
	}

	public static void main(String[] args) throws IOException {
//		questions = Data.getQ();
//		long seed = System.nanoTime();
//		for (Question aq : questions) {
//			Collections.shuffle(aq.getAnswers(), new Random(seed));
//			System.out.println(aq.getQ());
//			for (Answer an : aq.getAnswers()) {
//				System.out.println(an.toString());
//			}
//		}	
	}
	public boolean getcomplete() {
		return complete;
	}
	
	public static void processAnswer(boolean correct, int time) throws IOException {
		FileHandle outfile = Gdx.files.local("Gamedata/QuestionData.txt");
		outfile.writeString("",false);
		for(Map.Entry<Integer, QuestionData> entry : qdata.entrySet()) {
			outfile.writeString(entry.toString(), true);
		}
	}
}
