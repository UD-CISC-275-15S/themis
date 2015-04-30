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
import edu.udel.cisc275_15S.themis.interactables.Online;
import edu.udel.cisc275_15S.themis.interactables.UDSIS;
import edu.udel.cisc275_15S.themis.interactables.Objectives;

//	This class is used for loading Data from our GameData folder
//	GameData consists of:
//	In Game Questions
//	UDSIS info
//	BackPack items
//	Player and NPC's coordinates
//	Dialogue

public class Data {
	
	public static File PlayerData = new File("Gamedata/PlayerData.txt");
	public ArrayList<Question> questions = new ArrayList<Question>();
	public Backpack bag;
	public Player player;
	public UDSIS udsis;
	public Online online;
	private Objectives obj;
	
	public void readQ() throws FileNotFoundException {
		
		File QuestionFile = new File("Gamedata/Questions.txt");
		Scanner infile = new Scanner(QuestionFile);
		int i = 0;
	    while (infile.hasNextLine() && i < 5) {
	        String currentLine = infile.nextLine();
	        
	        if (!currentLine.isEmpty()) {
	        	if (currentLine.endsWith("?")) { 
		        	Question q = new Question(new ArrayList<Answer>(), "" );
	        		q.setQuestion(currentLine); 
	        		questions.add(q);
	        		i++;
	        		} 
	        }
	    } infile.close();
	}
	
//	In the textfile, always order correct answer directly after the question.

	public void readA() throws FileNotFoundException {
		
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
	    infile.close();
	}
	
	public static void readObjectives(Objectives obj) throws FileNotFoundException{
		File ObjectiveFile = new File("Gamedata/Objectives.txt");
		Scanner infile = new Scanner(ObjectiveFile);
		obj.setNumObjectives(infile.nextInt());
		while(infile.hasNext()){
			obj.addComplete(new Integer(infile.nextInt()));
			System.out.println(0);
			obj.addText(infile.nextLine());
			System.out.println(1);
		}
		infile.close();
	}
	
	public static void updateObjectives(Objectives obj) throws FileNotFoundException{
		File ObjectiveFile = new File("Gamedata/Objectives.txt");
		Scanner infile = new Scanner(ObjectiveFile);
		infile.nextLine();
		obj.updateObjectives();
		while(infile.hasNext()){
			obj.addComplete(new Integer(infile.nextInt()));
			infile.nextLine();
		}
		infile.close();
	}
	
	public static float readPlayer(File data, String p) throws FileNotFoundException {
		Scanner infile = new Scanner(data);
		
	    while (infile.hasNextLine()) {
	        String currentLine = infile.nextLine();
	        if (!currentLine.isEmpty()) {
				if (currentLine.endsWith(p + ":")) {
					float f = Float.parseFloat(infile.nextLine());
					infile.close();
					return f;
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
	        		String name = infile.nextLine();
	        		infile.close();
	        		return name;
	        		} 
	        } 
	    }
	    infile.close();
	    return "";
	}
	public static String readPlayerDir(File data) throws FileNotFoundException {
		Scanner infile = new Scanner(data);
		
	    while (infile.hasNextLine()) {
	        String currentLine = infile.nextLine();
	        if (!currentLine.isEmpty()) {
				if (currentLine.endsWith("dir")) {
					String dir = infile.nextLine();
					infile.close();
					return dir;
	        	}
	        }
	    } infile.close();
	    return "";
	}
	public static void savePlayerData(String filename, String playername, Float x0, Float y0, String dir, int map) throws FileNotFoundException, UnsupportedEncodingException {
			PrintWriter writer = new PrintWriter("Gamedata/PlayerData.txt", "UTF-8");
			String x = Float.toString(x0);
			String y = Float.toString(y0);
			String z = Integer.toString(map);
			writer.println("name:");
			writer.println(playername);
			writer.println("x:");
			writer.println(x);
			writer.println("y:");
			writer.println(y);
			writer.println("dir");
			writer.println(dir);
			writer.println("map:");
			writer.println(z);
			writer.close();
	}
	public static String getFilePath() {
	    return PlayerData.getAbsolutePath();
	}
	public ArrayList<Question> getQ() throws FileNotFoundException {
		readQ();
		readA();
		return questions;
	}
	public void main(String[] args) throws IOException {
//	    File pdata = new File("/Gamedata/PlayerData.txt");
//	    System.out.println(PlayerData.getAbsolutePath()+"\n");
////		Testing Reading Questions
//		readQ();
//		readA();
//		for (Question aq : questions) {
//			System.out.println(aq.toString());
//			for (Answer a: aq.getAnswers()){
//				System.out.println(a.toString() + " = " + a.getBool());
//			} 
//		}
//		System.out.println(questions.size());
//		System.out.println(questions.get(2).getAnswers().size());
//
	}
}
