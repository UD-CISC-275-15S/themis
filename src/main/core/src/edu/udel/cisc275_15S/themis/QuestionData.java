package edu.udel.cisc275_15S.themis;

import java.io.FileNotFoundException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;

//	This class is used for writing Data from to QuestionData.txt - will edit to be a csv soon
//	QuestionData consists of:
//		the question
//		the amount of times it was answered wrong
//		overall time it took to answer the question
//		if the question has been answered or not

public class QuestionData {
	public static File QuestionData = new File("Gamedata/QuestionData.txt");
	
	public static void createFile() throws FileNotFoundException {
		PrintWriter writer = new PrintWriter(QuestionData);
		writer.println("Question,Number Of Times Wrong,Time Taken,Completed\"\n\"");
	}
	
	public static void processAnswer(String question, boolean correct, int time) throws FileNotFoundException {
		Scanner infile = new Scanner(QuestionData);
		PrintWriter writer = new PrintWriter(QuestionData);
		
		while(infile.hasNextLine()) {
			String currentLine = infile.nextLine();
			int slashIndex = currentLine.indexOf("/", 0);
			int nextSlashIndex = currentLine.indexOf("/", slashIndex + 1);
			int thirdSlashIndex = currentLine.indexOf("/", nextSlashIndex + 1);
			int numWrong = Integer.parseInt(currentLine.substring(slashIndex + 1, nextSlashIndex));
			int questionTime = Integer.parseInt(currentLine.substring(nextSlashIndex + 1, thirdSlashIndex));
			
			
			if(currentLine.isEmpty()) {
				if(correct) { writer.println(question+"/0/"+time+"/yes"); }
				else { writer.println(question+"/1/"+time+"/no"); }
			}
			else if(currentLine.startsWith(question)) {
				if(correct) { writer.println(question+"/"+Integer.toString(numWrong)+"/"+Integer.toString(time+questionTime)+"/yes"); }
				else { writer.println(question+"/"+Integer.toString(numWrong + 1)+"/"+Integer.toString(time+questionTime)+"/no"); }
			}
		}
	}
}