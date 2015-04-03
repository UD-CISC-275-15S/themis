package edu.udel.cisc275_15S.themis;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

import edu.udel.cisc275_15S.themis.game_entities.Player;
import edu.udel.cisc275_15S.themis.interactables.Backpack;
import edu.udel.cisc275_15S.themis.interactables.UDSIS;

//	This class is used for loading Data from our GameData folder
//	GameData consists of:
//	In Game Questions
//	UDSIS info
//	BackPack items
//	Player and NPC's coordinates
//	Dialogue
public class Data {
	public static File PlayerData = new File("Gamedata/PlayerData.txt");
	public static ArrayList<Question> questions = new ArrayList<Question>();
	public Backpack bag;
	public static Player player;
	public UDSIS udsis;
	public ArrayList<Question> getQ() { return questions;}

	public static void readQ() throws FileNotFoundException {
		
		File QuestionFile = new File("Gamedata/Questions.txt");
		Scanner infile = new Scanner(QuestionFile);
		
	    while (infile.hasNextLine()) {
	        String currentLine = infile.nextLine();
	        
	        if (!currentLine.isEmpty()) {
	        	Question q = new Question(new Array<Answer>(), "" );
//					creating null questions because this is called every line, needs optimization
	        	if (currentLine.endsWith("?")) { 
//	        		In the textfile, always order correct answer directly after the question.
//	        		They can be rendered in a random order later, this just makes organization easier
	        		q.setQuestion(currentLine); 
	        		q.addAnswer(new Answer(infile.nextLine(), true));
	        		} 
				if (currentLine.endsWith(".")) { q.addAnswer(new Answer(currentLine, false));
	        	}
		questions.add(q);
	        }
	    } infile.close();
	}
	
	public static float readPlayerX(File data) throws FileNotFoundException {
//		File PlayerData = new File("Gamedata/PlayerData.txt");
		Scanner infile = new Scanner(data);
		
	    while (infile.hasNextLine()) {
	        String currentLine = infile.nextLine();
	        if (!currentLine.isEmpty()) {
				if (currentLine.endsWith("x:")) {
					return Float.parseFloat(infile.nextLine());
				}
	        }
	    } 	   infile.close(); 
	    return 0;
	}
	public static float readPlayerY(File data) throws FileNotFoundException {
//		File PlayerData = new File("Gamedata/PlayerData.txt");
		Scanner infile = new Scanner(data);
		
	    while (infile.hasNextLine()) {
	        String currentLine = infile.nextLine();
	        if (!currentLine.isEmpty()) {
				if (currentLine.endsWith("y:")) {
					return Float.parseFloat(infile.nextLine());
	        	}
	        }
	    }		infile.close();
	    return 0; 
	}
	public static String readPlayerName(File data) throws FileNotFoundException {
//		File PlayerData = new File("Gamedata/PlayerData.txt");
		Scanner infile = new Scanner(data);
		
	    while (infile.hasNextLine()) {
	        String currentLine = infile.nextLine();
	        if (!currentLine.isEmpty()) {
	        	if (currentLine.endsWith("name:")) { 
	        		return infile.nextLine();
	        		} 
	        } 
	    } infile.close();
	    return "";
	}
	public static String readPlayerDir(File data) throws FileNotFoundException {
//		File PlayerData = new File("Gamedata/PlayerData.txt");
		Scanner infile = new Scanner(data);
		
	    while (infile.hasNextLine()) {
	        String currentLine = infile.nextLine();
	        if (!currentLine.isEmpty()) {
				if (currentLine.endsWith("dir")) {
					return infile.nextLine();
	        	}
	        }
	    } infile.close();
	    return "";
	}
	public static void savePlayerData(String filename, Float x0, Float y0, String dir) throws FileNotFoundException, UnsupportedEncodingException {
			PrintWriter writer = new PrintWriter(filename, "UTF-8");
			String x = Float.toString(x0);
			String y = Float.toString(y0);
			writer.println("name:");
			writer.println("timmy");
			writer.println("x:");
			writer.println(x);
			writer.println("y:");
			writer.println(y);
			writer.println("dir");
			writer.println(dir);
			writer.close();
	}
//	Using a main method to test the methods, later take it out and remove static from all the methods and variables
	public static void main(String[] args) throws IOException {
	    String filePath = new File("").getAbsolutePath();
	    System.out.println(filePath+"\n");
//		Testing Reading Questions
		readQ();
		for (Question aq : questions) {
			System.out.println(aq.toString());
			for (Answer a: aq.getAnswers()){
				System.out.println(a.toString() + " = " + a.getBool());
			} 
		}
		savePlayerData("/home/mark/git/themis/src/main/core/Gamedata/PlayerData.txt", 500f, 400f, "DOWN");
		System.out.println(readPlayerName(PlayerData));
		System.out.println(readPlayerX(PlayerData));
		System.out.println(readPlayerY(PlayerData));
		System.out.println(readPlayerDir(PlayerData));
	}
}
