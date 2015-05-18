package edu.udel.cisc275_15S.themis.game_events;

import java.util.ArrayList;
import java.util.List;

public class QuestionData {
	String question;
	int numWrong;
	List<Integer> times;
	boolean completed;
	
	public QuestionData(String question) {
		this.question = question;
		this.numWrong = 0;
		this.times = new ArrayList<Integer>();
		this.completed = false;
	}
	
	public void updateQuestion(boolean comp, int time) {
		if(!this.completed) {
			if(comp){ this.completed = true; }
			else{ this.numWrong++; }
			this.times.add(time);
		}
	}
	
	public String toString(){
		String times = "";
		for(Integer i : this.times) {
			times = times + Integer.toString(this.times.get(i));
		}
		return question + "/" + Integer.toString(numWrong) + "/" + times + "/" + Boolean.toString(completed);
	}
}
