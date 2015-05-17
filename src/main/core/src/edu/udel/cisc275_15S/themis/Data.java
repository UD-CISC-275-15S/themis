package edu.udel.cisc275_15S.themis;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;
import java.io.BufferedReader;

import edu.udel.cisc275_15S.themis.game_entities.Player;
import edu.udel.cisc275_15S.themis.interactables.Backpack;
import edu.udel.cisc275_15S.themis.interactables.Online;
import edu.udel.cisc275_15S.themis.interactables.UDSIS;
import edu.udel.cisc275_15S.themis.interactables.Objectives;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.Gdx;
//	This class is used for loading Data from our GameData folder
//	GameData consists of:
//	In Game Questions
//	UDSIS info
//	BackPack items
//	Player and NPC's coordinates
//	Dialogue

public class Data {
	
	public Array<Question> questions = new Array<Question>();
	public Array<String> rDialogue = new Array<String>();
	public Backpack bag;
	public Player player;
	public UDSIS udsis;
	public Online online;
	
	public void readQ() throws IOException {
		FileHandle infile = Gdx.files.internal("Gamedata/Questions.txt");
		BufferedReader br = new BufferedReader(infile.reader());
		String string = null;
		int i = 0;
	    while ((string = br.readLine())!=null && i < 5) {
	        
	        if (!string.isEmpty()) {
	        	if (string.endsWith("?")) { 
		        	Question q = new Question(new Array<Answer>(), "" );
	        		q.setQuestion(string); 
	        		questions.add(q);
	        		i++;
	        		} 
	        }
	    } br.close();
	}
	
//	In the textfile, always order correct answer directly after the question.

	public void readA() throws IOException {
		
		FileHandle infile = Gdx.files.internal("Gamedata/Questions.txt");
		BufferedReader br = new BufferedReader(infile.reader());
        int i = 0;
        String str = null;
	    while ((str = br.readLine())!=null) {
	        if (questions.get(i).getAnswers().size == 4) {
					i++;
	        }
	        	if (questions.get(i).getAnswers().size == 0 && str.endsWith(".")) { 
		        	questions.get(i).addAnswer(new Answer(str, true));
	        	} else if (str.endsWith(".") && questions.get(i).getAnswers().size < 4) 
	        	{ questions.get(i).addAnswer(new Answer(str, false)); 
	        }
	    }
	    br.close();
	}
	
	public static void readObjectives(Objectives obj) throws IOException{
		FileHandle infile = Gdx.files.internal("Gamedata/Objectives.txt");
		BufferedReader br = new BufferedReader(infile.reader());
		String str = null;
		br.readLine();
		str=br.readLine();
		while(str!=null){
			if(str.equals("0")){
				obj.addComplete(obj.getIncomplete());
			}
			if(str.equals("1")){
				obj.addComplete(obj.getAttempted());
			}
			if(str.equals("2")){
				obj.addComplete(obj.getComplete());
			}
			System.out.println(str);
			str=br.readLine();
			obj.addText(str);
			System.out.println(str);
			str=br.readLine();
		}
		br.close();
	}

	public void readRDialogue() throws IOException {
		FileHandle infile = Gdx.files.internal("Gamedata/rDialogue.txt");
		BufferedReader br = new BufferedReader(infile.reader());
		String string = null;
		
	    while ((string = br.readLine())!=null) {      
	        if (!string.isEmpty()) {
	        	if (string.endsWith(":")) { 
	        		rDialogue.add(string);
	        		} 
	        }
	    } br.close();
	}
	
	public String loadRDialogue() {
		Random rn=new Random();
		int y = rDialogue.size;
		int n = rn.nextInt(y);
		return rDialogue.get(n);
	}
	
	public Array<String> readSDialogue(String name) throws IOException {
		Array<String> Dia = new Array<String>();
		FileHandle infile = Gdx.files.internal("Gamedata/" + name + ".txt");
		BufferedReader br = new BufferedReader(infile.reader());
		String string = null;
		String line;
	    while ((string = br.readLine())!=null) {      
	        if (!string.isEmpty()) {
	        	if (string.endsWith(":")) {
	        		StringBuilder sb = new StringBuilder(string);
	        		sb.deleteCharAt(string.length() -1);
	        		line = sb.toString();
	        		Dia.add(line);
	        		} 
	        }
	    } br.close();
	    return Dia;
	}
	
	
	public static void updateObjectives(Objectives obj) throws FileNotFoundException{
		FileHandle outfile = Gdx.files.local("Gamedata/Objectives.txt");
		outfile.writeString(""+obj.getText().size()+"\n", false);
		for(int i=0;i<obj.getText().size();i++){
			if(obj.getTextures().get(i)==obj.getIncomplete()){
				outfile.writeString("1\n",true);
			}
			if(obj.getTextures().get(i)==obj.getAttempted()){
				outfile.writeString("2\n", true);
			}
			if(obj.getTextures().get(i)==obj.getComplete()){
				outfile.writeString("3\n",true);
			}
			outfile.writeString(obj.getText().get(i)+"\n",true);
		}
	}
	public static float readPlayer(String p) throws IOException {
		FileHandle infile = Gdx.files.internal("Gamedata/PlayerData.txt");
		BufferedReader br = new BufferedReader(infile.reader());
		String str = null;
	    while ((str = br.readLine())!=null) {
	        if (!str.isEmpty()) {
				if (str.endsWith(p + ":")) {
					float f = Float.parseFloat(br.readLine());
					br.close();
					return f;
				}
	        }
	    } 	   br.close(); 
	    return 0;
	}
	
	public static String readPlayerName(String p) throws IOException {
		FileHandle infile = Gdx.files.internal("Gamedata/PlayerData.txt");
		BufferedReader br = new BufferedReader(infile.reader());
		String str = null;
	    while ((str = br.readLine())!=null) {
	        if (!str.isEmpty()) {
	        	if (str.endsWith(p + ":")) { 
	        		String name = br.readLine();
	        		br.close();
	        		return name;
	        		} 
	        } 
	    }
	    br.close();
	    return "";
	}
	public static String readPlayerDir() throws IOException {
		FileHandle infile = Gdx.files.internal("Gamedata/PlayerData.txt");
		BufferedReader br = new BufferedReader(infile.reader());
		String str = null;
	    while ((str = br.readLine())!=null) {
	        if (!str.isEmpty()) {
				if (str.endsWith("dir")) {
					String dir = br.readLine();
					br.close();
					return dir;
	        	}
	        }
	    } br.close();
	    return "";
	}
	
	public static void savePlayerName(String playername, String userid) throws FileNotFoundException { // inits with entrance to trabant
		FileHandle infile = Gdx.files.local("Gamedata/PlayerData.txt");
			infile.writeString("name:"+"\n",false);
			infile.writeString(playername+"\n",true);
			infile.writeString("x:"+"\n",true);
			infile.writeString("226.0"+"\n",true);
			infile.writeString("y:"+"\n",true);
			infile.writeString("120.0"+"\n",true);
			infile.writeString("dir"+"\n",true);
			infile.writeString("UP"+"\n",true);
			infile.writeString("map:"+"\n",true);
			infile.writeString("0"+"\n",true);
	}
	
	public static void savePlayerData(String playername, Float x0, Float y0, String dir, int map) throws FileNotFoundException {
		FileHandle infile = Gdx.files.local("Gamedata/PlayerData.txt");
			String x = Float.toString(x0);
			String y = Float.toString(y0);
			String z = Integer.toString(map);
			infile.writeString("name:"+"\n",false);
			infile.writeString(playername+"\n",true);
			infile.writeString("x:"+"\n",true);
			infile.writeString(x+"\n",true);
			infile.writeString("y:"+"\n",true);
			infile.writeString(y+"\n",true);
			infile.writeString("dir"+"\n",true);
			infile.writeString(dir+"\n",true);
			infile.writeString("map:"+"\n",true);
			infile.writeString(z+"\n",true);
	}
	public Array<Question> getQ() throws IOException {
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
