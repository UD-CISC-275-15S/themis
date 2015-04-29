package edu.udel.cisc275_15S.themis;

import java.io.FileNotFoundException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

//	This class is used for writing Data from to QuestionData.txt - will edit to be a csv soon
//	QuestionData consists of:
//		the question
//		the amount of times it was answered wrong
//		overall time it took to answer the question
//		if the question has been answered or not

public class QuestionData {
	public static FileHandle QuestionData = Gdx.files.local("Gamedata/QuestionData.csv");
	
	public QuestionData() {
		QuestionData.writeString("Question,Number Of Times Wrong,Time Taken,Answered\"\n\"", false);
	}
	
	public static void processAnswer(String question, boolean correct, int time) throws FileNotFoundException {
		String text = QuestionData.toString();
		String newtext = "";
		if(text.contains(question)){
			int commaIndex = text.indexOf(",", text.indexOf(question));
			int nextCommaIndex = text.indexOf(",", commaIndex + 1);
			int thirdCommaIndex = text.indexOf(",", nextCommaIndex + 1);
			int endLineIndex = text.indexOf("\"\n\"", thirdCommaIndex);
			newtext.concat(text.substring(0, commaIndex + 1));
			int numWrong = Integer.parseInt(text.substring(commaIndex + 1, nextCommaIndex));
			int questionTime = Integer.parseInt(text.substring(nextCommaIndex + 1, thirdCommaIndex));
			
			if(!correct) {
				newtext.concat(Integer.toString(numWrong++));
				newtext.concat(",");
				newtext.concat(Integer.toString(questionTime + time));
				newtext.concat(text.substring(thirdCommaIndex, text.length() - 1));
				QuestionData.writeString(newtext, false);
			}
			else {
				newtext.concat(text.substring(nextCommaIndex, thirdCommaIndex + 1));
				newtext.concat(Integer.toString(questionTime + time) + ",");
				newtext.concat("true");
				newtext.concat(text.substring(endLineIndex, text.length() - 1));
				QuestionData.writeString(newtext, false);
			}
		}
		
		else {
			if(!correct){
				QuestionData.writeString(question + ",1," + time + ",false\"\n\"", true);
			}
			else {
				QuestionData.writeString(question + ",0," + time + ",true\"\n\"", true);
			}
		}
	}
}