package edu.udel.cisc275_15S.themis;


import java.io.File;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;

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
	
	public static void readQ() throws FileNotFoundException {
		
		File QuestionFile = new File("Gamedata/Questions.txt");
		Scanner infile = new Scanner(QuestionFile);
		
	    while (infile.hasNextLine()) {
	        String currentLine = infile.nextLine();
	        
	        if (!currentLine.isEmpty()) {
	        	if (currentLine.endsWith("?")) { 
		        	Question q = new Question(new ArrayList<Answer>(), "" );
	        		q.setQuestion(currentLine); 
	        		questions.add(q);
	        		} 
	        }
	    } infile.close();
	}
	
//	In the textfile, always order correct answer directly after the question.

	public static void readA() throws FileNotFoundException {
		
		File QuestionFile = new File("Gamedata/Questions.txt");
		Scanner infile = new Scanner(QuestionFile);
        int i = 0;
	    while (infile.hasNextLine()) {
	        String currentLine = infile.nextLine();
	        if (questions.get(i).getAnswers().size() == 4) {
					i++;
	        }
	        	if (questions.get(i).getAnswers().size() == 0 && currentLine.endsWith(".")) { 
		        	questions.get(i).addAnswer(new Answer(currentLine, true));
	        	} else if (currentLine.endsWith(".") && questions.get(i).getAnswers().size() < 4) 
	        	{ questions.get(i).addAnswer(new Answer(currentLine, false)); 
	        }
	    }
	}
	public static float readPlayer(File data, String p) throws FileNotFoundException {
		Scanner infile = new Scanner(data);
		
	    while (infile.hasNextLine()) {
	        String currentLine = infile.nextLine();
	        if (!currentLine.isEmpty()) {
				if (currentLine.endsWith(p + ":")) {
					return Float.parseFloat(infile.nextLine());
				}
	        }
	    } 	   infile.close(); 
	    return 0;
	}
	
	public static String readPlayerName(File data, String p) throws FileNotFoundException {
		Scanner infile = new Scanner(data);
		
	    while (infile.hasNextLine()) {
	        String currentLine = infile.nextLine();
	        if (!currentLine.isEmpty()) {
	        	if (currentLine.endsWith(p + ":")) { 
	        		return infile.nextLine();
	        		} 
	        } 
	    } infile.close();
	    return "";
	}
	public static String readPlayerDir(File data) throws FileNotFoundException {
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
			PrintWriter writer = new PrintWriter("Gamedata/PlayerData.txt", "UTF-8");
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
	public static String getFilePath() {
	    return PlayerData.getAbsolutePath();
	}
	public static ArrayList<Question> getQ() throws FileNotFoundException {
		readQ();
		readA();
		return questions;
	}
	public static void main(String[] args) throws IOException {
	    File pdata = new File("/Gamedata/PlayerData.txt");
	    System.out.println(PlayerData.getAbsolutePath()+"\n");
//		Testing Reading Questions
		readQ();
		readA();
		for (Question aq : questions) {
			System.out.println(aq.toString());
			for (Answer a: aq.getAnswers()){
				System.out.println(a.toString() + " = " + a.getBool());
			} 
		}
		System.out.println(questions.size());
		System.out.println(questions.get(2).getAnswers().size());

	}
}
